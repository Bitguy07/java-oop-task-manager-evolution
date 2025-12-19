package com.bitguy.service;

import java.util.ArrayList;

import com.bitguy.model.PersonalTask;
import com.bitguy.model.Task;
import com.bitguy.model.User;
import com.bitguy.model.TaskStatus;
import com.bitguy.model.TaskDTO;

import java.util.List;

// Demonstrates: Interface (implements TaskRepository), Method  Overloading (createTask varants),
// Method Overriding (not directly, but via abstract), Dependency Injection (setter fo USerService),
// Static vs Dynamic Binding( polymorphic processTask), Pass by Value/Reference (assign mutates ref),
// Enums (Switch on status).

public class TaskService implements TaskRepository {

    private UserService userService;    // For DI
    private List<Task>  tasks = new ArrayList<Task>();

    // Setter for DI - old manual style
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void save(Task task) {
        tasks.add(task);
    }

    @Override
    public List<Task> getAllTasks() {
        return new ArrayList<Task>(tasks);
    }

    public Task getTaskByTitle(String title) {
        for( Task t: tasks) {
            if (t.getTitle().equals( title) ) {
                return t;
            }
        }
        return null;
    }

    public void deleteTask (String title) {
        tasks.removeIf(t -> t.getTitle().equals(title));
    }

    // Overloading: Multiple createTask signatures
    public void createTask(Task task){
        if (validateTask(task)) {
            save(task);
        }
    }

    public void createTask(String titile, String desc) {
        // Overlad
        createTask(new PersonalTask(titile, desc, 1, "Default note"));
    }

    // Business method: Demo dynamic binding (Task.processTask() calls override)
    public void processAllTasks() {
        for (Task t: tasks) {
            t.processTask();    // Dynamic: Calls PersonalTask.displayDetails() if instance is PersonalTask
        }
    }

    // Assign: Pass-by-refecrence (mutate task object)
    public void assignTask(Task task, User user) {
        if(task != null && user != null) {
            // Mutate via reference
            task.setDescription(task.getDescription()+ "Assigned to" + user.getName());
            System.out.println("Mutated: " + task.getDescription()); // Reflects change
        }
    }

    // Enum switch - verbose old style
    public String getStatusReport(TaskStatus status) {
        switch (status) {
            case PENDING: return "0 tasks pending";
            case IN_PROGRESS: return "In progress";
            default: return "Unknown";
        }
    }


    private boolean validateTask(Task task) {
        return task.getTitle() != null && !task.getTitle().isEmpty();
    }

    // DTO conversion: Simulate record uasge
    public TaskDTO toDTO(Task task) {
        return new TaskDTO(task.getId(), task.getTitle(), task.getStatus());
    }

    @Override
    protected void finalize() throws Throwable {        // finalize() is unrelaible to use as GC(Garbage Callector) does not garuntee that it will run this block or not.
                                                        // So that's why in the new Java versions it was deprecated and did not recommand to use.
        System.out.println("TaskService finalized - clearing " + tasks.size() + " tasks.");
        tasks.clear();
        super.finalize();
    }
}