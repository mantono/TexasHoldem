import static org.junit.Assert.*;
import texasholdem.*;

import org.junit.Test;


public class GameTest {

	@Test
	public void addingOnePlayerTest(){ //Detta testfall är egentligen onödigt, då ett game inte bör innehålla endast en spelare. Jag ladde till detta testfall bara för att kontrollera att det gick att lägga till en Player överhuvudtaget. - Emil
		Player kalle = new Player("kalle");
		Game newGame = newGame(kalle);
		assertTrue(newGame.getAllPlayers().containsKey(kalle));
		assertTrue(newGame.getBlindsRotation().contains(kalle));
		
	}
	@Test
	public void addingMorePlayersTest(){
		Player kalle = new Player("kalle");
		Player pelle = new Player("pelle");
		Game newGame = newGame(kalle, pelle);
		assertTrue(newGame.getAllPlayers().containsKey(kalle));
		assertTrue(newGame.getBlindsRotation().contains(kalle));
		assertTrue(newGame.getAllPlayers().containsKey(pelle));
		assertTrue(newGame.getBlindsRotation().contains(pelle));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void addingNoPlayersTest(){
		Game newGame = newGame();
	}
	
	@Test
	public void initiateGameTest(){  //Detta testfall behövs kompletteras. Vad ska initiateGame egentligen göra?
		Player kalle = new Player("kalle");
		Player pelle = new Player("pelle");
		Game newGame = newGame(kalle, pelle);
		newGame.initiateGame();
		assertEquals(0, newGame.getTurn());
	}
	
	@Test
	public void initiateRoundTest(){
		Player kalle = new Player("kalle");
		Player pelle = new Player("pelle");
		Game newGame = newGame(kalle, pelle);
		newGame.initiateRound();
		assertTrue(!(newGame.getCurrentDeck() == null));	
	}
	
	@Test
	public void initiateDealPhaseOneTest(){ //Detta testfall är endast påbörjat. Behövs ett avslut
		Player kalle = new Player("kalle");
		Player pelle = new Player("pelle");
		Game newGame = newGame(kalle, pelle);
		newGame.setPhase(1);
		newGame.initiateDeal();
		
		
	}
}
