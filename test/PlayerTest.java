import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

import texasholdem.Player;
import cards.Card;
import cards.Colour;
import cards.Rank;

public class PlayerTest {

	@Test
	public void nameTest() {
		Player player = new Player("kalle");
		assertEquals("kalle", player.getName());
	}

	@Test
	public void potTestIfIsZero() {
		Player player = new Player("kalle");
		assertEquals(0, player.getChips());

	}

	@Test
	public void getHandTestFromEmptyHand() {
		Player player = new Player("kalle");
		Card card1 = new Card(Colour.HEARTS, Rank.EIGHT);
		Card card2 = new Card(Colour.HEARTS, Rank.ACE);
		player.newHand(card1, card2);
		ArrayList<Card> player1Cards = player.getHand();
		assertTrue(player1Cards.contains(card1));
		assertTrue(player1Cards.contains(card2));

	}

	@Test
	public void getHandTestFromFullHand() {
		Player player = new Player("kalle");
		Card card1 = new Card(Colour.HEARTS, Rank.EIGHT);
		Card card2 = new Card(Colour.HEARTS, Rank.ACE);
		player.newHand(card1, card2);

		Card card3 = new Card(Colour.SPADES, Rank.EIGHT);
		Card card4 = new Card(Colour.CLUBS, Rank.THREE);
		player.newHand(card3, card4);
		ArrayList<Card> player1Cards = player.getHand();
		assertFalse(player1Cards.contains(card1));
		assertFalse(player1Cards.contains(card2));
		assertTrue(player1Cards.contains(card3));
		assertTrue(player1Cards.contains(card4));

	}

	@Test
	public void addChipsTest() {
		Player player = new Player("kalle");
		player.addChips(200);
		assertEquals(200, player.getChips());

	}

	@Test
	public void addToPotTestSuccess() {
		Player player = new Player("kalle");
		player.addChips(200);
		assertTrue(player.addToPot(150));
	}

	@Test
	public void addToPotTestFail() {
		Player player = new Player("kalle");
		player.addChips(200);
		assertFalse(player.addToPot(250));

	}
	
	@Test
	public void setInGameTest() {
		Player player = new Player("kalle");
		player.setInGame(true);
		assertTrue(player.getInGame());

	}

}
