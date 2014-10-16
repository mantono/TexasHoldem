package cards;

import java.util.Comparator;
import java.util.List;

public interface Rules extends Comparator<Hand>
{
	public Hand declareWinner(List<Hand> hands);
	public boolean isDraw(List<Hand> hands);
}
