package com.bitguy.service;

import com.bitguy.model.Task;
import java.util.List;

// Demonstrates: Interface (contract for repositories).
public interface TaskRepository {
    void save(Task task);
    List<Task> getAllTasks();
    Task getTaskByTitle(String title);
    void deleteTask(String title);
}