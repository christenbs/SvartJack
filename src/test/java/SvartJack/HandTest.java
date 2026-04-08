package SvartJack;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class HandTest{

    @Test
    public void testHandCreation() {
        Hand hand = new Hand();
        assertNotNull(hand);
        assertTrue(hand.getCards().isEmpty());
    }

    @Test
    public void testAddCard() {
        Hand hand = new Hand();
        Card card = new Card('H', 1, false);
        hand.add_card(card);
        assertEquals(1, hand.getCards().size());
        assertEquals(card, hand.getCards().get(0));
    }

    @Test
    public void testAddNullCardThrowsException() {
        Hand hand = new Hand();
        assertThrows(IllegalArgumentException.class, () -> hand.add_card(null));
    }

    @Test
    public void testGetAceCount() {
        Hand hand = new Hand();
        hand.add_card(new Card('H', 1, false));
        hand.add_card(new Card('D', 2, false));
        hand.add_card(new Card('C', 1, false));
        assertEquals(2, hand.getAceCount());
    }

    @Test
    public void testClearHand() {
        Hand hand = new Hand();
        hand.add_card(new Card('S', 5, false));
        hand.clearHand();
        assertTrue(hand.getCards().isEmpty());
    }
}