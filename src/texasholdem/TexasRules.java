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
		if(bestCombinationHand1 == 0)
			bestCombinationHand1 = getHighestCard(hand1);
		if(bestCombinationHand2 == 0)
			bestCombinationHand2 = getHighestCard(hand2);
		return bestCombinationHand1 - bestCombinationHand2;
	}

	private boolean hasCombination(Hand hand, CardCombination combination) {
		switch (combination) {
		case PAIR:
			if (hasPair(hand))
				return true;
		case TWO_PAIR:
			if (hasTwoPair(hand))

				return true;
		case THREE_OF_A_KIND:
			if (hasThreeOfAKind(hand))
				return true;
		case STRAIGHT:
			if (hasStraight(hand))
				return true;
		case FLUSH:
			if (hasFlush(hand))
				return true;
		case FULL_HOUSE:
			if (hasFullHouse(hand))
				return true;
		case FOUR_OF_A_KIND:
			if (hasFourOfAkind(hand))
				return true;
		case STRAIGHT_FLUSH:
			if (hasStraightFlush(hand))
				return true;
		default:
			return false;
		}
	}

	private int getHighestCard(Hand hand) {
		Rank bestRankOnHand = Rank.TWO;
		for (Card card : hand.copyOfAllCards()) {
			if (card.getRank().compareTo(bestRankOnHand) < 0)
				bestRankOnHand = card.getRank();

		}
		return bestRankOnHand.getValue();
	}

	private boolean hasStraightFlush(Hand hand) {
		// TODO Auto-generated method stub
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
			if(previousCard == 0 || (card.getRank().getValue()-previousCard)<=1){
				counter++;
				previousCard = card.getRank().getValue();
			}else{
				counter = 0;
			}
			if(counter == 5)
				return true;
				
		}

		// TODO Auto-generated method stub
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
