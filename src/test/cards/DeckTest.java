package test.cards;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import cards.*;



public class DeckTest
{
	private Deck deck;
	
	@Before
	public void setup()
	{
		deck = new Deck();
	}

	@Test
	public void testForValidSize()
	{
		assertEquals(52, deck.getSize());
		Deck custom = new Deck((byte) 2);
		assertEquals(104, custom.getSize());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testForInvalidSize()
	{
		Deck bad = new Deck((byte) -1);
	}
	
	@Test
	public void testDrawARandomCard()
	{
		deck.drawCard();
		assertEquals(51, deck.getSize());
	}
	
	@Test
	public void testDrawMultipleCards()
	{
		Card[] cards = deck.drawCards(0);
		assertEquals(0, cards.length);
		assertEquals(52, deck.getSize());
		
		cards = deck.drawCards(1);
		assertEquals(1, cards.length);
		assertEquals(51, deck.getSize());
		
		cards = deck.drawCards(10);
		assertEquals(10, cards.length);
		assertEquals(41, deck.getSize());
		
		Deck deck2 = new Deck();
		cards = deck2.drawCards(52);
		assertEquals(52, cards.length);
		assertEquals(0, deck2.getSize());		
	}
	
	@Test(expected=NegativeArraySizeException.class)
	public void testDrawMultipleCardsNegative()
	{
		deck.drawCards(-1);
	}
	
	@Test
	public void removeCardTest()
	{
		Card card = new Card(Colour.SPADES, Rank.ACE);
		deck.removeCard(card);
		assertFalse(deck.deckContains(card));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void removeNonExistingCardTest()
	{
		Card card = deck.drawCard();
		deck.removeCard(card);

	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testDrawMultipleCardsTooMany()
	{
		deck.drawCards(deck.getSize()+1);
	}
	
	@Test
	public void testToString()
	{
		assertEquals(845, deck.toString().length());		
	}
}
