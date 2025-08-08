# Notes App

Welcome! This is a **console-based Notes App** written in Java 17+, developed as part of the Elevate Labs Java Developer Internship Task 4. The project helps practice file I/O, exception handling, and modern Java features like records, enhanced switch expressions, and try-with-resources.

---

## Features

- Add, view, list, and delete notes with unique titles  
- Supports multi-line note bodies terminated by a single dot (.)  
- Data persists in a simple text file (`notes.txt`) across sessions  
- Case-insensitive note title matching for user convenience  
- Basic input validation and informative error messages  
- Uses modern Java features: `record`, enhanced switch, `try-with-resources`  
- Simple exception logging to standard error  

---

## Getting Started

### Prerequisites

- Java 17 or later installed (tested with Java 17 and 18)  
- Code editor like VS Code or IntelliJ IDEA  
- Access to a Terminal or Command Prompt  

### How to Compile & Run

1. Save the source code as `NotesApp.java`.  
2. Open your terminal or command prompt in that directory.  
3. Compile the program using:
4. Run the application using:

   *Tip: The app will create or use the `notes.txt` file in the same folder for note storage.*

---

## Usage

- Upon running, a menu displays options to list, add, view, or delete notes, or exit the app.  
- Select an option by typing the corresponding number and pressing Enter.  
- To add a note, enter a unique title and then input the note body line by line. Complete the body by entering a single dot `.` on a new line.  
- Note titles are case-insensitive and must be unique.  
- View or delete notes by entering the exact title (case-insensitive).  
- Exiting saves changes safely to the file.

---

## Code Structure

- **NotesApp**  
- Contains the `main` method with the user interface loop and menu logic.  
- **NotesManager**  
- Handles loading, saving, and managing notes on disk.  
- Coordinates user input for note CRUD operations.  
- **Note (record)**  
- Immutable data model representing a note with `title` and `body`.

---

## Modern Java Concepts Used

- **Records:** Simple syntax for immutable data objects (`Note`).  
- **Enhanced Switch Expressions:** Clear and concise menu-driven logic.  
- **Try-with-resources:** Automatic resource management for file I/O.  
- **Streams & Optionals:** Efficient searching and data processing.  

---

## What I Learned

- Practical use of Java file I/O with `BufferedReader`/`BufferedWriter`.  
- Safe and clean exception handling strategies.  
- Applying Java 17+ features to build maintainable and readable code.  
- Designing a user-friendly console application with persistent storage.  

---

## Example Session

Welcome to the Java Notes App!
-------------------
1. List notes
2. Add note
3. View note by title
4. Delete note
5. Exit
Enter choice (1-5):
1
No notes found.

-------------------
1. List notes
2. Add note
3. View note by title
4. Delete note
5. Exit
Enter choice (1-5):
2
Enter note title: Shopping List
Enter note body (end with a single '.' in a line):
Milk
Eggs
Bread
.

Note saved!

-------------------
1. List notes
2. Add note
3. View note by title
4. Delete note
5. Exit
Enter choice (1-5):
2
Enter note title: Work Tasks
Enter note body (end with a single '.' in a line):
Finish report
Email client
Prepare slides
.

Note saved!

-------------------
1. List notes
2. Add note
3. View note by title
4. Delete note
5. Exit
Enter choice (1-5):
1
--- Notes List ---
- Shopping List
- Work Tasks

-------------------
1. List notes
2. Add note
3. View note by title
4. Delete note
5. Exit
Enter choice (1-5):
3
Enter title to view: shopping list
Title: Shopping List
Body: Milk
Eggs
Bread

-------------------
1. List notes
2. Add note
3. View note by title
4. Delete note
5. Exit
Enter choice (1-5):
3
Enter title to view: Holiday Plan
Note not found.

-------------------
1. List notes
2. Add note
3. View note by title
4. Delete note
5. Exit
Enter choice (1-5):
4
Enter title to delete: work tasks
Note deleted.

-------------------
1. List notes
2. Add note
3. View note by title
4. Delete note
5. Exit
Enter choice (1-5):
1
--- Notes List ---
- Shopping List

-------------------
1. List notes
2. Add note
3. View note by title
4. Delete note
5. Exit
Enter choice (1-5):
5
Exiting. Goodbye!


---

## FAQ / Common Errors

- **Duplicate titles:** Not allowed; each note must have a unique title.  
- **Empty titles or bodies:** Input validation prevents empty notes or titles.  
- **Note not found:** Viewing or deleting non-existent notes prompts a user-friendly message.  
- **File issues:** App handles exceptions and logs errors for file reading/writing issues.

---

## Interview Prep Questions (Cheat Sheet)

- What are the differences between `FileReader` and `BufferedReader`?  
- Explain try-with-resources and its benefits.  
- How to handle `IOException` effectively?  
- What is the difference between checked and unchecked exceptions?  
- How does file writing work in Java?  
- When to append vs overwrite files?  
- What is exception propagation and how does it work?  
- What information does a stack trace provide?  
- When and why use the `finally` block?

---

## License

This project is a learning resource created for Elevate Labs and is free to use, modify, and share.
