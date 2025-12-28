package com.bitguy.service;

import com.bitguy.model.Task;
import java.util.List;
import java.util.stream.Collectors;

// Demonstrates: Interface (old contract, new default method for evolution).
public interface TaskRepository {

    void save(Task task);
    List<Task> findAll();

    // New: Default method (java 8+ hybrid)
    default List<Task> filterActive() {
        return findAll().stream()
                        .filter(t -> t.getStatus().isActive()) // Lambda
                        .collect(Collectors.toList());
    }

    Task findByTitle(String title);
    void deleteTask(String title);
}