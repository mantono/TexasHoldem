import static org.junit.Assert.*;

import org.junit.Test;

import cards.Card;
import cards.Deck;


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
	public void testDrawARandomCard() //Obe ej random ännu!
	{
		Deck deck = new Deck();
		Card card = deck.drawCard();
		assertEquals(51, deck.getSize());
	}
}
