package texasholdem;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Pot {

	private int amount = 0;
	private final Map<BettingPlayer, Integer> betHistory = new HashMap<BettingPlayer, Integer>();

	public Pot(int amount) {
		if (amount < 0)
			throw new IllegalArgumentException(
					"The Pot has to be at least 0");

		this.amount = amount;
	}

	public Pot() {

	}

	public int getAmount() {

		return amount;
	}

	public void receiveBet(int bet, BettingPlayer player) {
		if (bet < 1)
			throw new IllegalArgumentException(
					"The Bet has to be larger than 0");
		else {
			amount += bet;
			betHistory.put(player, this.getBetHistory(player) + bet);

		}
	}

	public void handOutChips(BettingPlayer player, int chipsWon, int playersWon){
		if (!betHistory.containsKey(player)){
			throw new IllegalArgumentException("The player must have betted something in order to recieve chips");
		}
		if (playersWon < 1){
			throw new IllegalArgumentException("The number of won players cannot be 0 or lower.");
		}
		int chipsToBeHandedOut = 0;
		Iterator<Map.Entry<BettingPlayer, Integer>> iterator = betHistory.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry<BettingPlayer, Integer> participant = iterator.next();
				if((participant.getValue() <= (chipsWon*playersWon))){
					int leftovers = (participant.getValue() % playersWon);
					if(leftovers < 1){
						chipsToBeHandedOut += (participant.getValue() / playersWon);
						betHistory.put(participant.getKey(), (getBetHistory(participant.getKey()) - (getBetHistory(participant.getKey()) / playersWon)) + leftovers);
					}
					else{
						chipsToBeHandedOut += ((participant.getValue() / playersWon) + 1);
						betHistory.put(participant.getKey(), getBetHistory(participant.getKey()) - (getBetHistory(participant.getKey()) / playersWon) - 1);
					}
					if(getBetHistory(participant.getKey()) == 0  && (participant.getKey() != player)){
						iterator.remove();	
					}
				}
				else{
					chipsToBeHandedOut += chipsWon;
					betHistory.put(participant.getKey(), (participant.getValue() - chipsWon));						
				}
			}
		amount -= chipsToBeHandedOut;
		player.addChips(chipsToBeHandedOut);
		if(getBetHistory(player) == 0){
				betHistory.remove(player);
		}
	}
	
	public void resetPot() {
		amount = 0;
		betHistory.clear();
		return;
	}

	public int getBetHistory(BettingPlayer p) {
		if (!betHistory.containsKey(p)) {
			return 0;
		}
		return betHistory.get(p);
	}
}
