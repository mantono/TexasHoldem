package cards;

import java.util.List;

public interface Rules extends Comparable<Hand>
{
	public Hand declareWinner(List<Hand> hands);
}
