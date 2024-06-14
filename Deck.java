import java.util.*;

/**
 * Deck class represents a deck of playing cards.
 * 
 * @author Tanner
 * @since 06/07/2024
 */
public class Deck {
    // Variable declarations
    private List<Card> deck; // List of cards in the deck
    private final int numberCards = 80; // 80 number cards including number 0 to 9
    private int topOfDeck;

    private final int colourRed = 0; // ID for colours
    private final int colourBlue = 1;
    private final int colourYellow = 2;
    private final int colourGreen = 3;
    private final int COLOR_BLACK = 4;

    private boolean selectable = true; // Sets selectable to not equal itself

    /**
     * Deck
     * Default constructor of deck
     * 
     * @author tba
     * @since 2024/06/12
     */
    public Deck() {
        super();
        deck = new ArrayList<>();
        this.fillDeck();
        topOfDeck = deck.size() - 1;
    }

    /**
     * drawCard
     * Draws the first card in the deck.
     * If the deck is empty, calls fillDeck() to refill the deck.
     * 
     * @author tba
     * @return The card drawn from the deck.
     */
    public Card drawCard() {
        if (deck.isEmpty()) {
            fillDeck();
        }
        Card drawnCard = deck.get(0);
        removeCard();
        return drawnCard;
    }

    /**
     * fillDeck
     * Refills and shuffles deck if all cards are drawn or a new game started
     * 
     * @author tba
     */
    public void fillDeck() {
        deck.clear();
        addNumberCards();
        addSpecialCards();
        Collections.shuffle(deck);
    }

    /**
     * removeCard
     * Removes the first card from the deck
     * 
     * @author tba
     */
    public void removeCard() {
        deck.remove(0);
    }

    public void addCard(Card card) //adds a card back into the deck
    {
        this.deck.add(0, card);
        Collections.shuffle(deck);
    }

    /**
     * addSpecialCards
     * adds all non number cards to deck
     * 
     * @author tba
     */
    public void addSpecialCards() {
        addPlusTwoCards();
        addPlusFourCards();
        addReverseCards();
        addBlockCards();
        addWildCards();
    }

    /**
     * addPlusFourCards
     * adds +4 cards to deck
     * 
     * @author tba
     */
    public void addPlusFourCards() {
        for (int i = 0; i < 4; i++) {
            deck.add(new Card(13, COLOR_BLACK));
        }
    }

    /**
     * addWildCards
     * adds colour changing cards to deck
     * 
     * @author tba
     */
    public void addWildCards() {
        for (int i = 0; i < 4; i++) {
            deck.add(new Card(14, COLOR_BLACK));
        }
    }

    /**
     * addBlockCards
     * adds skip turn card to deck
     * 
     * @author tba
     */
    public void addBlockCards() {
        for (int i = 0; i < 4; i++) {
            deck.add(new Card(12, i));
            deck.add(new Card(12, i));
        }
    }

    /**
     * addReverseCards
     * adds reverse action order card to deck
     * 
     * @author tba
     */
    public void addReverseCards() {
        for (int i = 0; i < 4; i++) {
            deck.add(new Card(10, i));
            deck.add(new Card(10, i));
        }
    }

    /**
     * addPlusTwoCards
     * adds draw 2 card to deck
     * 
     * @author tba
     */
    public void addPlusTwoCards() {
        for (int i = 0; i < 4; i++) {
            deck.add(new Card(11, i));
            deck.add(new Card(11, i));
        }
    }

    /**
     * addNumberCards
     * Method to add 80 number cards from 4 different colours to the deck
     * 
     * @author tba
     */
    public void addNumberCards() {
        for (int cardFaceValue = 0; cardFaceValue < numberCards / 8; cardFaceValue++) {
            for (int colour = 0; colour < 4; colour++) {
                deck.add(new Card(cardFaceValue, colour));
                deck.add(new Card(cardFaceValue, colour));
            }
        }
    }

    /**
     * getSelectable
     * returns if the deck is currently available to be pressed
     * 
     * @author tba
     */
    public boolean getSelectable() {
        return selectable;
    }

    /**
     * setSelectable
     * sets if the deck should be allowed to be accessed by the user
     * 
     * @author tba
     */
    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }
}
