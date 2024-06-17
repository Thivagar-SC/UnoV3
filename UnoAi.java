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
        this.delayTime = 3000;
    }

    public ArrayList<Card> getHand() {
        return this.cards;
    }

    public void placeCard(int cardIndex, Card currentlyPlacedCard) {
        wonRound = false;
        if (cards.size() == 0) {
            wonRound = true;
            return;
        }

        if (currentlyPlacedCard.getValue() == 13) {

            for (int y = 0; y < 4; y++) {
                model.drawCard();

            }
        }

        if (currentlyPlacedCard.getValue() == 11) {

            for (int y = 0; y < 2; y++) {
                model.drawCard();
            }
        }

        boolean placeCard = false;
        for (int x = 0; x < cards.size(); x++) { // Loops through all the cards
            Card cardToPlace = cards.get(x);
            if (cardToPlace.getColour() == currentlyPlacedCard.getColour()
                    || cardToPlace.getValue() == currentlyPlacedCard.getValue()
                    || cardToPlace.getValue() == 13
                    || cardToPlace.getValue() == 14) {
                cards.remove(x);
                model.placeCard(cardToPlace, null, this.playerNumber);
                placeCard = true;

                return; // prevent the ai from placing multiple cards
            }
            if (currentlyPlacedCard.getValue() == 10) {
                return;
            }

            if (currentlyPlacedCard.getValue() == 12) {
                model.nextTurn(1);
                return;
            }
            if (currentlyPlacedCard.getValue() == 13) {
                double randomColor = (Math.random() * 4);
                int color = (int) Math.round(randomColor);
                cardToPlace.changeColour(color);
                model.nextTurn(1);
                return;
            }
            if (currentlyPlacedCard.getValue() == 14) {
                double randomColor = (Math.random() * 4);
                int color = (int) Math.round(randomColor);
                cardToPlace.changeColour(color);
                return;
            }

        }

        if (placeCard == false) {
            model.drawCard();
        }
    }

    public int getAITotalScore() {
        int totalScore = 0;
        for (Card card : cards) {
            totalScore += card.getScoreValue();
        }
        return totalScore;
    }

    /**
     * Adds a card to the AI's hand
     *
     * @param card   the card to be added
     * @param source idk what this is
     */
    public void addCard(Card card, String source) {
        this.cards.add(card);
    }

    public void getcurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

}
