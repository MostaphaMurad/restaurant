package com.restapi.RestaurantException;

public class CustomException extends RuntimeException{
    public CustomException(String message) {
        super(message);
    }
}
