package behavioral;

import java.util.ArrayList;
import java.util.List;

// Observer
// Step 1: Observer interface
interface Observer {
    void update(String videoTitle);
}

// Step 2: Subject interface
interface Subject {
    void subscribe(Observer o);
    void unsubscribe(Observer o);
    void notifyObservers(String videoTitle);
}

// Step 3: Concrete Subject
class YouTubeChannel implements Subject {
    private List<Observer> subscribers = new ArrayList<>();

    @Override
    public void subscribe(Observer o) {
        subscribers.add(o);
    }

    @Override
    public void unsubscribe(Observer o) {
        subscribers.remove(o);
    }

    @Override
    public void notifyObservers(String videoTitle) {
        for (Observer o : subscribers) {
            o.update(videoTitle);
        }
    }

    public void uploadVideo(String title) {
        System.out.println("Channel uploaded: " + title);
        notifyObservers(title);
    }
}

// Step 4: Concrete Observers
class Subscriber implements Observer {
    private String name;
    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String videoTitle) {
        System.out.println(name + " got notified: New video -> " + videoTitle);
    }
}

// Step 5: Demo



public class ObserverDemo {
    public static void main(String[] args) {
        YouTubeChannel channel = new YouTubeChannel();

        Observer alice = new Subscriber("Alice");
        Observer bob = new Subscriber("Bob");
        Observer charlie = new Subscriber("Charlie");

        channel.subscribe(alice);
        channel.subscribe(bob);
        channel.subscribe(charlie);

        channel.uploadVideo("Observer Design Pattern in Java");

        channel.unsubscribe(bob);

        channel.uploadVideo("Decorator Pattern Explained");
    }
}
