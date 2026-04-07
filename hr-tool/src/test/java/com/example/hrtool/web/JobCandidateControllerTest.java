package com.example.hrtool.web;

import com.example.hrtool.domain.JobCandidate;
import com.example.hrtool.domain.Skill;
import com.example.hrtool.service.JobCandidateService;
import java.time.LocalDate;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(JobCandidateController.class)
class JobCandidateControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private JobCandidateService candidateService;

	@Test
	void addCandidateReturnsCreatedCandidate() throws Exception {
		JobCandidate candidate = new JobCandidate("Jane Doe", LocalDate.of(1990, 1, 1), "123", "jane@example.com");
		candidate.addSkill(new Skill("Java"));
		when(candidateService.addCandidate(any(), any())).thenReturn(candidate);

		mockMvc.perform(post("/api/candidates")
				.contentType(MediaType.APPLICATION_JSON)
				.content("""
					{
					  "fullName": "Jane Doe",
					  "dateOfBirth": "1990-01-01",
					  "contactNumber": "123",
					  "email": "jane@example.com",
					  "skills": ["Java"]
					}
					"""))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.fullName").value("Jane Doe"))
				.andExpect(jsonPath("$.skills[0]").value("Java"));
	}
}

