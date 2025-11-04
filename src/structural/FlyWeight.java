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


class CharacterFlyweight {
    private char symbol;
    private String font;
    private int size;

    public CharacterFlyweight(char symbol, String font, int size) {
        this.symbol = symbol;
        this.font = font;
        this.size = size;
    }

    public void display(int x, int y) {
        System.out.println("Char: " + symbol + " Font: " + font +
                " Size: " + size + " Position: (" + x + "," + y + ")");
    }
}


class CharactersFactory {
    private Map<String, CharacterFlyweight> cache = new HashMap<>();

    public CharacterFlyweight getCharacter(char symbol, String font, int size) {
        String key = symbol + font + size;
        if (!cache.containsKey(key)) {
            cache.put(key, new CharacterFlyweight(symbol, font, size));
        }
        return cache.get(key);
    }

    public int getCacheSize() {
        return cache.size();
    }
}

class FlyweightDemo {
    public static void main(String[] args) {
        CharactersFactory factory = new CharactersFactory();

        CharacterFlyweight a1 = factory.getCharacter('A', "Arial", 12);
        a1.display(10, 20);

        CharacterFlyweight a2 = factory.getCharacter('A', "Arial", 12);
        a2.display(30, 40);

        CharacterFlyweight b1 = factory.getCharacter('B', "Arial", 12);
        b1.display(50, 60);

        System.out.println("Unique flyweights created: " + factory.getCacheSize());
    }
}


