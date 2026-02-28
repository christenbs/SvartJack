package SvartJack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class CardDeck {

    private final ArrayList<Character> SUITS = new ArrayList<>(Arrays.asList('C', 'H', 'S', 'D'));
    
    private ArrayList<Card> deck = new ArrayList<>();

    public CardDeck() {

        for (Character suit : SUITS) {
            for (int value = 1; value < 14; value++) {
                this.deck.add(new Card(suit, value));
            }
        }
        Collections.shuffle(this.deck);
    }

    public void shuffle() {

        Collections.shuffle(this.deck);
    }

    public void add_deck() {

        CardDeck new_deck = new CardDeck();
        new_deck.shuffle();

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
}
