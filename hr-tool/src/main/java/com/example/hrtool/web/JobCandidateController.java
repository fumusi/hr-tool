package com.example.hrtool.web;

import com.example.hrtool.domain.JobCandidate;
import com.example.hrtool.service.JobCandidateService;
import com.example.hrtool.web.dto.JobCandidateRequest;
import com.example.hrtool.web.dto.JobCandidateResponse;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/candidates")
public class JobCandidateController {

	private final JobCandidateService svc;

	public JobCandidateController(JobCandidateService svc) {
		this.svc = svc;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public JobCandidateResponse addCandidate(@Valid @RequestBody JobCandidateRequest req) {
		JobCandidate candidate = toCandidate(req);
		List<String> skills = req.skills() == null ? List.of() : new java.util.ArrayList<>(req.skills());
		JobCandidateResponse saved = CandidateMapper.toResponse(svc.addCandidate(candidate, skills));
		return saved;
	}

	@PutMapping("/{id}")
	public JobCandidateResponse updateCandidate(@PathVariable Long id, @Valid @RequestBody JobCandidateRequest req) {
		JobCandidate candidate = toCandidate(req);
		List<String> skills = req.skills() == null ? List.of() : new java.util.ArrayList<>(req.skills());
		JobCandidateResponse saved = CandidateMapper.toResponse(svc.updateCandidate(id, candidate, skills));
		return saved;
	}

	@PostMapping("/{id}/skills")
	public JobCandidateResponse addSkills(@PathVariable Long id, @RequestBody List<String> skills) {
		return CandidateMapper.toResponse(svc.addSkills(id, skills));
	}

	@DeleteMapping("/{id}/skills/{skillName}")
	public JobCandidateResponse removeSkill(@PathVariable Long id, @PathVariable String skillName) {
		return CandidateMapper.toResponse(svc.removeSkill(id, skillName));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCandidate(@PathVariable Long id) {
		svc.removeCandidate(id);
	}

	@GetMapping("/{id}")
	public JobCandidateResponse getCandidate(@PathVariable Long id) {
		return CandidateMapper.toResponse(svc.getCandidate(id));
	}

	@GetMapping("/search/by-name")
	public List<JobCandidateResponse> searchByName(@RequestParam String name) {
		return svc.searchByName(name)
				.stream()
				.map(CandidateMapper::toResponse)
				.toList();
	}

	@GetMapping("/search/by-skills")
	public List<JobCandidateResponse> searchBySkills(@RequestParam List<String> skills) {
		return svc.searchBySkills(skills)
				.stream()
				.map(CandidateMapper::toResponse)
				.toList();
	}

	private JobCandidate toCandidate(JobCandidateRequest req) {
		return new JobCandidate(req.fullName(), req.dateOfBirth(), req.contactNumber(), req.email());
	}
}
