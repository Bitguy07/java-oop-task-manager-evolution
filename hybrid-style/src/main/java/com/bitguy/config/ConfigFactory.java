package com.bitguy.config;

import com.bitguy.service.TaskService;
import com.bitguy.service.UserService;

// Demonstrates: Classes/Objects (factory pattern), Dependency Injection (mixed wiring - evolution).
public class ConfigFactory {

    public UserService createUserService() {
        return new UserService();       // Old new-ing
    }
    
    public TaskService createTaskService(UserService userService){
        TaskService ts = new TaskService(userService);      // New constructor
        ts.setExtraService(userService);                    // Old setter for hybrid
        return ts;
    }
}