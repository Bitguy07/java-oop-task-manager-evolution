package com.bitguy.util;

import java.util.function.Predicate;

import com.bitguy.model.PersonalTask;
import com.bitguy.model.Task;

// Demonstrates: Nested Classes (old static, now inner for builders),
// Method Chaining (manual fluent- Lombok-like hybrid), Lambdas (in nested validator - new).
public class TaskBuilder {

    private String title;
    private String description;
    private int priority;

    // Static nested: Old access
    public static class NestedValidator {
        public static boolean isValidPriority(int p){
            return p > 0 && p < 6;
        }
        
        // New: :Lambda in nested (hybrid util)
        public Predicate<Integer> priorityChecker = p -> p > 0 && p < 6;
    }

    public TaskBuilder title (String title){
        this.title = title;
        return this;        // Chaining: Old manual, new fluent style
    }

    public TaskBuilder description(String description) {
        this.description = description;
        return this;
    }

    public TaskBuilder priority(int priority){
        if (!new NestedValidator().priorityChecker.test(priority)) {    // Lambda call
            throw new IllegalArgumentException("Invalid priority");
        }
        this.priority = priority;
        return this;
    }

    public Task build(){
        return new PersonalTask(title, description, priority, "Built via hybrid chain");
    }
}