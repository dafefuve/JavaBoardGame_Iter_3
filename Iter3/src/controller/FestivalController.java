package controller;

/**
 * Created by Daniel on 4/15/2014.
 */
public class FestivalController {
		//Facade facade;
	//ArrayList<JavaPlayer> listOfPlayers;
	//int currentPlayer;
	/*
	ArrayList<JavaPlayer> participants;
	int[] scores;
	*/
	public FestivalController(){
		//this.facade=f;
	//	this.listOfPlayers=f.getGameController().getPlayers();
		//currentPlayer=0;
		//participants=new ArrayList<JavaPlayer>;
	}
	/*
		Happens ONLY at the end of a player's turn
		To arrange a festival, this player must:
			Have at least one developer in a city
			Have at atleast one palace card
			
		A palace may only have one festival per each value it achieves. Basically you can't party at a festival again until you upgrade it.

		An opponent may join in the festival by playing a place cards.
		An opponent may only join if:
			They have a developer in the city
			They have one suitable palace card

		To determine which player will score...
			Either no other player joins the festival competition
				If this happens, the player who started the festival immediantly scores half the level of the palace
			
			Else the players play suitable palace cards so that the total points on all their played cards match or exceed the highest bid in the competition
			On a player's turn, he may: Raise his bid in the competition by playing a card
			Or not play a card and retire from the competition.

			After a player plays a card, we check for a tie. If there IS a tie between at least two players they may agree to end the competition

			If they do not agree to a tie, then the bidding continues until only one player remains or until there is a tie

		If the players still in the festival are tied with no remaining cards to play, they automatically share the points

		Sharing points is equal to the level of the palace /2 - 1
	*/

	/*
	public void getInfo(palace or city...){
		//this basically extracts the level of the palace the festival is at
	}
	public void checkPlayers(){
		//this method checks to see if a player wishes to play and can legally play
		//basically if they have a developer in the city and at least one palace card
		scores=new int[participants.getSize()]
	}
	//so we could have a loop if we want...
	public void festivalLoop(){
		while(){
			//if tie

			//if player bids

			//if player drops
		}
	}
	public void scorePlayers(ArrayList<JavaPlayer> winners){
		for(JavaPlayer p: list)
			p.setItem("famePoints",p.getItemCount("famePoints")+level)
	}
	*/
}
