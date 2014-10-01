package texasholdem;
import java.util.*;

import cards.*;
public class Game{
	Deck deck;
	Map<Player, Integer> allPlayers = new HashMap<Player, Integer>();
	List<Player> blindsRotation = new LinkedList<Player>();
	int turn; //Håller koll på hur många rundor som hittilds har spelats (tänkte att det kunde vara intressant längre fram)
	
	
	public Game(){
		
		
	}
	
	public void initiateRound(){
		
	}
	
	public Action requestPlayerAction(){
		
	}
	
	public void endRound(){
		
	}
	
	public void distrubuteChip(){
		
	}
	
	public void initiateGame(){ //Vad är tanken med denna funktion nu igen? - Emil
		
	}
	
	public void initiateDeal(){
		
	}
	public Map<Player, Integer> getAllPlayers(){
	return allPlayers;
	}
	
	public List<Player> getBlindsRotation(){ //Denna metod är mest till för testfallen
		return blindsRotation;
	}
	
	public int getTurn(){
		return turn;
	}
}
