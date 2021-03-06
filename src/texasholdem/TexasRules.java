package texasholdem;

import java.util.Collections;
import java.util.EnumSet;
import java.util.LinkedList;
import java.util.List;

import cards.Card;
import cards.Hand;
import cards.Rank;
import cards.Rules;

public class TexasRules implements Rules
{
	private final static EnumSet<CardCombination> fiveCardCombinations = EnumSet.of(CardCombination.STRAIGHT_FLUSH, CardCombination.STRAIGHT, CardCombination.FLUSH, CardCombination.FULL_HOUSE);

	@Override
	public List<Hand> declareWinner(List<Hand> hands)
	{
		Collections.sort(hands, new TexasRules());
		final List<Hand> winners = new LinkedList<Hand>();
		winners.add(hands.remove(hands.size()-1));
		
		for(Hand hand : hands)
			if(compare(hand, winners.get(0)) == 0)
				winners.add(hand);
		
		return winners;
	}

	@Override
	public boolean isDraw(List<Hand> hands)
	{
		Hand previousHand = null; 
		for(Hand hand : hands)
		{
			if(previousHand != null)
			{
				int result = compare(previousHand, hand);
				if(result != 0)
					return false;
			}
			previousHand = hand;
		}
		return true;
	}

	@Override
	public int compare(Hand hand1, Hand hand2)
	{
		assert hand1.getNumberOfCards() == hand2.getNumberOfCards();
		for(CardCombination combination : CardCombination.values())
		{
			final boolean hand1HasCombination = combination.inHand(hand1);
			final boolean hand2HasCombination = combination.inHand(hand2);
			if(hand1HasCombination && !hand2HasCombination)
				return 1;
			else if(!hand1HasCombination && hand2HasCombination)
				return -1;
			else if(hand1HasCombination && hand2HasCombination && combinationUsesFiveCards(combination))
				return comapareHighestCardInCombination(combination, hand1, hand2);
			else if(hand1HasCombination && hand2HasCombination)
			{
				final int result = comapareHighestCardInCombination(combination, hand1, hand2);
				if(result != 0)
					return result;
				return compareKicker(hand1, hand2);
			}
		}
		return compareKicker(hand1, hand2);
	}
	
	public CardCombination getHighestCardCombination(Hand hand)
	{
		for(CardCombination combination : CardCombination.values())
			if(combination.inHand(hand))
				return combination;
		return CardCombination.NONE;
	}

	private int comapareHighestCardInCombination(CardCombination combination, Hand hand1, Hand hand2)
	{
		Hand hand1WithCombinationCardsOnly = new Hand(combination.getCards(hand1));
		Hand hand2WithCombinationCardsOnly = new Hand(combination.getCards(hand2));
		hand1WithCombinationCardsOnly.sort();
		hand2WithCombinationCardsOnly.sort();
		
		final int lastCard = hand1WithCombinationCardsOnly.getNumberOfCards() - 1;
		final Card highestCardHand1 = hand1WithCombinationCardsOnly.dropCard(lastCard);
		final Card highestCardHand2 = hand2WithCombinationCardsOnly.dropCard(lastCard);
		
		return highestCardHand1.compareTo(highestCardHand2);
	}

	private int compareKicker(Hand hand1, Hand hand2)
	{
		List<Card> cardsHand1 = sortAndReverse(hand1);
		List<Card> cardsHand2 = sortAndReverse(hand2);
		
		for(int i = 0; i < cardsHand1.size(); i++)
		{
			Card cardInHand1 = cardsHand1.get(i);
			Card cardInHand2 = cardsHand2.get(i);
			if(cardInHand1.getRank() != cardInHand2.getRank())
				return cardInHand1.compareTo(cardInHand2);
		}
		return 0;
	}

	private List<Card> sortAndReverse(Hand hand)
	{
		List<Card> cards = hand.copyOfAllCards();
		Collections.sort(cards);
		Collections.reverse(cards);
		return cards;
	}

	private boolean combinationUsesFiveCards(CardCombination combination)
	{
		return fiveCardCombinations.contains(combination);
	}
}
