package texasholdem;

public enum CardCombination
{
	PAIR(15), TWO_PAIR(16), THREE_OF_A_KIND(17), STRAIGHT(18), FLUSH(19), FULL_HOUSE(20),
	FOUR_OF_A_KIND(21), STRAIGHT_FLUSH(22);
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
