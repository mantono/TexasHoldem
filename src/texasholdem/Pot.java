package texasholdem;

public class Pot {

	private int amount = 0;
	private int bet;
	private boolean valid;

	public Pot(int amount) {
		if (amount < 1)
			throw new IllegalArgumentException(
					"The Pot has to be larger than 0");

		this.amount = amount;
	}

	public Pot(int bet, boolean valid) {
		this.bet = bet;
		this.valid = valid;

	}

	public int getSize() {

		return amount;
	}

	public int betToPot(int bet, boolean valid) {
		if (bet < 1)
			throw new IllegalArgumentException(
					"The Bet has to be larger than 0");
		if (valid) {
			return amount += bet;
		}else{
			return -1;
		}
	}
}
