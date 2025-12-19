package com.bitguy.main;

import java.util.Scanner;

import com.bitguy.model.User;
import com.bitguy.service.TaskService;
import com.bitguy.service.UserService;
import com.bitguy.util.TaskBuilder;
import com.bitguy.model.Task;

public class App {
    public static void main(String[] args) {

        UserService userService = new UserService();
        TaskService taskService = new TaskService();
        
        Scanner scanner = new Scanner(System.in);
        boolean runing = true;

        System.out.println("=== Old Java Task Manager ===");

        while (runing) {
            System.out.println("\n1. Create User \n2. View Users \n3. Create Task \n4. View Tasks\n5. Assign Task \n6. Delete Task\n7. Exit");
            System.out.println("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline 

            switch (choice) {
                case 1:
                    System.out.println("Name: ");
                    String name = scanner.nextLine();

                    System.out.println("Email: ");
                    String email = scanner.nextLine();

                    // User user = new User(1, name, email);
                    // System.out.println(user);

                    // // System.out.println("Current Data: \nName: "+ name +"\nEmail: "+email);
                    User user = userService.createUser(name, email);
                    System.out.println("\nCreated: "+ user.getName());
                    break;
                
                case 2:
                    for (User u: userService.getAllUsers()){
                        System.out.println(u.getName() + " (" + u.getEmail() + ") ");
                    }
                    break;

                case 3:
                    System.out.println("Title: ");
                    String title = scanner.nextLine();

                    System.out.println("Description: ");
                    String desc = scanner.nextLine();

                    Task task = new TaskBuilder().title(title).description(desc).priority(1).build();
                    taskService.createTask(task);
                    // Task task = new PersonalTask(title, desc, 1, "this is the first test note");
                    // System.out.println(task);
                    // task.displayDetails();
                    // taskService.save(task);
                    System.out.println("Created: " + task.getTitle());
                    break;
                case 4:
                    for(Task t: taskService.getAllTasks())  {
                        System.out.println(t.getTitle() + " - " +  t.getStatus());
                    }
                    break;

                case 5:

                    // List all the Task present in the task list
                    for(Task t: taskService.getAllTasks()){
                        System.out.println("Id: " + t.getId() + "\nTitle: " + t.getTitle() + "\nDescription: " + t.getDescription() + "\nPreiority: " + t.getPriority() + "\nStatus: " + t.getStatus());
                    }

                    // List all the User present in the User list
                    for(User u: userService.getAllUsers()){
                        System.out.println("\nId: " + u.getId() + "\nName: " + u.getName() + "\nEmail: " + u.getEmail());
                    }

                    // Assign: Demo Pass-by-reference (mutate object)
                    System.out.print("\nEnter title: ");
                    Task assignTask = taskService.getTaskByTitle(scanner.nextLine());
                    System.out.print("\nEnter user name: ");
                    User assignee = userService.getUserByName(scanner.nextLine());
                    taskService.assignTask(assignTask, assignee);   // Reference passed, mutated
                    System.out.println("\n");
                    System.out.println("Assigned" + assignTask.getTitle() + " to " + assignee.getName());
                    break;

                case 6: 
                    // List all the Task present in the task list
                    for(Task t: taskService.getAllTasks()){
                        System.out.println("Id: " + t.getId() + "\nTitle: " + t.getTitle() + "\nDescription: " + t.getDescription() + "\nPreiority: " + t.getPriority() + "\nStatus: " + t.getStatus());
                    }
                    System.out.print("Enter title to delete task: ");
                    taskService.deleteTask(scanner.nextLine()); //Title-based delete
                    break;

                case 7:
                    runing = false;
                    break;
                    
                default:
                    System.out.println("Invalid choice.");
            }
        }

        // Object Lifecycle – Manual finalize() call (BAD PRACTICE)
        //
        // finalize() is a JVM lifecycle hook intended to be invoked only by the Garbage Collector.
        // Calling it explicitly:
        //  - Breaks the GC contract (the JVM may call finalize() again later)
        //  - Can cause cleanup code to run multiple times
        //  - Does NOT guarantee resource release or object destruction
        //  - Makes object lifecycle unpredictable and error-prone
        //
        // Even in older Java versions, finalize() was never meant to be called directly.
        // Cleanup should be explicit (via a method) or managed by the JVM.
        //
        // userService.finalize(); // DO NOT DO THIS
        // taskService.finalize(); // DO NOT DO THIS

        scanner.close();
        System.out.println("Shutdown complete.");
    }
}