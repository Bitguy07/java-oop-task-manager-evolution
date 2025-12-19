package com.bitguy.model;

public class PersonalTask extends Task {
    private String personalNote;        // Additional Attributes
    
    public PersonalTask(String title, String description, int priority, String personalNote){
        super(title, description, priority);
        this.personalNote = personalNote;
    }

    // Overriding: Dynamic binding demo
    @Override
    public void displayDetails(){
        System.out.println("Personal: " + title + " (Note:" + personalNote + ")");
    }

    // Additional method
    public String getPersonalNote() {
        return personalNote;
    }

    public void setPersonalNote(String personalNote ) {
        this.personalNote = personalNote;
    }

    // @Override
    // public String toString(){
    //     return "this is the test string from PersonalTask Class";
    // }
 
}