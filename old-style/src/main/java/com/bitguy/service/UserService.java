package com.bitguy.service;

import java.util.ArrayList;
import com.bitguy.model.User;
import java.util.List;

// Demonstrates: Classes/Objects (services as class), Attributes (private list),
// Methods (CRUD), static (counter), Packages (service pkg).
public class UserService{   
    private static long userNextId = 1;     // Static counter
    private List<User> users = new ArrayList<User>();      // In-memory

    // Create New Users
    public User createUser(String name, String email){
        User user = new User( userNextId++, name, email);
        if (!user.isValid()) {
            throw new IllegalArgumentException("Invalid user");
        }
        users.add(user);
        return user;
    }


    // Get All Users
    public List<User> getAllUsers() {
        return new ArrayList<User>(users);      //Defensive copy
    }

    // Get Specific User info by there name only.
    public User getUserByName(String name){
        for(User u:users) {
            if(u.getName().equals(name)){
                return u;
            }
        }
        return null;
    }

    // Delete Specific User info by there name only.
    public void deleteUser(String name){
        users.removeIf(u -> u.getName().equals(name));      //Note: Java 8 removeIf, but compatible
    }

    // Pass-by-value demo: Primitive int copy
    public void incrementId(int idCopy) {
        idCopy++;       //Does not change original
        System.out.println("Copied ID: " + idCopy);
    }

}