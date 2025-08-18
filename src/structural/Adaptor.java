package structural;
/* https://medium.com/@akshatsharma0610/adapter-design-pattern-in-java-fa20d6df25b8 */
// ❌ Without Adapter Pattern
// Existing interface
interface MediaPlayer {
    void play(String audioType, String fileName);
}

// Only supports mp3
class AudioPlayer implements MediaPlayer {
    @Override
    public void play(String audioType, String fileName) {
        if(audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file: " + fileName);
        } else if(audioType.equalsIgnoreCase("mp4")) {
            System.out.println("Playing mp4 file: " + fileName);
        } else if(audioType.equalsIgnoreCase("vlc")) {
            System.out.println("Playing vlc file: " + fileName);
        } else {
            System.out.println("Invalid media. " + audioType + " format not supported");
        }
    }
}
//With Adapter Pattern
// 1. target
interface DatabaseAdapter {
    void connect();
    void execute(String query);
}

// 2. adaptees
// MySQL API
class MySQLDatabase {
    public void executeSQL(String sql) {
        System.out.println("Executing SQL on MySQL: " + sql);
    }
}

// MongoDB API
class MongoDBDatabase {
    public void executeQuery(String jsonQuery) {
        System.out.println("Executing JSON query on MongoDB: " + jsonQuery);
    }
}

// MySQL Adapter
class MySQLAdapter implements DatabaseAdapter {
    private MySQLDatabase mySQLDatabase;

    public MySQLAdapter() {
        this.mySQLDatabase = new MySQLDatabase();
    }

    @Override
    public void connect() {
        System.out.println("Connected to MySQL Database");
    }

    @Override
    public void execute(String query) {
        mySQLDatabase.executeSQL(query); // adapts method
    }
}

// MongoDB Adapter
class MongoDBAdapter implements DatabaseAdapter {
    private MongoDBDatabase mongoDBDatabase;

    public MongoDBAdapter() {
        this.mongoDBDatabase = new MongoDBDatabase();
    }

    @Override
    public void connect() {
        System.out.println("Connected to MongoDB Database");
    }

    @Override
    public void execute(String query) {
        mongoDBDatabase.executeQuery(query); // adapts method
    }
}

public class Adaptor {
    public static void main(String[] args) {
        DatabaseAdapter db = new MySQLAdapter();
        db.connect();
        db.execute("SELECT * FROM users");

        // Switch to MongoDB
        db = new MongoDBAdapter();
        db.connect();
        db.execute("{ find: 'users' }");
    }
}

/*
Adapter design pattern is one of the structural design pattern
it is used so that two unrelated interfaces can work together.
best example: https://github.com/geekific-official/geekific-youtube/tree/main/design-patterns/structural-adapter/src/main/java/com/youtube/geekific
In an object adapter, the Adapter class implements the Target interface and contains an instance of the Adaptee class
 */