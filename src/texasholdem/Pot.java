package texasholdem;

public class Pot{
	
	private int amount = 0;

	public Pot(int amount){
		if(amount < 1)
			throw new IllegalArgumentException("The Pot has to be larger than 0");
		
		this.amount=amount;
	}
	
	public int getSize(){
		
		return amount;
	}
}
