package com.bitguy.model;

// Demonstrates: Enums (with values, switch usage in services).
public enum TaskStatus {
    PENDING, IN_PROGRESS, COMPLETED, CANCELLED;

    // Enum method
    public String getDisplayName() {
        switch ( this) {    // Verbose Switch - old style
            case PENDING: 
                        return "Not Started";
            case IN_PROGRESS:
                        return "Doing";
            case COMPLETED:
                        return "Done";
            case CANCELLED:
                        return "Dropped";
            default:
                        return "Unknown";
        }
    }
}