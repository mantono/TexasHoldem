import static org.junit.Assert.*;

import org.junit.Test;

import cards.Deck;


public class DeckTest
{

	@Test
	public void testForValidSize()
	{
		Deck regular = new Deck();
		assertEquals(52, regular.getSize());
		Deck custom = new Deck(102);
		assertEquals(102, custom.getSize());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testForInvalidSize()
	{
		Deck bad = new Deck(-5);
		assertEquals(-5, bad.getSize());
	}

}
