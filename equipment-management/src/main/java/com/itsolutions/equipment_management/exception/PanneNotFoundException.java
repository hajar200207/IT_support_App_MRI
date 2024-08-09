package com.itsolutions.equipment_management.exception;

public class PanneNotFoundException extends RuntimeException{
    public PanneNotFoundException(String message) {
        super(message);
    }
}
