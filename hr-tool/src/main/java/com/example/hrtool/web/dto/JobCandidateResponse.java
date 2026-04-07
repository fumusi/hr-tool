package com.example.hrtool.web.dto;

import java.time.LocalDate;
import java.util.List;

public record JobCandidateResponse(
		Long id,
		String fullName,
		LocalDate dateOfBirth,
		String contactNumber,
		String email,
		List<String> skills) {
}

