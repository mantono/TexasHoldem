package cards;

import java.security.SecureRandom;
import java.util.ArrayList;

public class Deck
{
	private ArrayList<Card> cards = new ArrayList<Card>();
	private final SecureRandom randomNumber = new SecureRandom();
	
	public Deck()
	{
		this(1);
	}
	
	public Deck(int numberOfSets)
	{
		if(numberOfSets < 1)
			throw new IllegalArgumentException("The deck must consist of at least one complete set.");
		for(int i = 0; i < numberOfSets; i++)
			generateCards();
	}
	
	private void generateCards()
	{
		for(Colour colour : Colour.values())
			for(Rank rank : Rank.values())
				cards.add(new Card(colour, rank));
	}
	
	protected void setSeed(long seed)
	{
		randomNumber.setSeed(seed);
	}
	
	public Card drawCard()
	{
		return cards.remove(randomNumber.nextInt(getSize()));
	}
	
	public int getSize()
	{
		return cards.size();
	}
	
	@Override
	public String toString()
	{
		return cards.toString();
		//Eller använd ♥9, ♠3, ♣4, ♦10 osv
	}
}
