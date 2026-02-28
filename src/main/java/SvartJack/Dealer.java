package SvartJack;

public class Dealer implements ActorInterface{

    private Hand hand;

    public Dealer() {
        this.hand = new Hand();
    }

    public Hand getHand() {
        return this.hand;
    }

    public int getHandvalue() {
        return this.hand.getCards()
        .stream()
        .mapToInt(c -> c.getValue())
        .sum();
    }

    public void hit(Card card) {
        this.hand.add_card(card);
    }

    public boolean isBust() {
        if (this.getHandvalue() > 21) {
            return true;
        }
        return false;
    }

    public boolean canHit() {
        if (this.getHandvalue() > 16) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return hand.toString();
    }
}
