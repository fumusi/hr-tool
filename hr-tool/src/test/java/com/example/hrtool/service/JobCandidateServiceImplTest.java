package com.example.hrtool.service;

import com.example.hrtool.domain.JobCandidate;
import com.example.hrtool.domain.Skill;
import com.example.hrtool.repository.JobCandidateRepository;
import com.example.hrtool.service.impl.JobCandidateServiceImpl;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class JobCandidateServiceImplTest {

	@Mock
	private JobCandidateRepository candidateRepository;

	@Mock
	private SkillService skillService;

	@InjectMocks
	private JobCandidateServiceImpl service;

	@Test
	void addCandidateStoresSkills() {
		when(skillService.getOrCreate("Java")).thenReturn(new Skill("Java"));
		when(candidateRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

		JobCandidate candidate = new JobCandidate("Jane Doe", LocalDate.of(1990, 1, 1), "123", "jane@example.com");
		JobCandidate saved = service.addCandidate(candidate, List.of("Java"));

		assertEquals(1, saved.getSkills().size());
		verify(candidateRepository).save(any(JobCandidate.class));
	}

	@Test
	void removeSkillDeletesMatchingSkill() {
		JobCandidate candidate = new JobCandidate("Jane Doe", LocalDate.of(1990, 1, 1), "123", "jane@example.com");
		candidate.addSkill(new Skill("Java"));
		when(candidateRepository.findById(1L)).thenReturn(Optional.of(candidate));
		when(candidateRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

		JobCandidate saved = service.removeSkill(1L, "Java");

		assertTrue(saved.getSkills().isEmpty());
	}

	@Test
	void searchByNameDelegatesToRepository() {
		when(candidateRepository.findByFullNameContainingIgnoreCase("Jane")).thenReturn(List.of());

		service.searchByName("Jane");

		verify(candidateRepository).findByFullNameContainingIgnoreCase("Jane");
	}
}

