import java.awt.event.KeyEvent;
import java.util.List;
import java.io.*;

/**
 * This class represents the model of the Uno game.
 * It manages the game state, players, deck, and game logic.
 */
public class UnoModel {
    private UnoView view;
    // private PauseMenu pauseMenu;
    private Card currentlyPlacedCard;
    private String gameState;
    private int turn = 0;
    private Player player;
    // private UnoAi ai;
    private Deck deck;
    private boolean safe;
    private int numberOfRounds;
    private List<Card> recentCards;
    private List<Card> cardsInHand;
    private List<Integer> points;
    private List<String> winners;
    private List<Player> players;
    public File saveFile;
    private int direction = 1;
    // GUI variables
    private boolean menuSelection;
    private int state; // state of game
    private int safeState;
    private UnoAi[] aiEnemy = new UnoAi[3];

    public final int MENU = 0;
    public final int SELECTION = 1;
    public final int GAME = 2;
    public final int PAUSED = 3;
    public final int ENDGAME = 4;
    public final int RESET = 5;
    public final int AI_TURN= 6;

    public final int SAFE = 0;
    public final int CALLED = 1;
    public final int NOT_SAFE = 2;

    /**
     * Constructor for UnoModel.
     */
    public UnoModel() {
        super();
    }

    /**
     * Moves the game state to the main menu.
     * Resets the number of rounds and menu selection status.
     * Updates the view to reflect the changes.
     */
    public void mainMenu() {
        this.state = this.MENU; // Set the game state to main menu
        this.numberOfRounds = -1; // Reset the number of rounds
        this.menuSelection = true; // Set the menu selection status to true
        this.view.update(); // Update the view to reflect the changes
    }

    /**
     * Resets the game when player chooses to return to main menu
     */
    public void reset() {
        this.view.update();
        this.state = this.RESET;
        this.view.update();
        this.mainMenu();
    }

    /**
     * Moves to the next turn.
     *@author Avaneesh
     * @param skip number of players to skip.
     */
    public void nextTurn(int skip) 
    {
        System.out.println(turn);
        turn = turn + direction;
        if (turn == 4){
            turn = 0;
        }
        if (turn == -1){
            turn = 3;
        }
        for (int x = 0; x<skip;x++){
            turn = turn + direction;

        
        if (turn == 4){
            turn = 0;
        }
        if (turn == -1){
            turn = 3;
        }
    }
        System.out.println(turn);
    }

    /**
     * Gets the current state of the game
     *
     * @return the current state of the game
     */
    public int getState() {
        return this.state;
    }

    /**
     * Sets the current state of the game
     *
     * @param state the new state of the game
     */
    public void setState(int state) {
        this.state = state;
        this.view.update();
    }

    /**
     * Checks if the current round is over.
     */
    public void checkIfRoundIsOver() {
        for (int i = 0; i < 3; i++)
        {
            UnoAi aiWinner = this.aiEnemy[i];
            if (aiWinner.getHand().isEmpty())
            {
                int totalScore = 0;
                totalScore += aiWinner.getAITotalScore();
            }

            if (player.getHand().isEmpty()) {
                int totalScore = 0;
                totalScore += player.getTotalScore();
            }
        }
        player.setWon();
    }

