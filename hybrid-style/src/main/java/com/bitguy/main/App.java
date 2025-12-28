package com.bitguy.main;

import java.util.Scanner;
import com.bitguy.model.User;
import com.bitguy.util.TaskBuilder;
import com.bitguy.config.ConfigFactory;
import com.bitguy.model.PersonalTask;
import com.bitguy.model.Task;
import com.bitguy.model.TaskStatus;

// Demonstrates: Packages (main pkg), Classes/Objects (factory wiring),
// Dependency Injection (mixed via factory), Object Lifecyle (try-with-resources for Scanner - new),
// var inference (Java 10+ - hybrid evolution from explicit types).

public class App{
    public static void main(String[] args){
        // DI: Hybrid - factory provides constructor-injected service (new) with some setters (old)
        var factory = new ConfigFactory();        // var: New inference, old explict new
        var userService = factory.createUserService();    // Constructor injection
        var taskService = factory.createTaskService(userService);   // Mixed: Constructor + internal setter

        try(Scanner scanner = new Scanner(System.in)){      // Try-with-resources: New lifecycle management
            boolean running = true;

            System.out.println("=== Mixed Java Task Manager ===");

            while (running) {
                System.out.println("\n1. Create User\n2. View Users\n3. Create Task\n4. View Task\n5. Filter Tasks by Status\n6. Assign Task\n7. Delete Task\n8. Exit");
                System.out.println("Choose: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        System.out.println("Name: ");
                        String name = scanner.nextLine();
                        System.out.println("Email: ");
                        String email = scanner.nextLine();
                        User user = userService.createUser(name, email);

                        // System.out.println("CREATED:"+ user);
                        // User user = new User(1, name, email);
                        // System.out.println(user);
                        // System.out.println("\nName: "+name+" & Email:"+ email);
                        System.out.println("Created: " + user.getName());
                        break;
                    
                    case 2:
                        for (var u: userService.getAllUsers()){     // var in loop - new
                            System.out.println(u.getName() + " ( " + u.getEmail() + " ) ");
                        }
                        break;
                    
                    case 3:
                        System.out.println("Title ");
                        String title = scanner.nextLine();
                        System.out.println("Description: ");
                        String desc = scanner.nextLine();
                        // Chaining (manual - hybrid)
                        Task task = new TaskBuilder().title(title).description(desc).priority(1).build();
                        taskService.createTask(task);
                        System.out.println("Created: " + task.getTitle());
                        // System.out.println("One Task is created:\n" );
                        // task.displayDetails();
                        break;
                    case 4:
                        for(var t : taskService.findAll()){
                            System.out.println(t.getTitle() + " - " + t.getStatus());
                        }
                        break;
                    case 5:     // New feature: Basic filtering with streams (hybrid - limited use)
                        System.out.println("Status (PENDING/IN_PROGRESS): ");
                        TaskStatus status = TaskStatus.valueOf(scanner.nextLine());
                        var filterd = taskService.filterByStatus(status);   // Lambda/stream in service
                        for (var t: filterd) {
                            System.out.println("Filtered: " + t.getTitle());
                        }
                        break;
                    case 6:
                        // List all the task present in the task list
                        System.out.println("\nAll the tasks are:- ");
                        for(Task t: taskService.findAll()){                                                                                                                                                            // Typecasting(object reference) to PersonalTask due to CompileTimeCheck
                            System.out.println("Id: " + t.getId() + "\nTitle: "+ t.getTitle()+"\nDescription: "+ t.getDescription() + "\nPriority: " + t.getPriority() + "\nStatus "+t.getStatus() + "\nPersonalNote: "+ ((PersonalTask)t).getPersonalNote() + "\n");
                        }

                        // List all the User present in the user list
                        System.out.println("\nAll the users are:-");
                        for(User u: userService.getAllUsers()){
                            System.out.println("Id: "+ u.getId() + "\nName: " + u.getName() + "\nEmail: " + u.getEmail() +"\n");

                        }

                        // Example what to input
                        System.out.print("Enter title: ");
                        Task getTask = taskService.findByTitle(scanner.nextLine());
                        System.out.print("Enter user name: ");
                        User assignee = userService.getUserByName(scanner.nextLine());
                        taskService.assignTask(getTask, assignee);     // pass-by-reference mutate

                        // List the assigned info
                        System.out.println("\nThe assign info is like this:");
                        for(Task t: taskService.findAll()){                                                                                                                                                            // Typecasting(object reference) to PersonalTask due to CompileTimeCheck
                            System.out.println("Id: " + t.getId() + "\nTitle: "+ t.getTitle()+"\nDescription: "+ t.getDescription() + "\nPriority: " + t.getPriority() + "\nStatus "+t.getStatus() + "\nPersonalNote: "+ ((PersonalTask)t).getPersonalNote());
                        }
                        break;
                    case 7:
                        // List all the task present in the task list
                        System.out.println("\nAll the tasks are:- ");
                        for(Task t: taskService.findAll()){                                                                                                                                                            // Typecasting(object reference) to PersonalTask due to CompileTimeCheck
                            System.out.println("Id: " + t.getId() + "\nTitle: "+ t.getTitle()+"\nDescription: "+ t.getDescription() + "\nPriority: " + t.getPriority() + "\nStatus "+t.getStatus() + "\nPersonalNote: "+ ((PersonalTask)t).getPersonalNote() + "\n");
                        }
                        System.out.print("Enter title to delete task: ");
                        taskService.deleteTask(scanner.nextLine());
                        break;
                    case 8:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option");
                }
            }
        }       // Auto-close Scanner - new lifecycle
        System.out.println("Shutdown completed.");
    }
}