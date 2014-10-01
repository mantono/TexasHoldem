package texasholdem;

public class Pot {

	private int amount = 0;
	private int bet = 0; //Varför finns det en bet och en amount? Jag kommer använda denna int som en "kom ihåg den senaste bet-summa" variabel tills vidare - Emil
	private boolean valid = true; //Jag vet att detta redan har påpekats i Skype-chatten, men varför finns denna variabel överhuvudtaget?

	public Pot(int amount) {
		if (amount < 1)
			throw new IllegalArgumentException(
					"The Pot has to be larger than 0");

		this.amount = amount;
	}

	public Pot(int bet, boolean valid) { //Varför finns denna konstruktor? - Emil
		this.bet = bet;
		this.valid = valid;

	}
	public int getBet(){
		return bet;
	}
	

	public int getSize() {

		return amount;
	}

	public void betToPot(int bet) { //<Lägger till en bet från en spelare till poten. Kommer ihåg summan som betades tills nästa spelare betar.>
		if (bet < 1)
			throw new IllegalArgumentException(
					"The Bet has to be larger than 0");
		else{
			amount = amount+bet;
			this.bet = bet;
		}
	}
}
