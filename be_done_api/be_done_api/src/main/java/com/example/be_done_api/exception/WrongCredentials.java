package com.example.be_done_api.exception;

public class WrongCredentials extends RuntimeException {
    public WrongCredentials(String message) {
        super(message);
    }
}