    /**
     * Places a card from a player's hand.
     * @author Avaneesh
     * @param card     the card to be placed.
     * @param playerID ID of the player placing the card.
     */
    public void placeCard(Card aCard,RoundedJPane card, int playerID) 
    {
        System.out.println("stuff");
        if (card!=null){    
            turn = 0;
        }
        if (currentlyPlacedCard.getValue() == 13) {
            if (turn == 0){
                cardsInHand = this.player.getHand();
            }
            else{
                cardsInHand = this.aiEnemy[turn-1].getHand();
            }
            for (int x = 0; x < cardsInHand.size(); x++) {
                Card cardCheck = cardsInHand.get(x);
                if (cardCheck.getValue() == 13) {

                } else {
                    for (int y = 0; y < 4; y++) {
                        drawCard();
                    }

                }
            }
        }
        if (currentlyPlacedCard.getValue() == 11) {
            if (turn == 0){
                cardsInHand = this.player.getHand();
            }
            else{
                cardsInHand = this.aiEnemy[turn-1].getHand();
            }
            //this.view.update(); //possibly temp
            for (int x = 0; x < cardsInHand.size(); x++) {
                Card cardCheck = cardsInHand.get(x);
                if (cardCheck.getValue() == 11) {
                    this.player.getHand().remove(x);
                    this.currentlyPlacedCard = cardCheck;
                    nextTurn(1);
                } else {
                    for (int y = 0; y < 2; y++) {
                        drawCard();
                    }

                }
            }
        }

        if (turn == 0)
        {
            System.out.println("blah");
        int cardIndex = -1;
        for (int x = 0; x < this.view.getCards().size(); x++) {
            if (card.equals(this.view.getCards().get(x))) {
                cardIndex = x;
            }
        }
        System.out.println("THE CARD LOCATION" + cardIndex);
        
        Card cardToPlace = player.getHand().get(cardIndex);
        if (cardToPlace.getValue() == 13) {
            this.currentlyPlacedCard = cardToPlace;
            this.player.getHand().remove(cardIndex);
            this.view.displayColourSelectors();
            return;
        }
        if (cardToPlace.getValue() == 14) {
            this.currentlyPlacedCard = cardToPlace;
            this.player.getHand().remove(cardIndex);
            this.view.displayColourSelectors();
            return;
        }
        if (cardToPlace.getColour() == currentlyPlacedCard.getColour()
                || cardToPlace.getValue() == currentlyPlacedCard.getValue()) {
            this.player.getHand().remove(cardIndex);
            currentlyPlacedCard = cardToPlace;
            if (cardToPlace.getValue() == 10) {
                direction *= -1;
                nextTurn(1);
            } else if (cardToPlace.getValue() == 11) {
                plusTwoCard();
            } else if (cardToPlace.getValue() == 12) {
                nextTurn(2);
            }
        }
        if (cardToPlace.getColour() == currentlyPlacedCard.getColour()
                || cardToPlace.getValue() == currentlyPlacedCard.getValue()){
        System.out.println(this.state);
        this.view.update();
        System.out.println("TESTTT2");
        this.setState(this.AI_TURN);
        this.view.update();
                }
    }
    else{
        this.currentlyPlacedCard = aCard;
        this.view.update();
    }
    }


    /**
     * @author Avaneesh
     * Adds 2 cards to the player's hand if no counter
     */
    private void plusTwoCard() 
    {
        int totalStack = 2;
        int nextPlayer = (turn + direction);

    }

    /**
     * Returns the current player.
     *
     * @return the current player.
     */
    public Player getCurrentPlayer() {
        return player;
    }

    /**
     * Quits the game.
     */
    public void quitGame() {
        System.exit(0);
    }

    /**
     * Handles input for the ESC key.
     *
     * @param keyCode - the key code of the pressed key.
     */
    public void pauseByESC(int keyCode) {
        if (keyCode == KeyEvent.VK_ESCAPE) {
            System.out.println("Esc is pressed");
            pauseGame();
        }
    }

    /**
     * Pauses the game
     */
    public void pauseGame() {
        if (getState() == this.PAUSED) {
            view.update();
            this.state = this.GAME;
        } else {
            this.state = this.PAUSED;
            view.update();
        }
    }

    /**
     * Changes the color of the next card to be played.
     *@author Avaneesh
     * @param colour - the new color.
     */
    public void changeColour(int colour) 
    {
        currentlyPlacedCard.changeColour(colour);
        this.view.update();
        //this.nextTurn(0);
        //this.state = this.AI_TURN;
        //this.view.update();
    }

