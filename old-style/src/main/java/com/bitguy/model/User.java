package com.bitguy.model;


// Demonstrate: Classes and Objects (User as Class, instantiate via new),
// Attribute and Methods (private fields, getters/setters),
// Access Specifiers (private fields, public methods),
// Encapsulation (hide id, expose via getters),
// Final keyword (final id - immutable once set),
// Pass by Value/ Reference (setter mutate reference, primitives copied).

public class User{
    private final long id; //Final: Connot reassign after init
    private String name;
    private String email;

    //Full Constructor - verbose old style
    public User(long id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Manual getter and setters - no Lombok
    public long getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;                   //Demo: pass-by-value for String (immutable, but referenced copied)
    }

    public String getEmail() {
        return this.email;
    }

    public void SetEmail(String email) {
        this.email = email;
    }

    //Method: Validation
    public boolean isValid() {
        return name != null && name.length() > 0 && email != null && email.contains("@");
    }


    @Override
    public String toString(){
        return "User {id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}