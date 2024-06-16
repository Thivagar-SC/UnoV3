
/**
 * Card
 * Creates a playing card that has a value and colour
 * 
 * @author Avaneesh
 * @since 2024/06/12
 */
public class Card {
    private final int cardFaceValue; // value of card
    private int colour; // color of card
    private final int scoreValue; // score value of cards

    /**
     * Card
     * Card Constructor
     * 
     * @author Avaneesh
     * @param cardFaceValue - set value of card
     * @param colour        - colour card will be
     */
    public Card(int cardFaceValue, int colour, int scoreValue) {
        super();
        this.cardFaceValue = cardFaceValue;
        this.colour = colour;
        this.scoreValue = scoreValue;
    }

    /**
     * Gets score value based on the cards
     * Number cards are their own number values
     * Wild and Wild +4 are worth 50, others are 20
     *
     * @return the value based on the cards
     */
    public int getScoreValue()
    {
        if (cardFaceValue < 10)
        {
            return scoreValue;
        }
        else if (scoreValue == 13 || scoreValue == 14)
        {
            return 50;
        }
        else
        {
            return 20;
        }
    }

    /**
     * getValue
     * returns value of card
     * 
     * @return cardFaceValue
     * @author Avaneesh
     */
    public int getValue() {
        return cardFaceValue;
    }

    /**
     * getColour
     * returns the colour of a card
     * 
     * @author Avaneesh
     * @return colour
     */
    public int getColour() {
        return colour;
    }

    /**
     * changeColour
     * changes the colour of a card
     * 
     * @author Avaneesh
     * @param newColour - colour card will change to
     */
    public void changeColour(int newColour) {
        colour = newColour;
    }

}
