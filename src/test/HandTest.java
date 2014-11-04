package test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

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
	public void hashCodeTest()
	{
		assertEquals(434, hand.hashCode());
		hand = new Hand();
		assertEquals(0, hand.hashCode());
	}
}
