import static org.junit.Assert.*;

import org.junit.Test;

import texasholdem.Player;

public class PlayerTest {

	@Test
	public void test()
	{
		Player player = new Player("kalle");
		assertEquals("kalle",player.getName());
	}

}
