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
	public String toString()
	{
		return rank + " of " + colour;
	}

	@Override
	public int compareTo(Card card)
	{
		return this.rank.getValue() - card.rank.getValue();
	}
	
	@Override
	public boolean equals(Object object)
	{
		if(object == null)
			return false;
		if(!object.getClass().equals(this.getClass()))
			return false;
		Card other = (Card) object;
		return (this.colour.equals(other.colour) && this.rank.equals(other.rank));
	}

}
