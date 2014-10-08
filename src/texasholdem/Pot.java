package texasholdem;

import java.util.HashMap;
import java.util.Map;

import cards.Player;

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

	public boolean checkNull() {

		for (Map.Entry<Player, Integer> me : betHistory.entrySet()) {
			Player p = me.getKey();
			Integer i = me.getValue();

			if (p == null || i == null)

				return true;

		}
		return false;

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
		if (!betHistory.containsKey(p)) {
			return 0;
		}
		return betHistory.get(p);
	}
}
