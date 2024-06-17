import java.util.ArrayList;

/**
 * Player
 * Player object storing info on the user
 *
 * @author Avaneesh
 * @since 2024/06/12
 */
public class Player {
    private UnoModel model; // model of the Uno game
    private String playerName; // name of the player
    private boolean wonRound; // if player won or not
    private int totalScore; // score of player
    private int playerNumber; // value of player (determines order)
    private ArrayList<Card> cards; // List of cards player has
    private String source;// Reason for drawing cards
    private boolean selectable; // if players turn

    /**
     * Player
     * Player Constructor
     *
     * @param playerNumber - player id
     * @param playerName   - player userbane
     * @author Avaneesh
     */
    public Player(int playerNumber, String playerName, UnoModel model) {
        this.playerNumber = playerNumber;
        this.playerName = playerName;
        this.model = model;
        this.wonRound = false;
        this.totalScore = 0;
        this.selectable = false;
        this.cards = new ArrayList<>();
        model.setUNOState(model.SAFE);
    }

    /**
     * addCard
     * adds a card to users hand
     *
     * @param card   - card being added
     * @param source - why player is drawing a card (may not be needed we'll see)
     * @author Avaneesh
     */
    public void addCard(Card card, String source) {
        this.cards.add(card);
        this.source = source;
        this.organizeHand();
    }

    /**
     * placeCard
     * places a card out of the users hand
     *
     * @param cardIndex - location of users card in hand
     * @author Avaneesh
     */
    public void placeCard(int cardIndex) {
        cards.remove(cardIndex);
    }

    /**
     * getHand
     * returns the hand of the user
     *
     * @return cards
     * @author Avaneesh
     */
    public ArrayList<Card> getHand() {
        return cards;
    }

    /**
     * setSelectable
     * sets if user can select his cards
     *
     * @author Avaneesh
     */
    public void setSelectable() {
        this.selectable = !this.selectable;
    }

    /**
     * setGetSelectable
     * returns if users turn
     *
     * @return THISS IS AN ERROR JKNEJN
     * @author Avaneesh
     */
    public boolean setGetSelectable() {
        return setGetSelectable();
    }

    /**
     * getPlayerName
     * returns users name
     *
     * @return playerName
     * @author Avaneesh
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * getUnoState
     * gets the uno state
     * 
     * @author Tanner
     * @return model.SAFE
     * @return model.NOT_SAFE
     * 
     */
    public int getUnoState() {
        if (getHand().size() != 1) {
            return model.SAFE;
        } else
            return model.NOT_SAFE;
    }

    /**
     * setWon
     * sets if player won the game
     *
     * @author Avaneesh
     */
    public void setWon() {
        this.wonRound = true;
    }

    public boolean hasWon() {
        return wonRound;
    }

    /**
     * getTotalScore
     * returns players score
     *
     * @return totalScore
     * @author Avaneesh
     */
    public int getTotalScore() {
        totalScore = 0;
        for (Card card : cards) {
            totalScore += card.getScoreValue();
        }
        return totalScore;
    }

    /**
     * GetPlayerID
     * returns players id
     *
     * @return playerNumber
     * @author Avaneesh
     */
    public int getPlayerID() {
        return playerNumber;
    }

    /**
     * organizeHand
     * sorts cards in players hand
     *
     * @author Avaneesh
     */
    public void organizeHand() {

        sortByNumbWithColour();
        sortByColour();
    }

    /**
     * sortByColour
     * sorts cards in players hand by colour
     *
     * @author Avaneesh
     */
    private void sortByColour() {
        int minIndex;
        for (int x = 0; x < cards.size(); x++) { // for each card
            minIndex = x;
            for (int y = x + 1; y < cards.size(); y++) { // for every unsorted card
                if (cards.get(y).getColour() < cards.get(minIndex).getColour()) { // if card colour is different prior
                                                                                  // in index than current colour being
                                                                                  // sorted
                    minIndex = y;
                }
            }
            Card temp = cards.get(minIndex);
            cards.set(minIndex, cards.get(x));
            cards.set(x, temp);
        }
    }

    /**
     * sortByNumbWithColour
     * sorts cards in players hand by number saving colour order as priority
     *
     * @author Avaneesh
     */
    private void sortByNumbWithColour() {
        System.out.println("org");
        int minIndex;
        for (int x = 0; x < cards.size(); x++) { // for each card
            int color = cards.get(x).getColour();
            int endIndex = x;
            while (endIndex < cards.size() && cards.get(endIndex).getColour() == color) { // Find the cards with the
                                                                                          // same color

            }

            for (int a = x; a < cards.size(); a++) { //sort cards by value within the same color
                minIndex = a;
                for (int y = a + 1; y < cards.size(); y++) { // sort the cards by value of the same color
                    if (cards.get(y).getValue() < cards.get(minIndex).getValue()) {// find the the card with the lowest
                                                                                   // value
                        minIndex = y;
                    }
                }
                Card temp = cards.get(minIndex);
                cards.set(minIndex, cards.get(a));
                cards.set(a, temp);
            }
            x = endIndex - 1;
        }
    }
}