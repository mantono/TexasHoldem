package texasholdem;

public class Pot {

	private int amount = 0;
	private int bet = 0; //Varf�r finns det en bet och en amount? Jag kommer anv�nda denna int som en "kom ih�g den senaste bet-summa" variabel tills vidare - Emil
	private boolean valid = true; //Jag vet att detta redan har p�pekats i Skype-chatten, men varf�r finns denna variabel �verhuvudtaget?

	public Pot(int amount) {
		if (amount < 1)
			throw new IllegalArgumentException(
					"The Pot has to be larger than 0");

		this.amount = amount;
	}

	public Pot(int bet, boolean valid) { //Varf�r finns denna konstruktor? - Emil
		this.bet = bet;
		this.valid = valid;

	}
	public int getBet(){
		return bet;
	}
	

	public int getSize() {

		return amount;
	}

	public void betToPot(int bet) { //<L�gger till en bet fr�n en spelare till poten. Kommer ih�g summan som betades tills n�sta spelare betar.>
		if (bet < 1)
			throw new IllegalArgumentException(
					"The Bet has to be larger than 0");
		else{
			amount = amount+bet;
			this.bet = bet;
		}
	}
}
