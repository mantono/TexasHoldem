import static org.junit.Assert.*;

import org.junit.Test;

import cards.*;



public class DeckTest
{

	@Test
	public void testForValidSize()
	{
		Deck regular = new Deck();
		assertEquals(52, regular.getSize());
		Deck custom = new Deck(2);
		assertEquals(104, custom.getSize());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testForInvalidSize()
	{
		Deck bad = new Deck(-1);
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
