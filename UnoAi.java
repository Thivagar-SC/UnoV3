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
        this.cards = new ArrayList<Card>();
        this.model = model;
    }

    public void placeCard(int cardIndex, Card currentlyPlacedCard) {
        if (currentlyPlacedCard.getValue() == 13) {
            for (int x = 0; x < cards.size(); x++) {
                Card cardCheck = cards.get(x);
                if (cardCheck.getValue() == 13) {

                } else {
                    for (int y = 0; y < 4; y++) {
                        model.drawCard();
                    }

                }
            }
        }
        if (currentlyPlacedCard.getValue() == 11) {
            for (int x = 0; x < cards.size(); x++) {
                Card cardCheck = cards.get(x);
                if (cardCheck.getValue() == 11) {

                } else {
                    for (int y = 0; y < 2; y++) {
                        model.drawCard();
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

    public void hitUNO() {
        if (cards.size() == 1) {

        }
    }

    public void hitBlock() {

    }

    private void addCard(Card card, String source) {

    }

    public void getcurrentCard(Card currentCard) {
        this.currentCard = currentCard;
    }

}
