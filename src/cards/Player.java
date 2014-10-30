package cards;

public class Player {
	private final String name;
	private final Hand hand = new Hand();
	private boolean inRound = false;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
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
		return new Hand(hand);
	}
}
