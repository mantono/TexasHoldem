import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import texasholdem.*;
import cards.*;


public class GameStateMachineTest
{
	private Player emil, rasmus, elliot;
	private Game game;
	
	@Before
	public void setup()
	{
		emil = new Player("emil", 100);
		rasmus = new Player("rasmus", 100);
		elliot = new Player("elliot", 100);
		game = new Game(emil, rasmus, elliot);
	}
	
	@Test(expected=IllegalStateException.class)
	public void notInRoundToIllegalStateTest_Check()
	{
		game.playerAction(emil, Action.CHECK);
	}
	
	@Test(expected=IllegalStateException.class)
	public void notInRoundToIllegalStateTest_AllIn()
	{
		game.playerAction(emil, Action.ALL_IN);
	}
	
	@Test(expected=IllegalStateException.class)
	public void notInRoundToIllegalStateTest_Raise()
	{
		game.playerAction(emil, Action.RAISE);
	}
	
	@Test(expected=IllegalStateException.class)
	public void notInRoundToIllegalStateTest_Call()
	{
		game.playerAction(emil, Action.CALL);
	}
	
	@Test(expected=IllegalStateException.class)
	public void notInRoundToIllegalStateTest_Fold()
	{
		game.playerAction(emil, Action.FOLD);
	}
}
