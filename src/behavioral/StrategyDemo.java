package behavioral;

interface RouteStrategy {
    void navigate(String from, String to);
}
class CarStrategy implements RouteStrategy {
    public void navigate(String from, String to) {
        System.out.println("Navigating from " + from + " to " + to + " by Car");
    }
}

class BikeStrategy implements RouteStrategy {
    public void navigate(String from, String to) {
        System.out.println("Navigating from " + from + " to " + to + " by Bike");
    }
}

class TrainStrategy implements RouteStrategy {
    public void navigate(String from, String to) {
        System.out.println("Navigating from " + from + " to " + to + " by Train");
    }
}

class Navigator {
    private RouteStrategy strategy;

    // Allow changing strategy at runtime
    public void setStrategy(RouteStrategy strategy) {
        this.strategy = strategy;
    }

    public void navigate(String from, String to) {
        strategy.navigate(from, to);
    }
}


public class StrategyDemo {
    public static void main(String[] args) {
        Navigator navigator = new Navigator();

        navigator.setStrategy(new CarStrategy());
        navigator.navigate("Home", "Office");

        navigator.setStrategy(new BikeStrategy());
        navigator.navigate("Home", "Gym");

        navigator.setStrategy(new TrainStrategy());
        navigator.navigate("City A", "City B");
    }
}
