package structural;

import java.util.ArrayList;
import java.util.List;

interface Box{
    double calculatePrice();
}
abstract class Product implements Box{
    protected final String title;
    protected final double price;
    public Product(String title, double price) {
        this.title = title;
        this.price = price;
    }
}
class SimpleProduct extends Product {
    public SimpleProduct(String title, double price){
        super(title, price);
    }
    public double calculatePrice(){
        return this.price;
    }
}
class DiscountedProduct extends Product {
    public double discountPercentage;
    public DiscountedProduct(String title, double price, double discountPercentage){
        super(title, price);
        this.discountPercentage = discountPercentage;
    }
    public double calculatePrice(){
        return this.price * (1 - discountPercentage / 100);
    }
}
class CompositeBox implements Box{
    private final List<Box> children= new ArrayList<>();
    public CompositeBox(List<Box> boxes){
        children.addAll(boxes);
    }
    public double calculatePrice(){
        return children.stream().mapToDouble(Box:: calculatePrice).sum();
    }
}
public class Composite {
    public static void main(String[] args) {
        SimpleProduct book = new SimpleProduct("Book", 300);
        DiscountedProduct headphone = new DiscountedProduct("Headphones", 2000, 10);
        SimpleProduct pen = new SimpleProduct("Pen", 50);

        List<Box> smallBoxItems = new ArrayList<>();
        smallBoxItems.add(book);
        smallBoxItems.add(pen);
        CompositeBox smallBox = new CompositeBox(smallBoxItems);

        List<Box> bigBoxItems = new ArrayList<>();
        bigBoxItems.add(headphone);
        bigBoxItems.add(new SimpleProduct("Notebook", 120));
        bigBoxItems.add(new DiscountedProduct("Charger", 500, 20));
        bigBoxItems.add(smallBox); // Composite within composite

        CompositeBox bigBox = new CompositeBox(bigBoxItems);

        System.out.println("Total price of big box: ₹" + bigBox.calculatePrice());
    }
}

/*
Unified Structure: Simplifies code by handling both individual objects and collections uniformly.
Scalability: Easily extendable to add new components, departments, or elements without changing the client code.
Flexibility: Since individual objects and composites are treated similarly, adding or removing components becomes seamless.
https://medium.com/code-and-concepts/part-15-design-patterns-composite-pattern-402616b970b6
 */
