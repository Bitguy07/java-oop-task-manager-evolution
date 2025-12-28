package com.bitguy.model;

// Demonstrates: Records (new Java 16+: Auto equals/hashCode/toString - evolution from verbose DTO).
public record TaskResponse(long id, String title, TaskStatus status) {
    // Compact constructor for validation (new hybrid)
    public TaskResponse{
        if(title == null){
            throw new IllegalArgumentException("Title required");
        }
    }
}