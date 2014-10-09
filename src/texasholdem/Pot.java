package texasholdem;

import java.util.HashMap;
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
