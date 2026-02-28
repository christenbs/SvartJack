package SvartJack;

public class Card {
    
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

    @Override
    public String toString() {
        return  suit + "" + value;
    }
}