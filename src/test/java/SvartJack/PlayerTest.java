package SvartJack;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class PlayerTest{

    @Test
    public void testPlayerCreation() {
        Player player = new Player("TestPlayer");
        assertNotNull(player);
        assertEquals("TestPlayer", player.getName());
        assertEquals(0, player.getBalance());
        assertEquals(0, player.getActiveBet());
        assertFalse(player.isBust());
        assertNotNull(player.getHand());
        assertTrue(player.getHand().getCards().isEmpty());
    }

    @Test
    public void testDeposit() {
        Player player = new Player("TestPlayer");
        player.deposit(100);
        assertEquals(100, player.getBalance());
        player.deposit(50);
        assertEquals(150, player.getBalance());
    }

    @Test
    public void testBet() {
        Player player = new Player("TestPlayer");
        player.deposit(100);
        player.bet(50);
        assertEquals(50, player.getBalance());
        assertEquals(50, player.getActiveBet());
    }

    @Test
    public void testWin() {
        Player player = new Player("TestPlayer");
        player.deposit(100);
        player.bet(50);
        player.win();
        assertEquals(150, player.getBalance());
        assertEquals(0, player.getActiveBet());
    }

    @Test
    public void testLose() {
        Player player = new Player("TestPlayer");
        player.deposit(100);
        player.bet(50);
        player.lose();
        assertEquals(50, player.getBalance());
        assertEquals(0, player.getActiveBet());
    }

    @Test
    public void testEven() {
        Player player = new Player("TestPlayer");
        player.deposit(100);
        player.bet(50);
        player.even();
        assertEquals(100, player.getBalance());
        assertEquals(0, player.getActiveBet());
    }

    @Test
    public void testHit() {
        Player player = new Player("TestPlayer");
        Card card = new Card('H', 5, false);
        player.hit(card);
        assertEquals(1, player.getHand().getCards().size());
        assertEquals(card, player.getHand().getCards().get(0));
    }

    @Test
    public void testGetHandvalue() {
        Player player = new Player("TestPlayer");
        player.hit(new Card('H', 5, false));
        player.hit(new Card('D', 7, false));
        assertEquals(12, player.getHandvalue());
    }

    @Test
    public void testIsBust() {
        Player player = new Player("TestPlayer");
        player.hit(new Card('H', 10, false));
        player.hit(new Card('D', 10, false));
        player.hit(new Card('C', 10, false));
        assertTrue(player.isBust());
        assertEquals(30, player.getHandvalue());
    }

    @Test
    public void testHasBlackjack() {
        Player player = new Player("TestPlayer");
        player.hit(new Card('H', 1, false));
        player.hit(new Card('D', 10, false));
        assertTrue(player.hasBlackjack());
        assertEquals(21, player.getHandvalue());
    }

    @Test
    public void testClearHand() {
        Player player = new Player("TestPlayer");
        player.hit(new Card('H', 5, false));
        player.clearHand();
        assertTrue(player.getHand().getCards().isEmpty());
        assertFalse(player.isBust());
    }

    @Test
    public void testSetName() {
        Player player = new Player("TestPlayer");
        player.setName("NewName");
        assertEquals("NewName", player.getName());
    }
}