package org.arta.vopros.exception;

public class ConnectionManagerOpenException extends RuntimeException {
    public ConnectionManagerOpenException(Exception e) {
        super(e);
    }
}
