package texasholdem;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cards.Player;

public class Pot {

	private int amount = 0;
	private final Map<Player, Integer> betHistory = new HashMap<Player, Integer>();

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

	public void receiveBet(int bet, Player player) {
		if (bet < 1)
			throw new IllegalArgumentException(
					"The Bet has to be larger than 0");
		else {
			amount += bet;
			betHistory.put(player, this.getBetHistory(player) + bet);

		}
	}

	public void handOutChips(Player player, int chipsWon, int playersWon){
		if (!betHistory.containsKey(player)){
			throw new IllegalArgumentException("The player must have betted something in order to recieve chips");
		}
		if (playersWon < 1){
			throw new IllegalArgumentException("The number of won players cannot be 0 or lower.");
		}
		int chipsToBeHandedOut = 0;
		Iterator<Map.Entry<Player, Integer>> iterator = betHistory.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry<Player, Integer> participant = iterator.next();
				if((participant.getValue() <= (chipsWon*playersWon))){
					System.out.println("participant" + participant.getValue() + " 54");
					int leftovers = (participant.getValue() % playersWon);
					System.out.println("leftover" + participant.getValue() % playersWon + " 56");
					if(leftovers < 1){
						chipsToBeHandedOut += (participant.getValue() / playersWon);
						System.out.println("chips" +chipsToBeHandedOut + " 59");
						betHistory.put(participant.getKey(), (getBetHistory(participant.getKey()) - (getBetHistory(participant.getKey()) / playersWon)) + leftovers);
					}
					else{
						chipsToBeHandedOut += ((participant.getValue() / playersWon) + 1);
						System.out.println("chips" +chipsToBeHandedOut + " 64");
						betHistory.put(participant.getKey(), getBetHistory(participant.getKey()) - (getBetHistory(participant.getKey()) / playersWon) - 1);
						System.out.println("Newparticipant" + getBetHistory(participant.getKey()) + " 66");
					}
					assert(getBetHistory(participant.getKey()) > 0);
					if(getBetHistory(participant.getKey()) == 0  && (participant.getKey() != player)){
						iterator.remove();
						
					}
					
				}
				else{
					System.out.println("participant" + participant.getValue() + " 75");
					chipsToBeHandedOut += chipsWon;
					betHistory.put(participant.getKey(), (participant.getValue() - chipsWon));	
					System.out.println("chips" +chipsToBeHandedOut + " 77");
					
				}
			}
		amount -= chipsToBeHandedOut;
		System.out.println("chips" +chipsToBeHandedOut + " 82" + " sista");
		player.addChips(chipsToBeHandedOut);
		assert(getBetHistory(player) > 0);
		if(getBetHistory(player) == 0){
				betHistory.remove(player);
		}
	}
	
	public void resetPot() {
		amount = 0;
		betHistory.clear();
		return;
	}

	public int getBetHistory(Player p) {
		if (!betHistory.containsKey(p)) {
			return 0;
		}
		return betHistory.get(p);
	}
}
