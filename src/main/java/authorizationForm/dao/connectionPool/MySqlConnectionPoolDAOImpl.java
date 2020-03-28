package main.java.authorizationForm.dao.connectionPool;

import main.java.authorizationForm.dao.DAOException;

public class MySqlConnectionPoolDAOImpl implements ConnectionPoolDAO {

    public MySqlConnectionPoolDAOImpl() {
    }

    @Override
    public void init() throws DAOException {
        try {
            ConnectionPool.getInstance().poolInitialization();
        } catch (ConnectionPoolException e) {
            throw new DAOException("Impossible to initialise pool", e);
        }

    }

    @Override
    public void destroy() {
        ConnectionPool.getInstance().dispose();
    }
}
