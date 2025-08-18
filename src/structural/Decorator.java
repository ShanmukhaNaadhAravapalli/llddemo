package structural;
// Component Interface
interface Pizza {
    String getDescription();
    double getCost();
}

// Concrete Component
class PlainPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Plain Pizza";
    }

    @Override
    public double getCost() {
        return 5.0;
    }
}

// Decorator
abstract class PizzaDecorator implements Pizza {
    protected Pizza decoratedPizza;

    public PizzaDecorator(Pizza pizza) {
        this.decoratedPizza = pizza;
    }
}

// Concrete Decorators
class CheeseDecorator extends PizzaDecorator {
    public CheeseDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return decoratedPizza.getDescription() + ", Cheese";
    }

    @Override
    public double getCost() {
        return decoratedPizza.getCost() + 1.5;
    }
}

class PepperoniDecorator extends PizzaDecorator {
    public PepperoniDecorator(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDescription() {
        return decoratedPizza.getDescription() + ", Pepperoni";
    }

    @Override
    public double getCost() {
        return decoratedPizza.getCost() + 2.0;
    }
}

public class Decorator {
    public static void main(String[] args) {
        // Create a plain pizza
        Pizza pizza = new PlainPizza();

        // Decorate the pizza with cheese and pepperoni
        Pizza pizza1 = new CheeseDecorator(pizza);
        Pizza pizza2 = new PepperoniDecorator(pizza1);

        // Get the description and cost of the decorated pizza
        System.out.println("Description: " + pizza1.getDescription());
        System.out.println("Cost: $" + pizza1.getCost());

        System.out.println("Description: " + pizza2.getDescription());
        System.out.println("Cost: $" + pizza2.getCost());
    }
}
// https://github.com/geekific-official/geekific-youtube/tree/main/design-patterns/structural-decorator/src/main/java/com/youtube/geekific