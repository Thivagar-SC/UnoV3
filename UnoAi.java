import java.util.ArrayList;

/**
 * UnoAi
 * 
 * @author Avaneesh
 * @since 2024/06/14
 */
public class UnoAi {
    private ArrayList<Card> cards; // list of cards ai has
    private boolean wonRound; // ai won the game
    private int playerNumber; // value for player order
    private Card currentCard; //the current card
    private UnoModel model; //

/**
 * UnoAi
 * UnoAi constructor
 * 
 * @author Avaneesh
 * @param playerNumber - player id
 * @param model - model of Uno
 */
    public UnoAi(int playerNumber, UnoModel model) {
        this.playerNumber = playerNumber;
        this.wonRound = false;
        this.cards = new ArrayList<>();
        this.model = model;
    }
/**
 * getHand
 * get the Ai's hand
 * 
 * @author Avaneesh
 * @return this.cards
 */
    public ArrayList<Card> getHand() {
        return this.cards;
    }

/**
 * placeCard
 * placing a card for Ai
 * 
 * @author Avaneesh
 * @param cardIndex - deck of the Ai's
 * @param currentlyPlacedCard - the currently placed card
 */
    public void placeCard(int cardIndex, Card currentlyPlacedCard) {
        if (cards.size() == 0) { //if Ai's hand has 0 cards
            this.wonRound = true;
            return; 
        }

        if (currentlyPlacedCard.getValue() == 13) { //if the currently placed card is a +4

            for (int y = 0; y < 4; y++) {
                model.drawCard();

            }
        }

        if (currentlyPlacedCard.getValue() == 11) { //if the currently placed card is a +2

            for (int y = 0; y < 2; y++) {
                model.drawCard();
            }
        }

        boolean placeCard = false;
        for (int x = 0; x < cards.size(); x++) { // Loops through all the cards
            Card cardToPlace = cards.get(x);
            if (cardToPlace.getColour() == currentlyPlacedCard.getColour() //if the card color the ai wants to place  = the currently placed color
                    || cardToPlace.getValue() == currentlyPlacedCard.getValue() // or if the value the ai wants to plave = the currently placed value
                    || cardToPlace.getValue() == 13 //or if the ai wants to place a color swap
                    || cardToPlace.getValue() == 14) { //or if the ai wants to place a +4
                cards.remove(x);
                model.placeCard(cardToPlace, null, this.playerNumber);
                placeCard = true;

                return; // prevent the ai from placing multiple cards
            }
            if (currentlyPlacedCard.getValue() == 10) { //if the currently placed card value is a swap
                return; 
            }

            if (currentlyPlacedCard.getValue() == 12) { //if the currently placed card value is a block
                model.nextTurn(1);
                return;
            }
            if (currentlyPlacedCard.getValue() == 13) { //if the currently placed card is a +4
                double randomColor = (Math.random() * 4);
                int color = (int) Math.round(randomColor);
                cardToPlace.changeColour(color);
                model.nextTurn(1);
                return;
            }
            if (currentlyPlacedCard.getValue() == 14) { //if the currently placed card is a color swap
                double randomColor = (Math.random() * 4);
                int color = (int) Math.round(randomColor);
                cardToPlace.changeColour(color);
                return;
            }

        }

        if (placeCard == false) { //if ai doesnt place a card
            model.drawCard();
        }
    }
/**
 * getAiTotalScore
 * Gets the Ai's total score
 * 
 * @author Tanner
 * @return total score
 */
    public int getAITotalScore() {
        int totalScore = 0;
        for (Card card : cards) {
            totalScore += card.getScoreValue();
        }
        return totalScore;
    }

    /**
     * addCard
     * Adds a card to the AI's hand
     * 
     * @author Avaneesh
     * @param card   the card to be added
     * @param source idk what this is
     */
    public void addCard(Card card, String source) {
        this.cards.add(card);
    }
    /**
     * getcurrentCard
     * provides ai with current card
     * 
     * @author Avaneesh
     * @param currentCard
     */
    public void getcurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

}
