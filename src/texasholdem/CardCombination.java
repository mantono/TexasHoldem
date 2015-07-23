package texasholdem;

import java.util.ArrayList;

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
	PAIR(15);
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

	private static boolean hasStraightFlush(final Hand hand)
	{
		hand.sort();
		int counter = 0;
		int previousCard = 0;
		Colour previousColour = null;
		for(Card card : hand.copyOfAllCards())
		{
			if(previousCard == 0 || ((card.getRank().getValue() - previousCard) <= 1 && previousColour == card
					.getColour()))
				counter++;
			else
				counter = 0;
			if(counter == 5)
				return true;
			previousCard = card.getRank().getValue();
			previousColour = card.getColour();
		}
		return false;
	}

	private static boolean hasFourOfAKind(Hand hand)
	{
		ArrayList<Card> tempCards = new ArrayList<Card>();
		for(Card card : hand.copyOfAllCards())
		{
			if(hand.getNumberOfRank(card.getRank()) == 4)
			{
				for(Card innerCard : hand.copyOfAllCards())
				{
					if(innerCard.getRank() == card.getRank())
					{
						tempCards.add(innerCard);
					}

				}
				hand.newHand(tempCards);
				return true;

			}

		}
		return false;
	}

	private static boolean hasFullHouse(Hand hand)
	{
		if(hasThreeOfAKind(hand) && hasPair(hand))
			return true;
		return false;
	}

	private boolean hasFlush(Hand hand)
	{
		for(Card card : hand.copyOfAllCards())
		{
			if(hand.getNumberOfColour(card.getColour()) == 5)
				return true;

		}
		return false;
	}

	private static boolean hasStraight(Hand hand)
	{
		ArrayList<Card> tempCards = new ArrayList<Card>();
		hand.sort();
		int counter = 0;
		int previousCard = 0;
		for(Card card : hand.copyOfAllCards())
		{
			if(card.getRank().getValue() == 14)
			{
				counter = 1;
				previousCard = 1;
			}
		}
		for(Card card : hand.copyOfAllCards())
		{
			if(previousCard == 0 || (card.getRank().getValue() - previousCard) <= 1)
			{
				if(previousCard == 0 || (card.getRank().getValue() - previousCard) == 1)
				{
					counter++;
				}
				tempCards.add(card);
			}
			else
			{
				counter = 0;
				tempCards.clear();
			}
			if(counter == 5)
			{
				hand.newHand(tempCards);
				return true;
			}
			previousCard = card.getRank().getValue();
		}
		return false;
	}

	private static boolean hasThreeOfAKind(Hand hand)
	{
		ArrayList<Card> tempCards = new ArrayList<Card>();
		for(Card card : hand.copyOfAllCards())
		{
			if(hand.getNumberOfRank(card.getRank()) == 3)
			{
				for(Card innerCard : hand.copyOfAllCards())
				{
					if(innerCard.getRank() == card.getRank())
					{
						tempCards.add(innerCard);
					}

				}
				hand.newHand(tempCards);
				return true;

			}

		}
		return false;
	}

	private static boolean hasTwoPair(Hand hand)
	{
		int counter = 0;
		for(Card card : hand.copyOfAllCards())
			if(hand.getNumberOfRank(card.getRank()) == 2)
				counter++;

		return counter >= 4;
	}

	private static boolean hasPair(Hand hand)
	{
		for(Card card : hand.copyOfAllCards())
		{
			if(hand.getNumberOfRank(card.getRank()) == 2)
				return true;
		}
		return false;
	}
}
