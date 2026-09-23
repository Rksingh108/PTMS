package com.ptms.app.dao;

import com.ptms.app.model.User;
import com.ptms.app.model.Roles;

import java.util.List;

public interface IUserDao {

    void addUser(User user);

    User getUserById(int id);

    User getUserByUsername(String username);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(int id);

    Roles getRoleById(int id);
}