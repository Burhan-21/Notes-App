import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class NotesApp {

    private static final String NOTES_FILE = "notes.txt";

    // Note record to model a note
    public record Note(String title, String body) implements Serializable {
        @Override
        public String toString() {
            return "Title: " + title + System.lineSeparator() + "Body: " + body;
        }
    }

    public static void main(String[] args) {
        NotesManager manager = new NotesManager(NOTES_FILE);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Java Notes App!");
        while (true) {
            System.out.println("""
                    -------------------
                    1. List notes
                    2. Add note
                    3. View note by title
                    4. Delete note
                    5. Exit
                    Enter choice (1-5):
                    """);
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> manager.listNotes();
                case "2" -> manager.addNote(scanner);
                case "3" -> manager.viewNote(scanner);
                case "4" -> manager.deleteNote(scanner);
                case "5" -> {
                    System.out.println("Exiting. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }
    }
}

/**
 * Manages reading/writing notes.
 */
class NotesManager {

    private final Path notesPath;

    public NotesManager(String fileName) {
        this.notesPath = Paths.get(fileName);
        // Create file if not exists
        try {
            if (!Files.exists(notesPath)) {
                Files.createFile(notesPath);
            }
        } catch (IOException e) {
            logException(e);
        }
    }

    /**
     * Display all note titles.
     */
    public void listNotes() {
        List<NotesApp.Note> notes = loadNotes();
        if (notes.isEmpty()) {
            System.out.println("No notes found.");
            return;
        }
        System.out.println("--- Notes List ---");
        notes.forEach(note -> System.out.println("- " + note.title()));
    }

    /**
     * Adds a new note.
     */
    public void addNote(Scanner scanner) {
        System.out.print("Enter note title: ");
        String title = scanner.nextLine().strip();
        if (title.isEmpty()) {
            System.out.println("Title cannot be empty!");
            return;
        }
        if (hasTitle(title)) {
            System.out.println("A note with this title already exists!");
            return;
        }
        System.out.println("Enter note body (end with a single '.' in a line):");
        StringBuilder body = new StringBuilder();
        String line;
        while (!(line = scanner.nextLine()).equals(".")) {
            body.append(line).append(System.lineSeparator());
        }
        NotesApp.Note note = new NotesApp.Note(title, body.toString().strip());
        var notes = loadNotes();
        notes.add(note);
        saveNotes(notes);
        System.out.println("Note saved!");
    }

    /**
     * Views a note by title.
     */
    public void viewNote(Scanner scanner) {
        System.out.print("Enter title to view: ");
        String title = scanner.nextLine().trim();
        Optional<NotesApp.Note> note = loadNotes().stream()
                .filter(n -> n.title().equalsIgnoreCase(title))
                .findFirst();
        note.ifPresentOrElse(
                n -> System.out.println(n),
                () -> System.out.println("Note not found.")
        );
    }

    /**
     * Delete a note by title.
     */
    public void deleteNote(Scanner scanner) {
        System.out.print("Enter title to delete: ");
        String title = scanner.nextLine().trim();
        List<NotesApp.Note> notes = loadNotes();
        List<NotesApp.Note> filtered = notes.stream()
                .filter(n -> !n.title().equalsIgnoreCase(title))
                .collect(Collectors.toList());
        if (filtered.size() == notes.size()) {
            System.out.println("No such note found.");
        } else {
            saveNotes(filtered);
            System.out.println("Note deleted.");
        }
    }

    /**
     * Loads notes from file.
     */
    private List<NotesApp.Note> loadNotes() {
        List<NotesApp.Note> notes = new ArrayList<>();
        if (!Files.exists(notesPath)) return notes;
        try (BufferedReader reader = Files.newBufferedReader(notesPath)) {
            String line;
            String currentTitle = null;
            StringBuilder currentBody = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("## ")) { // title marker
                    if (currentTitle != null) { // save previous
                        notes.add(new NotesApp.Note(currentTitle, currentBody.toString().strip()));
                    }
                    currentTitle = line.substring(3).strip();
                    currentBody = new StringBuilder();
                } else {
                    currentBody.append(line).append(System.lineSeparator());
                }
            }
            if (currentTitle != null) { // last note
                notes.add(new NotesApp.Note(currentTitle, currentBody.toString().strip()));
            }
        } catch (IOException e) {
            logException(e);
        }
        return notes;
    }

    /**
     * Writes all notes to file.
     */
    private void saveNotes(List<NotesApp.Note> notes) {
        try (BufferedWriter writer = Files.newBufferedWriter(notesPath)) {
            for (NotesApp.Note note : notes) {
                writer.write("## " + note.title());
                writer.newLine();
                writer.write(note.body());
                writer.newLine();
            }
        } catch (IOException e) {
            logException(e);
        }
    }

    private boolean hasTitle(String title) {
        return loadNotes().stream().anyMatch(n -> n.title().equalsIgnoreCase(title));
    }

    /**
     * Logs exception, prints stack trace.
     */
    static void logException(Exception e) {
        System.err.println("Error: " + e.getMessage());
        e.printStackTrace(System.err);
        // In a real-world project: use logging libraries like java.util.logging or SLF4J
    }
}
