package texasholdem;

import java.util.HashMap;
import java.util.Map;

public class Pot {

	private int amount = 0;
	private Map<Player, Integer> betHistory = new HashMap<Player, Integer>();

	public Pot(int amount) {
		if (amount < 1)
			throw new IllegalArgumentException(
					"The Pot has to be larger than 0");

		this.amount = amount;
	}

	public Pot() {

	}

	public int getAmount() {

		return amount;
	}

	public void betToPot(int bet, Player p) {
		int oldPlayerBet = 0;
		if (bet < 1)
			throw new IllegalArgumentException(
					"The Bet has to be larger than 0");
		else {
			amount += bet;
			if (betHistory.containsKey(p)) {
				oldPlayerBet = betHistory.get(p);
			}
			betHistory.put(p, oldPlayerBet + bet);

		}
	}

	public void resetPot() {
		amount = 0;
		betHistory.clear();
		return;
	}

	public int getBetHistory(Player p) {

		return betHistory.get(p);
	}
}
