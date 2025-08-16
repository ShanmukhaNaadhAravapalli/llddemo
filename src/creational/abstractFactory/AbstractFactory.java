package creational.abstractFactory;
// https://www.tutorialspoint.com/design_pattern/abstract_factory_pattern.htm
interface Chair {
    void sitOn();
}

interface Table {
    void placeStuff();
}

class VictorianChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("Sitting on a Victorian chair.");
    }
}

class VictorianTable implements Table{
    @Override
    public void placeStuff(){
        System.out.println("placing stuff on a Victorian table.");
    }
}


class ModernChair implements Chair {
    @Override
    public void sitOn() {
        System.out.println("Sitting on a Modern chair.");
    }
}


class ModernTable implements Table {
    @Override
    public void placeStuff() {
        System.out.println("Placing stuff on a Modern table.");
    }
}
// Abstract Factory
interface FurnitureFactory {
    Chair createChair();
    Table createTable();
}

// Concrete Factory 1
class VictorianFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new VictorianChair();
    }

    @Override
    public Table createTable() {
        return new VictorianTable();
    }
}

// Concrete Factory 2
class ModernFurnitureFactory implements FurnitureFactory {
    @Override
    public Chair createChair() {
        return new ModernChair();
    }

    @Override
    public Table createTable() {
        return new ModernTable();
    }
}
class Client {
    private final Chair chair;
    private final Table table;

    public Client(FurnitureFactory factory) {
        this.chair = factory.createChair();
        this.table = factory.createTable();
    }

    public void useFurniture() {
        chair.sitOn();
        table.placeStuff();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        FurnitureFactory victorianFactory = new VictorianFurnitureFactory();
        Client victorianClient = new Client(victorianFactory);
        System.out.println("Creating a Victorian set:");
        victorianClient.useFurniture();

        System.out.println("--------------------");

        // Create a Modern furniture set
        FurnitureFactory modernFactory = new ModernFurnitureFactory();
        Client modernClient = new Client(modernFactory);
        System.out.println("Creating a Modern set:");
        modernClient.useFurniture();
    }
}
