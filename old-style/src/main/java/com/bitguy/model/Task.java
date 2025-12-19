package com.bitguy.model;


// Demonstrates: Inheritance (base for PersonalTask), Abstraction (abstract class with template method),
// Attributes/Methods (fields, abstract/concrete methods), Access Specifiers (Protected for inheritance).
// Static keyword (Static counter for IDs), Initializer Block (instance init),
// Enums (TaskStatus), Method Overloading (getStatus variants),
// Static vs Dynamic Binding (static ID gen vs overriden display()).

public abstract class Task {        // Abstraction connot instantiate directly
    protected static long nextId = 1;       //Static: Shared across instances

    protected long id;
    protected String title;
    protected String description;
    protected int priority;
    protected TaskStatus status;        // Enum 

    // Static initializer block: Runs once when class loaded
    static {
        System.out.println("Task class loaded - static init.");     // Demo static block
        nextId = 1;         // Reset for demo
    }

    // Instance Initializer block: Runs before constructor
    {
        this.id = nextId++;     //Demo instance block
    }

    // Full constructor
    public Task( String title, String description, int priority){
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = TaskStatus.PENDING;
    }

    // Abstract method: Must be overriden
    public abstract void displayDetails();      // Template method part 

    // Concrete templete  method: Abstraction pattern
    public final void processTask() {           // Final: Connot override
        System.out.println("Processing.. " + title);
        validate();             // Abstract hook
        updateStatus();         // Concrete
        displayDetails();       // Dynamic binding here
        System.out.println("Everything went smoothly.");
    }

    // Overloaded methods
    public TaskStatus getStatus() {
        return status;
    }
    public String getStatus(String format){
        return format.equals("short") ? status.name(): status.toString();
    }

    // Protected method for inharitance 
    protected void validate() {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("Title required");
        }
    }

    private void updateStatus() {
        status = TaskStatus.IN_PROGRESS;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription(){
        return description;
    }
    public void setDescription(String description){
        this.description = description;
    }

    public int getPriority(){
        return priority;
    }
    public void setPriority(int priority){
        this.priority = priority;
    }

    public void setStatus(TaskStatus status){
        this.status = status;
    }

    // Oject LifeCycle: Deprecated but demo explicit finalize
    @Override
    protected void finalize() throws Throwable {
        System.out.println("Task " + id + " finalized - cleanup resources.");
        super.finalize();
    }

}