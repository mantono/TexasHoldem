import static org.junit.Assert.*;
import texasholdem.*;

import org.junit.Before;
import org.junit.Test;


public class GameTest {
	
	private Player kalle, pelle;
	
	@Before
	public void setup(){
		kalle = new Player("kalle");
		pelle = new Player("pelle");
	}

	@Test
	public void onePlayerTest(){ 
		Game newGame = new Game(kalle);
		assertTrue(newGame.getAllPlayers().contains(kalle));		
	}
	
	@Test
	public void twoPlayersTest(){
		Game newGame = new Game(kalle, pelle);
		assertTrue(newGame.getAllPlayers().contains(kalle));
		assertTrue(newGame.getAllPlayers().contains(pelle));
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
	}
	
	@Test
	public void blindRotationTest(){
		Game newGame = new Game(4, 0.3, kalle, pelle);
		assertTrue(newGame.getAllPlayers().contains(kalle));
		assertTrue(newGame.getAllPlayers().contains(pelle));
		assertTrue(newGame.getBlindsRotation().contains(kalle));
		assertTrue(newGame.getBlindsRotation().contains(pelle));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addingNegativeBigBlindTest(){
		Game newGame = new Game(-4, 0.3, kalle);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addingNegativeSmallBlindTest(){
		Game newGame = new Game(4, -0.2, kalle);
	}
	
	@Test
	public void initiateGameTest(){
		Game newGame = new Game(4, 0.3, kalle, pelle);
		newGame.initiateGame();
		assertEquals(0, newGame.getTurn());
		assertEquals(2, newGame.getSmallBlind());
		assertEquals(4, newGame.getBigBlind());
		assertEquals(4, newGame.getBlindsRaiseFrequency());
	}
	
	@Test
	public void initiateRoundTest(){
		kalle.addChips(5);
		pelle.addChips(5);
		Game newGame = new Game(4, 0.3, kalle, pelle);
		newGame.initiateRound();
		assertFalse(newGame.getCurrentDeck() == null);	
		for(Player player : newGame.getBlindsRotation()){
			if(player.getInGame()){
				assertTrue(player.getHand().size() == 2);
				assertTrue(player.getChips() > 0);
			}
			else{
				assertTrue(player.getHand().size() == 0);
				assertTrue(player.getChips() <= 0);
			}
		}
	}
	
	@Test
	public void initiateDealPhaseOneTest(){  //P�b�rjad testfall. Beh�ver ett avslut.
		kalle.addChips(5);
		pelle.addChips(5);
		Game newGame = new Game(kalle, pelle);
		newGame.setPhase(1);
		newGame.initiateDeal();
		for(Player player : newGame.getBlindsRotation()){
			if(newGame.getBlindsRotation().get(0) == player){
				assertTrue(newGame.getAllPlayers().get(player) <= newGame.getSmallBlind());
				assertTrue(newGame.getAllPlayers().get(player) > 0);
			}
			else if(newGame.getBlindsRotation().get(1) == player){
				assertTrue(newGame.getAllPlayers().get(player) <= newGame.getBigBlind());
				assertTrue(newGame.getAllPlayers().get(player) > 0);
			}
			else if(player.getInGame()){

			}
		}
		
		
	}
	
}
