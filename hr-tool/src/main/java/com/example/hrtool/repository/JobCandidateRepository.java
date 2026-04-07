package com.example.hrtool.repository;

import com.example.hrtool.domain.JobCandidate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobCandidateRepository extends JpaRepository<JobCandidate, Long> {

	@EntityGraph(attributePaths = "skills")
	Optional<JobCandidate> findById(Long id);

	@EntityGraph(attributePaths = "skills")
	List<JobCandidate> findByFullNameContainingIgnoreCase(String fullName);

	@EntityGraph(attributePaths = "skills")
	List<JobCandidate> findDistinctBySkills_NameIn(List<String> skillNames);
}

