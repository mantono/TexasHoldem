package cards;

public class Card implements Comparable<Card>
{
	private final Colour colour;
	private final Rank rank;

	public Card(Colour colour, Rank rank)
	{
		this.colour = colour;
		this.rank = rank;
	}

	public Rank getRank()
	{
		return rank;
	}

	public Colour getColour()
	{
		return colour;
	}

	@Override
	public int compareTo(Card card)
	{
		return this.rank.getValue() - card.rank.getValue();
	}

}
