package com.example.hrtool.web;

import com.example.hrtool.domain.JobCandidate;
import com.example.hrtool.web.dto.JobCandidateResponse;
import java.util.List;
import java.util.stream.Collectors;

public final class CandidateMapper {

	private CandidateMapper() {
	}

	public static JobCandidateResponse toResponse(JobCandidate candidate) {
		List<String> skills = candidate.getSkills().stream().map(skill -> skill.getName()).sorted().collect(Collectors.toList());
		return new JobCandidateResponse(candidate.getId(), candidate.getFullName(), candidate.getDateOfBirth(), candidate.getContactNumber(), candidate.getEmail(), skills);
	}
}

