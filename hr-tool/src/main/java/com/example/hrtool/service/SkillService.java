package com.example.hrtool.service;

import com.example.hrtool.domain.Skill;

public interface SkillService {

	Skill getOrCreate(String name);
}

