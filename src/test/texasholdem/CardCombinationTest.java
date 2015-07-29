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
		
		final Hand hand = new Hand(twoOfSpades, aceOfHearts);
		assertFalse(CardCombination.PAIR.inHand(hand));
	}

	@Test
	public void testTwoPair()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card twoOfHearts = new Card(Colour.HEARTS, Rank.TWO);
		final Card fiveOfSpades = new Card(Colour.SPADES, Rank.FIVE);
		final Card fiveOfHearts = new Card(Colour.HEARTS, Rank.FIVE);

		final Hand hand = new Hand(twoOfSpades, twoOfHearts, fiveOfSpades, fiveOfHearts);
		assertTrue(CardCombination.TWO_PAIR.inHand(hand));
	}

	@Test
	public void testTwoPairForFalse()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card twoOfHearts = new Card(Colour.HEARTS, Rank.TWO);
		final Card fourOfSpades = new Card(Colour.SPADES, Rank.FOUR);
		final Card fiveOfHearts = new Card(Colour.HEARTS, Rank.FIVE);

		final Hand hand = new Hand(twoOfSpades, twoOfHearts, fourOfSpades, fiveOfHearts);
		assertFalse(CardCombination.TWO_PAIR.inHand(hand));
	}

	@Test
	public void testThreeOfAKind()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card twoOfHearts = new Card(Colour.HEARTS, Rank.TWO);
		final Card fiveOfSpades = new Card(Colour.SPADES, Rank.FIVE);
		final Card fiveOfHearts = new Card(Colour.HEARTS, Rank.FIVE);
		final Card fiveOfClubs = new Card(Colour.CLUBS, Rank.FIVE);

		final Hand hand = new Hand(twoOfSpades, fiveOfClubs, twoOfHearts, fiveOfSpades, fiveOfHearts);
		assertTrue(CardCombination.THREE_OF_A_KIND.inHand(hand));
	}

	@Test
	public void testThreeOfAKindForFalse()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card twoOfHearts = new Card(Colour.HEARTS, Rank.TWO);
		final Card fiveOfSpades = new Card(Colour.SPADES, Rank.FIVE);
		final Card fiveOfHearts = new Card(Colour.HEARTS, Rank.FIVE);
		final Card sixOfClubs = new Card(Colour.CLUBS, Rank.SIX);

		final Hand hand = new Hand(twoOfSpades, twoOfHearts, fiveOfSpades, fiveOfHearts, sixOfClubs);
		assertFalse(CardCombination.THREE_OF_A_KIND.inHand(hand));
	}

	@Test
	public void testStraight()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card threeOfHearts = new Card(Colour.HEARTS, Rank.THREE);
		final Card fourOfSpades = new Card(Colour.SPADES, Rank.FOUR);
		final Card fiveOfHearts = new Card(Colour.HEARTS, Rank.FIVE);
		final Card sixOfClubs = new Card(Colour.CLUBS, Rank.SIX);

		final Hand hand = new Hand(twoOfSpades, sixOfClubs, threeOfHearts, fourOfSpades, fiveOfHearts);
		assertTrue(CardCombination.STRAIGHT.inHand(hand));
	}

	@Test
	public void testStraightForFalse()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card threeOfHearts = new Card(Colour.HEARTS, Rank.THREE);
		final Card fourOfSpades = new Card(Colour.SPADES, Rank.FOUR);
		final Card fiveOfHearts = new Card(Colour.HEARTS, Rank.FIVE);
		final Card svenOfClubs = new Card(Colour.CLUBS, Rank.SEVEN);

		final Hand hand = new Hand(twoOfSpades, svenOfClubs, threeOfHearts, fourOfSpades, fiveOfHearts);
		assertFalse(CardCombination.STRAIGHT.inHand(hand));
	}

	@Test
	public void testFlush()
	{
		final Card twoOfHearts = new Card(Colour.HEARTS, Rank.TWO);
		final Card threeOfHearts = new Card(Colour.HEARTS, Rank.THREE);
		final Card fourOfHearts = new Card(Colour.HEARTS, Rank.FOUR);
		final Card fiveOfHearts = new Card(Colour.HEARTS, Rank.FIVE);
		final Card eightOfHearts = new Card(Colour.HEARTS, Rank.EIGHT);

		final Hand hand = new Hand(twoOfHearts, threeOfHearts, fourOfHearts, fiveOfHearts, eightOfHearts);
		assertTrue(CardCombination.FLUSH.inHand(hand));
	}

	@Test
	public void testFlushForFalse()
	{
		final Card twoOfHearts = new Card(Colour.HEARTS, Rank.TWO);
		final Card threeOfHearts = new Card(Colour.HEARTS, Rank.THREE);
		final Card fourOfDiamonds = new Card(Colour.DIAMONDS, Rank.FOUR);
		final Card fiveOfHearts = new Card(Colour.HEARTS, Rank.FIVE);
		final Card eightOfHearts = new Card(Colour.HEARTS, Rank.EIGHT);

		final Hand hand = new Hand(twoOfHearts, threeOfHearts, fourOfDiamonds, fiveOfHearts, eightOfHearts);
		assertFalse(CardCombination.FLUSH.inHand(hand));
	}

}
