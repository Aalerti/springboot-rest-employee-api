package org.example.springbootrest.exception_handling;

public class UserExistsWithThatUsername extends RuntimeException {
    public UserExistsWithThatUsername(String message) {
        super(message);
    }
}
