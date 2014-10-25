import java.util.Random;

import cards.Player;
import texasholdem.Action;
import texasholdem.Game;


public class ProfilerTest
{
	private static Random random = new Random();

	public static void main(String[] args)
	{
		for(int i = 0; i < 100000; i++)
		{
			Player[] players = new Player[20];
			Game game = new Game(5, 0.2);
			for(byte n = 0; n < 20; n++)
			{
				players[n] = new Player("player", 300);
				game.addPlayer(players[n]);
			}
			game.initiateRound();
			game.initiateDeal();
			doSomething(game, players);
			game.initiateDeal();
			doSomething(game, players);
			game.initiateDeal();
			game.endRound();
		}
		System.out.println("Finished");
		System.exit(0);
	}
	
	private static void doSomething(Game game, Player[] players)
	{
		for(byte n = 0; n < 20; n++)
		{
			if(players[n].isInRound())
				game.playerAction(players[n], getRandomAction());
		}
	}
	
	private static Action getRandomAction()
	{
		int n = random.nextInt(5);
		switch(n)
		{
			case 1: return Action.FOLD;
			case 2: return Action.ALL_IN;
			case 3: return Action.CHECK;
			case 4: return Action.RAISE;
			default: return Action.CHECK;
		}
	}

}
