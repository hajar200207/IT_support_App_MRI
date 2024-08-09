package com.itsolutions.equipment_management.models;

public enum Role {
    ROLE_USER,
    ROLE_ADMIN,
    ROLE_TECHNICIEN;

    public static Role fromString(String role) {
        switch (role.toUpperCase()) {
            case "TECHNICIEN":
                return ROLE_TECHNICIEN;
            case "USER":
                return ROLE_USER;
            case "ADMIN":
                return ROLE_ADMIN;
            default:
                throw new IllegalArgumentException("Unknown role: " + role);
        }
    }
}
