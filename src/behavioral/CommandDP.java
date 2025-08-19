package behavioral;
// Receiver
// 🚫 Without ExecCommand Pattern
class Light {
    public void on() { System.out.println("Light is ON"); }
    public void off() { System.out.println("Light is OFF"); }
}

class MusicSystem {
    public void play() { System.out.println("Music is PLAYING"); }
    public void stop() { System.out.println("Music is STOPPED"); }
}

// Remote control that directly calls methods
class RemoteControl {
    private Light light; // RemoteControl must know all receivers (Light, MusicSystem, etc.) and their actions.
    private MusicSystem music;

    public RemoteControl(Light light, MusicSystem music) {
        this.light = light;
        this.music = music;
    }

    public void pressButton(String action) {      // Every new device/action requires modifying RemoteControl. Hard to add undo/redo, macros, or dynamic assignment of actions.
        if (action.equals("lightOn")) light.on();
        else if (action.equals("lightOff")) light.off();
        else if (action.equals("musicPlay")) music.play();
        else if (action.equals("musicStop")) music.stop();    // You must change existing code for new functionality.
    }
}

class WithoutCommand {
    public static void main(String[] args) {
        Light light = new Light();
        MusicSystem music = new MusicSystem();
        RemoteControl remote = new RemoteControl(light, music);

        remote.pressButton("lightOn");
        remote.pressButton("musicPlay");
    }
}



interface ExecCommand {
    void execute();
}

class LightOnCommand implements ExecCommand {
    private Light light;
    public LightOnCommand(Light light) { this.light = light; }
    public void execute() { light.on(); }
}

class LightOffCommand implements ExecCommand {
    private Light light;
    public LightOffCommand(Light light) { this.light = light; }
    public void execute() { light.off(); }
}

class PlayMusicCommand implements ExecCommand {
    private MusicSystem music;
    public PlayMusicCommand(MusicSystem music) { this.music = music; }
    public void execute() { music.play(); }
}

class RemoteController {
    private ExecCommand command;

    public void setCommand(ExecCommand command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}


public class CommandDP {
    public static void main(String[] args) {
        Light light = new Light();
        MusicSystem music = new MusicSystem();

        ExecCommand lightOn = new LightOnCommand(light);
        ExecCommand lightOff = new LightOffCommand(light);
        ExecCommand musicPlay = new PlayMusicCommand(music);

        RemoteController remote = new RemoteController();

        remote.setCommand(lightOn);
        remote.pressButton();

        remote.setCommand(musicPlay);
        remote.pressButton();
    }
}
