package behavioral;
import java.util.List;
import java.util.ArrayList;
interface ChatMediator {
    void sendMessage(String msg, User user);
    void addUser(User user);
    void removeUser(User user);
}

class ChatRoom implements ChatMediator {
    private List<User> users = new ArrayList<>();
    @Override
    public void sendMessage(String msg, User sender){
        for(User user: users){
            if(user!= sender)
                user.send(msg);
        }
    }
    @Override
    public void addUser(User user){
        users.add(user);
    }
    @Override
    public void removeUser(User user){
        users.remove(user);
    }

}
abstract class User {
    protected ChatMediator mediator;
    protected String name;

    public User(ChatMediator mediator, String name) {
        this.mediator = mediator;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract void send(String msg);
    public abstract void receive(String msg, String sender);
}

class ChatUser extends User {
    public ChatUser(ChatMediator mediator, String name) {
        super(mediator, name);
    }

    @Override
    public void send(String msg) {
        System.out.println(this.name + " sends: " + msg);
        mediator.sendMessage(msg, this);
    }

    @Override
    public void receive(String msg, String sender) {
        System.out.println(this.name + " receives from " + sender + ": " + msg);
    }

}


public class Mediator {
    public static void main(String[] args) {
        ChatMediator chatRoom = new ChatRoom();

        User u1 = new ChatUser(chatRoom, "Alice");
        User u2 = new ChatUser(chatRoom, "Bob");
        User u3 = new ChatUser(chatRoom, "Charlie");

        chatRoom.addUser(u1);
        chatRoom.addUser(u2);
        chatRoom.addUser(u3);

        u1.send("Hello everyone!");
        u2.send("Hi Alice!");
    }
}
