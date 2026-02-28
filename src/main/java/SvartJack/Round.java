package SvartJack;

import java.util.ArrayList;

public class Round {
    
    private Hand dealer;
    private ArrayList<Hand> players = new ArrayList<>();
    private CardDeck deck = new CardDeck();

    public Round(int numberOfPlayers) {

        validateNumber_of_players(numberOfPlayers);

        this.dealer = new Hand();
        makePlayerHands(numberOfPlayers);

    }

    public void makePlayerHands(int numberOfPlayers) {
        for (int index = 0; index < numberOfPlayers; index++) {
            this.players.add(new Hand());
        }
    }

    public void deal() {

        for (int index = 0; index < 2; index++) {

            this.players.forEach(c -> c.add_card((this.deck.getDeck().remove(0))));

            dealer.add_card(this.deck.getDeck().remove(0));
        }
    }

    public void add_deck() {
        this.deck.add_deck();
    }
    
    public Hand getDealer() {
        return dealer;
    }
    
    public ArrayList<Hand> getPlayers() {
        return players;
    }
    
    public CardDeck getDeck() {
        return deck;
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
        System.out.println(round1.getDeck());
    }
}
