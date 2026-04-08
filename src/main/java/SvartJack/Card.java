package SvartJack;

import java.util.ArrayList;
import java.util.Arrays;

import javafx.scene.image.Image;

public class Card {

    private final String PATH = "/Users/christenstaib/Desktop/Objektprosjekt/src/main/resources/SvartJack/images/playing_cards/";
    private final ArrayList<Character> SUITS = new ArrayList<>(Arrays.asList('C', 'H', 'S', 'D'));
    
    private char suit;
    private int value;
    private Image image;

    public Card(char suit, int value) {
        validateSuit(suit);
        validateValue(value);

        this.suit = suit;
        this.value = value;

        this.image = new Image("file:" + PATH + String.valueOf(suit) + String.valueOf(value) + ".png");
    }

    public Card(char suit, int value, boolean image) {
        validateSuit(suit);
        validateValue(value);

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

    public Image getImage() {
        return this.image;
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