package structural;

// Subsystem 1
class DVDPlayer {
    public void on() {
        System.out.println("DVD Player ON");
    }
    public void play(String movie) {
        System.out.println("Playing movie: " + movie);
    }
    public void off() {
        System.out.println("DVD Player OFF");
    }
}

// Subsystem 2
class Projector {
    public void on() {
        System.out.println("Projector ON");
    }
    public void wideScreenMode() {
        System.out.println("Projector in widescreen mode");
    }
    public void off() {
        System.out.println("Projector OFF");
    }
}

// Subsystem 3
class Lights {
    public void dim() {
        System.out.println("Lights dimmed");
    }
    public void on() {
        System.out.println("Lights ON");
    }
}

// Facade
class HomeTheaterFacade {
    private DVDPlayer dvd;
    private Projector projector;
    private Lights lights;

    public HomeTheaterFacade(DVDPlayer dvd, Projector projector, Lights lights) {
        this.dvd = dvd;
        this.projector = projector;
        this.lights = lights;
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        lights.dim();
        projector.on();
        projector.wideScreenMode();
        dvd.on();
        dvd.play(movie);
    }

    public void endMovie() {
        System.out.println("Shutting movie theater down...");
        lights.on();
        projector.off();
        dvd.off();
    }
}

// Client
public class Facade {
    public static void main(String[] args) {
        DVDPlayer dvd = new DVDPlayer();
        Projector projector = new Projector();
        Lights lights = new Lights();

        HomeTheaterFacade homeTheater = new HomeTheaterFacade(dvd, projector, lights);

        homeTheater.watchMovie("Inception");
        System.out.println("--- After movie ---");
        homeTheater.endMovie();
    }
}


