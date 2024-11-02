package com.vishal.PersonDetails.exceptions;


public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String message) {
        super(message);
    }
}