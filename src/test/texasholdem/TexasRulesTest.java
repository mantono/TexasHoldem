package test.texasholdem;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
	private final TexasRules rules = new TexasRules();

	@Test
	public void testHasStraight()
	{
		Card flop1 = new Card(Colour.CLUBS, Rank.TWO);
		Card flop2 = new Card(Colour.DIAMONDS, Rank.SEVEN);
		Card flop3 = new Card(Colour.CLUBS, Rank.NINE);
		Card turn = new Card(Colour.CLUBS, Rank.JACK);
		Card river = new Card(Colour.HEARTS, Rank.SIX);
				
		Card card1 = new Card(Colour.HEARTS, Rank.TWO);
		Card card2 = new Card(Colour.SPADES, Rank.THREE);
		
		Card card3 = new Card(Colour.DIAMONDS, Rank.TEN);
		Card card4 = new Card(Colour.DIAMONDS, Rank.EIGHT);
		
		Hand straightHand = new Hand(card3, card4);
		straightHand.addToHand(flop1,flop2,flop3,turn,river);
		
		Hand pairOfTwosHand = new Hand(card1, card2);
		pairOfTwosHand.addToHand(flop1,flop2,flop3,turn,river);
		
		assertEquals(1, rules.compare(straightHand, pairOfTwosHand));
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
		
		assertEquals(1, rules.compare(straightFlushHand, emptyHand));
	}
	
	@Test
	public void testTwoHandsWithFourOfAKind()
	{
		Card flop1 = new Card(Colour.SPADES, Rank.TWO);
		Card flop2 = new Card(Colour.HEARTS, Rank.TWO);
		Card flop3 = new Card(Colour.SPADES, Rank.THREE);
		Card turn = new Card(Colour.HEARTS, Rank.THREE);
		Card river = new Card(Colour.DIAMONDS, Rank.KING);
		
		Card card1 = new Card(Colour.CLUBS, Rank.THREE);
		Card card2 = new Card(Colour.DIAMONDS, Rank.THREE);
		
		Card card3 = new Card(Colour.CLUBS, Rank.TWO);
		Card card4 = new Card(Colour.DIAMONDS, Rank.TWO);
		
		Hand lowFourOfAKind = new Hand(flop1, flop2, flop3, turn, river, card3, card4);
		Hand highFourOfAKind = new Hand(flop1, flop2, flop3, turn, river, card1, card2);
		
		assertEquals(1, rules.compare(highFourOfAKind, lowFourOfAKind));
	}
	
	@Test
	public void testTieOnHasStraight()
	{
		Card flop1 = new Card(Colour.HEARTS, Rank.TWO);
		Card flop2 = new Card(Colour.CLUBS, Rank.THREE);
		Card flop3 = new Card(Colour.DIAMONDS, Rank.KING);
		Card turn = new Card(Colour.SPADES, Rank.FIVE);
		Card river = new Card(Colour.HEARTS, Rank.SIX);
		
		Card card1 = new Card(Colour.SPADES, Rank.FOUR);
		Card card2 = new Card(Colour.SPADES, Rank.ACE);
		
		Card card3 = new Card(Colour.SPADES, Rank.FOUR);
		Card card4 = new Card(Colour.SPADES, Rank.SEVEN);
		
		
		Hand lowStraight = new Hand(flop1, flop2, flop3, turn, river, card1, card2);
		Hand highStraight = new Hand(flop1, flop2, flop3, turn, river, card3, card4);
		
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
		
		assertEquals(1, rules.compare(straightHand,emptyHand));
	}
	
	@Ignore
	@Test
	public void testCompareThreeOfAKind()
	{
		Hand threeOfAKindHand = new Hand();
		assertEquals(1, rules.compare(threeOfAKindHand, new Hand()));
	}
	
	@Test
	public void testCompareTwoPairsOfDifferentRank()
	{
		Card flop1 = new Card(Colour.CLUBS, Rank.TWO);
		Card flop2 = new Card(Colour.DIAMONDS, Rank.SEVEN);
		Card flop3 = new Card(Colour.CLUBS, Rank.NINE);
		Card turn = new Card(Colour.CLUBS, Rank.JACK);
		Card river = new Card(Colour.HEARTS, Rank.SIX);
		
		Card card1hand1 = new Card(Colour.HEARTS, Rank.TWO);
		Card card2hand1 = new Card(Colour.SPADES, Rank.THREE);
		
		Card card1hand2 = new Card(Colour.DIAMONDS, Rank.ACE);
		Card card2hand2 = new Card(Colour.CLUBS, Rank.ACE);
		
		Hand pairOfTwosHand = new Hand(card1hand1, card2hand1);
		pairOfTwosHand.addToHand(flop1, flop2, flop3, turn, river);
		Hand pairOfAcesHand = new Hand(card1hand2, card2hand2);
		pairOfAcesHand.addToHand(flop1, flop2, flop3, turn, river);
		
		assertEquals(-1, rules.compare(pairOfTwosHand, pairOfAcesHand));
		
		pairOfTwosHand.newHand(new Card(Colour.SPADES, Rank.TWO), new Card(Colour.DIAMONDS, Rank.ACE));
		Hand pairOfKingsHand = new Hand(new Card(Colour.CLUBS, Rank.KING), new Card(Colour.DIAMONDS, Rank.KING));
		pairOfTwosHand.addToHand(flop1, flop2, flop3, turn, river);
		pairOfKingsHand.addToHand(flop1, flop2, flop3, turn, river);
		assertEquals(-1, rules.compare(pairOfTwosHand, pairOfKingsHand));
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
