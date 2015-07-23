package texasholdem;

import java.util.List;

import cards.Card;
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
		return -1; //TODO behövs hashCode för rules?
	}

	@Override
	public int compare(Hand hand1, Hand hand2)
	{
		Hand hand1local = new Hand(hand1.copyOfAllCards());
		Hand hand2local = new Hand(hand2.copyOfAllCards());
		int bestCombinationHand1 = 0;
		int bestCombinationHand2 = 0;
		for(CardCombination combination : CardCombination.values()){
			if(combination.inHand(hand1local) && bestCombinationHand1 == 0)
				bestCombinationHand1 = combination.getValue();
			if(combination.inHand(hand2local) && bestCombinationHand2 == 0)
				bestCombinationHand2 = combination.getValue();
		}
		boolean tieOnCombination = bestCombinationHand1 == bestCombinationHand2 && bestCombinationHand1 > 14;
		if(tieOnCombination){
			bestCombinationHand1 = bestCombinationHand2 = 0; //Vi måste bara ta bort alla kort förutom de som ingår i den bästa kombinationen så fungerar detta! 
		}
		if(bestCombinationHand1 == 0)
			bestCombinationHand1 = getHighestCard(hand1local);
		if(bestCombinationHand2 == 0)
			bestCombinationHand2 = getHighestCard(hand2local);
		return bestCombinationHand1 - bestCombinationHand2;
	}

	private int getHighestCard(Hand hand) {
		if(hand.getNumberOfCards() == 0)
			return 0;
		Rank bestRankOnHand = Rank.TWO;
		for(Card card : hand.copyOfAllCards()) {
			if(card.getRank().getValue() > bestRankOnHand.getValue())
				bestRankOnHand = card.getRank();

		}
		return bestRankOnHand.getValue();
	}
}
