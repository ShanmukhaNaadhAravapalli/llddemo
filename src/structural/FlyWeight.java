package structural;

import java.util.HashMap;
import java.util.Map;

// Flyweight Interface - Character
interface Character {
    void display(String font);
}
// Concrete Flyweight - ConcreteCharacter
class ConcreteCharacter implements Character {
    private char character;
    public ConcreteCharacter(char character) {
        this.character = character;
    }
    @Override
    public void display(String font) {
        System.out.println("Character: " + character + ", Font: " + font);
    }
}
// Flyweight Factory - CharacterFactory
 class CharacterFactory {
    private final Map<java.lang.Character, Character> characterCache;
    public CharacterFactory() {
        characterCache = new HashMap<>();
    }
    public Character getCharacter(char c) {
        Character character = characterCache.get(c);
        if (character == null) {
            character = new ConcreteCharacter(c);
            characterCache.put(c, character);
        }
        return character;
    }
}

public class FlyWeight {
    public static void main(String[] args) {
        CharacterFactory characterFactory = new CharacterFactory();
        Character character1 = characterFactory.getCharacter('A');
        character1.display("Arial");
        Character character2 = characterFactory.getCharacter('B');
        character2.display("Times New Roman");
        Character character3 = characterFactory.getCharacter('A');
        character3.display("Calibri");
    }
}
