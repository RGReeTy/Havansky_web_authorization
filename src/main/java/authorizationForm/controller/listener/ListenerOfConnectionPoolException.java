package main.java.authorizationForm.controller.listener;

public class ListenerOfConnectionPoolException extends Exception {

    public ListenerOfConnectionPoolException() {
        super();
    }

    public ListenerOfConnectionPoolException(String message) {
        super(message);
    }

    public ListenerOfConnectionPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public ListenerOfConnectionPoolException(Throwable cause) {
        super(cause);
    }
}
