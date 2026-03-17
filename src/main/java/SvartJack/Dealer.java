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

        int value =  this.hand.getCards()
        .stream()
        .mapToInt(c -> {
            if (c.getValue() > 10) {
                return 10;
            }
            else if (c.getValue() == 1) {
                return 11;
            }
            else {
                return c.getValue();
            }
        })
        .sum();

        int aceCount = this.hand.getAceCount();

        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }

        return value;
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

    public void clearHand() {
        this.hand.clearHand();
    }

    public boolean hasBlackjack() {
        if (this.getHandvalue() == 21 && this.getHand().getCards().size() == 2) {
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return hand.toString();
    }

    public static void main(String[] args) {
        Dealer dealer = new Dealer();

        dealer.hit(new Card('H', 10));
        dealer.hit(new Card('D', 10));
        dealer.hit(new Card('C', 2));

        System.out.println(dealer.getHandvalue());
    }
}
