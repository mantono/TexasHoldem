package texasholdem;

public class Player {
	String name;
	int chips = 0;
	
	public Player(String name){
	this.name = name;
		
	}
	
	public String getName(){
		return name;
	}

	public int getChips() {
		return chips;
	}

}
