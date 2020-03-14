package com.testAuthorization.dao;

import com.testAuthorization.bean.UserAccount;

import java.util.List;

public interface UserDao {
    List<UserAccount> getAll() throws DAOException;

    void save(UserAccount user) throws DAOException;

    UserAccount getUserByLogin(String login) throws DAOException;
}
