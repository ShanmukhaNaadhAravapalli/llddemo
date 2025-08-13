package behavioural; // Changed "behavoourial" to "behavioural" - common typo

import java.util.ArrayList; // Import for ArrayList
import java.util.List;    // Import for List

// 1. Command Interface
interface Command {
    void execute(); // The core method that performs the action
    void undo();    // Optional: For undoable operations
}

// The Receiver: performs the actual work
// This is the object that knows how to perform the actual operations.
class FileSystemReceiver {
    public void openFile(String fileName) {
        System.out.println("Receiver: Opening file: " + fileName);
    }

    public void saveFile(String content) {
        System.out.println("Receiver: Saving content to file: " + content);
    }
}

// ConcreteCommand for opening a file
// This command encapsulates the request to open a file.
class OpenFileCommand implements Command {
    private FileSystemReceiver fileSystem; // The receiver of the operation
    private String fileName;               // Parameters for the action

    public OpenFileCommand(FileSystemReceiver fs, String fileName) {
        this.fileSystem = fs;
        this.fileName = fileName;
    }

    @Override
    public void execute() {
        System.out.println("Executing Command: OpenFileCommand...");
        fileSystem.openFile(fileName); // Delegate the actual work to the receiver
    }

    @Override
    public void undo() {
        // For undo, you'd need to implement logic to close the opened file,
        // or revert its state. This often requires storing the previous state.
        System.out.println("Undoing Command: OpenFileCommand - (Not fully implemented for complex state reversal)");
    }
}

// ConcreteCommand for saving a file
// This command encapsulates the request to save a file.
class SaveFileCommand implements Command {
    private FileSystemReceiver fileSystem; // The receiver of the operation
    private String content;                // Parameters for the action

    public SaveFileCommand(FileSystemReceiver fs, String content) {
        this.fileSystem = fs;
        this.content = content;
    }

    @Override
    public void execute() {
        System.out.println("Executing Command: SaveFileCommand...");
        fileSystem.saveFile(content); // Delegate the actual work to the receiver
    }

    @Override
    public void undo() {
        // Undo for save might involve deleting the saved file or reverting to a previous version.
        System.out.println("Undoing Command: SaveFileCommand - (Not fully implemented for complex state reversal)");
    }
}

// The Invoker: a generic button or menu item
// This class holds a Command object and knows when to execute it.
// It doesn't know anything about the concrete command or the receiver.
class Button {
    private Command command;

    public Button(Command command) {
        this.command = command;
    }

    public void click() {
        System.out.println("Invoker: Button clicked!");
        command.execute(); // The invoker simply triggers the command's execution
    }
}

// Another Invoker type
class MenuItem {
    private Command command;

    public MenuItem(Command command) {
        this.command = command;
    }

    public void select() {
        System.out.println("Invoker: Menu item selected!");
        command.execute(); // The invoker simply triggers the command's execution
    }
}

// The Client: responsible for creating concrete commands and setting their receivers,
// then associating them with invokers.
public class CommandDemo {
    public static void main(String[] args) {
        // The Receiver: The object that will perform the actual operations.
        FileSystemReceiver fileSystem = new FileSystemReceiver();

        // Concrete Commands: We create command objects, associating them with the receiver
        // and any necessary parameters.
        Command openCommand = new OpenFileCommand(fileSystem, "document.txt");
        Command saveCommand = new SaveFileCommand(fileSystem, "Hello, Command Pattern! This is the content.");
        Command anotherOpenCommand = new OpenFileCommand(fileSystem, "another_file.txt");


        // Invokers: We create invoker objects and give them the specific commands
        // to execute when triggered (e.g., clicked or selected).
        Button openButton = new Button(openCommand);
        MenuItem saveMenuItem = new MenuItem(saveCommand);

        // Client (main method) uses the invokers to trigger operations.
        // The client and invokers don't need to know the specifics of FileSystemReceiver.
        openButton.click();
        saveMenuItem.select();

        System.out.println("\n--- Demonstrating Queueing Commands (Macro) ---");
        // Since commands are first-class objects, we can store them in a list (a queue/history).
        List<Command> commandHistory = new ArrayList<>();
        commandHistory.add(openCommand);
        commandHistory.add(saveCommand);
        commandHistory.add(anotherOpenCommand); // Add another open command

        // Execute all commands from the history (e.g., replaying a macro)
        for (Command cmd : commandHistory) {
            cmd.execute();
        }

        System.out.println("\n--- Demonstrating Undo (Simplified) ---");
        // To implement undo, we can iterate backwards through the history.
        // In a real system, `undo()` would need to revert actual state changes.
        if (!commandHistory.isEmpty()) {
            Command lastCommand = commandHistory.get(commandHistory.size() - 1);
            System.out.println("Attempting to undo the last command: " + lastCommand.getClass().getSimpleName());
            lastCommand.undo();
            commandHistory.remove(commandHistory.size() - 1); // Remove from history after undo
        }
        if (!commandHistory.isEmpty()) {
            Command secondLastCommand = commandHistory.get(commandHistory.size() - 1);
            System.out.println("Attempting to undo the second-to-last command: " + secondLastCommand.getClass().getSimpleName());
            secondLastCommand.undo();
            commandHistory.remove(commandHistory.size() - 1);
        }
    }
}
/*
previously we could have
Benefits of Encapsulating a Request as an Object:
Parameterize Clients with Different Requests (Decoupling):

The Button and MenuItem classes (invokers) no longer know anything about FileSystemReceiver or its methods (openFile, saveFile). They only know how to execute() a Command.
You can assign any Command object to a Button or MenuItem at runtime, making them highly reusable and configurable.
Adding a new operation (e.g., "Delete File") only requires creating a new DeleteFileCommand class, not modifying existing invokers.
Queue or Log Requests:

Since commands are objects, you can store them in collections (e.g., List<Command>, Queue<Command>).
Queuing: You can build a macro recorder by storing a sequence of Command objects and replaying them later.
Logging: You can easily log every Command object executed for auditing or debugging purposes. You could even persist them to disk.
Support Undoable Operations:

Each ConcreteCommand can implement an undo() method that reverses the effect of its execute() method.
By keeping a history of executed Command objects, you can traverse back through the history and call undo() on previous commands to revert the application's state. This is incredibly powerful for features like "undo" in text editors.
Turns an Operation into a First-Class Object:

Variables: Command openCommand = new OpenFileCommand(...)
Arguments: new Button(openCommand)
Data Structures: List<Command> commandHistory
Return Values: A factory method could return different Command objects. */
