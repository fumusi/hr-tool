package com.example.hrtool.service.impl;

import com.example.hrtool.domain.JobCandidate;
import com.example.hrtool.domain.Skill;
import com.example.hrtool.repository.JobCandidateRepository;
import com.example.hrtool.service.JobCandidateService;
import com.example.hrtool.service.SkillService;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JobCandidateServiceImpl implements JobCandidateService {

	private final JobCandidateRepository repo;
	private final SkillService skillSvc;

	public JobCandidateServiceImpl(JobCandidateRepository repo, SkillService skillSvc) {
		this.repo = repo;
		this.skillSvc = skillSvc;
	}

	@Override
	public JobCandidate addCandidate(JobCandidate c, List<String> skillNames) {
		c.getSkills().clear();
		assignSkills(c, skillNames);
		return repo.save(c);
	}

	@Override
	public JobCandidate updateCandidate(Long id, JobCandidate c, List<String> skillNames) {
		JobCandidate existing = getCandidate(id);
		existing.setFullName(c.getFullName());
		existing.setDateOfBirth(c.getDateOfBirth());
		existing.setContactNumber(c.getContactNumber());
		existing.setEmail(c.getEmail());
		existing.getSkills().clear();
		assignSkills(existing, skillNames);
		return repo.save(existing);
	}

	@Override
	public JobCandidate addSkills(Long id, List<String> skillNames) {
		JobCandidate c = getCandidate(id);
		assignSkills(c, skillNames);
		return repo.save(c);
	}

	@Override
	public JobCandidate removeSkill(Long id, String skillName) {
		JobCandidate c = getCandidate(id);
		String name = skillName.trim();
		c.getSkills().removeIf(s -> s.getName().equalsIgnoreCase(name));
		return repo.save(c);
	}

	@Override
	public void removeCandidate(Long id) {
		if (!repo.existsById(id)) {
			throw new EntityNotFoundException("Not found: " + id);
		}
		repo.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public JobCandidate getCandidate(Long id) {
		return repo.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Not found: " + id));
	}

	@Override
	@Transactional(readOnly = true)
	public List<JobCandidate> searchByName(String name) {
		return repo.findByFullNameContainingIgnoreCase(name.trim());
	}

	@Override
	@Transactional(readOnly = true)
	public List<JobCandidate> searchBySkills(List<String> skillNames) {
		var normalized = skillNames.stream()
				.map(String::trim)
				.toList();
		return repo.findDistinctBySkills_NameIn(normalized);
	}

	private void assignSkills(JobCandidate c, List<String> skillNames) {
		for (String name : skillNames) {
			Skill skill = skillSvc.getOrCreate(name);
			c.addSkill(skill);
		}
	}
}