    /**
     * Returns the current colour of the next card to be played.
     *
     * @return the current colour.
     */
    public int getCurrentColour() {
        return 0; // placeholder
    }

    /**
     * Sets the UNO state for the current player.
     */
    public void setUNOState(int safeState) {
        if (this.safeState == CALLED && this.safeState == NOT_SAFE) {
            return;
        }
        this.safeState = safeState;
    }

    public void nextUser(){
        //cause movement
        
        this.state = this.AI_TURN;
        System.out.println("AI TURN");

        int skipCount = 0;

        if (this.getCurrentCard().getValue()==12){
            skipCount++;
        }
        this.nextTurn(skipCount);
        if (turn == 0){
            this.state = this.GAME;
            if (this.player.getHand().size()==0){
                this.nextRound();
                return;
            }
        }
        else{
            System.out.println("AI: "+turn);
            try{
            aiEnemy[turn-1].placeCard(-1, this.currentlyPlacedCard);
            if(this.aiEnemy[turn-1].getHand().size()==0){
                this.nextRound();
                return;
            }
        }
        catch(Exception e){}
        }
        
        this.view.update();
    }

    /**
     * Checks if the current player is safe.
     *
     * @return - SAFE if the player is safe, NOT_SAFE otherwise.
     */
    public int isSafe(int safeState) {
        if (player.getHand().size() != 1) {
            return SAFE;
        } else {
            return NOT_SAFE;
        }
    }

    /**
     * Adds two cards to the slow player's hand
     *
     * @param player the player affected by the UNO Block
     */
    public void addTwoFromUnoBlock(Player player) {
        this.setUNOState(SAFE);
        for (int i = 0; i < 2; i++) {
            this.player.addCard(deck.drawCard(), "Didn't call UNO in time");
        }
    }

    /**
     * Starts the game.
     */
    public void startGame() {
        int numberRounds;
        String nameOfPlayer;
        this.turn = 0;
        this.createSaveFile();
        this.state = SELECTION;
        this.menuSelection = false;
        nameOfPlayer = this.view.getPlayerName();
        numberRounds = this.view.getRounds();
        if (numberRounds > 0) {
            this.state = GAME;
            this.numberOfRounds = numberRounds;
            player = new Player(0, nameOfPlayer, this); // player number temporary
            aiEnemy[0] = new UnoAi(1, this);
            aiEnemy[1] = new UnoAi(1, this);
            aiEnemy[2] = new UnoAi(1, this);
            this.deck = new Deck();
            this.gameResultOutput(); // placed here to test

            for (int x = 1; x <= this.view.getNumOfStartingCards(); x++) {
                this.player.addCard(this.deck.drawCard(), "TBA");
                this.aiEnemy[0].addCard(this.deck.drawCard(), "TBA");
                this.aiEnemy[1].addCard(this.deck.drawCard(), "TBA");
                this.aiEnemy[2].addCard(this.deck.drawCard(), "TBA");
            }

        } else {
            this.numberOfRounds = -1;
        }

        this.placeStarterCard();
        this.view.update();
    }

    public UnoAi getCurrentAi() {
        switch (turn) {
            case 0:
                return this.aiEnemy[0];
            case 1:
                return this.aiEnemy[1];

            case 2:
                return this.aiEnemy[2];

            case 3:
                return this.aiEnemy[3];

            default:
                return null;
        }
    }

