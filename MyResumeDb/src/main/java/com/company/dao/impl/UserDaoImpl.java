package com.company.dao.impl;

import com.company.dao.entity.Country;
import com.company.dao.entity.User;
import com.company.dao.inter.AbstractDao;
import com.company.dao.inter.UserDaoInter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDaoInter {

    public User getUser(ResultSet rs) throws Exception {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String surname = rs.getString("surname");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        String profileDescription = rs.getString("profile_description");
        String address = rs.getString("address");
        Date birthDate = rs.getDate("birthdate");
        int birthplaceId = rs.getInt("birthplace_id");
        int nationalityId = rs.getInt("nationality_id");
        String birthPlaceStr = rs.getString("birthplace");
        String nationalityStr = rs.getString("nationality");

        Country c = new Country(birthplaceId, birthPlaceStr, null);
        Country c2 = new Country(nationalityId, null, nationalityStr);

        User user = new User(id, name, surname, email, phone, profileDescription, address, birthDate, c, c2);
        return user;
    }

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("select " +
                    "u.*," +
                    "n.name as birthplace," +
                    "c.nationality " +
                    "from user u " +
                    "left join Country n on n.id = u.birthplace_id " +
                    "left join Country c on c.id = u.nationality_id");
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                User u = getUser(rs);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    public User getUserById(int userId) {
        User user = null;
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("select " +
                    "u.*, " +
                    "n.name as birthplace, " +
                    "c.nationality " +
                    "from user u " +
                    "left join Country n on n.id = u.birthplace_id " +
                    "left join Country c on c.id = u.nationality_id where u.id = " + userId);
            stmt.execute();
            ResultSet rs = stmt.getResultSet();

            while (rs.next()) {
                user = getUser(rs);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean removeUser(int userId) {
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("delete from user where id = " + userId);
            return stmt.execute();
        }catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(User user) {
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("update user set name=?, surname=?, email=?, phone=?, birthplace_id=?, nationality_id =? where id = ?");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            stmt.setInt(5, user.getBirthplace().getId());
            stmt.setInt(6, user.getNationality().getId());
            stmt.setInt(5, user.getId());
            return stmt.execute();
        }catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean insertUser(User user) {
        try(Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("insert into user(name, surname, email, phone) values(?,?,?,?)");
            stmt.setString(1, user.getName());
            stmt.setString(2, user.getSurname());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getPhone());
            return stmt.execute();
        }catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
}
