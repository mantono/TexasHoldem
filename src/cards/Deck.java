package cards;

public class Deck
{
	public Deck()
	{
		this(52);
	}
	
	public Deck(int numberOfCards)
	{
		if(numberOfCards < 1)
			throw new IllegalArgumentException("The deck must contain at least one card.");
	}
	
	public int getSize()
	{
		return 52;
	}
}
