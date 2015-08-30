package texasholdem;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import cards.AceFirst;
import cards.Card;
import cards.Colour;
import cards.Hand;
import cards.Rank;

public enum CardCombination
{
	STRAIGHT_FLUSH(22),
	FOUR_OF_A_KIND(21),
	FULL_HOUSE(20),
	FLUSH(19),
	STRAIGHT(18),
	THREE_OF_A_KIND(17),
	TWO_PAIR(16),
	PAIR(15),
	NONE(0);
	private final int value;

	private CardCombination(int value)
	{
		this.value = value;
	}

	public int getValue()
	{
		return value;
	}

	public boolean inHand(Hand hand)
	{
		switch(this)
		{
			case PAIR:
				return hasPair(hand);
			case TWO_PAIR:
				return hasTwoPair(hand);
			case THREE_OF_A_KIND:
				return hasThreeOfAKind(hand);
			case STRAIGHT:
				return hasStraight(hand);
			case FLUSH:
				return hasFlush(hand);
			case FULL_HOUSE:
				return hasFullHouse(hand);
			case FOUR_OF_A_KIND:
				return hasFourOfAKind(hand);
			case STRAIGHT_FLUSH:
				return hasStraightFlush(hand);
			default:
				return false;
		}
	}

	private boolean hasStraightFlush(final Hand hand)
	{
		hand.sort();
		return getCardsForStraightFlush(hand).size() == 5;
	}

	private boolean hasFourOfAKind(Hand hand)
	{
		for(Card card : hand.copyOfAllCards())
			if(hand.getNumberOfRank(card.getRank()) == 4)
				return true;
		return false;
	}

	private boolean hasFullHouse(Hand hand)
	{
		if(hasThreeOfAKind(hand) && hasPair(hand))
			return true;
		return false;
	}

	private boolean hasFlush(Hand hand)
	{
		for(Colour colour : Colour.values())
			if(hand.getNumberOfColour(colour) == 5)
				return true;
		return false;
	}

	private boolean hasStraight(Hand hand)
	{
		hand.sort();
		if(containsStraight(hand))
			return true;
		hand.sort(new AceFirst());
		return containsStraight(hand);
	}

	private boolean containsStraight(Hand hand)
	{
		int straightCards = 0;
		Card previousCard = null;
		for(Card currentCard : hand.copyOfAllCards())
		{
			if(previousCard != null)
			{
				if(isAdjacentCardsByRank(previousCard, currentCard))
					straightCards++;
				else if(previousCard.getRank() != currentCard.getRank())
					straightCards = 0;
				if(straightCards == 4)
					return true;
			}
			previousCard = currentCard;
		}
		return false;
	}

	private boolean isAdjacentCardsByRank(Card card1, Card card2)
	{
		final boolean adjacent = card1.getRank().getValue() - card2.getRank().getValue() == -1;
		final boolean aceAndTwo = card1.getRank() == Rank.ACE && card2.getRank() == Rank.TWO;
		return adjacent || aceAndTwo;
	}

	private boolean hasThreeOfAKind(Hand hand)
	{
		for(Card card : hand.copyOfAllCards())
			if(hand.getNumberOfRank(card.getRank()) == 3)
				return true;
		return false;
	}

	private boolean hasTwoPair(Hand hand)
	{
		int counter = 0;
		for(Rank rank : Rank.values())
			if(hand.getNumberOfRank(rank) == 2)
				counter++;
		return counter == 2;
	}

	private boolean hasPair(Hand hand)
	{
		for(Card card : hand.copyOfAllCards())
		{
			if(hand.getNumberOfRank(card.getRank()) == 2)
				return true;
		}
		return false;
	}

	public Set<Card> getCards(Hand hand)
	{
		hand.sort();
		switch(this)
		{
			case PAIR:
				return getCardsForPair(hand);
			case TWO_PAIR:
				return getCardsForTwoPair(hand);
			case THREE_OF_A_KIND:
				return getCardsForThreeOfAKind(hand);
			case STRAIGHT:
				return getCardsForStraight(hand);
			case FLUSH:
				return getCardsForFlush(hand);
			case FULL_HOUSE:
				return getCardsForFullHouse(hand);
			case FOUR_OF_A_KIND:
				return getCardsForFourOfAKind(hand);
			case STRAIGHT_FLUSH:
				return getCardsForStraightFlush(hand);
			default:
				return null;
		}
	}

