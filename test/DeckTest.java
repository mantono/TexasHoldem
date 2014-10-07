import static org.junit.Assert.*;

import org.junit.Test;

import cards.Card;
import cards.Colour;
import cards.Deck;
import cards.Rank;


public class DeckTest
{

	@Test
	public void testForValidSize()
	{
		Deck regular = new Deck();
		assertEquals(52, regular.getSize());
		Deck custom = new Deck((byte) 2);
		assertEquals(104, custom.getSize());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testForInvalidSize()
	{
		Deck bad = new Deck((byte) -1);
		assertEquals(-52, bad.getSize());
	}
	
	@Test
	public void testDrawARandomCard()
	{
		Deck deck = new Deck();
		deck.drawCard();
		assertEquals(51, deck.getSize());
	}
}
