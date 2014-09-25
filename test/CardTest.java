import static org.junit.Assert.*;

import org.junit.Test;

import cards.*;


public class CardTest
{

	@Test
	public void test()
	{
		Card card = new Card(Colour.CLUBS, Value.FIVE);
		assertEquals(card.getValue(), (Value.FIVE));
	}

}
