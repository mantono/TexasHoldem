package cards;

import static org.junit.Assert.*;

import org.junit.Test;

public class CardTest
{

	@Test
	public void testCreateValidCard()
	{
		Card card = new Card(Colour.CLUBS, Value.FIVE);
	}

}
