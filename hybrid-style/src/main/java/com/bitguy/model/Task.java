package com.bitguy.model;

// Demonstrates: INheritance/Abstraction (abstract sealed-permit hint - new Java 17, but old abstract base),
// Initializer Block (static/instance - old), Static Keyword (Counter),
// Static vs Dynamic Binding (static init vs runtime override calls),
// Method Overloading (getStatus variants - old).
public abstract sealed class Task permits PersonalTask {        // Sealed: New hierarchy control, old abstract
    protected static long nextId = 1;   // Static: Old shared state

    protected long id;
    protected final String title; // Final: Hybird immutable field
    protected String description; // Mutable - old
    protected int priority;
    protected TaskStatus status;

    // Static block: Old init, uns on load
    static {
        System.out.println("Task loaded - static init (old style).");
        nextId = 1;
    }

    public Task (String title, String description, int priority) {      // Old full constructor
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = TaskStatus.PENDING;
    }

    // Abstract: Old hook for overriding
    public abstract void displayDetails();

    // Template: Old pattern, final to prevent override
    public final void processTask() {
        System.out.println("Processing" + title);
        validate();
        updateStatus();
        displayDetails();       // Dynamic binding: New runtime polymorphism
    }

    protected void updateStatus(){
        status = TaskStatus.IN_PROGRESS;
    }

    protected void validate() {
        if(title == null || title.isEmpty()){
            throw new IllegalArgumentException("Title required");
        }
    }

    // Manual getters/setters - old verbose
    public long getId(){
        return id;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public int getPriority(){
        return priority;
    }

    // Overloading: Old compile-time
    public TaskStatus getStatus(){
        return status;
    }
    public TaskStatus geTaskStatus(String format){
        return "short".equals(format) ? status : null;      // Simplified hybrid
    }

    public void setDescription(String description){
        this.description = description;
    }
    public void setPriority(int priority) {
        this.priority = priority;
    }
    public void setStatus(TaskStatus status){
        this.status = status;
    }
}