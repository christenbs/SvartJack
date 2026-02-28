package SvartJack;

import java.util.ArrayList;

public class Round {
    
    private Dealer dealer;
    private ArrayList<Player> players = new ArrayList<>();
    private CardDeck deck = new CardDeck();

    public Round(int numberOfPlayers) {

        validateNumber_of_players(numberOfPlayers);

        this.dealer = new Dealer();
        makePlayerHands(numberOfPlayers);

    }

    public void makePlayerHands(int numberOfPlayers) {
        for (int index = 0; index < numberOfPlayers; index++) {
            this.players.add(new Player());
        }
    }

    public void deal() {

        for (int index = 0; index < 2; index++) {

            this.players.forEach(c -> c.getHand().add_card((this.deck.getDeck().remove(0))));

            dealer.getHand().add_card(this.deck.getDeck().remove(0));
        }
    }

    public void add_deck() {
        this.deck.add_deck();
    }
    
    public Hand getDealer() {
        return dealer.getHand();
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
    
    public void validateNumber_of_players(int number) {
        if (number < 0) {
            throw new IllegalArgumentException("Can not have a negative player number");
        }
        else if (number > 5) {
            throw new IllegalArgumentException("Can not play with more than 5 players");
        }
    }


    public static void main(String[] args) {
        Round round1 = new Round(4);

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

    }
}
