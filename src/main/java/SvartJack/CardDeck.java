package SvartJack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CardDeck {

    private final ArrayList<Character> SUITS = new ArrayList<>(Arrays.asList('C', 'H', 'S', 'D'));
    
    private ArrayList<Card> deck = new ArrayList<>();

    public CardDeck() {
        for (int index = 0; index < 4; index++) {
            this.deck.addAll(makeDeck());
        }

        Collections.shuffle(this.deck);
    }

    public ArrayList<Card> makeDeck() {
        ArrayList<Card> out = new ArrayList<>();

        for (Character suit : SUITS) {
            for (int value = 1; value < 14; value++) {
                out.add(new Card(suit, value));
            }
        }

        return out;
    }

    public CardDeck(boolean image) {
        for (int index = 0; index < 4; index++) {
            this.deck.addAll(makeDeck(false));
        }

        Collections.shuffle(this.deck);
    }

    public ArrayList<Card> makeDeck(boolean image) {
        ArrayList<Card> out = new ArrayList<>();

        for (Character suit : SUITS) {
            for (int value = 1; value < 14; value++) {
                out.add(new Card(suit, value, false));
            }
        }

        return out;
    }

    public void add_deck() {

        CardDeck new_deck = new CardDeck();

        this.deck.addAll(new_deck.getDeck());
    }

    public void add_deck(boolean image) {

        CardDeck new_deck = new CardDeck(image);

        this.deck.addAll(new_deck.getDeck());
    }

    public ArrayList<Card> getDeck() {
        return this.deck;
    }

    public int get_size() {
        return this.deck.size();
    }

    @Override
    public String toString() {
        return this.deck.toString();
    }

    public static void main(String[] args) {
        CardDeck deck = new CardDeck();

        System.out.println(deck);
    }
}
