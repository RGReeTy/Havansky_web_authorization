package main.java.authorizationForm.dao.connectionpool;

import main.java.authorizationForm.dao.DAOException;

public interface ConnectionPoolDAO {

    void init() throws DAOException;

    void destroy();
}
