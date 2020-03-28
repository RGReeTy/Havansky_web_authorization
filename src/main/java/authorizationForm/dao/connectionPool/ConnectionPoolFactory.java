package main.java.authorizationForm.dao.connectionPool;

public class ConnectionPoolFactory {

    private static final ConnectionPoolFactory instance = new ConnectionPoolFactory();

    private final MySqlConnectionPoolDAOImpl mySqlConnectionPoolDAO = new MySqlConnectionPoolDAOImpl();

    private ConnectionPoolFactory() {
    }

    public static ConnectionPoolFactory getInstance() {
        return instance;
    }

    public MySqlConnectionPoolDAOImpl getMySqlConnectionPoolDAO() {
        return mySqlConnectionPoolDAO;
    }

}
