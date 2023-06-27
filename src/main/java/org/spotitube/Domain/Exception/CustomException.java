package org.spotitube.Domain.Exception;

public class CustomException extends RuntimeException{
    public CustomException(Exception e) {
        super(e);
    }
    public CustomException(String message) {
        super(message);
    }
}
