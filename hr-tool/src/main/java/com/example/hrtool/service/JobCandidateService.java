package com.example.hrtool.service;

import com.example.hrtool.domain.JobCandidate;
import java.util.List;

public interface JobCandidateService {
	JobCandidate addCandidate(JobCandidate candidate, List<String> skillNames);

	JobCandidate updateCandidate(Long id, JobCandidate candidate, List<String> skillNames);

	JobCandidate addSkills(Long candidateId, List<String> skillNames);

	JobCandidate removeSkill(Long candidateId, String skillName);

	void removeCandidate(Long id);

	JobCandidate getCandidate(Long id);

	List<JobCandidate> searchByName(String name);

	List<JobCandidate> searchBySkills(List<String> skillNames);
}
