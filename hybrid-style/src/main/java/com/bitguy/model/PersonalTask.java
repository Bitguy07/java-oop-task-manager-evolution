package com.bitguy.model;

// Demonstrates: Inheritance (extends sealed Task - new control), Method Overriding (old runtime poly).
public final class PersonalTask extends Task {      // Final: New immutability hint 
    private String personalNote;

    public PersonalTask(String title, String description, int priority, String personalNote){
        super(title, description, priority);
        this.personalNote = personalNote;
    }

    @Override
    public void displayDetails() {
        System.out.println("Personal: " + title + " (Note: " + personalNote + ")");
    }

    public String getPersonalNote() {
        return personalNote;
    }

    public void setPersonalNote(String personalNote) {
        this.personalNote = personalNote;
    }
  
}