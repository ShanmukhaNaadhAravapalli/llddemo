package creational;


// refer https://www.digitalocean.com/community/tutorials/java-singleton-design-pattern-best-practices-examples
public class singleton  {
    private singleton() {}
    // Bill Pugh Singleton Implementation
    private static class SingletonHolder {
        public static final singleton instance = new singleton();
    }

    public static singleton getInstance() {
        return SingletonHolder.instance;
    }
}
/*
Notice the private inner static class that contains the instance of the singleton class.
When the singleton class is loaded, SingletonHelper class is not loaded into memory
and only when someone calls the getInstance() method, this class gets loaded and
creates the singleton class instance.
This is the most widely used approach for the singleton class as it doesn’t require synchronization.

 */
