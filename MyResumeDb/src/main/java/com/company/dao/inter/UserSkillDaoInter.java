package com.company.dao.inter;

import com.company.dao.entity.UserSkill;

import java.util.List;

public interface UserSkillDaoInter {

    public List<UserSkill> getAllSkillByUserID(int id);

    public boolean removeUserSkillByID(int id);

    public boolean insertUserSkillById(UserSkill userSkill);

    public boolean updateUserSkillById(UserSkill userSkill);

}
