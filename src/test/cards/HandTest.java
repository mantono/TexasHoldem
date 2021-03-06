package test.cards;
import static org.junit.Assert.*;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import cards.*;

public class HandTest
{
	private Card card1, card2, card3;
	private List<Card> cards;
	private Hand hand;
	
	@Before
	public void setup()
	{
		card1 = new Card(Colour.HEARTS, Rank.EIGHT);
		card2 = new Card(Colour.HEARTS, Rank.ACE);
		card3 = new Card(Colour.CLUBS, Rank.QUEEN);
		cards = new ArrayList<Card>();
		cards.add(card1);
		cards.add(card2);
		cards.add(card3);
		hand = new Hand(card1, card2, card3);
	}
	
	@Test
	public void testGetNumberOfRank(){
		
		Hand hand1 = new Hand (cards);
		assertEquals(1,hand1.getNumberOfRank(Rank.EIGHT));
		
	}
	
	@Test
	public void testGetNumberOfColour(){
		
		Hand hand1 = new Hand (cards);
		assertEquals(2,hand1.getNumberOfColour(Colour.HEARTS));
		
	}
	
	@Test
	public void testConstructor()
	{
		Hand hand1 = new Hand(cards);
		assertEquals(3, hand1.getNumberOfCards());
		
		hand1 = new Hand();
		assertEquals(0, hand1.getNumberOfCards());
		
		Hand hand2 = new Hand(card1, card2, card3);
		assertEquals(3, hand2.getNumberOfCards());
		
	}
	
	@Test
	public void testSort()
	{
		String first = hand.toString();
		hand.sort();
		String second = hand.toString();
		assertFalse(first.equals(second));
	}
	
	@Test
	public void newHandTest()
	{
		int before = hand.getNumberOfCards();
		Card card4 = new Card(Colour.DIAMONDS, Rank.JACK);
		hand.newHand(card4, card3);
		assertEquals(2, hand.getNumberOfCards());
		assertEquals(card4, hand.dropCard(0));
		
		hand.newHand(cards);
	}	
	
	@Test
	public void addToHandTest()
	{
		hand.addToHand(card2);
		assertEquals(4, hand.getNumberOfCards());
		hand.addToHand(cards);
		assertEquals(7, hand.getNumberOfCards());
	}	
	
	@Test
	public void clearHandTest()
	{
		assertEquals(3, hand.getNumberOfCards());
		hand.clearHand();
		assertEquals(0, hand.getNumberOfCards());
	}	
	
	@Test
	public void dropCardTest()
	{
		hand.dropCard(2);
		assertEquals(2, hand.getNumberOfCards());
		
		assertTrue(hand.dropCard(card2));
		assertEquals(1, hand.getNumberOfCards());
	}	
	
	@Test
	public void containsTest()
	{
		assertTrue(hand.contains(card1));
		assertTrue(hand.contains(card2));
		Card card4 = new Card(Colour.SPADES, Rank.JACK);
		assertFalse(hand.contains(card4));
	}
	
	@Test
	public void cloneTest()
	{
		Player player = new Player("anton");
		player.newHand(card1, card2, card3);
		Hand secondHand = player.getHand();
		assertEquals(hand, secondHand);
	}
	
	@Test
	public void cloneIntegrityTest()
	{
		Player player = new Player("anton");
		player.newHand(card1, card2, card3);
		Hand secondHand = player.getHand();
		hand.dropCard(0);
		assertEquals(2, hand.getNumberOfCards());
		assertEquals(3, player.getNumberOfCards());
	}
	
	@Test
	public void equalsTest()
	{
		Hand hand2 = new Hand();
		Hand hand3 = new Hand(card1, card2, card3);
		Card card4 = new Card(Colour.HEARTS, Rank.THREE);
		Hand hand4 = new Hand(card1, card2, card3, card4);
		
		assertTrue(hand.equals(hand3));
		assertFalse(hand.equals(hand2));
		assertFalse(hand.equals(hand4));
		assertFalse(hand.equals(new Object()));
		assertFalse(hand.equals(null));
	}
	
	@Test
	public void hashCodeTestEmptyHand()
	{
		hand = new Hand();
		assertEquals(1, hand.hashCode());
	}
	
	@Test
	public void hashCodeTestDistribution()
	{
		final int number = 10000;
		final Set<Integer> hashCodes = new HashSet<Integer>(number);
		final Set<Hand> hands = new HashSet<Hand>(number);
		for(int i = 0; i < number; i++)
		{
			final Hand hand = new Hand();
			for(int n = 0; n < 10; n++)
				hand.addToHand(getRandomCard());
			hashCodes.add(hand.hashCode());
			hands.add(hand);
		}
		final float distribution = ((float) hashCodes.size()/hands.size());
		assertTrue("Distribution:" + distribution, distribution > 0.99);
	}
	
	@Test
	public void hashCodeTestMultipleHands()
	{
		for(int i = 0; i < 100; i++)
		{
			final Hand hand = new Hand();
			final Hand differentHand = new Hand();

			for(int n = 0; n < 10; n++)
			{
				hand.addToHand(getRandomCard());
				differentHand.addToHand(getRandomCard());
			}
			
			final Hand equalHand = new Hand(hand);
			
			assertEquals(hand, equalHand);
			assertEquals(hand.hashCode(), equalHand.hashCode());
			assertFalse(hand.equals(differentHand));
			assertFalse(hand.hashCode() == differentHand.hashCode());
		}
	}
	
	private Card getRandomCard()
	{
		Colour colour = getRandomColour();
		Rank rank = getRandomRank();
		return new Card(colour, rank);
	}

	private Colour getRandomColour()
	{
		final SecureRandom random = new SecureRandom();
		final int stop = random.nextInt(4);
		for(Colour colour : Colour.values())
			if(colour.getValue() == stop)
				return colour;
		throw new IllegalStateException("We are not supposed to ber here...");
	}

	private Rank getRandomRank()
	{
		final SecureRandom random = new SecureRandom();
		final int stop = random.nextInt(12) + 2;
		for(Rank rank : Rank.values())
			if(rank.getValue() == stop)
				return rank;
		throw new IllegalStateException("We are not supposed to ber here...");
	}
}
