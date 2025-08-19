package behavioral;

import java.util.Stack;

// Memento
class TextEditorMemento {
    private String text;
    public TextEditorMemento(String text){
        this.text = text;
    }
    public String getText(){
        return this.text;
    }
}

// Originator
class TextEditor{
    StringBuilder text;
    public TextEditor(){
        text = new StringBuilder();
    }
    public void append(String st){
        text.append(st);
    }
    public String getText() {
        return text.toString();
    }
    public void restore(TextEditorMemento mem){
        text = new StringBuilder(mem.getText());
    }
    public TextEditorMemento save() {
        return new TextEditorMemento(text.toString());
    }
}

// Caretaker
class Caretaker {
    Stack<TextEditorMemento> history = new Stack();
    public void save(TextEditorMemento mem){
        history.push(mem);
    }
    public boolean isEmpty(){
        return history.isEmpty();
    }
    public TextEditorMemento restoreState(){
        if(history.isEmpty())
            return null;
        return history.pop();
    }
}
public class Memento {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Caretaker history = new Caretaker();
        editor.append("Hello,");
        editor.append("there");
        history.save(editor.save());
        editor.append(" I am here");
        history.save(editor.save());
        editor.append("By the way");
        System.out.println("before redoing"); //
        System.out.println(editor.getText()); //
        TextEditorMemento memento = history.restoreState();
        if(memento!= null){
            editor.restore(memento);
        }
        System.out.println("after redoing"); //
        System.out.println(editor.getText()); //

    }
}
