import static org.junit.Assert.*;
import texasholdem.*;

import org.junit.Test;


public class GameTest {

	@Test
	public void addingOnePlayerTest(){ //Detta testfall �r egentligen on�digt, d� ett game inte b�r inneh�lla endast en spelare. Jag ladde till detta testfall bara f�r att kontrollera att det gick att l�gga till en Player �verhuvudtaget. - Emil
		Player kalle = new Player("kalle");
		Game newGame = new Game(kalle);
		assertTrue(newGame.getAllPlayers().containsKey(kalle));
		assertTrue(newGame.getBlindsRotation().contains(kalle));
		
	}
	@Test
	public void addingMorePlayersTest(){
		Player kalle = new Player("kalle");
		Player pelle = new Player("pelle");
		Game newGame = new Game(kalle, pelle);
		assertTrue(newGame.getAllPlayers().containsKey(kalle));
		assertTrue(newGame.getBlindsRotation().contains(kalle));
		assertTrue(newGame.getAllPlayers().containsKey(pelle));
		assertTrue(newGame.getBlindsRotation().contains(pelle));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addingNoPlayersTest(){
		Game newGame = new Game();
	}
	
	@Test
	public void initiateGameTest(){  //Detta testfall beh�vs kompletteras. Vad ska initiateGame egentligen g�ra?
		Player kalle = new Player("kalle");
		Player pelle = new Player("pelle");
		Game newGame = new Game(kalle, pelle);
		newGame.initiateGame();
		assertEquals(0, newGame.getTurn());
	}
	
	@Test
	public void initiateRoundTest(){
		Player kalle = new Player("kalle");
		Player pelle = new Player("pelle");
		Game newGame = new Game(kalle, pelle);
		newGame.initiateRound();
		assertTrue(!(newGame.getCurrentDeck() == null));	
	}
	
	@Test
	public void initiateDealPhaseOneTest(){ //Detta testfall �r endast p�b�rjat. Beh�vs ett avslut
		Player kalle = new Player("kalle");
		Player pelle = new Player("pelle");
		Game newGame = new Game(kalle, pelle);
		newGame.setPhase(1);
		newGame.initiateDeal();
		
		
	}
}
