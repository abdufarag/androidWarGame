package assignment.warGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class War {
	
	CardSet deckOfCards = new CardSet();
	Card lastCard = new Card(0, R.drawable.ic_launcher, "", "");
	ArrayList<Card> player1Deck = new ArrayList<Card>();
	Card player1Card, player2Card, temp;
	ArrayList<Card> player2Deck = new ArrayList<Card>();
	ArrayList<Card> waitingDeck = new ArrayList<Card>();
	
	Random generator;
	int random;
	String roundResult = "";
	String replayOffering = "";
	
	public String getRoundResult() {
		return roundResult;
	}

	public void setRoundResult(String roundResult) {
		this.roundResult = roundResult;
	}

	public ArrayList<Card> getPlayer1Deck() {
		return player1Deck;
	}

	public Card getPlayer1Card() {
		return player1Card;
	}

	public void setPlayer1Card(Card player1Card) {
		this.player1Card = player1Card;
	}

	public ArrayList<Card> getPlayer2Deck() {
		return player2Deck;
	}

	public Card getPlayer2Card() {
		return player2Card;
	}

	public void setPlayer2Card(Card player2Card) {
		this.player2Card = player2Card;
	}

	public War(){
		scrambleDeck(deckOfCards.getDeck());
		generatePlayersDeck();
	}
	
	public void play(){
		if (player1Deck.size()==52 || player2Deck.size()==52){
			gameOver();
		}
		else {
			regularGame();
		}
	}
	
	public void regularGame(){
		player1Card = player1Deck.get(0);	//0 refers to the card at the top of the queue
		player2Card = player2Deck.get(0);
		getWinner();
		replayOffering = "";
	}
	
	public void gameOver(){
		scrambleDeck(deckOfCards.getDeck());
		roundResult = "Game Over";
		replayOffering = "Press Play to play again";
		player1Deck.removeAll(player1Deck);
		player2Deck.removeAll(player2Deck);
		generatePlayersDeck();
	}
	
	public void getWinner(){
		if (player1Card.getValue() > player2Card.getValue()){
			roundResult = "Player 1 wins";
			temp = player1Card;
			player1Deck.remove(player1Card);
			player1Deck.add(player2Card);
			player1Deck.add(temp);
			player2Deck.remove(0);
			player1Deck.addAll(waitingDeck);
			waitingDeck.removeAll(waitingDeck);
		}
		else if (player1Card.getValue() < player2Card.getValue()){
			roundResult = "Player 2 wins";
			temp = player2Card;
			player2Deck.remove(player2Card);
			player2Deck.add(player1Card);
			player2Deck.add(temp);
			player1Deck.remove(0);
			player2Deck.addAll(waitingDeck);
			waitingDeck.removeAll(waitingDeck);
		}
		else {
			roundResult = "War!";
			warGame();
		}
	}
	
	public void warGame(){
		waitingDeck.add(player1Card);
		waitingDeck.add(player2Card);
		player1Deck.remove(0);
		player2Deck.remove(0);
		player1Card = player1Deck.get(0);
		player2Card = player2Deck.get(0);
	}
	
	public void generatePlayersDeck(){
		for (int i=0; i<52; i++) {
			player1Deck.add(deckOfCards.getDeck().get(i));
			i++;
			player2Deck.add(deckOfCards.getDeck().get(i));
		}
	}
	
	public void scrambleDeck(ArrayList<Card> l){
		generator = new Random();
		for (int i=0; i<l.size(); i++){
			Card temp = l.get(i);
			random = generator.nextInt(l.size()-i) + i;
			l.set(i, l.get(random));
			l.set(random, temp);
		}
	}
	
}
