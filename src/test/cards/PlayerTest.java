package test.cards;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import cards.Card;
import cards.Colour;
import cards.Hand;
import cards.Player;
import cards.Rank;

public class PlayerTest {

	private Player player;

	@Before
	public void setup() {
		player = new Player("kalle");
	}

	@Test
	public void nameTest() {

		assertEquals("kalle", player.getName());
	}

	@Test
	public void getHandTestFromEmptyHand() {

		Card card1 = new Card(Colour.HEARTS, Rank.EIGHT);
		Card card2 = new Card(Colour.HEARTS, Rank.ACE);
		player.newHand(card1, card2);
		Hand clonedHand = player.getHand();
		assertEquals(card2, clonedHand.dropCard(1));
		assertEquals(card1, clonedHand.dropCard(0));

	}

	@Test
	public void getHandTestFromFullHand() {

		Card card1 = new Card(Colour.HEARTS, Rank.EIGHT);
		Card card2 = new Card(Colour.HEARTS, Rank.ACE);
		player.newHand(card1, card2);

		Card card3 = new Card(Colour.SPADES, Rank.EIGHT);
		Card card4 = new Card(Colour.CLUBS, Rank.THREE);
		player.newHand(card3, card4);
		Hand player1Hand = player.getHand();
		assertFalse(player1Hand.contains(card1));
		assertFalse(player1Hand.contains(card2));
		assertTrue(player1Hand.contains(card3));
		assertTrue(player1Hand.contains(card4));

	}

	@Test
	public void setInRoundTest() {

		player.setInRound(true);
		assertTrue(player.isInRound());

	}

	@Test
	public void addToHandTest()
	{
		assertEquals(0, player.getNumberOfCards());
		player.addTohand(new Card(Colour.DIAMONDS, Rank.JACK));
		assertEquals(1, player.getNumberOfCards());
	}
}
