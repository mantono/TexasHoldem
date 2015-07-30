package cards;

import java.util.Comparator;

public class AceFirst implements Comparator<Card>
{
	@Override
	public int compare(Card arg0, Card arg1)
	{
		if(arg0.getRank() != Rank.ACE && arg1.getRank() != Rank.ACE)
			return arg0.compareTo(arg1);
		if(arg0.getRank() == Rank.ACE && arg1.getRank() == Rank.ACE)
			return 0;
		if(arg0.getRank() == Rank.ACE)
			return -1;
		else
			return 1;
	}
}
