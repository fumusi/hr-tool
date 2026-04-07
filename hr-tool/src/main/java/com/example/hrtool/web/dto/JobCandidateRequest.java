package com.example.hrtool.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

public record JobCandidateRequest(
		@NotBlank String fullName,
		@NotNull LocalDate dateOfBirth,
		@NotBlank String contactNumber,
		@Email @NotBlank String email,
		List<String> skills) {
}

