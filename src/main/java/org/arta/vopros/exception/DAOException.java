package org.arta.vopros.exception;

public class DAOException extends RuntimeException {
    public DAOException(Exception e) {
        super(e);
    }
}

