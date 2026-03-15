package SvartJack;

import java.util.ArrayList;

public class SvartJack {
    
    private Dealer dealer;
    private ArrayList<Player> players = new ArrayList<>();
    private CardDeck deck = new CardDeck();

    public SvartJack() {

        this.dealer = new Dealer();
    }

    public void add_player(String name) {

        validateName(name);

        this.players.add(new Player(name));
    }

    public void deal() {

        for (int index = 0; index < 2; index++) {

            this.players.forEach(c -> {
                if (c.getActiveBet() > 0) {
                    c.getHand().add_card((this.deck.getDeck().remove(0)));
                }
            });

            dealer.getHand().add_card(this.deck.getDeck().remove(0));
        }
    }

    public void add_deck() {
        this.deck.add_deck();
    }
    
    public Dealer getDealer() {
        return this.dealer;
    }
    
    public ArrayList<Player> getPlayers() {
        return players;
    }
    
    public CardDeck getDeck() {
        return deck;
    }

    public int getPlayerValue(int index) {
        return players.get(index).getHandvalue();
    }

    public void newCard() {
        this.players.forEach(c -> c.getHand().add_card((this.deck.getDeck().remove(0))));
    }

    public void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name can not be 'null'");
        }
        if (name.length() < 3) {
            throw new IllegalArgumentException("Nme must be longer than 2 characters");
        }
    }

    public static void main(String[] args) {
        SvartJack round1 = new SvartJack();

        round1.add_player("Christen");

        round1.deal();
        round1.add_deck();

        System.out.println(round1.players);
        System.out.println(round1.dealer);
        
        System.out.println(round1.getDeck().get_size());
        
        round1.newCard();
        System.out.println(round1.players);
        System.out.println(round1.dealer);
        System.out.println(round1.getDeck().get_size());
        System.out.println(round1.getPlayerValue(0));
        System.out.println(round1.getDeck());

    }
}
