package SvartJack;

public class Main {
    
    public static void main(String[] args) {
        
        CardDeck deck = new CardDeck();

        System.out.println(deck);
        
        deck.shuffle();
        
        System.out.println(deck);
        System.out.println(deck.get_size());




    }
}
