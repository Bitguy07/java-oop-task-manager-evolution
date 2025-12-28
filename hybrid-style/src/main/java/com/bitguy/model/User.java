package com.bitguy.model;


// Demonstrates: Classes/Objects (core class), Attributes/Methods (manual getters/setters - old),
// Access Specifiers (pribate/public), Encapsulation (controlled access),
// Final Keyword (final id - immutable, hybrid with new immutability trend).
public class User {

    private final long id;      // Final: Old immutable field, align with new records
    private String name;        // Mutable via setter - old style
    private String email;

    public User(long id, String name, String email) {       // Full constructor - old verbose
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Manual getter/setters - old, but could evolve to records
    public long getId(){
        return id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;               // Old mutability
    }

    public String getEmail(){
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public boolean isEmailValid() {
        return name != null && !name.isEmpty() && email != null && email.contains("@");     // Old validation
    }

    @Override
    public String toString(){
        return "User{id=" + id + ", name='" + name + "', email='" + email + "'}";                       // Old toString
    }
}