package behavioral;

// State interface
interface PhoneState {
    void pressPowerButton(Phone phone);
    void pressLockButton(Phone phone);
    void usePhone(Phone phone);
}

// Concrete states
class OffState implements PhoneState {
    @Override
    public void pressPowerButton(Phone phone) {
        phone.setState(new OnState());
        System.out.println("Phone is now ON.");
    }

    @Override
    public void pressLockButton(Phone phone) {
        System.out.println("Phone is OFF. Cannot lock.");
    }

    @Override
    public void usePhone(Phone phone) {
        System.out.println("Phone is OFF. Turn it ON first.");
    }
}

class OnState implements PhoneState {
    @Override
    public void pressPowerButton(Phone phone) {
        phone.setState(new OffState());
        System.out.println("Phone is now OFF.");
    }

    @Override
    public void pressLockButton(Phone phone) {
        phone.setState(new LockedState());
        System.out.println("Phone is now LOCKED.");
    }

    @Override
    public void usePhone(Phone phone) {
        System.out.println("Phone is READY to use.");
    }
}

class LockedState implements PhoneState {
    @Override
    public void pressPowerButton(Phone phone) {
        phone.setState(new OffState());
        System.out.println("Phone is now OFF.");
    }

    @Override
    public void pressLockButton(Phone phone) {
        phone.setState(new OnState());
        System.out.println("Phone is now UNLOCKED.");
    }

    @Override
    public void usePhone(Phone phone) {
        System.out.println("Phone is LOCKED. Unlock to use.");
    }
}

// Context class
class Phone {
    private PhoneState state;

    public Phone() {
        this.state = new OffState(); // Initial state
    }

    public void setState(PhoneState state) {
        this.state = state;
    }

    public void pressPowerButton() {
        state.pressPowerButton(this);
    }

    public void pressLockButton() {
        state.pressLockButton(this);
    }

    public void usePhone() {
        state.usePhone(this);
    }
}

// Main class to demonstrate the State Design Pattern
public class StateDesignDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();

        // Trying to use the phone when it's off
        phone.usePhone();

        // Powering on the phone
        phone.pressPowerButton();
        phone.usePhone();

        // Locking the phone
        phone.pressLockButton();
        phone.usePhone();

        // Unlocking the phone
        phone.pressLockButton();
        phone.usePhone();

        // Powering off the phone
        phone.pressPowerButton();
        phone.usePhone();
    }
}

/*
Use the State pattern when you have an object that behaves differently depending on its current state, the number of states is enormous, and the state-specific code changes frequently.
The State Design Pattern is a behavioral design pattern that allows an object to alter its behavior when its internal state changes.
The object will appear to change its class. This pattern is particularly useful when an object can be in multiple states, and the behavior of the object changes based on its current state.
example with command design pattern
class Phone {
    private String state;

    public Phone() {
        this.state = "Off"; // Initial state
    }

    public void pressPowerButton() {
        if (state.equals("Off")) {
            state = "On";
            System.out.println("Phone is now ON.");
        } else if (state.equals("On")) {
            state = "Off";
            System.out.println("Phone is now OFF.");
        }
    }

    public void pressLockButton() {
        if (state.equals("On")) {
            if (state.equals("Locked")) {
                state = "Ready";
                System.out.println("Phone is now UNLOCKED.");
            } else {
                state = "Locked";
                System.out.println("Phone is now LOCKED.");
            }
        }
    }

    public void usePhone() {
        if (state.equals("Ready")) {
            System.out.println("Using the phone...");
        } else if (state.equals("Locked")) {
            System.out.println("Phone is locked. Unlock to use.");
        } else if (state.equals("Off")) {
            System.out.println("Phone is off. Turn it on first.");
        }
    }
}
Issues with the Above Implementation
Complexity: As the number of states increases, the number of conditional statements grows, making the code harder to maintain.
Scalability: Adding new states or changing existing behavior requires modifying the existing code, which can lead to bugs
Separation of Concerns: Each state is encapsulated in its own class, making the code easier to manage.
Scalability: Adding new states or changing existing behavior can be done by creating new state classes without modifying existing code.
Cleaner Code: Reduces the complexity of conditional statements, leading to cleaner and more maintainable code.
 */
