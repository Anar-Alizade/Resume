package com.company.dao.impl;

import com.company.dao.entity.Skill;
import com.company.dao.entity.User;
import com.company.dao.entity.UserSkill;
import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.UserSkillDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserSkillDaoImpl extends AbstractDao implements UserSkillDaoInter {

    private UserSkill getUserSkill(ResultSet rs) throws Exception {
        int userSkillId = rs.getInt("userSkillId");
        int userId = rs.getInt("id");
        int skillId = rs.getInt("skill_id");
        String skillName = rs.getString("skill_name");
        int power = rs.getInt("power");

        UserSkill us = new UserSkill(userSkillId, new User(userId), new Skill(skillId, skillName), power);
        return us;
    }

    @Override
    public List<UserSkill> getAllSkillByUserID(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select "
                    + "us.id as userSkillId, "
                    + "u.*, "
                    + "us.skill_id, "
                    + "s.name as skill_name, "
                    + "us.power "
                    + "from "
                    + "user_skill us "
                    + "LEFT JOIN user u ON us.user_id = u.id "
                    + "LEFT JOIN skill s ON us.skill_id = s.id "
                    + "WHERE "
                    + "us.user_id = ?");
            stmt.setInt(1, userId);
            stmt.execute();

            ResultSet rs = stmt.getResultSet();
            while (rs.next()) {
                UserSkill userSkill = getUserSkill(rs);
                result.add(userSkill);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean removeUserSkillByID(int id) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("delete from user_skill where id=?");
            stmt.setInt(1, id);
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertUserSkillById(UserSkill userSkill) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into user_skill(user_id, skill_id, power) values(?, ?, ?)");
            stmt.setInt(1, userSkill.getUser().getId());
            stmt.setInt(2, userSkill.getSkill().getId());
            stmt.setInt(3, userSkill.getPower());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUserSkillById(UserSkill userSkill) {
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user_skill set user_id=?, skill_id=?, power=? where id = ?");
            stmt.setInt(1, userSkill.getUser().getId());
            stmt.setInt(2, userSkill.getSkill().getId());
            stmt.setInt(3, userSkill.getPower());
            stmt.setInt(4, userSkill.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
