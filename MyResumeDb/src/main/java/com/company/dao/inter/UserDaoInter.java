package com.company.dao.inter;

import com.company.dao.entity.User;

import java.util.List;

public interface UserDaoInter {

    public List<User> getAll();

    public User getUserById(int userId);

    public boolean removeUser(int userId);

    public boolean updateUser(User user);

    public boolean insertUser(User user);

}
