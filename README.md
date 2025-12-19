# Java OOP Task Manager Evolution

## Overview

Welcome to **Java OOP Task Manager Evolution –** a hands-on learning project that traces the transformation of Java Object-Oriented Programming (OOP) practices across three eras: **Old (Pre-Java 8), Hybrid (Java 11-17)**, and **Modern (Java 21+)**.

This repo implements the same **console-based Task Management System** three times, each version rebuilt from scratch to showcase evolving methodologies, techniques, and best practices. The system allows users to create, view, assign, and delete tasks in a simple in-memory setup via a menu-driven console interface. It's designed for Java backend learners transitioning from procedural/functional languages (like JavaScript or C) to enterprise-grade OOP, emphasizing how Java has reduced boilerplate, improved readability, and enhanced maintainability over time.

**Core Functionality** (Shared Across Versions):
 - **User Management:** Create, view, and delete users with validation (e.g., name/email checks).
 - **Task Management:** Create general/personal tasks, view lists, assign to users, delete by title, and process workflows (e.g., status updates).
 - **OOP Demonstrations:** Each version covers 22 key topics (e.g., classes/objects, inheritance, abstraction, enums, dependency injection) with inline code comments.
 - **Storage:** In-memory (ArrayList-based) for simplicity—no DB needed.
 - **I/O:** Console inputs/outputs using Scanner.
 - **Tech:** Pure Maven + JDK (version-specific); ~500-800 LOC per version.

Run any version to interact: e.g., create a user "Alice", add task "Buy groceries", assign it, view status, and exit. See evolution: Old is verbose (~750 LOC), Hybrid blends (~700 LOC), Modern is concise (~500 LOC).

## Origin & Motivation

This project was born from a personal challenge to master Java's OOP spectrum while bridging the "intimidation gap" many developers face when switching from non-OOP languages like JavaScript/TypeScript or C. As a full-stack engineer with JS/TS experience, I struggled with Java's upfront class-based structure—it felt "reverse-engineered" compared to iterative prototyping (e.g., grabbing user input first, then shaping data structures around it).

The idea crystallized from this reflection: Java has layers of evolution—manual getters/setters gave way to Lombok, verbose classes to records, inheritance-heavy designs to sealed classes and immutables, I planned to:

- List OOP topics spanning old/new Java.
- Build one project covering them.
- Recode it **three times:**
  1. **Old Style:** Pre-Java 8 verbosity (manual everything, no sugar).
  2. **Hybrid Style:** Mix old reliability with new efficiencies (e.g., records for DTOs, some lambdas).
  3. **Modern Style:** Java 21+ best practices (immutables, pattern matching, minimal code).

  The purpose? Visualize the **transition steps—**from boilerplate hell to elegant, colleague-friendly code. This equips me (and you!) to decode any enterprise codebase, regardless of era. Inspired by design patterns books and Reddit threads on "JS to Java pain points," it's a self-taught roadmap to senior backend readiness.

## Project Structure

The repo is organized by style/version for easy comparison:
```text
java-oop-task-manager-evolution/
├── README.md                 # This file: Overall guide
├── old-style/                # Pre-Java 8: Verbose, boilerplate-heavy (~750 LOC)
│   ├── pom.xml               # Maven config (JDK 8)
│   ├── src/main/java/...     # Code: com.bitguy packages (models, services, etc.)
│   └── README.md             # Version-specific setup & features
├── hybrid-style/             # Java 11-17: Blended old/new (~700 LOC) [WIP/In Progress]
│   ├── pom.xml               # Maven config (JDK 17)
│   ├── src/main/java/...     # Hybrid code
│   └── README.md             # Hybrid notes
└── modern-style/             # Java 21+: Concise, immutable-first (~500 LOC) [WIP/In Progress]
    ├── pom.xml               # Maven config (JDK 21)
    ├── src/main/java/...     # Modern code
    └── README.md             # Modern best practices
```
- **Packages (Per Version):** `model` (entities), `service` (logic/DI), `util` (builders), `main` (entry).
- **OOP Topics Covered:** Classes/Objects, Inheritance, Abstraction, Enums, Records (simulated/old), Dependency Injection, and more—detailed in version READMEs.

## Quick Start

### Prerequisites
Before diving into this OOP-focused project, ensure you're comfortable with Java's foundational concepts. These basics form the building blocks for the OOP layers explored here. If you're new, spend 1-2 days reviewing them via free resources like Oracle's Java Tutorials or Codecademy.

- **Basic Syntax:** Understand `public class`, `main` method, semicolons, and compilation (`javac` → `java`).
- **Lifecycle of a Program:** From source code → bytecode → JVM execution → termination (e.g., `System.exit()`).
- **Data Types:** Primitives (int, double, boolean) vs. references (String, arrays); wrappers (Integer).
- **Variables and Scopes:** Declaration (`int x;`), initialization, local/instance/static scopes; `final` for constants.
- **Type Casting:** Widening (automatic, e.g., int → long) vs. narrowing (explicit, e.g., `(int) 9.7`); risks like truncation.
- **String and Methods:** Immutability, `length()`, `substring()`, `equals()` vs. `==`; method signatures (return types, params).
- **Math Operations:** Arithmetic `(+,` `-`, `*`, `/`,`%`), Math class (`abs()`, `pow()`, `random()`).
- **Arrays:** Declaration (`int[] arr = new int[5];`), initialization (`{1,2,3}`), access (`arr[0]`), length; multi-dimensional.
- **Conditionals:** `if-else`, `switch` (pre-14 style), ternary (`? :`).
- **Loops:** `for`, `while`, `do-while`; enhanced for-each for arrays/collections.
These ensure you can follow the code without getting stuck on syntax—OOP builds directly on them!

### Running a Version

1. `cd <version-folder>` (e.g., `cd old-style`).
2. Compile: `mvn clean compile`.
3. Run: `mvn exec:java` (or j`ava -cp target/classes com.bitguy.main.App`).
3. Interact: Follow the menu (1=Create User, etc.).

### Example Output (All Versions)
```text
=== Task Manager ===
1. Create User ... 7. Exit
Choose: 1
Name: Alice
Email: alice@email.com
Created: Alice
```

### Learning Path & Contributions
- **Start Here:** Build/run `old-style` to feel the "old pain," then evolve.
- **Extend:** Add features (e.g., file persistence) across versions.
- **Contribute:** PRs welcome—suggest topics, fix bugs, or add a "Kotlin Coroutines" bonus!

### License
MIT License – Free to fork, learn, and build upon.
