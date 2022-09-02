package com.company.dao.inter;

import com.company.dao.entity.Skill;

import java.util.List;

public interface SkillDaoInter {

    public List<Skill> getAll();

    public Skill getSkillById(int id);

    public boolean removeSkill(int id);

    public boolean insertSkill(Skill skill);

    public boolean updateSkill(Skill skill);

}
