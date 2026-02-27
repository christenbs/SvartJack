package SvartJack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.stream.Collectors;

public class CardDeck {

    private final ArrayList<Character> SUITS = new ArrayList<>(Arrays.asList('C', 'H', 'S', 'D'));
    
    private ArrayList<Card> deck = new ArrayList<>();

    public CardDeck() {

        for (Character suit : SUITS) {
            for (int value = 1; value < 14; value++) {
                this.deck.add(new Card(suit, value));
            }
        }
    }

    public ArrayList<Card> shiffle(ArrayList<Card> deck) {

        this.deck.addAll(deck);

        return this.deck;
    }

    public void shuffle() {

        Collections.shuffle(this.deck);
    }

    public int get_size() {
        return this.deck.size();
    }

    @Override
    public String toString() {
        return this.deck.stream().map(x -> x.toString()).collect(Collectors.toList()).toString();
    }
}
