package io.albot.example.grpc.exception;

public class NoSuchUserException extends RuntimeException{
    public NoSuchUserException(long id) {
        super(String.format("No such user with id %d", id));
    }
}