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

    public int getAceCount() {
        int acesCount = this.hand
        .stream()
        .mapToInt(c -> {
            if (c.getValue() == 1) {
                return 1;
            }
            else {
                return 0;
            }
        })
        .sum();

        return acesCount;
    }

    public void clearHand() {
        this.hand.clear();
    }

    @Override
    public String toString() {
        return hand.toString();
    }
}
