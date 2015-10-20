package test.texasholdem;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import texasholdem.CardCombination;
import texasholdem.TexasRules;
import cards.Colour;
import cards.Hand;
import cards.Card;
import cards.Rank;
import cards.Rules;


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
	
	@Test
	public void testCompareThreeOfAKind()
	{
		Hand threeOfAKindHand = new Hand();
		Card card1 = new Card(Colour.DIAMONDS, Rank.FIVE);
		Card card2 = new Card(Colour.HEARTS, Rank.FIVE);
		Card card3 = new Card(Colour.CLUBS, Rank.FIVE);
		threeOfAKindHand.addToHand(card1);
		threeOfAKindHand.addToHand(card2);
		threeOfAKindHand.addToHand(card3);
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
		
		assertTrue(rules.compare(pairOfTwosHand, pairOfAcesHand) < 0);
		
		pairOfTwosHand.newHand(new Card(Colour.SPADES, Rank.TWO), new Card(Colour.DIAMONDS, Rank.ACE));
		Hand pairOfKingsHand = new Hand(new Card(Colour.CLUBS, Rank.KING), new Card(Colour.DIAMONDS, Rank.KING));
		pairOfTwosHand.addToHand(flop1, flop2, flop3, turn, river);
		pairOfKingsHand.addToHand(flop1, flop2, flop3, turn, river);
		assertTrue(rules.compare(pairOfTwosHand, pairOfKingsHand) < 0);
	}
	
	@Test
	public void testCompareKickerOnPairOfSameRank()
	{
		Card flop1 = new Card(Colour.CLUBS, Rank.TWO);
		Card flop2 = new Card(Colour.DIAMONDS, Rank.TWO);
		
		Card threeOfClubs = new Card(Colour.CLUBS, Rank.THREE);
		
		Card fourOfHearts = new Card(Colour.HEARTS, Rank.FOUR);
		
		Hand hand1 = new Hand(flop1, flop2, threeOfClubs);
		Hand hand2 = new Hand(flop1, flop2, fourOfHearts);
		assertTrue(rules.compare(hand1, hand2) < 0);
	}

	@Test
	public void testDeclareWinnerWithOneWinner()
	{
		TexasRules rules = new TexasRules();
		
		Card flop1 = new Card(Colour.DIAMONDS, Rank.TWO);
		Card flop2 = new Card(Colour.DIAMONDS, Rank.FIVE);
		Card flop3 = new Card(Colour.HEARTS, Rank.SIX);
		Card turn = new Card(Colour.DIAMONDS, Rank.JACK);
		Card river = new Card(Colour.SPADES, Rank.EIGHT);
		
		Card player1_1 = new Card(Colour.DIAMONDS, Rank.FOUR);
		Card player1_2 = new Card(Colour.DIAMONDS, Rank.QUEEN);
		
		Card player2_1 = new Card(Colour.CLUBS, Rank.TWO);
		Card player2_2 = new Card(Colour.CLUBS, Rank.THREE);
		
		Card player3_1 = new Card(Colour.HEARTS, Rank.ACE);
		Card player3_2 = new Card(Colour.HEARTS, Rank.KING);
		
		Hand player1 = new Hand(flop1, flop2, flop3, turn, river, player1_1, player1_2);
		assertEquals(CardCombination.FLUSH, rules.getHighestCardCombination(player1));
		
		Hand player2 = new Hand(flop1, flop2, flop3, turn, river, player2_1, player2_2);
		assertEquals(CardCombination.PAIR, rules.getHighestCardCombination(player2));
		
		Hand player3 = new Hand(flop1, flop2, flop3, turn, river, player3_1, player3_2);
		assertEquals(CardCombination.NONE, rules.getHighestCardCombination(player3));
		
		final List<Hand> hands = new ArrayList<Hand>(3);
		hands.add(player1);
		hands.add(player2);
		hands.add(player3);
		
		final List<Hand> winner = rules.declareWinner(hands);
		assertEquals(1, winner.size());
		assertEquals(player1, winner.get(0));
	}
	
	@Test
	public void testDeclareWinnerWithTwoWinners()
	{
		TexasRules rules = new TexasRules();
		
		Card flop1 = new Card(Colour.DIAMONDS, Rank.NINE);
		Card flop2 = new Card(Colour.DIAMONDS, Rank.THREE);
		Card flop3 = new Card(Colour.HEARTS, Rank.FOUR);
		Card turn = new Card(Colour.DIAMONDS, Rank.FIVE);
		Card river = new Card(Colour.SPADES, Rank.SIX);
		
		Card player1_1 = new Card(Colour.DIAMONDS, Rank.SEVEN);
		Card player1_2 = new Card(Colour.SPADES, Rank.QUEEN);
		
		Card player2_1 = new Card(Colour.CLUBS, Rank.SEVEN);
		Card player2_2 = new Card(Colour.CLUBS, Rank.THREE);
		
		Card player3_1 = new Card(Colour.HEARTS, Rank.JACK);
		Card player3_2 = new Card(Colour.HEARTS, Rank.KING);
		
		Hand player1 = new Hand(flop1, flop2, flop3, turn, river, player1_1, player1_2);
		assertEquals(CardCombination.STRAIGHT, rules.getHighestCardCombination(player1));
		
		Hand player2 = new Hand(flop1, flop2, flop3, turn, river, player2_1, player2_2);
		assertEquals(CardCombination.STRAIGHT, rules.getHighestCardCombination(player2));
		
		Hand player3 = new Hand(flop1, flop2, flop3, turn, river, player3_1, player3_2);
		assertEquals(CardCombination.NONE, rules.getHighestCardCombination(player3));
		
		final List<Hand> hands = new ArrayList<Hand>(3);
		hands.add(player1);
		hands.add(player2);
		hands.add(player3);
		
		final List<Hand> winner = rules.declareWinner(hands);
		assertEquals(2, winner.size());
		assertTrue(winner.get(0).equals(player1) || winner.get(0).equals(player2));
		assertTrue(winner.get(1).equals(player1) || winner.get(1).equals(player2));
	}
	
	@Test
	public void testDeclareWinnerOnKicker()
	{
		TexasRules rules = new TexasRules();
		
		Card flop1 = new Card(Colour.CLUBS, Rank.TWO);
		Card flop2 = new Card(Colour.SPADES, Rank.FOUR);
		Card flop3 = new Card(Colour.HEARTS, Rank.FIVE);
		Card turn = new Card(Colour.DIAMONDS, Rank.SEVEN);
		Card river = new Card(Colour.SPADES, Rank.NINE);
		
		Card player1_1 = new Card(Colour.DIAMONDS, Rank.KING);
		Card player1_2 = new Card(Colour.SPADES, Rank.QUEEN);
		
		Card player2_1 = new Card(Colour.CLUBS, Rank.JACK);
		Card player2_2 = new Card(Colour.CLUBS, Rank.THREE);
		
		Card player3_1 = new Card(Colour.HEARTS, Rank.JACK);
		Card player3_2 = new Card(Colour.HEARTS, Rank.EIGHT);
		
		Hand player1 = new Hand(flop1, flop2, flop3, turn, river, player1_1, player1_2);
		assertEquals(CardCombination.NONE, rules.getHighestCardCombination(player1));
		
		Hand player2 = new Hand(flop1, flop2, flop3, turn, river, player2_1, player2_2);
		assertEquals(CardCombination.NONE, rules.getHighestCardCombination(player2));
		
		Hand player3 = new Hand(flop1, flop2, flop3, turn, river, player3_1, player3_2);
		assertEquals(CardCombination.NONE, rules.getHighestCardCombination(player3));
		
		final List<Hand> hands = new ArrayList<Hand>(3);
		hands.add(player1);
		hands.add(player2);
		hands.add(player3);
		
		final List<Hand> winner = rules.declareWinner(hands);
		assertEquals(1, winner.size());
		assertEquals(player1, winner.get(0));
	}

	@Test
	public void testIsDraw()
	{
		Card flop1 = new Card(Colour.CLUBS, Rank.TWO);
		Card flop2 = new Card(Colour.DIAMONDS, Rank.TWO);
		
		Card fourOfClubs = new Card(Colour.CLUBS, Rank.FOUR);
		
		Card fourOfHearts = new Card(Colour.HEARTS, Rank.FOUR);
		
		Hand hand1 = new Hand(flop1, flop2, fourOfClubs);
		Hand hand2 = new Hand(flop1, flop2, fourOfHearts);
		assertTrue(rules.compare(hand1, hand2) == 0);
		List<Hand> hands = new ArrayList<Hand>(2);
		hands.add(hand1);
		hands.add(hand2);
		assertTrue(rules.isDraw(hands));
	}
}
