package test;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import texasholdem.TexasRules;
import cards.Colour;
import cards.Hand;
import cards.Card;
import cards.Rank;


public class TexasRulesTest
{
	private Hand pairOfAcesHand, pairOfTwosHand, emptyHand;
	private Card flop1, flop2, flop3, turn, river;
	private TexasRules rules = new TexasRules();

	@Before
	public void setup() throws Exception
	{
		flop1 = new Card(Colour.CLUBS, Rank.TWO);
		flop2 = new Card(Colour.DIAMONDS, Rank.SEVEN);
		flop3 = new Card(Colour.CLUBS, Rank.NINE);
		turn = new Card(Colour.CLUBS, Rank.JACK);
		river = new Card(Colour.HEARTS, Rank.SIX);
		
		Card card1 = new Card(Colour.HEARTS, Rank.TWO);
		Card card2 = new Card(Colour.SPADES, Rank.THREE);
		
		Card card3 = new Card(Colour.DIAMONDS, Rank.ACE);
		Card card4 = new Card(Colour.CLUBS, Rank.ACE);
				
		pairOfTwosHand = new Hand(card1, card2);
		pairOfTwosHand.addToHand(flop1, flop2, flop3, turn, river);
		pairOfAcesHand = new Hand(card3, card4);
		pairOfAcesHand.addToHand(flop1, flop2, flop3, turn, river);
		
		emptyHand = new Hand();
	}

	@Test
	public void testHasStraight()
	{
		Hand straightHand = new Hand(new Card(Colour.DIAMONDS, Rank.TEN),new Card(Colour.DIAMONDS, Rank.EIGHT));
		straightHand.addToHand(flop1,flop2,flop3,turn,river); //Hela handen: 2, 6, (7, 8, 9, 10, J)
		//andra handen: (2, 2), 3, 6, 7, 9, 11
		assertEquals(3, rules.compare(straightHand, pairOfTwosHand)); //straight(18) - par(15) = 3 
		// detta ska bli 3 då straighthand har straight(18) och badHand har pair(15)
	}
	
	
	
	@Test
	public void testHasStraightFlush()
	{
		Card card1 = new Card(Colour.SPADES, Rank.TWO);
		Card card2 = new Card(Colour.SPADES, Rank.THREE);
		Card card3 = new Card(Colour.SPADES, Rank.FOUR);
		Card card4 = new Card(Colour.SPADES, Rank.FIVE);
		Card card5 = new Card(Colour.SPADES, Rank.SIX);
		Card card6 = new Card(Colour.SPADES, Rank.TWO);
		Card card7 = new Card(Colour.SPADES, Rank.TWO);
		
		Hand straightFlushHand = new Hand(card1, card2, card3, card4, card5, card6, card7);
		Hand emptyHand = new Hand();
		
		assertEquals(22, rules.compare(straightFlushHand, emptyHand));
	}
	
	@Test
	public void testTieOnFourOfAKind()
	{
		Card card1 = new Card(Colour.SPADES, Rank.TWO);
		Card card2 = new Card(Colour.HEARTS, Rank.TWO);
		Card card3 = new Card(Colour.CLUBS, Rank.TWO);
		Card card4 = new Card(Colour.DIAMONDS, Rank.TWO);
		Card card5 = new Card(Colour.SPADES, Rank.THREE);
		Card card6 = new Card(Colour.HEARTS, Rank.THREE);
		Card card7 = new Card(Colour.CLUBS, Rank.THREE);
		Card card8 = new Card(Colour.DIAMONDS, Rank.THREE);
		Card card9 = new Card(Colour.DIAMONDS, Rank.KING);
		
		Hand lowFourOfAKind = new Hand(card1, card2, card3, card4,card9);
		Hand highFourOfAKind = new Hand(card5,card6,card7,card8);
		Hand emptyHand = new Hand();
		
		assertEquals(1, rules.compare(highFourOfAKind, lowFourOfAKind));
	}
	
	@Ignore
	@Test
	public void testTieOnHasStraight()
	{
		Card card1 = new Card(Colour.SPADES, Rank.ACE);
		Card card2 = new Card(Colour.HEARTS, Rank.TWO);
		Card card3 = new Card(Colour.CLUBS, Rank.THREE);
		Card card4 = new Card(Colour.DIAMONDS, Rank.FOUR);
		Card card5 = new Card(Colour.SPADES, Rank.FIVE);
		Card card6 = new Card(Colour.HEARTS, Rank.SIX);
		
		
		Hand lowStraight = new Hand(card1, card2, card3, card4,card5);
		Hand highStraight = new Hand(card2,card3,card4,card5,card6);
		Hand emptyHand = new Hand();
		
		assertEquals(1, rules.compare(highStraight, lowStraight));
	}
	
	@Test
	public void testAceAsOneInStraight()
	{
		Card card1 = new Card(Colour.DIAMONDS, Rank.TWO);
		Card card2 = new Card(Colour.SPADES, Rank.THREE);
		Card card3 = new Card(Colour.SPADES, Rank.FOUR);
		Card card4 = new Card(Colour.SPADES, Rank.FIVE);
		Card card5 = new Card(Colour.SPADES, Rank.ACE);
		Hand straightHand = new Hand(card1, card2, card3, card4, card5);
		Hand emptyHand = new Hand();
		
		assertEquals(18, rules.compare(straightHand,emptyHand));
	}
	
	@Ignore
	@Test
	public void testCompareThreeOfAKind()
	{
		Hand threeOfAKindHand = new Hand();
		assertEquals(17, rules.compare(threeOfAKindHand, emptyHand));
	}
	
	@Ignore
	@Test
	public void testCompareTwoPairsOfDifferentRank()
	{
		assertEquals(-12, rules.compare(pairOfTwosHand, pairOfAcesHand));
		
		pairOfTwosHand.newHand(new Card(Colour.SPADES, Rank.TWO), new Card(Colour.DIAMONDS, Rank.ACE));
		Hand pairOfKingsHand = new Hand(new Card(Colour.CLUBS, Rank.KING), new Card(Colour.DIAMONDS, Rank.KING));
		pairOfTwosHand.addToHand(flop1, flop2, flop3, turn, river);
		pairOfKingsHand.addToHand(flop1, flop2, flop3, turn, river);
		assertEquals(-11, rules.compare(pairOfTwosHand, pairOfKingsHand));
	}

	@Ignore
	@Test
	public void testDeclareWinner()
	{
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testIsDraw()
	{
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testHashCode()
	{
		fail("Not yet implemented");
	}

	@Ignore
	@Test
	public void testEquals()
	{
		fail("Not yet implemented");
	}
}
