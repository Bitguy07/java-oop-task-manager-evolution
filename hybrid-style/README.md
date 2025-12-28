# Hybrid-Style Java Task Manager (Java 11-17 OOP Hybrid Edition)


## Introduction

This is the **second iteration** of a console-based Task Management System, built in a **hybrid old + new Java style** (targeting Java 11-17 compatibility) as part of a personal learning project to deeply explore the evolution of Java OOP practices. The intention is to master Java's progression firsthand: starting from verbose pre-Java 8 code, transitioning through this mixed phase (blending boilerplate with modern features like records for DTOs, limited lambdas/streams, var inference, and default interface methods), and culminating in fully modern Java 21+. By coding the same project three times, I aim to understand legacy codebases (for enterprise adaptability), appreciate transitional efficiencies, and adopt cutting-edge best practices for clean, maintainable backend development.
This version evolves the old-style by reducing some verbosity (e.g., records auto-generate equals/hashCode/toString, streams for selective filtering) while retaining core "old" elements (manual getters/setters on entities, full constructors, loops where streams aren't forced). It highlights how Java 8-17 bridged the gap: safer resources (try-with-resources), functional touches (lambdas), and immutability hints (sealed classes). Total ~680 LOC—notice the slimming from the old version's ~750.

**Intention:** Gain fluency in mixed-era code (common in mid-2010s-2020s projects) to read/upgrade real-world systems, while seeing iterative improvements in readability and safety.

## Features

- **User Management:** Create, view, and delete users (with basic validation for name/email).
- **Task Management:** Create, view, assign to users, and delete tasks (in-memory storage).
- **Task Types:** General tasks with inheritance for "PersonalTask" (e.g., added notes), using sealed classes for controlled hierarchy.
- **Enhanced Filtering:** Basic status-based task filtering (using streams selectively—hybrid feature).
- **Validation & Processing:** Basic checks (e.g., title required) and polymorphic task processing.
- **Console I/O:** Menu-driven interface using `Scanner` with try-with-resources for auto-closure.
- **OOP Coverage:** Explicitly demonstrates 22 OOP topics (e.g., method chaining via manual builders with var, dynamic binding via overrides with streams, pass-by-value/reference in hybrid methods).

No database—everything's in-memory. Added filtering evolves the old version without full modernization.

## Tech Stack

- **Java:** 17 (JDK 17—supports records, sealed classes, var)
- **Build Tool:** Maven (for compilation and execution)
- **Testing:** Basic JUnit 3.8.1 (in `src/test/java`)
- **No External Libs:** Pure core Java (beyond JUnit for tests; uses `java.util.stream` for hybrids)

## Project Structure

```text
hybrid-style/
├── pom.xml                  # Maven config (Java 17 target, exec plugin)
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── bitguy/ # Base package
│   │               ├── config/
│   │               │   └── ConfigFactory.java  # DI factory (mixed injection)
│   │               ├── main/
│   │               │   └── App.java            # Entry point
│   │               ├── model/
│   │               │   ├── PersonalTask.java   # Inheritance & overriding
│   │               │   ├── Task.java           # Abstract sealed base
│   │               │   ├── TaskResponse.java   # Record for DTO (new)
│   │               │   ├── TaskStatus.java     # Enums with custom methods
│   │               │   └── User.java           # Encapsulation & attributes
│   │               ├── service/
│   │               │   ├── TaskRepository.java # Interface with defaults
│   │               │   ├── TaskService.java    # Core service (overloading, binding)
│   │               │   └── UserService.java    # User CRUD with light streams
│   │               └── util/
│   │                   └── TaskBuilder.java    # Chaining & nested classes with lambdas
│   └── test/
│       └── java/
│           └── com/
│               └── bitguy/
│                   └── AppTest.java           # Basic JUnit test
└── target/                 # Maven build output (auto-generated)
    ├── classes/           # Compiled .class files
    └── test-classes/
```

- **Packages:** Layered organization (`model` for entities, `service` for logic, `util` for helpers, `config` for DI wiring, `main` for entry).
- **Key Demos:** Inline comments explain OOP topics and old/new transitions (e.g., streams as "new" evolution from loops).

## Setup Instructions

### Prerequisites

- **Java JDK 17:** Download OpenJDK 17 or Oracle [JDK 17](https://www.oracle.com/java/technologies/downloads/?#java17). Verify: `java -version` (should show 17.x).
- **Maven 3.6+:** Install via [Apache Maven](https://maven.apache.org/download.cgi) or package manager (e.g., macOS: `brew install maven`; Ubuntu: `sudo apt install maven`). Verify: `mvn -version`.
- **IDE (Recommended):** IntelliJ IDEA Community, VS Code (with Java Extension Pack), or Eclipse for editing/running.
- **Terminal/Command Prompt:** For Maven commands

## Step 1: Clone or Set Up the Project

- If from GitHub: `git clone <repo-url> && cd hybrid-style`.
- Manual Setup:
  - Create a folder: `mkdir hybrid-style && cd hybrid-style`.
  - Copy the provided `pom.xml` to the root.
  - Recreate the directory tree (use `mkdir -p` for nested folders).
  - Copy all `.java` files into their respective paths (e.g., `src/main/java/com/bitguy/main/App.java`).
  - (Optional) Add IDE settings (e.g., .`vscode/settings.json` for VS Code Java support).

## Step 2: Compile

- Run: `mvn clean compile`
- This builds to `target/classes/` and downloads deps (just JUnit).
- Success: `[INFO] BUILD SUCCESS.` Errors? Check Java version or package/folder mismatches.

## Step 3: Run the App

- Primary Command: `mvn exec:java`
  - This executes the `main` method in `com.bitguy.main.App` via the exec plugin.

- Alternative (Manual):
  - `mvn clean compile` (if not done).
  - `java -cp target/classes com.bitguy.main.App`

- In IDE: Open the project folder → Right-click `App.java` → Run.

The app starts a menu: Choose options (1-8) to interact. Note: Prompts use `println` for line breaks—inputs follow immediately.

## Step 4: Test (Optional)

- Run tests: `mvn test`
- Executes `AppTest.java` (basic smoke test).

## Troubleshooting

- **"Main class not found":** Ensure `<mainClass>com.bitguy.main.App</mainClass>` in `pom.xml` matches your entry file.
- **Java Version Mismatch:** Set `JAVA_HOME` to JDK 17 path (e.g., `export JAVA_HOME=/path/to/jdk-17`).
- **Clean Build:** `mvn clean` to reset `target/`.
- **No Maven?:** Use `javac` manually: `find src -name "*.java" | xargs javac -d target/classes`, then java -cp target/classes com.bitguy.main.App.
- **Input Issues:** If prompts seem "empty" (e.g., extra newlines), it's due to `println`—type after the cursor.

## Usage Example

1. Run: `mvn exec:java`
2. Output starts: `=== Mixed Java Task Manager ===` (plus "Task loaded" from static init).
3. Menu:

```text
1. Create User
2. View Users
3. Create Task
4. View Tasks
5. Filter Tasks by Status
6. Assign Task
7. Delete Task
8. Exit
Choose: 1
Name: 
Bob
Email: 
bob@email.com
Created: Bob
```
4. Create a task (3), view (4), filter (5—e.g., input "PENDING" to see matches), assign (6—lists tasks/users for selection), delete (7), exit (8).
5. On exit: "Shutdown completed." (try-with-resources auto-closes Scanner).

## Next Steps in the Evolution Series

- **Old Style (Pre-Java 8):** Verbose baseline (~750 LOC, manual everything).
- **Modern Style (Java 21+):** Full immutables, pattern matching, concise code (~500 LOC).
- Compare versions: Run side-by-side to spot reductions (e.g., records cut DTO boilerplate) and run diffs for OOP evolutions.

## Contributing / License

This is a personal learning repo—fork and experiment! MIT License.