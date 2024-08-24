package org.arta.vopros.exception;

public class ServletResponseException extends RuntimeException{
    public ServletResponseException(Exception e) {
        super(e);
    }
}
