package test.texasholdem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import texasholdem.CardCombination;
import cards.Card;
import cards.Colour;
import cards.Hand;
import cards.Rank;

public class CardCombinationTest
{

	@Before
	public void setUp() throws Exception
	{
	}

	@Test
	public void testPair()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card twoOfHearts = new Card(Colour.HEARTS, Rank.TWO);
		final Hand handWithPair = new Hand(twoOfSpades, twoOfHearts);
		
		assertTrue(CardCombination.PAIR.inHand(handWithPair));
	}
	
	@Test
	public void testPairForFalse()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card aceOfHearts = new Card(Colour.HEARTS, Rank.ACE);
		final Hand handWithPair = new Hand(twoOfSpades, aceOfHearts);
		
		assertFalse(CardCombination.PAIR.inHand(handWithPair));
	}
	
	@Test
	public void testTwoPair()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card twoOfHearts = new Card(Colour.HEARTS, Rank.TWO);
		final Card fiveOfSpades = new Card(Colour.SPADES, Rank.FIVE);
		final Card fiveOfHearts = new Card(Colour.HEARTS, Rank.FIVE);
		
		final Hand handWithPair = new Hand(twoOfSpades, twoOfHearts, fiveOfSpades, fiveOfHearts);
		
		assertTrue(CardCombination.TWO_PAIR.inHand(handWithPair));
	}
	
	@Test
	public void testTwoPairForFalse()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card twoOfHearts = new Card(Colour.HEARTS, Rank.TWO);
		final Card fourOfSpades = new Card(Colour.SPADES, Rank.FOUR);
		final Card fiveOfHearts = new Card(Colour.HEARTS, Rank.FIVE);
		
		final Hand handWithPair = new Hand(twoOfSpades, twoOfHearts, fourOfSpades, fiveOfHearts);
		
		assertFalse(CardCombination.TWO_PAIR.inHand(handWithPair));
	}

}
