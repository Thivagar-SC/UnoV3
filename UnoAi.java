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
    private UnoModel model;

    // I aint commenting this yet
    public UnoAi(int playerNumber, UnoModel model) {
        this.playerNumber = playerNumber;
        this.wonRound = false;
        this.cards = new ArrayList<>();
        this.model = model;
        model.setUNOState(model.SAFE);
    }

    /**
     * Places card from the AI's hand
     *
     * @param cardIndex           the index of the card to be placed
     * @param currentlyPlacedCard the currently placed card
     */
    public void placeCard(int cardIndex, Card currentlyPlacedCard) {
        if (currentlyPlacedCard.getValue() == 13) {
            for (int x = 0; x < cards.size(); x++) {
                Card cardCheck = cards.get(x);
                if (cardCheck.getValue() == 13) {

                } else {
                    for (int y = 0; y < 4; y++) {
                        // cards.add(model.drawCard());
                    }

                }
            }
        }
        for (int x = 0; x < cards.size(); x++) {
            Card cardToPlace = cards.get(x);
            if (cardToPlace.getColour() == currentlyPlacedCard.getColour()
                    || cardToPlace.getValue() == currentlyPlacedCard.getValue() || cardToPlace.getValue() == 13
                    || cardToPlace.getValue() == 14) {
                cards.remove(x);
                currentCard = cardToPlace;
            }
            if (currentlyPlacedCard.getValue() == 13) {
                model.nextTurn(1);

            }
            if (currentlyPlacedCard.getValue() == 14) {
                double randomColor = (Math.random() * 4);
                int color = (int) Math.round(randomColor);
                cardToPlace.changeColour(color);
            }
        }
    }

    /**
     * Hits UNO when the AI's hand has one card
     */
    public void hitUNO() {
        if (cards.size() == 1) {

        }
    }

    /**
     * Hits a block when to counter UNO other players
     */
    public void hitBlock() {

    }

    /**
     * Adds a card to the AI's hand
     *
     * @param card   the card to be added
     * @param source idk what this is
     */
    private void addCard(Card card, String source) {

    }

    /**
     * Gets the AI's current card
     *
     * @param currentCard the current card
     */
    public void getcurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

}
