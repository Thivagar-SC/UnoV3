import java.util.ArrayList;

//object represent ai player vs in game

// * @author Avaneesh
// * @since 2024/06/12
// */
public class UnoAi {
    private double delayTime; // delays the time of the AI's actions
    private ArrayList<Card> cards; // list of cards ai has
    private boolean wonRound; // ai won the game
    private int playerNumber; // value for player order
    private Card currentCard;

    // I aint commenting this yet
    public UnoAi(int playerNumber) {
        this.playerNumber = playerNumber;
        this.wonRound = false;
        this.cards = new ArrayList<Card>();
    }

    public void placeCard(int cardIndex, Card currentlyPlacedCard)
    {
         for(int x = 0; x < cards.size(); x++)
         {            Card cardToPlace = cards.get(x);
            if(cardToPlace.getColour() == currentlyPlacedCard.getColour() || cardToPlace.getValue() == currentlyPlacedCard.getValue() || cardToPlace.getValue() == 13 || cardToPlace.getValue() == 14)
            {
                cards.remove(x);
                currentCard = cardToPlace;
            }
            if(currentlyPlacedCard.getValue() == 13)
            {
                nextPlayerTurn();
            }
            if(currentlyPlacedCard.getValue() == 14)
            {
                double randomColor  = (Math.random()*4);
                int color = (int)Math.round(randomColor);
                cardToPlace.changeColour(color);
            }
        }
    }

    public void hitUNO() {
        if(cards.size() == 1)
        {

        }
    }

    public void hitBlock() {

    }

    private void addCard(Card card, String source) {

    }
public void getcurrentCard(Card currentCard)
    {
        this.currentCard = currentCard;
    }


}
