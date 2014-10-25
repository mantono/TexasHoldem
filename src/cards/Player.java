package cards;

public class Player {
	private final String name;
	private int chips = 0;
	private final Hand hand = new Hand();
	private boolean inRound = false;

	public Player(String name, int chips) {
		this.name = name;
		this.chips = chips;
	}

	public String getName() {
		return name;
	}

	public int getChips() {
		return chips;
	}

	public int addChips(int add) {
		return chips += add;
	}

	public boolean subtractChips(int bet) {
		if(bet > chips || bet < 1)
			return false;
		chips -= bet;
		assert chips >= 0 : "The amount of chips must not be negative";
		return true;
	}

	public void setInRound(boolean inGame) {
		this.inRound = inGame;

	}

	public boolean isInRound() {
		return inRound;
	}
	
	public void newHand(Card... cards){
		hand.newHand(cards);
	}
	
	public void addTohand(Card... cards){
		hand.addToHand(cards);
	}
	
	public int getNumberOfCards(){
		return hand.getNumberOfCards();
	}
	
	public void clearHand(){
		hand.clearHand();
	}
	
	public Hand getHand()
	{
		return (Hand) this.hand.clone();
	}
}
