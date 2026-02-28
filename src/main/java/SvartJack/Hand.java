package SvartJack;

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> hand;

    public Hand() {
        this.hand = new ArrayList<>();
    }

    public void add_card(Card card) {

        validateCard(card);

        this.hand.add(card);
    }

    public void validateCard(Card card) {
        if (card == null) {
            throw new IllegalArgumentException("Card can not be 'null'");
        }
    }

    public ArrayList<Card> getCards() {
        return this.hand;
    }

    @Override
    public String toString() {
        return hand.toString();
    }
}
