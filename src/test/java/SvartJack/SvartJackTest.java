package SvartJack;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class SvartJackTest{

    @Test
    public void testSvartJackCreation() {
        SvartJack game = new SvartJack(false);
        assertNotNull(game);
        assertNotNull(game.getDealer());
        assertNotNull(game.getPlayers());
        assertNotNull(game.getDeck());
        assertTrue(game.getPlayers().isEmpty());
    }

    @Test
    public void testAddPlayerWithString() {
        SvartJack game = new SvartJack(false);
        game.add_player("Alice");
        assertEquals(1, game.getPlayers().size());
        assertEquals("alice", game.getPlayers().get(0).getName());
    }

    @Test
    public void testAddPlayerWithPlayerObject() {
        SvartJack game = new SvartJack(false);
        Player player = new Player("Bob");
        game.add_player(player);
        assertEquals(1, game.getPlayers().size());
        assertEquals(player, game.getPlayers().get(0));
    }

    @Test
    public void testValidateName() {
        SvartJack game = new SvartJack(false);
        assertThrows(IllegalArgumentException.class, () -> game.validateName(null));
        assertThrows(IllegalArgumentException.class, () -> game.validateName("AB"));
        assertDoesNotThrow(() -> game.validateName("ABC"));
    }

    @Test
    public void testGetPlayerNames() {
        SvartJack game = new SvartJack(false);
        game.add_player("Charlie");
        game.add_player("Dave");
        assertEquals(2, game.getPlayerNames().size());
        assertTrue(game.getPlayerNames().contains("charlie"));
        assertTrue(game.getPlayerNames().contains("dave"));
    }

    @Test
    public void testAddDeck() {
        SvartJack game = new SvartJack(false);
        int initialSize = game.getDeck().getDeck().size();
        game.add_deck(false);
        assertEquals(initialSize + 4*52, game.getDeck().getDeck().size()); 
    }

    @Test
    public void testDeal() {
        SvartJack game = new SvartJack(false);
        Player player = new Player("Eve");
        player.bet(10);
        game.add_player(player);
        int initialDeckSize = game.getDeck().getDeck().size();
        game.deal();
        assertEquals(2, player.getHand().getCards().size());
        assertEquals(2, game.getDealer().getHand().getCards().size());
        assertEquals(initialDeckSize - 4, game.getDeck().getDeck().size()); 
    }

    @Test
    public void testResultsDealerBustsPlayerWins() {
        SvartJack game = new SvartJack(false);
        Player player = new Player("Eve");
        player.deposit(100);
        player.bet(10);
        game.add_player(player);

        player.getHand().add_card(new Card('H', 10, false));
        player.getHand().add_card(new Card('D', 7, false));

        game.getDealer().getHand().add_card(new Card('S', 10, false));
        game.getDealer().getHand().add_card(new Card('C', 10, false));
        game.getDealer().getHand().add_card(new Card('H', 5, false));

        game.results();

        assertEquals(110, player.getBalance());
        assertEquals(0, player.getActiveBet());
    }

    @Test
    public void testResultsPushEven() {
        SvartJack game = new SvartJack(false);
        Player player = new Player("Frank");
        player.deposit(50);
        player.bet(10);
        game.add_player(player);

        player.getHand().add_card(new Card('H', 10, false));
        player.getHand().add_card(new Card('D', 10, false));

        game.getDealer().getHand().add_card(new Card('S', 10, false));
        game.getDealer().getHand().add_card(new Card('C', 10, false));

        game.results();

        assertEquals(50, player.getBalance());
        assertEquals(0, player.getActiveBet());
    }
}
