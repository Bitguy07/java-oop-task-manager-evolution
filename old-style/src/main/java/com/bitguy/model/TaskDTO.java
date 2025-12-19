package com.bitguy.model;

// Demonstrates: Simulate Record (pre-java 16: Verbose class with all fields final, manual equals/hashCode/toString).
// Encapsulation: Immutable-like (no setters).
public class TaskDTO {

    private final long id;
    private final String title;
    private final TaskStatus status;

    // Full constructor
    public TaskDTO(long id, String title, TaskStatus status) {
        this.id = id;
        this.title = title;
        this.status = status;
    }

    // Manual getters (no setters for "record" simulation)
    public long getId() { return id; }
    public String getTitle() { return title; }
    public TaskStatus getStatus() { return status; }

    // Manual equals (old style - verbose)
    @Override
    public boolean equals (Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        TaskDTO taskDTO = (TaskDTO) obj;
        return id == taskDTO.id && title.equals(taskDTO.title) && status == taskDTO.status;
    }

    // Manual hashCode
    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + title.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }

    // Manual toString
    @Override
    public String toString() {
        return "TaskDTO{id=" + id + ", title='" + title + "', status=" + status + "}";
    }
    
}