package com.bitguy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.bitguy.model.PersonalTask;
import com.bitguy.model.Task;
import com.bitguy.model.TaskResponse;
import com.bitguy.model.TaskStatus;
import com.bitguy.model.User;

// Demonstrates: Interface   impl (old), Method Overloading (old), Overriding (via abstract),
// Static vs Dynamic binding (mixed calls), Pass by Value/Reference (hybrid mutate),
// Dependency Injection (constructor + setter mix)

public class TaskService implements TaskRepository{
    private final UserService userService;          // Constructor-injected (new pref)
    private UserService setterService;              // Setter for old-style (hybrid)
    private List<Task> tasks = new ArrayList<Task>();

    // Constructor injection (new) + setter option (old)
    public TaskService (UserService userService){
        this.userService = userService;
    }

    public void setExtraService(UserService setterService) {    // Old setter
        this.setterService = setterService;
    }

    @Override
    public void save(Task task) {
        tasks.add(task);
    }

    @Override
    public List<Task> findAll(){
        return new ArrayList<Task> (tasks);
    }

    // New feature: Hybrid stream filter (calls interface default)
    public List<Task> filterByStatus(TaskStatus status){
        return findAll().stream()
                        .filter(t -> t.getStatus() == status) // Lambda - new
                        .collect(Collectors.toList());
    }

    @Override
    public Task findByTitle(String title){
        for ( Task t: tasks){   // Old loop
            if(t.getTitle().equals(title)){
                return t;
            }
        }
        return null;
    }

    @Override
    public void deleteTask(String title){
        tasks.removeIf(t -> t.getTitle().equals(title));
        System.out.println("Deleted successfully.");
    }

    // Overloading: Old variants
    public void  createTask(Task task) {
        if(validateTask(task)){
            save(task);
        }
    }

    public void createTask(String title, String desc){
        createTask(new PersonalTask(title, desc, 1, "Default via TaskService"));
    }

    // Dynamic binding: Call override at runtime
    public void processAllTask() {
        for (Task t: tasks){
            t.processTask();        // Hybrid: Old template, new poly
        }
    }

    // Pass-by-reference: Mutate task (old object ref, new Optional hint in response)
    public void assignTask(Task task, User user) {
        if ( task != null && user != null ){
            task.setDescription(task.getDescription() + " Assigned to " + user.getName());
        }
    }

    // To records: New DTO evolution 
    public TaskResponse toResponse(Task task) {
        return new TaskResponse(task.getId(), task.getTitle(), task.getStatus());
    }

    private boolean validateTask(Task task){
        return task.getTitle() !=null && !task.getTitle().isEmpty();
    }
}