package com.example.hrtool.service.impl;

import com.example.hrtool.domain.Skill;
import com.example.hrtool.repository.SkillRepository;
import com.example.hrtool.service.SkillService;
import java.util.Locale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class SkillServiceImpl implements SkillService {

	private final SkillRepository skillRepository;

	public SkillServiceImpl(SkillRepository skillRepository) {
		this.skillRepository = skillRepository;
	}

	@Override
	public Skill getOrCreate(String name) {
		if (!StringUtils.hasText(name)) {
			throw new IllegalArgumentException("Skill name must not be empty");
		}
		String normalized = name.trim();
		return skillRepository.findByNameIgnoreCase(normalized)
				.orElseGet(() -> skillRepository.save(new Skill(normalized)));
	}
}

