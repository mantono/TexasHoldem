package texasholdem;

public enum CardCombination
{
	STRAIGHT_FLUSH(22), FOUR_OF_A_KIND(21), FULL_HOUSE(20), FLUSH(19), 
	STRAIGHT(18), THREE_OF_A_KIND(17), TWO_PAIR(16), PAIR(15);
	private final int value;
	
	private CardCombination(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return value;
	}
}
