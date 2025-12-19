package com.bitguy.util;

import com.bitguy.model.PersonalTask;
import com.bitguy.model.Task;

// Demonstrates: Nested Classes (static Builder nested in TaskBuilder),
// Method Chaining (fluent setters return this),
// Final Keyword (final build()).
public class TaskBuilder {
    private String title;
    private String description;
    private int priority;

    // Static nested class: Can access outer statics, but not instances
    public static class NestedValidator {
        public static boolean isValidPriority(int p) {
            return p > 0 && p < 6;
        }
    }

    // Non-static inner class: Would need outer instance, but we use simplicity
    // (Demo: Could be used as Taskbuilder.NestedValidator)

    public TaskBuilder title(String title) {
        this.title = title;
        return this;    // Chaining
    }

    public TaskBuilder description(String description) {
        this.description = description;
        return this;
    }

    public TaskBuilder priority(int priority) {
        if (!NestedValidator.isValidPriority(priority)) {      // Nested class usage
            new IllegalArgumentException("Invalid Prioriy");
        }
        this.priority = priority;
        return this;
    }

    public final Task build() {  // Final method
        return new PersonalTask(title, description, priority, "Built via chain");
    }
}