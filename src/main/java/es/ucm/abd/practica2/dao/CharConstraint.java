package es.ucm.abd.practica2.dao;

public class CharConstraint {
    private int position;
    private char character;

    public CharConstraint(int position, char character) {
        this.position = position;
        this.character = character;
    }

    public int getPosition() {
        return position;
    }

    public char getCharacter() {
        return character;
    }

    @Override
    public String toString() {
        return "{" + character + " at " + position + "}";
    }
    
    
}
