package SvartJack;

public class Player implements ActorInterface{
    
    private String name;
    private Hand hand;
    private int balance;
    private int activeBet;
    private boolean isBust = false;

    public Player(String name) {
        this.name = name.toLowerCase();
        this.hand = new Hand();
        this.balance = 0;
        this.activeBet = 0;
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
            this.isBust = true;
            return this.isBust;
        }
        return this.isBust;
    }

    public String getName() {
        return this.name;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public void bet(int bet) {
        this.activeBet += bet;
        this.balance -= bet;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBalance() {
        return this.balance;
    }

    public int getActiveBet() {
        return this.activeBet;
    }

    public void win() {
        this.deposit(activeBet * 2);
        this.activeBet = 0;
    }

    public void lose() {
        this.activeBet = 0;
    }

    public void even() {
        this.deposit(activeBet);
        this.activeBet = 0;
    }

    public void clearHand() {
        this.hand.clearHand();
        this.isBust = false;
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
}