	private Set<Card> getCardsForStraight(Hand hand)
	{
		Set<Card> foundCards = findStraightCards(hand);
		if(foundCards.size() != 5)
		{
			hand.sort(new AceFirst());
			foundCards = findStraightCards(hand);
		}
		return foundCards;
		
	}
	
	private Set<Card> getCardsForStraightFlush(Hand hand)
	{
		final Map<Colour, SortedSet<Card>> cards = initiateCardColourMap();
		for(Card card : hand.copyOfAllCards())
			cards.get(card.getColour()).add(card);
		for(Colour colour : Colour.values())
			if(cards.get(colour).size() >= 5)
				return getCardsForStraight(new Hand(cards.get(colour)));
		return new TreeSet<Card>();
	}
	
	private Map<Colour, SortedSet<Card>> initiateCardColourMap()
	{
		Map<Colour, SortedSet<Card>> cards = new EnumMap<Colour, SortedSet<Card>>(Colour.class);
		for(Colour colour : Colour.values())
			cards.put(colour, new TreeSet<Card>());
		return cards;
	}
	
	private Set<Card> findStraightCards(Hand hand)
	{
		SortedSet<Card> foundCards = new TreeSet<Card>();
		Card previousCard = null;
		for(Card card : hand.copyOfAllCards())
		{
			if(foundCards.isEmpty())
				foundCards.add(card);
			else if(isAdjacentCardsByRank(previousCard, card))
				foundCards.add(card);
			else if(foundCards.size() == 5)
				return foundCards;
			else if(!sameRank(previousCard, card) && foundCards.size() < 5)
			{
				foundCards.clear();
				foundCards.add(card);
			}
			previousCard = card;
		}
		if(foundCards.size() < 5)
			foundCards.clear();
		else
			dropRedundantCards(foundCards);
			
		return foundCards;
	}

	private boolean sameRank(Card card1, Card card2)
	{
		return card1.getRank().equals(card2.getRank());
	}

	private Set<Card> getCardsForFlush(Hand hand)
	{
		SortedSet<Card> foundCards = new TreeSet<Card>();
		Colour flushColor = findFlushColor(hand);
		if(flushColor != null)
			for(Card card : hand.copyOfAllCards())
				if(card.getColour().equals(flushColor))
					foundCards.add(card);
		dropRedundantCards(foundCards);
		return foundCards;
	}

	private Colour findFlushColor(Hand hand)
	{
		for(Colour colour : Colour.values())
			if(hand.getNumberOfColour(colour) >= 5)
				return colour;
		return null;
	}

	private void dropRedundantCards(SortedSet<Card> foundCards)
	{
		while(foundCards.size() > 5)
			foundCards.remove(foundCards.first());
	}

	private Set<Card> getCardsForFourOfAKind(Hand hand)
	{
		Set<Card> cards = new HashSet<Card>(4);
		for(Card card : hand.copyOfAllCards())
			if(hand.getNumberOfRank(card.getRank()) == 4)
				cards.add(card);
		return cards;
	}

	private Set<Card> getCardsForFullHouse(Hand hand)
	{
		Set<Card> cards = CardCombination.PAIR.getCards(hand);
		cards.addAll(CardCombination.THREE_OF_A_KIND.getCardsForThreeOfAKind(hand));
		if(cards.size() != 5)
			cards.clear();
		return cards;
	}

	private Set<Card> getCardsForThreeOfAKind(Hand hand)
	{
		Set<Card> cards = new HashSet<Card>(3);
		for(Card card : hand.copyOfAllCards())
		{
			if(hand.getNumberOfRank(card.getRank()) == 3)
			{
				if(cards.size() == 3)
					cards.clear();
				cards.add(card);
			}
		}
		return cards;
	}

	private Set<Card> getCardsForTwoPair(Hand hand)
	{
		List<Card> cards = new LinkedList<Card>();
		for(Card card : hand.copyOfAllCards())
		{
			if(hand.getNumberOfRank(card.getRank()) == 2)
			{
				if(cards.size() == 4)
					cards.remove(0);
				cards.add(card);
			}
		}
		return new HashSet<Card>(cards);
	}

	private Set<Card> getCardsForPair(Hand hand)
	{
		Set<Card> cards = new HashSet<Card>(2);
		for(Card card : hand.copyOfAllCards())
		{
			if(hand.getNumberOfRank(card.getRank()) == 2)
			{
				if(cards.size() == 2)
					cards.clear();
				cards.add(card);
			}
		}
		return cards;
	}
}
