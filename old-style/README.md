# Old-Style Java Task Manager (Pre-Java 8 OOP Edition)


## Introduction

This is the **first iteration** of a console-based Task Management System, built in **old-school Java style** (targeting Java 7/8 compatibility) as part of a learning project to explore the evolution of Java OOP practices. The goal is to demonstrate verbose, boilerplate-heavy coding from the pre-Java 8 era—think manual getters/setters, full constructors, inheritance hierarchies, and explicit resource management—while covering core OOP concepts like encapsulation, abstraction, inheritance, and more.

This project simulates enterprise Java from the 2000s-2010s: no lambdas, streams, records, or modern sugar—just raw classes, interfaces, and enums. It's designed to feel "clunky" on purpose, highlighting why Java evolved (e.g., Lombok for boilerplate, records for immutables). Later iterations (hybrid and modern) will refactor the same features with transitional and cutting-edge practices.
**Intention:** Build muscle memory for reading/maintaining legacy codebases while appreciating how Java has streamlined OOP for cleaner, more maintainable backends.

## Features

- **User Management:** Create, view, and delete users (with basic validation for name/email).
- **Task Management:** Create, view, assign to users, and delete tasks (in-memory storage).
- **Task Types:** General tasks with inheritance for "PersonalTask" (e.g., added notes).
- **Validation & Processing:** Basic checks (e.g., title required) and task processing workflows.
- **Console I/O:** Simple menu-driven interface using `Scanner` for inputs.
- **OOP Coverage:** Explicitly demonstrates 22 OOP topics (e.g., method chaining via manual builders, dynamic binding via overrides, pass-by-value/         reference examples).

No database—everything's in-memory for simplicity. Total ~750 LOC.

## Tech Stack

- **Java:** 1.8 (JDK 8)
- **Build Tool:** Maven (for compilation and execution)
- **Testing:** Basic JUnit 3.8.1 (in `src/test/java`)
- **No External Libs:** Pure core Java (beyond JUnit for tests)

## Project Structure

```text
old-style/
├── .vscode/                 # VS Code settings (optional)
│   └── settings.json
├── pom.xml                  # Maven config (Java 8 target, exec plugin)
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── bitguy/ # Base package
│   │               ├── main/
│   │               │   └── App.java          # Entry point (renamed from Main for package)
│   │               ├── model/
│   │               │   ├── PersonalTask.java # Inheritance & overriding
│   │               │   ├── Task.java         # Abstract base class
│   │               │   ├── TaskDTO.java      # Simulated record (verbose immutable)
│   │               │   ├── TaskStatus.java   # Enums
│   │               │   └── User.java         # Encapsulation & attributes
│   │               ├── service/
│   │               │   ├── TaskRepository.java # Interface
│   │               │   ├── TaskService.java  # Core service (DI, overloading)
│   │               │   └── UserService.java  # User CRUD
│   │               └── util/
│   │                   └── TaskBuilder.java  # Method chaining & nested classes
│   └── test/
│       └── java/
│           └── com/
│               └── bitguy/
│                   └── AppTest.java         # Basic JUnit test
└── target/                 # Maven build output (auto-generated)
    ├── classes/           # Compiled .class files
    └── test-classes/
```

- **Packages:** Organized by layer (`model` for entities, `service` for logic, `util` for helpers, `main` for entry).
- **Key Demos:** Check inline comments in code for OOP topic explanations (e.g., `finalize()` for object lifecycle).

## Setup Instructions

### Prerequisites

- **Java JDK 8:** Download OpenJDK 8 or Oracle [JDK 8](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html). Verify: `java       -version` (should show 1.8.x).
- **Maven 3.6+:** Install via [Apache Maven](https://maven.apache.org/download.cgi) or package manager (e.g., macOS: `brew install maven`; Ubuntu: `sudo  apt install maven`). Verify: `mvn -version`.
- **IDE (Recommended):** IntelliJ IDEA Community, VS Code (with Java Extension Pack), or Eclipse for editing/running.
- **Terminal/Command Prompt:** For Maven commands.

## Step 1: Clone or Set Up the Project

- If from GitHub: `git clone <repo-url> && cd old-style`.
- Manual Setup:
  - Create a folder: `mkdir old-style && cd old-style`.
  - Copy the provided `pom.xml` to the root.
  - Recreate the directory tree (use `mkdir -p` for nested folders).
  - Copy all `.java` files into their respective paths (e.g., `src/main/java/com/bitguy/main/App.java`).
  - (Optional) Add the `.vscode/settings.json` for VS Code Java support.

## Step 2: Compile

- Run: `mvn clean compile`
- This builds to `target/classes/` and downloads any deps (just JUnit).
- Success: `[INFO] BUILD SUCCESS`. Errors? Check Java version or package/folder mismatches.

## Step 3: Run the App

- Primary Command: `mvn exec:java`
  - This executes the `main` method in `com.bitguy.main.App` via the exec plugin.

- Alternative (Manual):
  - `mvn clean compile` (if not done).
  - `java -cp target/classes com.bitguy.main.App`

- In IDE: Open the project folder → Right-click `App.java` → Run.

The app starts a menu: Choose options (1-7) to interact.

## Step 4: Test (Optional)

- Run tests: mvn test
- Executes AppTest.java (basic smoke test).

## Troubleshooting

- **"Main class not found":** Ensure `<mainClass>com.bitguy.main.App</mainClass>` in `pom.xml` matches your entry file.
- **Java Version Mismatch:** Set `JAVA_HOME` to JDK 8 path (e.g., `export JAVA_HOME=/path/to/jdk1.8`).
- **Clean Build:** `mvn clean` to reset `target/`.
- **No Maven?:** Use `javac` manually: `javac -d target/classes src/main/java/com/bitguy/**/*.java`, then `java -cp target/classes com.bitguy.main.App`.

## Usage Example

1. Run: `mvn exec:java`
2. Output starts: `=== Old Java Task Manager ===` (plus "Task class loaded" from static init).
3. Menu:

```text
1. Create User
2. View Users
3. Create Task
...
Choose: 1
Name: Alice
Email: alice@email.com
Created: Alice
```
4. Create a task (3), assign (5), view (4), delete (6), exit (7).
5. On exit: Sees finalize() cleanup messages (object lifecycle demo).

## Next Steps in the Evolution Series

- **Hybrid Style (Java 11-17):** Blends old verbosity with lambdas, records for DTOs.
- **Modern Style (Java 21+):** Immutable records, sealed classes, concise best practices.
- Compare versions side-by-side to see LOC reductions and readability gains!

## Contributing / License

This is a personal learning repo—fork and experiment! MIT License.