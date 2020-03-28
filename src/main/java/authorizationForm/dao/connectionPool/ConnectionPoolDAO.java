package main.java.authorizationForm.dao.connectionPool;

import main.java.authorizationForm.dao.DAOException;

public interface ConnectionPoolDAO {

    void init() throws DAOException;

    void destroy();
}
