package test.cards;
import static org.junit.Assert.*;

import org.junit.Test;

import cards.*;


public class CardTest
{

	@Test
	public void testCreateValidCard()
	{
		Card card = new Card(Colour.CLUBS, Rank.THREE);
		assertEquals(Rank.THREE, card.getRank());
		assertEquals(Colour.CLUBS, card.getColour());
	}
	
	@Test
	public void testCompareCards()
	{
		Card card1 = new Card(Colour.HEARTS, Rank.THREE);
		Card card2 = new Card(Colour.DIAMONDS, Rank.SIX);
		assertEquals(-3, card1.compareTo(card2));
	}
	
	@Test
	public void testCompareEqualRankCards()
	{
		Card card1 = new Card(Colour.HEARTS, Rank.QUEEN);
		Card card2 = new Card(Colour.DIAMONDS, Rank.QUEEN);
		assertEquals(0, card1.compareTo(card2));
	}
	
	@Test
	public void testEqualsValid()
	{
		Card card1 = new Card(Colour.HEARTS, Rank.QUEEN);
		Card card2 = new Card(Colour.HEARTS, Rank.QUEEN);
		assertTrue(card1.equals(card2));
	}
	
	@Test
	public void testEqualsInvalid()
	{
		Card card1 = new Card(Colour.HEARTS, Rank.QUEEN);
		Card card2 = new Card(Colour.HEARTS, Rank.ACE);
		assertFalse(card1.equals(card2));
		card2 = new Card(Colour.CLUBS, Rank.QUEEN);
		assertFalse(card1.equals(card2));
		card2 = null;
		assertFalse(card1.equals(card2));
		assertFalse(card1.equals("A string"));
	}
	
	@Test
	public void testHashCode()
	{
		Card card1 = new Card(Colour.SPADES, Rank.TWO);
		Card card2 = new Card(Colour.HEARTS, Rank.THREE);
		Card card3 = new Card(Colour.DIAMONDS, Rank.FOUR);
		Card card4 = new Card(Colour.CLUBS, Rank.FIVE);
		assertEquals(302, card1.hashCode());
		assertEquals(203, card2.hashCode());
		assertEquals(104, card3.hashCode());
		assertEquals(5, card4.hashCode());
	}
	
	@Test
	public void testToString()
	{
		Card card = new Card(Colour.SPADES, Rank.ACE);
		assertEquals("ACE of SPADES", card.toString());
	}

}
