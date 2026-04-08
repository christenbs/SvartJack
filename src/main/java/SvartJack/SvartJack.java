package SvartJack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.image.Image;

public class SvartJack {
    
    private Dealer dealer;
    private ArrayList<Player> players = new ArrayList<>();
    private CardDeck deck;
    private Image cardBack;

    public SvartJack() {

        this.dealer = new Dealer();
        this.deck = new CardDeck();
        this.cardBack = new Image("file:/Users/christenstaib/Desktop/Objektprosjekt/src/main/resources/SvartJack/images/card_back.png");
    }

    public SvartJack(boolean image) {

        this.dealer = new Dealer();
        this.deck = new CardDeck(false);
    }

    public void add_player(String name) {

        validateName(name);

        this.players.add(new Player(name));
    }

    public void add_player(Player player) {

        this.players.add(player);
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

    public void add_deck(boolean image) {
        this.deck.add_deck(image);
    }

    public void results() {
        Dealer dealer = this.getDealer();
        int dealerHandvalue = dealer.getHandvalue();
        List<Player> players = this.getPlayers();

        if (dealer.isBust()) {
            players.forEach(c -> {
                if (c.isBust()) {
                    c.lose();
                } else if (c.hasBlackjack()) {
                    c.deposit(c.getActiveBet() / 2);
                    c.win();
                } else {
                    c.win();
                }
            });
        } else {
            players.forEach(c -> {
                if (c.isBust()) {
                    c.lose();
                } else if (c.hasBlackjack() && !dealer.hasBlackjack()) {
                    c.deposit(c.getActiveBet() / 2);
                    c.win();
                } else if (dealerHandvalue < c.getHandvalue()) {
                    c.win();
                } else if (dealerHandvalue > c.getHandvalue()) {
                    c.lose();
                } else {
                    c.even();
                }
            });
        }
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

    public void hit(Player player) {
        player.hit(this.deck.getDeck().remove(0));
    }

    public void hit(Dealer dealer) {
        dealer.hit(this.deck.getDeck().remove(0));
    }

    public Image getCardBack() {
        return this.cardBack;
    }

    public void validateName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name can not be 'null'");
        }
        if (name.length() < 3) {
            throw new IllegalArgumentException("Nme must be longer than 2 characters");
        }
    }

    public ArrayList<String> getPlayerNames() {
        return this.players.stream()
        .map(Player::getName)
        .collect(Collectors.toCollection(ArrayList::new));
    }

    public static void main(String[] args) {
        SvartJack round1 = new SvartJack();

        round1.add_player("Christen");

        round1.deal();
        round1.add_deck();

        System.out.println(round1.players);
        System.out.println(round1.dealer);
        
        System.out.println(round1.getDeck().get_size());
        
        System.out.println(round1.players);
        System.out.println(round1.dealer);
        System.out.println(round1.getDeck().get_size());
        System.out.println(round1.getPlayerValue(0));
        System.out.println(round1.getDeck());

    }
}
