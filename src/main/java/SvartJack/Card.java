package SvartJack;

import java.util.ArrayList;
import java.util.Arrays;

public class Card {

    private final ArrayList<Character> SUITS = new ArrayList<>(Arrays.asList('C', 'H', 'S', 'D'));
    
    private char suit;
    private int value;

    public Card(char suit, int value) {
        this.suit = suit;
        this.value = value;
    }

    public char getSuit() {
        return suit;
    }

    public void setSuit(char suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void validateSuit(char suit) {
        if (!SUITS.contains(suit)) {
            throw new IllegalArgumentException("Invalid suit");
        }
    }

    public void validateValue(int value) {
        if (value < 1 || value > 13) {
            throw new IllegalArgumentException("Invalid value");
        }
    }

    @Override
    public String toString() {
        return  suit + "" + value;
    }
}