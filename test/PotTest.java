import static org.junit.Assert.*;

import java.util.IllegalFormatException;

import org.junit.Test;

import cards.Deck;

import texasholdem.Pot;

public class PotTest {
	
	@Test
	public void PotSizeTest(){
		Pot pot = new Pot(500);
		assertEquals(500, pot.getSize());
		
	}
	@Test(expected=IllegalArgumentException.class)
	public void testForInvalidSize()
	{
		Pot invalidpot = new Pot(-1);
		assertEquals(0, invalidpot.getSize());
	}

}
