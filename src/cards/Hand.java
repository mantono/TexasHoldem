package cards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class Hand implements Cloneable
{
	private final List<Card> cards = new ArrayList<Card>();

	public Hand(List<Card> cards)
	{
		this.cards.addAll(cards);
	}
	
	public Hand(Card... cards)
	{
		this(Arrays.asList(cards));
	}
	
	public void newHand(Card... newCards)
	{
		cards.clear();
		addToHand(Arrays.asList(newCards));
	}
	
	public void newHand(List<Card> newCards)
	{
		cards.clear();
		cards.addAll(newCards);
	}

	public void addToHand(Card... newCards)
	{
		cards.addAll(Arrays.asList(newCards));
	}
	
	public void addToHand(List<Card> newCards)
	{
		cards.addAll(newCards);
	}

	public void clearHand()
	{
		cards.clear();
	}
	
	public Card dropCard(int index)
	{
		return cards.remove(index);
	}
	
	public boolean dropCard(Card card)
	{
		return cards.remove(card);
	}
	
	public boolean contains(Card card)
	{
		return cards.contains(card);
	}
	
	public void sort()
	{
		Collections.sort(cards);
	}
	
	public int getNumberOfCards()
	{
		return cards.size();
	}
	
	public List<Card> copyOfAllCards()
	{
		final List<Card> copyOfCards = new ArrayList<Card>();
		copyOfCards.addAll(cards);
		return copyOfCards;
	}
		
	@Override
	public String toString()
	{
		return cards.toString();
	}
	
	@Override
	protected Object clone()
	{
		return new Hand(copyOfAllCards());
	}
	
	@Override
	public boolean equals(Object object)
	{
		if(object == null)
			return false;
		if(!object.getClass().equals(this.getClass()))
			return false;
		Hand other = (Hand) object;
		return (this.cards.containsAll(other.cards) && other.cards.containsAll(cards));
	}
	
	@Override
	public int hashCode()
	{
		int sumOfCards = 0;
		for(Card card : cards)
		{
			sumOfCards += card.hashCode();
		}
		return sumOfCards;
	}
	
	public int getNumberOfRank(Rank rank)
	{
		int i = 0;
		for(Card card : cards)
		{
			if(card.getRank() == rank)
				i++;
		}
		return i;
	}
	
	public int getNumberOfColour(Colour colour)
	{
		int i = 0;
		for(Card card : cards)
		{
			if(card.getColour() == colour)
				i++;
		}
		return i;
	}
}
