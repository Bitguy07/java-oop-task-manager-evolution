package com.bitguy.service;

import java.util.ArrayList;
import com.bitguy.model.User;
import java.util.List;
import java.util.stream.Collectors;     // For light Streams

// Demonstrates: Attributes/Methods (old list, new stream filter).
public class UserService {

    private static long userNextId = 1;
    private final List<User> users = new ArrayList<User>();     // Final: New immutability

    // Create a new user
    public User createUser(String name, String email) {
        User user = new User(userNextId++, name, email);
        if(!user.isEmailValid()){
            throw new IllegalArgumentException("Invalid user");
        }

        users.add(user);
        return user;
    }

    public List<User> getAllUsers() {
        return new ArrayList<>(users);      // Old defensive copy
    }

    // Hybrid: New stream for filtering (e.g., active users)
    public List<User> getActiveUsers() {
        return users.stream()
                    .filter(u -> u.getName() != null)       // Simple lamda
                    .collect(Collectors.toList());
    }

    public User getUserByName(String name){
        for(User u: users) {
            if(u.getName().equals(name)){
                return u;
            }
        }
        return null;
    }

    public void deleteUser(String name) {
        users.removeIf(u -> u.getName().equals(name));      // Java 8+ but old-style
    }
}