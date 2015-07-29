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


}
