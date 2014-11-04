package texasholdem;

import cards.Player;

public class BettingPlayer extends Player
{
	private int chips = 0;

	public BettingPlayer(String name, int chips)
	{
		super(name);
		this.chips = chips;
	}
	
	public int getChips()
	{
		return chips;
	}

	public int addChips(int add)
	{
		return chips += add;
	}

	public boolean subtractChips(int bet)
	{
		if(bet > chips || bet < 1)
			return false;
		chips -= bet;
		assert chips >= 0 : "The amount of chips must not be negative";
		return true;
	}
	
	public boolean hasChips()
	{
		return chips > 0;
	}
	
	public boolean isAllIn()
	{
		return !hasChips() && isInRound();
	}
}
