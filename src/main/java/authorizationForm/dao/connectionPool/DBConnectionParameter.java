package main.java.authorizationForm.dao.connectionPool;

public enum DBConnectionParameter {

    DRIVER("database.driver"),
    URL("database.url"),
    USER("database.user"),
    PASSWORD("database.password"),
    POOL_SIZE("database.pool_size");

    private String key;

    DBConnectionParameter(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

}
