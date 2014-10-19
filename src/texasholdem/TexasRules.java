package texasholdem;

import java.util.List;

import cards.Card;
import cards.Colour;
import cards.Hand;
import cards.Rank;
import cards.Rules;

public class TexasRules implements Rules
{

	@Override
	public Hand declareWinner(List<Hand> hands)
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDraw(List<Hand> hands)
	{
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean equals(Object object)
	{
		return false;
	}

	@Override
	public int hashCode(){
		return -1;
	}

	@Override
	public int compare(Hand hand1, Hand hand2)
	{
		int bestCombinationHand1 = 0;
		int bestCombinationHand2 = 0;
		for(CardCombination combination : CardCombination.values()){
			if(hasCombination(hand1, combination))
				bestCombinationHand1 = combination.getValue();
			if(hasCombination(hand2, combination))
				bestCombinationHand2 = combination.getValue();
		}
		boolean tieOnCombination = bestCombinationHand1 == bestCombinationHand2 && bestCombinationHand1 > 14;
		if(tieOnCombination){
			bestCombinationHand1 = bestCombinationHand2 = 0; //Vi måste bara ta bort alla kort förutom de som ingår i den bästa kombinationen så fungerar detta! 
		}
		if(bestCombinationHand1 == 0)
			bestCombinationHand1 = getHighestCard(hand1);
		if(bestCombinationHand2 == 0)
			bestCombinationHand2 = getHighestCard(hand2);
		return bestCombinationHand1 - bestCombinationHand2;
	}

	private boolean hasCombination(Hand hand, CardCombination combination) {
		switch (combination) {
		case PAIR: return (hasPair(hand));
		case TWO_PAIR: return (hasTwoPair(hand));
		case THREE_OF_A_KIND: return (hasThreeOfAKind(hand));
		case STRAIGHT: return (hasStraight(hand));
		case FLUSH:	return (hasFlush(hand));
		case FULL_HOUSE: return (hasFullHouse(hand));
		case FOUR_OF_A_KIND: return (hasFourOfAkind(hand));
		case STRAIGHT_FLUSH: return (hasStraightFlush(hand));
		default: return false;
		}
	}

	private int getHighestCard(Hand hand) {
		if(hand.getNumberOfCards() == 0)
			return 0;
		Rank bestRankOnHand = Rank.TWO;
		for (Card card : hand.copyOfAllCards()) {
			if (card.getRank().compareTo(bestRankOnHand) < 0)
				bestRankOnHand = card.getRank();

		}
		return bestRankOnHand.getValue();
	}

	private boolean hasStraightFlush(Hand hand) {
		hand.sort();
		int counter = 0;
		int previousCard = 0;
		Colour previousColour = null;
		for (Card card : hand.copyOfAllCards()) {
			if(previousCard == 0 || ((card.getRank().getValue()-previousCard)<=1 && previousColour == card.getColour()))
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

	private boolean hasFourOfAkind(Hand hand) {
		for (Card card : hand.copyOfAllCards()) {
			if (hand.getNumberOfRank(card.getRank()) == 4)
				return true;

		}
		return false;
	}

	private boolean hasFullHouse(Hand hand) {
		if (hasThreeOfAKind(hand) & hasPair(hand))
			return true;
		return false;
	}

	private boolean hasFlush(Hand hand) {
		for (Card card : hand.copyOfAllCards()) {
			if (hand.getNumberOfColour(card.getColour()) == 5)
				return true;

		}
		return false;
	}

	private boolean hasStraight(Hand hand) {
		hand.sort();
		int counter = 0;
		int previousCard = 0;
		for (Card card : hand.copyOfAllCards()) {
			if(previousCard == 0 || (card.getRank().getValue()-previousCard)<=1)
				counter++;
			else
				counter = 0;
			if(counter == 5)
				return true;
			previousCard = card.getRank().getValue();
		}
		return false;
	}

	private boolean hasThreeOfAKind(Hand hand) {
		for (Card card : hand.copyOfAllCards()) {
			if (hand.getNumberOfRank(card.getRank()) == 3)
				return true;

		}
		return false;
	}

	private boolean hasPair(Hand hand) {
		for (Card card : hand.copyOfAllCards()) {
			if (hand.getNumberOfRank(card.getRank()) == 2)
				return true;

		}
		return false;
	}

	private boolean hasTwoPair(Hand hand) {
		int counter = 0;
		for (Card card : hand.copyOfAllCards()) {
			if (hand.getNumberOfRank(card.getRank()) == 2)
				counter++;

		}
		if (counter >= 4)
			return true;
		return false;
	}
}