    /**
     * Creates a save file after user starts the game
     */
    public void createSaveFile() {
        try {
            int fileNumber = 1; // Starts with the first file number

            while (this.state == 1) {
                saveFile = new File("SaveFiles/Save File #" + fileNumber + ".txt");
                if (!saveFile.exists()) {
                    break;
                }
                fileNumber++; // Increments the file number if the file already exists
            }

            if (saveFile != null && saveFile.createNewFile()) {
                System.out.println("File created: " + saveFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Places the first card in the pile to start off the game
     */
    public void placeStarterCard() {
        this.turn = 0;
        Card cuCard = this.deck.drawCard();
        while (cuCard.getValue() > 9) // while card is invalid to start with
        {
            this.deck.addCard(cuCard);
            cuCard = this.deck.drawCard();
        }
        this.currentlyPlacedCard = cuCard;
    }

    /**
     * Raises the hovered card above the rest
     */
    public void raiseCard(Object card) {
        this.view.updateCard(true, card);
    }

    /**
     * Drops the raised card back down if mouse no longer hovers
     */
    public void dropCard(Object card) {
        this.view.updateCard(false, card);
    }

    public UnoAi[] getAi(){
        return this.aiEnemy;
    }

    // ta=ba
    public int getNumberOfRound() {
        return this.numberOfRounds;
    }

    /**
     * Starts game startup
     */
    public void startSelection() {
        this.state = 1;
        this.menuSelection = true;
        this.view.update();
    }

    // tba
    public boolean getMenuSelect() {
        return this.menuSelection;
    }

    /**
     * Moves to the next round.
     */
    public void nextRound() {
        this.view.nextRound();
        
    }

    /**
     * Checks if a card is legal to play.
     *
     * @param card - the card to check.
     * @return - true if the card is legal, false otherwise.
     */
    public boolean isLegal(Card card) {
        return true; // placeholder
    }

    public void setGUI(UnoView gui) {
        this.view = gui;
    }

    public void drawFromDeck() {
        this.state = this.GAME;
        Card drawnCard = new Card(-1, -1, -1); // placeholder card
        if (this.view.getDeckMod()) {
            while (drawnCard.getColour() != this.currentlyPlacedCard.getColour()
                    && drawnCard.getValue() != this.currentlyPlacedCard.getValue()
                    && drawnCard.getColour() != 4) {
                this.view.update();
                // try { -please help me

                drawnCard = this.deck.drawCard();
                this.player.addCard(drawnCard, "");
                this.view.update();
                System.out.println("#: " + drawnCard.getValue());
                System.out.println("Colour: " + drawnCard.getColour());
                // Thread.sleep(200);
                // } catch (Exception e) {
                // System.out.println("error");
                // }
            }
            return;
        }
        drawnCard = this.deck.drawCard();
        this.player.addCard(drawnCard, "TBA");
        System.out.println("#: " + drawnCard.getValue());
        System.out.println("Colour: " + drawnCard.getColour());
        this.view.update();
    }

    public void drawCard() {
        //this.state = this.GAME;
        if (this.turn == 0){
        this.player.addCard(this.deck.drawCard(), "TBA");
        this.view.update();
        this.state = this.AI_TURN;
        }
        else{
            this.aiEnemy[this.turn-1].addCard(this.deck.drawCard(), "TBA");
        }
        this.view.update();
    }

    public Card getCurrentCard() {
        return this.currentlyPlacedCard;
    }

    /**
     * Sorts the winner and the rest by the scores
     */
    public void sortByScore() {

    }

    /**
     * Writes game result of each round to a save file
     *
     * @author Tanner
     */
    public void gameResultOutput() {
        try {
            PrintWriter output = new PrintWriter("SaveFiles/" + saveFile.getName());
            output.println("LAST GAME'S RESULTS: \n");
            if (player.getPlayerName().isEmpty())
            {
                output.println("Player's name: " + "GUEST");
            }
            else
            {
                output.println("Player's name: " + player.getPlayerName());
            }

            if (player.hasWon()) {
                output.println("You won!");
            } else {
                output.println("You lost!");
            }

            output.println("Total scores: " + player.getTotalScore());

            for (int i = 0; i < 3; i++)
            {
                output.println("Total score of AI#" + (i+1) + ": " + aiEnemy[i].getAITotalScore());
            }

            // More
            output.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        // Needs to finish the sortByScore method to write the 1st, 2nd, 3rd and 4th
        // place

    }
}