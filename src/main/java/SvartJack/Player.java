package SvartJack;

public class Player implements ActorInterface{
    
    private String name;
    private Hand hand;
    private int balance;
    private int activeBet;

    public Player(String name) {
        this.name = name;
        this.hand = new Hand();
        this.balance = 0;
        this.activeBet = 0;
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

    public void deposit(int amount) {
        this.balance += amount;
    }

    public void bet(int bet) {
        this.activeBet += bet;
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

    @Override
    public String toString() {
        return hand.toString();
    }
}
