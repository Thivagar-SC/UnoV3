import java.util.ArrayList;
import java.util.*;

/**
 * Player
 * Player object storing info on the user
 * 
 * @author Avaneesh
 * @since 2024/06/12
 */
public class Player {
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
     * @author tba
     * @param playerNumber - player id
     * @param playerName   - player userbane
     */
    public Player(int playerNumber, String playerName) {
        this.playerNumber = playerNumber;
        this.playerName = playerName;
        this.wonRound = false;
        this.totalScore = 0;
        this.selectable = false;
        this.cards = new ArrayList<Card>();

    }

    /**
     * addCard
     * adds a card to users hand
     * 
     * @author tba
     * @param card   - card being added
     * @param source - why player is drawing a card (may not be needed we'll see)
     */
    public void addCard(Card card, String source) {

        this.cards.add(card);
        this.source = source;
    }

    /**
     * placeCard
     * places a card out of the users hand
     * 
     * @author tba
     * @param cardIndex - location of users card in hand
     */
    public void placeCard(int cardIndex) {
        cards.remove(cardIndex);
    }

    /**
     * getHand
     * returns the hand of the user
     * 
     * @author tba
     * @return cards
     */
    public ArrayList<Card> getHand() {
        return cards;
    }

    /**
     * setSelectable
     * sets if user can select his cards
     * 
     * @author tba
     */
    public void setSelectable() {
        this.selectable = !this.selectable;
    }

    /**
     * setGetSelectable
     * returns if users turn
     * 
     * @author tba
     * @return THISS IS AN ERROR JKNEJN
     */
    public boolean setGetSelectable() {
        return setGetSelectable();
    }

    /**
     * getPlayerName
     * returns users name
     * 
     * @author Avaneesh
     * @return playerName
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * setWon
     * sets if player won the game
     * 
     * @author AVaneesh
     */
    public void setWon() {
        this.wonRound = true;
    }

    /**
     * getTotalScore
     * returns players score
     * 
     * @author Avaneesh
     * @return totalScore
     */
    public int getTotalScore() {
        return totalScore;
    }

    /**
     * GetPlayerID
     * returns players id
     * 
     * @author Avaneesh
     * @return playerNumber
     */
    public int GetPlayerID() {
        return playerNumber;
    }

    /**
     * organizeHand
     * sorts cards in players hand
     * 
     * @author Avaneesh
     */
    public void organizeHand() {
        sortByColour();
        sortByNumbWithColour();
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
        int minIndex;
        for (int x = 0; x < cards.size(); x++) { // for each card
            int color = cards.get(x).getColour();
            int endIndex = x;
            while (endIndex < cards.size() && cards.get(endIndex).getColour() == color) { // tba u do this i cant be
                                                                                          // bothered
                endIndex++;
            }

            for (int a = x; a < cards.size(); a++) { // u can do this
                minIndex = a;
                for (int y = a + 1; y < cards.size(); y++) {
                    if (cards.get(y).getValue() < cards.get(minIndex).getValue()) {
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