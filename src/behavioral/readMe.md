## 🔹 Strategy Pattern
The Strategy pattern is used when we have multiple ways (algorithms/strategies) to perform a task, and we want to switch between them at runtime without changing the client code.
## 🔹 Command Pattern
Command is a behavioral design pattern that turns a request into a stand-alone object that contains all information about the request(encapsulates request as an object). This transformation lets you pass requests as a method arguments, delay or queue a request’s execution, and support undoable operations.
The Command design pattern encapsulates a request as an object, allowing you to parameterize clients with different requests, queue requests, or log them.\

### Core Components of the Command Pattern
The pattern involves four main actors:\
`Command`: This is an interface (or an abstract class) that declares a single method, typically named execute(). This method is what triggers the action.\
`Concrete` Command: This class implements the Command interface. It contains a reference to a Receiver and the necessary parameters for the request. The execute() method simply calls a method on the Receiver to perform the action.\
`Receiver`: This class contains the business logic. It knows how to perform the actual work requested by the Concrete Command. The Receiver is often a specific service or component.\
`Invoker`: This object holds a Command object and, at some point, tells it to execute(). The Invoker doesn't know anything about the Concrete Command or the Receiver; it only knows about the Command interface.\
How it Works\
Imagine you're designing a text editor. You have buttons for actions like "Save," "Open," and "Print." Instead of putting the logic for each action directly in the button's code, you can use the Command pattern.\
* Define the Command Interface: Create an ICommand interface with an execute() method.
* Create Concrete Commands: For each action, create a ConcreteCommand class.
* SaveCommand will have a reference to a TextEditor (the Receiver) and, when its execute() method is called, it will call textEditor.saveFile().
* PrintCommand will hold the same TextEditor and call textEditor.printDocument().
* The Invoker: The button itself acts as the Invoker. When the "Save" button is clicked, it doesn't know how to save a file; it only knows to call command.execute() on the SaveCommand object that was assigned to it.\
## 🔹 Mediator Design Pattern
* The Mediator design pattern is a behavioral pattern that reduces coupling between components (called "colleagues") by making them communicate indirectly, through a central "mediator" object.
* defines an object how a set of other objects interact with one another./
* restricts direct communications between objects and forces them to collaborate via mediator, hence reducing dependencies between them.
Example of implementing chatbot
* Loose coupling: Users don’t need to know each other.
* Centralized control: Easy to enforce rules (e.g., block spam).
* Extensible: Add new users or rules without changing existing classes.
## 🔹 Template Method
Template Method is a behavioral design pattern that defines the skeleton of an algorithm in the superclass but lets subclasses override specific steps of the algorithm without changing its structure.\
we will give what are all common in superclass and do not give implementations to those methods which may vary and lets subclasses decide it.\
It promotes code reuse and enforces a common algorithm structure across multiple subclasses.
https://medium.com/@mehar.chand.cloud/template-design-pattern-use-case-multiple-data-serialization-methods-76d2428423eb
## Memento Pattern
The Memento pattern allows you to save and restore the previous state of an object without revealing the details of its implementation. It acts like a "snapshot" that can be stored and used to revert an object to a previous state.\
When It's Used: This pattern is very common in applications that require undo/redo functionality, such as text editors, graphic design software, or even a game where you can save and load your progress. It's also used in transaction management systems to roll back operations if an error occurs.\
Why It's Widespread: The Memento pattern directly addresses a very common and important application feature—the ability to undo an action. Its simple, clean separation of roles (Originator, Memento, and Caretaker) makes it straightforward to implement and integrate into existing systems without breaking encapsulation.\
Key Features of the Memento Design Pattern:/
1.Originator: The Originator class represents the object whose state needs to be saved and restored. It creates and retrieves Mementos to capture and restore its state./
2.Memento: The Memento class encapsulates the state of the Originator object. It provides methods for retrieving and setting the state, but it doesn’t expose its internal structure./
3.Caretaker: The Caretaker class is responsible for storing and managing the Mementos. It requests Mementos from the Originator to save the state and provides Mementos back to the Originator for state restoration./