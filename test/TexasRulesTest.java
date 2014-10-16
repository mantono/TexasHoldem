import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import texasholdem.TexasRules;
import cards.Colour;
import cards.Hand;
import cards.Card;
import cards.Rank;


public class TexasRulesTest
{
	private Hand goodHand, badHand;
	private Card flop, turn, river;
	private TexasRules rules = new TexasRules();

	@Before
	public void setup() throws Exception
	{
		flop = new Card(Colour.CLUBS, Rank.TWO);
		turn = new Card(Colour.CLUBS, Rank.JACK);
		river = new Card(Colour.HEARTS, Rank.SIX);
		
		Card card1 = new Card(Colour.HEARTS, Rank.TWO);
		Card card2 = new Card(Colour.SPADES, Rank.THREE);
		
		Card card3 = new Card(Colour.DIAMONDS, Rank.ACE);
		Card card4 = new Card(Colour.CLUBS, Rank.ACE);
				
		badHand = new Hand(card1, card2);
		badHand.addToHand(flop, turn, river);
		goodHand = new Hand(card3, card4);
		goodHand.addToHand(flop, turn, river);
	}

	@Test
	public void testCompare()
	{
		assertEquals(-12, rules.compare(badHand, goodHand));
	}

	@Test
	public void testDeclareWinner()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testIsDraw()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testHashCode()
	{
		fail("Not yet implemented");
	}

	@Test
	public void testEquals()
	{
		fail("Not yet implemented");
	}

}
