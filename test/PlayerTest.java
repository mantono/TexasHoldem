import static org.junit.Assert.*;

import org.junit.Test;

import texasholdem.Player;
import cards.Card;
import cards.Colour;
import cards.Value;


public class PlayerTest {

	@Test
	public void nameTest()
	{
		Player player = new Player("kalle");
		assertEquals("kalle",player.getName());
	}
	
	@Test
	public void potTest1(){
		Player player = new Player("kalle");
		assertEquals(0,player.getChips());
		
		
	}
}
