package com.bitguy.model;

// Demonstrates: Enums (old base, new custom methods for evolution).
public enum TaskStatus {
    PENDING, IN_PROGRESS, COMPLETED, CANCELLED;

    // New: Custm method (java 8+ enum evolution)
    public boolean isActive() {
        return this == IN_PROGRESS || this == PENDING;
    }

    // Old: Switch for display (hybrid)
    public String getDisplayName(){
        switch (this) {
            case PENDING:
                            return "Not Started";
            case IN_PROGRESS:
                            return "Doing";
            default:        
                            return "Done/Dropped";
        }
    }
}