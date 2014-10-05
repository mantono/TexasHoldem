import static org.junit.Assert.*;
import texasholdem.*;

import org.junit.Before;
import org.junit.Test;


public class GameTest {
	
	private Player kalle, pelle, kent;
	
	
	@Before
	public void setup(){
		kalle = new Player("kalle", 200);
		pelle = new Player("pelle", 200);
		kent = new Player("kent", 200);
	}

	@Test
	public void onePlayerTest(){ 
		Game newGame = new Game(kalle);
		assertTrue(newGame.getPlayersInGame().contains(kalle));	
	}
		
		@Test
	public void addingOnePlayerTest(){ 
		Game newGame = new Game(kalle, pelle);
		assertTrue(newGame.getPlayersInGame().contains(kalle));
		
	}
	
	@Test
	public void twoPlayersTest(){
		Game newGame = new Game(kalle, pelle);
		assertTrue(newGame.getPlayersInGame().contains(kalle));
		assertTrue(newGame.getPlayersInGame().contains(pelle));
	}
	
	@Test
	public void testRaiseOfBlinds(){
		Game game = new Game(4, 1, kalle, pelle);
		game.endRound();
		assertEquals(4, game.getSmallBlind());		
		assertEquals(8, game.getBigBlind());		
		game = new Game(10, 0.5, kalle, pelle);
		game.endRound();
		assertEquals(7, game.getSmallBlind());		
		assertEquals(15, game.getBigBlind());		
		game = new Game(10, 0, kalle, pelle);
		game.endRound();
		assertEquals(5, game.getSmallBlind());		
		assertEquals(10, game.getBigBlind());		
	}
	
	@Test
	public void blindRotationTest(){
		Game newGame = new Game(4, 0.3, kalle, pelle);
		assertTrue(newGame.getPlayersInGame().contains(kalle));
		assertTrue(newGame.getPlayersInGame().contains(pelle));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addingNegativeBigBlindTest(){
		Game newGame = new Game(-4, 0.3, kalle);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addingNegativeBlindsRaiserPercentageTest(){
		Game newGame = new Game(4, -0.2, kalle);
	}
	
	@Test
	public void initiateGameTest(){
		Game newGame = new Game(4, 0.3, kalle, pelle);
		newGame.initiateGame();
		assertEquals(2, newGame.getSmallBlind());
		assertEquals(4, newGame.getBigBlind());
		assertEquals(0.3, newGame.getBlindsRaisePercentage(), 0.0001);
	}
	
	@Test
	public void initiateRoundTest(){
		Game newGame = new Game(4, 0.3, kalle, pelle);
		newGame.initiateRound();
		assertTrue(newGame.getCurrentDeck() == null);
		assertTrue(kalle.getChips() == 196);
		assertTrue(pelle.getChips() == 198);
		assertTrue(kent.getChips() == 200);
	}
	
	@Test
	public void initiateDealSmallBlindTest(){
		
	}
	
}
