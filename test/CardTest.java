import static org.junit.Assert.*;

import org.junit.Test;

import cards.*;


public class CardTest
{

	@Test
	public void testCreateValidCard()
	{
		Card card = new Card(Colour.CLUBS, Rank.THREE);
		assertEquals(card.getRank(), (Rank.THREE));
	}
	
	@Test
	public void testCompareCards()
	{
		Card card1 = new Card(Colour.HEARTS, Rank.THREE);
		Card card2 = new Card(Colour.DIAMONDS, Rank.SIX);
		assertEquals(-3, card1.compareTo(card2));
	}

}
