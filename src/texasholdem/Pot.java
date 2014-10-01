package texasholdem;

public class Pot {

	private int amount = 0;
	private int bet = 0; //Varf�r finns det en bet och en amount? Jag kommer anv�nda denna int som en "kom ih�g den senaste bet-summa" variabel tills vidare - Emil
						//T�nkte att amount skulle vara summan av alla bet och bet skulle vara varje enskild insats som en spelare g�r
						//Var inte helt s�ker p� om den skulle vara i Player-klassen eller i Pot-klassen
	public Pot(int amount) {
		if (amount < 1)
			throw new IllegalArgumentException(
					"The Pot has to be larger than 0");

		this.amount = amount;
	}
	public Pot(){
		
	}
	
	public int getBet(){
		return bet;
	}
	

	public int getAmount() {

		return amount;
	}

	public void betToPot(int bet) { //<L�gger till en bet fr�n en spelare till poten. Kommer ih�g summan som betades tills n�sta spelare betar.>
		if (bet < 1)
			throw new IllegalArgumentException(
					"The Bet has to be larger than 0");
		else{
			amount =+bet;
			this.bet = bet;
		}
	}
	
	public void resetPot(){
		amount = 0;
		bet = 0;
		return;
	}
}
