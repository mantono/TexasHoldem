package cards;

public enum Colour
{
	SPADES(0), HEARTS(1), DIAMONDS(2), CLUBS(3);
	private final int colour;

	private Colour(int colour)
	{
		this.colour = colour;
	}
}
