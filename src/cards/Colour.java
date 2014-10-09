package cards;

public enum Colour
{
	CLUBS(0), DIAMONDS(1), HEARTS(2), SPADES(3);
	private final int colour;

	private Colour(int colour)
	{
		this.colour = colour;
	}
	
	public int getValue()
	{
		return colour;
	}
}
