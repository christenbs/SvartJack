package SvartJack;

public class BlackJack {
    
    public static void main(String[] args) {
        
        CardDeck deck = new CardDeck();

        System.out.println(deck);
        
        deck.shuffle();
        
        System.out.println(deck);

        deck.add_deck();
        System.out.println(deck);
        System.out.println(deck.get_size());
    }
}
