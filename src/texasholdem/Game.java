package texasholdem;
import java.util.*;

import cards.*;
public class Game{
	Deck deck;
	Map<Player, Integer> allPlayers = new HashMap<Player, Integer>();
	List<Player> blindsRotation = new LinkedList<Player>();
	int turn; //H�ller koll p� hur m�nga rundor som hittilds har spelats (t�nkte att det kunde vara intressant l�ngre fram)
	
	
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
	
	public void initiateGame(){ //Vad �r tanken med denna funktion nu igen? - Emil
		
	}
	
	public void initiateDeal(){
		
	}
	public Map<Player, Integer> getAllPlayers(){
	return allPlayers;
	}
	
	public List<Player> getBlindsRotation(){ //Denna metod �r mest till f�r testfallen
		return blindsRotation;
	}
	
	public int getTurn(){
		return turn;
	}
}
