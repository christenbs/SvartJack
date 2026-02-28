package SvartJack;

public class Player implements ActorInterface{
    
    private String name;
    private Hand hand;

    public Player() {
        //this.name = name;
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

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return hand.toString();
    }
}
