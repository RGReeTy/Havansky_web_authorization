package main.java.authorizationForm.dao.connectionPool;

import java.sql.Connection;
import java.util.concurrent.BlockingQueue;

public class PoolExistenceValidation {

    public static boolean doesPoolExist(BlockingQueue<Connection> queue) {
        return queue != null;
    }
}
