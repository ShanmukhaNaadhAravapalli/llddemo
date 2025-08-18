package structural;
// https://refactoring.guru/design-patterns/bridge
// Implementor
interface DrawingAPI {
    void drawCircle(int x, int y, int radius);
}

// Concrete Implementors
class OpenGLAPI implements DrawingAPI {
    public void drawCircle(int x, int y, int radius) {
        System.out.println("Drawing Circle using OpenGL at (" + x + "," + y + ") radius " + radius);
    }
}

class DirectXAPI implements DrawingAPI {
    public void drawCircle(int x, int y, int radius) {
        System.out.println("Drawing Circle using DirectX at (" + x + "," + y + ") radius " + radius);
    }
}

// Abstraction
abstract class Shape {
    protected DrawingAPI drawingAPI;

    protected Shape(DrawingAPI drawingAPI) {
        this.drawingAPI = drawingAPI;
    }

    public abstract void draw();
}

// Refined Abstraction
class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawingAPI drawingAPI) {
        super(drawingAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawingAPI.drawCircle(x, y, radius);
    }
}

public class Bridge {
    public static void main(String[] args) {
        Shape circle1 = new Circle(10, 20, 5, new OpenGLAPI());
        Shape circle2 = new Circle(15, 30, 10, new DirectXAPI());

        circle1.draw(); // OpenGL rendering
        circle2.draw(); // DirectX rendering
    }
}
