package test.texasholdem;

import static org.junit.Assert.*;

import java.util.Set;
import java.util.HashSet;

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
	public void testGetValue()
	{
		int value = 22;
		for(CardCombination combination : CardCombination.values())
			assertEquals(value--, combination.getValue());
		assertEquals(14, value);
	}

	@Test
	public void testPair()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card twoOfHearts = new Card(Colour.HEARTS, Rank.TWO);

		final Card threeOfDiamonds = new Card(Colour.DIAMONDS, Rank.THREE);
		final Card queenOfClubs = new Card(Colour.CLUBS, Rank.QUEEN);
		final Card jackOfHearts = new Card(Colour.HEARTS, Rank.JACK);
		final Card kingOfClubs = new Card(Colour.CLUBS, Rank.KING);
		final Card nineOfDiamonds = new Card(Colour.DIAMONDS, Rank.NINE);

		final Hand handWithPair = new Hand(twoOfSpades, twoOfHearts, threeOfDiamonds, queenOfClubs, jackOfHearts, kingOfClubs, nineOfDiamonds);

		assertTrue(CardCombination.PAIR.inHand(handWithPair));
	}

	@Test
	public void testPairForFalse()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card aceOfHearts = new Card(Colour.HEARTS, Rank.ACE);
		final Card threeOfDiamonds = new Card(Colour.DIAMONDS, Rank.THREE);
		final Card queenOfClubs = new Card(Colour.CLUBS, Rank.QUEEN);
		final Card jackOfHearts = new Card(Colour.HEARTS, Rank.JACK);
		final Card kingOfClubs = new Card(Colour.CLUBS, Rank.KING);
		final Card nineOfDiamonds = new Card(Colour.DIAMONDS, Rank.NINE);

		final Hand hand = new Hand(twoOfSpades, aceOfHearts, threeOfDiamonds, queenOfClubs, jackOfHearts, kingOfClubs, nineOfDiamonds);

		assertFalse(CardCombination.PAIR.inHand(hand));
	}

	@Test
	public void testTwoPair()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card twoOfHearts = new Card(Colour.HEARTS, Rank.TWO);
		final Card fiveOfSpades = new Card(Colour.SPADES, Rank.FIVE);
		final Card fiveOfHearts = new Card(Colour.HEARTS, Rank.FIVE);

		final Card threeOfDiamonds = new Card(Colour.DIAMONDS, Rank.THREE);
		final Card queenOfClubs = new Card(Colour.CLUBS, Rank.QUEEN);
		final Card aceOfHearts = new Card(Colour.HEARTS, Rank.ACE);

		final Hand hand = new Hand(twoOfSpades, twoOfHearts, fiveOfSpades, fiveOfHearts, threeOfDiamonds, queenOfClubs, aceOfHearts);

		assertTrue(CardCombination.TWO_PAIR.inHand(hand));
	}

	@Test
	public void testTwoPairForFalse()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card twoOfHearts = new Card(Colour.HEARTS, Rank.TWO);
		final Card fourOfSpades = new Card(Colour.SPADES, Rank.FOUR);
		final Card fiveOfHearts = new Card(Colour.HEARTS, Rank.FIVE);
		final Card threeOfDiamonds = new Card(Colour.DIAMONDS, Rank.THREE);
		final Card queenOfClubs = new Card(Colour.CLUBS, Rank.QUEEN);
		final Card aceOfHearts = new Card(Colour.HEARTS, Rank.ACE);

		final Hand hand = new Hand(twoOfSpades, twoOfHearts, fourOfSpades, fiveOfHearts, threeOfDiamonds, queenOfClubs, aceOfHearts);

		assertFalse(CardCombination.TWO_PAIR.inHand(hand));
	}

	@Test
	public void testFourOfAKindAsNotTwoPair()
	{
		final Card eightOfClubs = new Card(Colour.CLUBS, Rank.EIGHT);
		final Card eightOfHearts = new Card(Colour.HEARTS, Rank.EIGHT);
		final Card eightOfDiamonds = new Card(Colour.DIAMONDS, Rank.EIGHT);
		final Card eightOfSpades = new Card(Colour.SPADES, Rank.EIGHT);

		final Card twoOfHearts = new Card(Colour.HEARTS, Rank.TWO);
		final Card threeOfDiamonds = new Card(Colour.DIAMONDS, Rank.THREE);
		final Card queenOfClubs = new Card(Colour.CLUBS, Rank.QUEEN);

		final Hand hand = new Hand(twoOfHearts, eightOfClubs, eightOfHearts, eightOfDiamonds, eightOfSpades, threeOfDiamonds, queenOfClubs);

		assertFalse(CardCombination.TWO_PAIR.inHand(hand));
	}

	@Test
	public void testThreeOfAKind()
	{
		final Card fiveOfSpades = new Card(Colour.SPADES, Rank.FIVE);
		final Card fiveOfHearts = new Card(Colour.HEARTS, Rank.FIVE);
		final Card fiveOfClubs = new Card(Colour.CLUBS, Rank.FIVE);

		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card twoOfHearts = new Card(Colour.HEARTS, Rank.TWO);
		final Card threeOfDiamonds = new Card(Colour.DIAMONDS, Rank.THREE);
		final Card queenOfClubs = new Card(Colour.CLUBS, Rank.QUEEN);

		final Hand hand = new Hand(twoOfSpades, fiveOfClubs, twoOfHearts, fiveOfSpades, fiveOfHearts, threeOfDiamonds, queenOfClubs);

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
		final Card threeOfDiamonds = new Card(Colour.DIAMONDS, Rank.THREE);
		final Card queenOfClubs = new Card(Colour.CLUBS, Rank.QUEEN);

		final Hand hand = new Hand(twoOfSpades, twoOfHearts, fiveOfSpades, fiveOfHearts, sixOfClubs, threeOfDiamonds, queenOfClubs);

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

		final Card threeOfDiamonds = new Card(Colour.DIAMONDS, Rank.THREE);
		final Card queenOfClubs = new Card(Colour.CLUBS, Rank.QUEEN);

		final Hand hand = new Hand(twoOfSpades, sixOfClubs, threeOfHearts, fourOfSpades, fiveOfHearts, threeOfDiamonds, queenOfClubs);

		assertTrue(CardCombination.STRAIGHT.inHand(hand));
	}

	@Test
	public void testStraightWithLeadingAce()
	{
		final Card aceOfSpades = new Card(Colour.SPADES, Rank.ACE);
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card threeOfHearts = new Card(Colour.HEARTS, Rank.THREE);
		final Card fourOfSpades = new Card(Colour.SPADES, Rank.FOUR);
		final Card fiveOfHearts = new Card(Colour.HEARTS, Rank.FIVE);

		final Card threeOfDiamonds = new Card(Colour.DIAMONDS, Rank.THREE);
		final Card queenOfClubs = new Card(Colour.CLUBS, Rank.QUEEN);

		final Hand hand = new Hand(twoOfSpades, aceOfSpades, threeOfHearts, fourOfSpades, fiveOfHearts, threeOfDiamonds, queenOfClubs);

		assertTrue(CardCombination.STRAIGHT.inHand(hand));
	}

	@Test
	public void testStraightWithThreeLeadingAce()
	{
		final Card aceOfSpades = new Card(Colour.SPADES, Rank.ACE);
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card threeOfHearts = new Card(Colour.HEARTS, Rank.THREE);
		final Card fourOfSpades = new Card(Colour.SPADES, Rank.FOUR);
		final Card fiveOfHearts = new Card(Colour.HEARTS, Rank.FIVE);

		final Card aceOfClubs = new Card(Colour.CLUBS, Rank.ACE);
		final Card aceOfDiamonds = new Card(Colour.DIAMONDS, Rank.ACE);

		final Hand hand = new Hand(twoOfSpades, aceOfSpades, threeOfHearts, fourOfSpades, fiveOfHearts, aceOfDiamonds, aceOfClubs);

		assertTrue(CardCombination.STRAIGHT.inHand(hand));
	}

	@Test
	public void testStraightWithEndingAce()
	{
		final Card tenOfSpades = new Card(Colour.SPADES, Rank.TEN);
		final Card jackOfHearts = new Card(Colour.HEARTS, Rank.JACK);
		final Card queenOfSpades = new Card(Colour.SPADES, Rank.QUEEN);
		final Card kingOfHearts = new Card(Colour.HEARTS, Rank.KING);
		final Card aceOfSpades = new Card(Colour.SPADES, Rank.ACE);

		final Card nineOfDiamonds = new Card(Colour.DIAMONDS, Rank.NINE);
		final Card queenOfClubs = new Card(Colour.CLUBS, Rank.QUEEN);

		final Hand hand = new Hand(tenOfSpades, aceOfSpades, queenOfSpades, kingOfHearts, jackOfHearts, nineOfDiamonds, queenOfClubs);

		assertTrue(hand.toString(), CardCombination.STRAIGHT.inHand(hand));
	}

	@Test
	public void testStraightForFalse()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card threeOfHearts = new Card(Colour.HEARTS, Rank.THREE);
		final Card fourOfSpades = new Card(Colour.SPADES, Rank.FOUR);
		final Card fiveOfHearts = new Card(Colour.HEARTS, Rank.FIVE);
		final Card svenOfClubs = new Card(Colour.CLUBS, Rank.SEVEN);
		final Card threeOfDiamonds = new Card(Colour.DIAMONDS, Rank.THREE);
		final Card queenOfClubs = new Card(Colour.CLUBS, Rank.QUEEN);

		final Hand hand = new Hand(twoOfSpades, svenOfClubs, threeOfHearts, fourOfSpades, fiveOfHearts, threeOfDiamonds, queenOfClubs);

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

		final Card nineOfDiamonds = new Card(Colour.DIAMONDS, Rank.NINE);
		final Card queenOfClubs = new Card(Colour.CLUBS, Rank.QUEEN);

		final Hand hand = new Hand(twoOfHearts, threeOfHearts, fourOfHearts, fiveOfHearts, eightOfHearts, nineOfDiamonds, queenOfClubs);

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
		final Card nineOfDiamonds = new Card(Colour.DIAMONDS, Rank.NINE);
		final Card queenOfClubs = new Card(Colour.CLUBS, Rank.QUEEN);

		final Hand hand = new Hand(twoOfHearts, threeOfHearts, fourOfDiamonds, fiveOfHearts, eightOfHearts, nineOfDiamonds, queenOfClubs);

		assertFalse(CardCombination.FLUSH.inHand(hand));
	}

	@Test
	public void testFullHouse()
	{
		final Card twoOfHearts = new Card(Colour.HEARTS, Rank.TWO);
		final Card twoOfClubs = new Card(Colour.CLUBS, Rank.TWO);
		final Card eightOfHearts = new Card(Colour.HEARTS, Rank.EIGHT);
		final Card eightOfDiamonds = new Card(Colour.DIAMONDS, Rank.EIGHT);
		final Card eightOfSpades = new Card(Colour.SPADES, Rank.EIGHT);

		final Card fourOfDiamonds = new Card(Colour.DIAMONDS, Rank.FOUR);
		final Card queenOfClubs = new Card(Colour.CLUBS, Rank.QUEEN);

		final Hand hand = new Hand(twoOfHearts, twoOfClubs, eightOfHearts, eightOfDiamonds, eightOfSpades, fourOfDiamonds, queenOfClubs);

		assertTrue(CardCombination.FULL_HOUSE.inHand(hand));
	}

	@Test
	public void testFullHouseFalse()
	{
		final Card twoOfHearts = new Card(Colour.HEARTS, Rank.TWO);
		final Card twoOfClubs = new Card(Colour.CLUBS, Rank.TWO);
		final Card eightOfHearts = new Card(Colour.HEARTS, Rank.EIGHT);
		final Card sevenOfDiamonds = new Card(Colour.DIAMONDS, Rank.SEVEN);
		final Card eightOfSpades = new Card(Colour.SPADES, Rank.EIGHT);
		final Card fourOfDiamonds = new Card(Colour.DIAMONDS, Rank.FOUR);
		final Card queenOfClubs = new Card(Colour.CLUBS, Rank.QUEEN);

		final Hand hand = new Hand(twoOfHearts, twoOfClubs, eightOfHearts, sevenOfDiamonds, eightOfSpades, fourOfDiamonds, queenOfClubs);

		assertFalse(CardCombination.FULL_HOUSE.inHand(hand));
	}

	@Test
	public void testFourOfAKind()
	{
		final Card eightOfClubs = new Card(Colour.CLUBS, Rank.EIGHT);
		final Card eightOfHearts = new Card(Colour.HEARTS, Rank.EIGHT);
		final Card eightOfDiamonds = new Card(Colour.DIAMONDS, Rank.EIGHT);
		final Card eightOfSpades = new Card(Colour.SPADES, Rank.EIGHT);

		final Card twoOfHearts = new Card(Colour.HEARTS, Rank.TWO);
		final Card fourOfDiamonds = new Card(Colour.DIAMONDS, Rank.FOUR);
		final Card queenOfClubs = new Card(Colour.CLUBS, Rank.QUEEN);

		final Hand hand = new Hand(twoOfHearts, eightOfClubs, eightOfHearts, eightOfDiamonds, eightOfSpades, fourOfDiamonds, queenOfClubs);

		assertTrue(CardCombination.FOUR_OF_A_KIND.inHand(hand));
	}

	@Test
	public void testFourOfAKindFalse()
	{
		final Card twoOfHearts = new Card(Colour.HEARTS, Rank.TWO);
		final Card twoOfClubs = new Card(Colour.CLUBS, Rank.TWO);
		final Card eightOfHearts = new Card(Colour.HEARTS, Rank.EIGHT);
		final Card eightOfDiamonds = new Card(Colour.DIAMONDS, Rank.EIGHT);
		final Card eightOfSpades = new Card(Colour.SPADES, Rank.EIGHT);
		final Card fourOfDiamonds = new Card(Colour.DIAMONDS, Rank.FOUR);
		final Card queenOfClubs = new Card(Colour.CLUBS, Rank.QUEEN);

		final Hand hand = new Hand(twoOfHearts, twoOfClubs, eightOfHearts, eightOfDiamonds, eightOfSpades, fourOfDiamonds, queenOfClubs);

		assertFalse(CardCombination.FOUR_OF_A_KIND.inHand(hand));
	}

	@Test
	public void testStraightFlush()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card threeOfSpades = new Card(Colour.SPADES, Rank.THREE);
		final Card fourOfSpades = new Card(Colour.SPADES, Rank.FOUR);
		final Card fiveOfSpades = new Card(Colour.SPADES, Rank.FIVE);
		final Card sixOfSpades = new Card(Colour.SPADES, Rank.SIX);

		final Card fourOfDiamonds = new Card(Colour.DIAMONDS, Rank.FOUR);
		final Card queenOfClubs = new Card(Colour.CLUBS, Rank.QUEEN);

		final Hand hand = new Hand(twoOfSpades, sixOfSpades, threeOfSpades, fourOfSpades, fiveOfSpades, fourOfDiamonds, queenOfClubs);

		assertTrue(CardCombination.STRAIGHT_FLUSH.inHand(hand));
	}

	@Test
	public void testStraightFlushForFalse()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card threeOfSpades = new Card(Colour.SPADES, Rank.THREE);
		final Card fourOfDiamonds = new Card(Colour.DIAMONDS, Rank.FOUR);
		final Card fiveOfSpades = new Card(Colour.SPADES, Rank.FIVE);
		final Card sixOfSpades = new Card(Colour.SPADES, Rank.SIX);
		final Card nineOfDiamonds = new Card(Colour.DIAMONDS, Rank.NINE);
		final Card queenOfClubs = new Card(Colour.CLUBS, Rank.QUEEN);

		final Hand hand = new Hand(twoOfSpades, sixOfSpades, threeOfSpades, fourOfDiamonds, fiveOfSpades, nineOfDiamonds, queenOfClubs);

		assertFalse(CardCombination.STRAIGHT_FLUSH.inHand(hand));
	}

	@Test
	public void testGetCardsForPair()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card threeOfSpades = new Card(Colour.SPADES, Rank.THREE);
		final Card fourOfDiamonds = new Card(Colour.DIAMONDS, Rank.FOUR);
		final Card fiveOfSpades = new Card(Colour.SPADES, Rank.FIVE);
		final Card sixOfSpades = new Card(Colour.SPADES, Rank.SIX);
		final Card nineOfDiamonds = new Card(Colour.DIAMONDS, Rank.NINE);
		final Card fourOfClubs = new Card(Colour.CLUBS, Rank.FOUR);

		final Hand hand = new Hand(twoOfSpades, sixOfSpades, threeOfSpades, fourOfDiamonds, fiveOfSpades, nineOfDiamonds, fourOfClubs);

		Set<Card> cards = CardCombination.PAIR.getCards(hand);
		Set<Card> expected = new HashSet<Card>(2);
		expected.add(fourOfDiamonds);
		expected.add(fourOfClubs);

		assertEquals(expected, cards);
	}

	@Test
	public void testGetCardsForHighestPair()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card threeOfSpades = new Card(Colour.SPADES, Rank.THREE);
		final Card fourOfDiamonds = new Card(Colour.DIAMONDS, Rank.FOUR);
		final Card sixOfClubs = new Card(Colour.CLUBS, Rank.SIX);
		final Card sixOfSpades = new Card(Colour.SPADES, Rank.SIX);
		final Card nineOfDiamonds = new Card(Colour.DIAMONDS, Rank.NINE);
		final Card fourOfClubs = new Card(Colour.CLUBS, Rank.FOUR);

		final Hand hand = new Hand(twoOfSpades, sixOfSpades, threeOfSpades, fourOfDiamonds, sixOfClubs, nineOfDiamonds, fourOfClubs);

		Set<Card> cards = CardCombination.PAIR.getCards(hand);
		Set<Card> expected = new HashSet<Card>(2);
		expected.add(sixOfSpades);
		expected.add(sixOfClubs);

		assertEquals(expected, cards);
	}

	@Test
	public void testGetNoCardsForPair()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card threeOfSpades = new Card(Colour.SPADES, Rank.THREE);
		final Card fourOfDiamonds = new Card(Colour.DIAMONDS, Rank.FOUR);
		final Card fiveOfSpades = new Card(Colour.SPADES, Rank.FIVE);
		final Card sixOfSpades = new Card(Colour.SPADES, Rank.SIX);
		final Card nineOfDiamonds = new Card(Colour.DIAMONDS, Rank.NINE);
		final Card kingOfClubs = new Card(Colour.CLUBS, Rank.KING);

		final Hand hand = new Hand(twoOfSpades, sixOfSpades, threeOfSpades, fourOfDiamonds, fiveOfSpades, nineOfDiamonds, kingOfClubs);

		Set<Card> cards = CardCombination.PAIR.getCards(hand);

		assertTrue(cards.size() == 0);
	}

	@Test
	public void testGetCardsForTwoPair()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card threeOfSpades = new Card(Colour.SPADES, Rank.THREE);
		final Card fourOfDiamonds = new Card(Colour.DIAMONDS, Rank.FOUR);
		final Card sixOfClubs = new Card(Colour.CLUBS, Rank.SIX);
		final Card sixOfSpades = new Card(Colour.SPADES, Rank.SIX);
		final Card nineOfDiamonds = new Card(Colour.DIAMONDS, Rank.NINE);
		final Card fourOfClubs = new Card(Colour.CLUBS, Rank.FOUR);

		final Hand hand = new Hand(twoOfSpades, sixOfSpades, threeOfSpades, fourOfDiamonds, sixOfClubs, nineOfDiamonds, fourOfClubs);

		Set<Card> cards = CardCombination.TWO_PAIR.getCards(hand);
		Set<Card> expected = new HashSet<Card>(2);
		expected.add(fourOfDiamonds);
		expected.add(fourOfClubs);
		expected.add(sixOfSpades);
		expected.add(sixOfClubs);

		assertEquals(expected, cards);
	}

	@Test
	public void testGetCardsForHighestTwoPair()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card nineOfSpades = new Card(Colour.SPADES, Rank.NINE);
		final Card fourOfDiamonds = new Card(Colour.DIAMONDS, Rank.FOUR);
		final Card sixOfClubs = new Card(Colour.CLUBS, Rank.SIX);
		final Card sixOfSpades = new Card(Colour.SPADES, Rank.SIX);
		final Card nineOfDiamonds = new Card(Colour.DIAMONDS, Rank.NINE);
		final Card fourOfClubs = new Card(Colour.CLUBS, Rank.FOUR);

		final Hand hand = new Hand(twoOfSpades, sixOfSpades, nineOfSpades, fourOfDiamonds, sixOfClubs, nineOfDiamonds, fourOfClubs);

		Set<Card> cards = CardCombination.TWO_PAIR.getCards(hand);
		Set<Card> expected = new HashSet<Card>(2);
		expected.add(sixOfSpades);
		expected.add(sixOfClubs);
		expected.add(nineOfSpades);
		expected.add(nineOfDiamonds);

		assertEquals(expected, cards);
	}
	
	@Test public void testGetCardsForThreeOfAkind()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card threeOfSpades = new Card(Colour.SPADES, Rank.THREE);
		final Card fourOfDiamonds = new Card(Colour.DIAMONDS, Rank.FOUR);
		final Card sixOfClubs = new Card(Colour.CLUBS, Rank.SIX);
		final Card sixOfSpades = new Card(Colour.SPADES, Rank.SIX);
		final Card sixOfDiamonds = new Card(Colour.DIAMONDS, Rank.SIX);
		final Card fourOfClubs = new Card(Colour.CLUBS, Rank.FOUR);

		final Hand hand = new Hand(twoOfSpades, sixOfSpades, threeOfSpades, fourOfDiamonds, sixOfClubs, sixOfDiamonds, fourOfClubs);

		Set<Card> cards = CardCombination.THREE_OF_A_KIND.getCards(hand);
		Set<Card> expected = new HashSet<Card>(3);
		expected.add(sixOfSpades);
		expected.add(sixOfClubs);
		expected.add(sixOfDiamonds);

		assertEquals(expected, cards);
	}
	
	@Test public void testGetCardsForNoThreeOfAkind()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card threeOfSpades = new Card(Colour.SPADES, Rank.THREE);
		final Card fourOfDiamonds = new Card(Colour.DIAMONDS, Rank.FOUR);
		final Card sixOfClubs = new Card(Colour.CLUBS, Rank.SIX);
		final Card sixOfSpades = new Card(Colour.SPADES, Rank.SIX);
		final Card kingOfDiamonds = new Card(Colour.DIAMONDS, Rank.KING);
		final Card fourOfClubs = new Card(Colour.CLUBS, Rank.FOUR);
		
		final Hand hand = new Hand(twoOfSpades, sixOfSpades, threeOfSpades, fourOfDiamonds, sixOfClubs, kingOfDiamonds, fourOfClubs);
		
		Set<Card> cards = CardCombination.THREE_OF_A_KIND.getCards(hand);
		
		assertEquals(0, CardCombination.THREE_OF_A_KIND.getCards(hand).size());
	}
	
	@Test public void testGetCardsForFourOfAkind()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card threeOfSpades = new Card(Colour.SPADES, Rank.THREE);
		final Card fourOfDiamonds = new Card(Colour.DIAMONDS, Rank.FOUR);
		final Card sixOfClubs = new Card(Colour.CLUBS, Rank.SIX);
		final Card sixOfSpades = new Card(Colour.SPADES, Rank.SIX);
		final Card sixOfDiamonds = new Card(Colour.DIAMONDS, Rank.SIX);
		final Card sixOfHearts = new Card(Colour.HEARTS, Rank.SIX);

		final Hand hand = new Hand(twoOfSpades, sixOfSpades, threeOfSpades, fourOfDiamonds, sixOfClubs, sixOfDiamonds, sixOfHearts);

		Set<Card> cards = CardCombination.FOUR_OF_A_KIND.getCards(hand);
		Set<Card> expected = new HashSet<Card>(3);
		expected.add(sixOfSpades);
		expected.add(sixOfClubs);
		expected.add(sixOfDiamonds);
		expected.add(sixOfHearts);

		assertEquals(expected, cards);
	}
	
	@Test
	public void testGetNoCardsForFourOfAKind()
	{
		final Card twoOfSpades = new Card(Colour.SPADES, Rank.TWO);
		final Card threeOfSpades = new Card(Colour.SPADES, Rank.THREE);
		final Card fourOfDiamonds = new Card(Colour.DIAMONDS, Rank.FOUR);
		final Card sixOfClubs = new Card(Colour.CLUBS, Rank.SIX);
		final Card sixOfSpades = new Card(Colour.SPADES, Rank.SIX);
		final Card sixOfDiamonds = new Card(Colour.DIAMONDS, Rank.SIX);
		final Card nineOfHearts = new Card(Colour.HEARTS, Rank.NINE);

		final Hand hand = new Hand(twoOfSpades, sixOfSpades, threeOfSpades, fourOfDiamonds, sixOfClubs, sixOfDiamonds, nineOfHearts);

		assertEquals(0, CardCombination.FOUR_OF_A_KIND.getCards(hand).size());
	}

}
