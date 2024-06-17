
import javax.management.timer.Timer;
import javax.swing.*;

import javafx.event.ActionEvent;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener; //recode later if time so this is unneeded
import java.util.*;
import java.util.List;
import java.io.*;

/**
 * UnoView
 * View displaying game
 *
 * @author Thivagar Kesavan
 * @since 2024/06/12
 */
public class UnoView extends JPanel {
    private UnoModel model; // model of game
    private ArrayList<RoundedJPane> cards = new ArrayList<RoundedJPane>(); // display of users cards

    private boolean isPaused;
    // components for gui
    private JPanel menu = new JPanel();
    private JPanel postGame = new JPanel();
    private JPanel gameSelect = new JPanel();
    private JButton startGame = new JButton();
    private JButton quitGame = new JButton();
    private JTextArea numberOfRounds = new JTextArea();
    private JTextArea playerName = new JTextArea();
    private JTextField roundInput = new JTextField();
    private JTextField nameInput = new JTextField();
    private JButton playGame = new JButton();
    private RoundedJPane deck = new RoundedJPane(50, 4);
    private RoundedJPane currentCard;
    private PauseMenu pauseMenu;
    private File cardFile = new File("Images");
    private JRadioButton deckModifier = new JRadioButton("Keep Drawing");
    private JPanel icon1 = new JPanel();
    private JTextArea name1 = new JTextArea("Guest");
    private JPanel[] aiIcon = new JPanel[3];
    private JPanel[] aiCards = new JPanel[3];
    private boolean playerTurn = true; //temp
    private List<List<RoundedJPane>> aiHands = new ArrayList<List<RoundedJPane>>();
    private int roundCount = 0;
    private JTextArea[] aiName = new JTextArea[3];
    private JButton returnToMainMenu;
    private JButton restartGame;

    private UnoButton unoButton = new UnoButton();
    private UnoBlockButton unoBlockButton = new UnoBlockButton();

    private JButton red = new JButton("Red");
        private JButton blue = new JButton("Blue");
        private JButton yellow = new JButton("Yellow");
        private JButton green = new JButton("Green");

    /**
     * UnoView
     * UnoView Constructor
     *
     * @param uModel - model of game
     * @author Thivagar
     */
    public UnoView(UnoModel uModel) {
        super();
        this.model = uModel;
        this.mainMenu();
        this.pauseMenu = new PauseMenu();
        this.registerStarterControllers();
        this.model.setGUI(this);
    }

    /**
     * getPlayerName
     * returns players username
     * <p>
     * TO be moved to model in due time
     */
    public String getPlayerName() {
        return this.nameInput.getText();
    }

    // To be moved to model l8r
    public int getRounds() {
        int number = -1;
        try {
            number = Integer.parseInt(this.roundInput.getText());
        } catch (Exception e) {
            // TODO: handle exception
        }
        return number;
    }

    /**
     * mainMenu
     * Opening display of uno game
     *
     * @author Thivagar
     */
    private void mainMenu() { // gui temporary for use
        this.setLayout(new BorderLayout());
        this.cards.clear();
        this.removeAll();
        this.startGame.setText("Start");
        this.startGame.setPreferredSize(new Dimension(200, 100));
        this.quitGame.setText("Quit Game");
        this.quitGame.setPreferredSize(new Dimension(200, 100));

        this.setBackground(Color.PINK);
        this.menu.add(this.startGame);
        this.menu.add(this.quitGame);
        this.add(this.menu, BorderLayout.WEST);
        this.refresh();
    }

    /**
     * gameSetup
     * Display game setup of uno (rounds etc)
     *
     * @author Thivagar
     */
    private void gameSetup() {
        this.removeAll();
        this.numberOfRounds.setText("How many rounds will you play?");
        this.playerName.setText("Please enter your username");
        this.roundInput.setPreferredSize(new Dimension(650, 50));
        this.nameInput.setPreferredSize(new Dimension(650, 50));
        this.roundInput.setText("");
        this.nameInput.setText("");

        this.numberOfRounds.setEditable(false);
        this.playerName.setEditable(false);
        this.playGame.setText("Play!");
        this.playGame.setPreferredSize(new Dimension(400, 200));

        // adding
        this.gameSelect.add(numberOfRounds);
        this.gameSelect.add(roundInput);
        this.gameSelect.add(playerName);
        this.gameSelect.add(nameInput);
        this.gameSelect.add(playGame);
        this.add(gameSelect, BorderLayout.CENTER);
        this.add(deckModifier, BorderLayout.SOUTH);
        this.refresh();

    }

    private void setAiHands(){
        List<RoundedJPane> hand1 = new ArrayList<RoundedJPane>();
        List<RoundedJPane> hand2 = new ArrayList<RoundedJPane>();
        List<RoundedJPane> hand3 = new ArrayList<RoundedJPane>();
        File caImg = new File(cardFile,"_HiddenCard.png");
        aiCards[0] = new JPanel();
        aiCards[1] = new JPanel();
        aiCards[2] = new JPanel();
        aiCards[0].setLayout(null);
        aiCards[1].setLayout(null);
        aiCards[2].setLayout(null);
        aiCards[0].setBackground(new Color(0,0,0,0));
        aiCards[1].setBackground(new Color(0,0,0,0));
        aiCards[2].setBackground(new Color(0,0,0,0));

        


        aiCards[0].setBounds(10,350,400,112);
        this.add(aiCards[0]);
        aiCards[1].setBounds(1400,350,400,112);
        this.add(aiCards[1]);
        aiCards[2].setBounds(750,140,400,112);
        this.add(aiCards[2]);

        aiHands.add(0, hand1);
        aiHands.add(1, hand2);
        aiHands.add(2, hand3);
        for (int x = 0; x<this.model.getAi().length;x++){
            for (int y = 0; y<this.model.getAi()[x].getHand().size();y++){
                imgComp2 img = new imgComp2(caImg.getAbsolutePath(),70,112);
                img.setBounds(0,0,70,112);
                aiHands.get(x).add(new RoundedJPane(60,5));//colour doesnt need to be accurate as player wont see ai cards until placed
                aiHands.get(x).get(y).add(img);
                aiHands.get(x).get(y).setBounds((int) Math.round(
                    (((300) / (this.model.getAi()[x].getHand().size() + 1) * y))
                             + 20),0,70,112);
                aiHands.get(x).get(y).setBackground(new Color(0,0,0,0));
                aiCards[x].add(aiHands.get(x).get(y));
            }
        }

        aiHands.set(0, hand1);
        aiHands.set(1, hand2);
        aiHands.set(2, hand3);
    }

    public boolean getDeckMod() {
        return (this.deckModifier.isSelected());
    }

    /**
     * setHand
     * adding new cards to show
     *
     * @author Thivagar
     */
    private void setHand() {
        this.cards.clear(); // reset cards as order of cards may change from sorting
        for (int x = 0; x < this.model.getCurrentPlayer().getHand().size(); x++) { // for each card player has
            this.cards.add(new RoundedJPane(60, this.model.getCurrentPlayer().getHand().get(x).getColour()));
        }
    }

    /**
     * displayDeck
     * displays deck of cards
     *
     * @author Thivagar
     */
    private void displayDeck() {
        try {
            ImgComponent img = new ImgComponent((new File(cardFile, "_HiddenCard.png")).getCanonicalPath());
            this.deck.setBounds(700, 500, 211, 336);
            img.setBounds(0, 0, 211, 336);
            this.deck.add(img);
            this.add(deck);
            this.deck.setVisible(true);
        } catch (Exception e) {

        }
        this.displayIcons();
                
    }

    /**
     * displayCurrentCard
     * displays card in play
     *
     * @author Thivagar
     */
    public void displayCurrentCard() {
        this.currentCard = new RoundedJPane(50, this.model.getCurrentCard().getColour());
        ImgComponent a = new ImgComponent(
                new File(cardFile, (this.model.getCurrentCard().getValue() + ".png").trim()).getAbsolutePath());
        a.setBounds(0, 0, 211, 336);
        this.currentCard.add(a);
        this.currentCard.setBounds(1000, 500, 211, 336);
        this.add(this.currentCard);
        this.refresh();
    }

    /**
     * displayCards
     * displays cards of user
     *
     * @author Thivagar
     */
    private void displayCards() {
        this.removeAll();

        this.setFocusable(true);
        this.requestFocus();
        this.setHand();
        this.setAiHands();
        this.removeAll();
        this.setLayout(null);

        ImgComponent img;

        for (int x = 0; x < this.model.getCurrentPlayer().getHand().size(); x++) { // for each card
            Card currentCard = this.model.getCurrentPlayer().getHand().get(x);
            this.cards.get(x).setBounds(
                    (int) Math.round(
                            (((this.getWidth() - 200) / (this.model.getCurrentPlayer().getHand().size() + 1) * x))
                                    / 1.25 + 250),
                    850, 211, 336);
            img = new ImgComponent(new File(cardFile, (currentCard.getValue() + ".png".trim())).getAbsolutePath());
            img.setBounds(0, 0, 211, 336);
            this.cards.get(x).add(img);
            this.add(cards.get(x));
        }
        this.setAiHands();
        this.registerControllers(); // new card also needs to be clickable
        this.refresh();
        this.displayDeck();
        System.out.println("TEST 4");
                
    }


    /**
     * displayIcons
     * displays icons of players
     *
     * @author Thivagar
     */
    public void displayIcons() {
        File ply1 = new File(cardFile, "userIcon.png");
        imgComp2 user = new imgComp2(ply1.getAbsolutePath(), 100, 100);
        File ai1 = new File(cardFile, "aiIcon.png");
        imgComp2 aiImage = new imgComp2(ai1.getAbsolutePath(), 100, 100);
        imgComp2 aiImage2 = new imgComp2(ai1.getAbsolutePath(), 100, 100);
        imgComp2 aiImage3 = new imgComp2(ai1.getAbsolutePath(), 100, 100);

        this.aiName[0] = new JTextArea("AI #1");
        this.aiName[1] = new JTextArea("AI #2");
        this.aiName[2] = new JTextArea("AI #3");

        this.aiIcon[0] = new JPanel();
        this.aiIcon[1] = new JPanel();
        this.aiIcon[2] = new JPanel();

        this.icon1.add(user);
        this.icon1.setBounds(10, 800, 100, 100);
        this.icon1.setOpaque(false);
        this.name1.setBounds(10, 910, 400, 400);
        this.name1.setOpaque(false);
        this.name1.setText(this.nameInput.getText());
        this.name1.setFont(new Font("Times New Roman", 1, 30));
        if (this.nameInput.getText().isEmpty()) {
            this.name1.setText("GUEST");
        }
        this.name1.setEditable(false);
        this.add(icon1);
        this.add(name1);

        // System.out.println(aiImage.getAbsol);
        this.aiIcon[0].add(aiImage);
        this.aiIcon[1].add(aiImage2);
        this.aiIcon[2].add(aiImage3);
        this.aiIcon[0].setOpaque(false);
        this.aiIcon[1].setOpaque(false);
        this.aiIcon[2].setOpaque(false);

        this.aiName[0].setBackground(new Color(0,0,0,0));
        this.aiName[1].setBackground(new Color(0,0,0,0));
        this.aiName[2].setBackground(new Color(0,0,0,0));
        this.aiName[0].setFont(new Font("Times New Roman", 1, 30));
        this.aiName[0].setEditable(false);
        this.aiName[1].setFont(new Font("Times New Roman", 1, 30));
        this.aiName[1].setEditable(false);
        this.aiName[2].setFont(new Font("Times New Roman", 1, 30));
        this.aiName[2].setEditable(false);

        this.aiIcon[0].setBounds(1600, 200, 100, 100);
        this.aiIcon[1].setBounds(50, 200, 100, 100);
        this.aiIcon[2].setBounds(850, 10, 100, 100);
        this.aiName[0].setBounds(1700, 250, 100, 100);
        this.aiName[1].setBounds(950, 50, 100, 150);
        this.aiName[2].setBounds(150, 250, 100, 100);

        this.add(aiIcon[0]);
        this.add(aiName[0]);
        this.add(aiIcon[1]);
        this.add(aiName[1]);
        this.add(aiIcon[2]);
        this.add(aiName[2]);
    }

    public void postGameMenu()
    {
        this.removeAll();
        pauseMenu.quitGameButton = new JButton("Return to Main Menu");
        restartGame = new JButton("Restart Game");
        quitGame = new JButton("Quit Game");

        this.setLayout(new BorderLayout());
        pauseMenu.quitToMainMenuButton.setPreferredSize(new Dimension(300, 95));
        this.restartGame.setPreferredSize(new Dimension(300, 100));
        this.restartGame.setFont(new Font("Arial", Font.BOLD, 20));
        this.quitGame.setPreferredSize(new Dimension(300, 100));
        this.quitGame.setFont(new Font("Arial", Font.BOLD, 20));

        this.setBackground(Color.PINK);
        this.postGame.add(pauseMenu.quitToMainMenuButton);
        this.postGame.add(this.restartGame);
        this.postGame.add(this.quitGame);
        this.add(this.postGame, BorderLayout.SOUTH);
        this.refresh();
    }

    /**
     * Displays Uno and Uno Block buttons
     *
     * someone do this because idk what to do here
     */
    public void displayUnoButtons() {
        this.add(this.unoButton);
        this.add(this.unoBlockButton);
        this.refresh();
    }

    /**
     * registerControllers
     * register available user actions
     *
     * @author Thivagar
     */
    private void registerControllers() {
        // Variable Declaration
        CardSelector setup = new CardSelector(this.model); // Setup
        deckListener addCard = new deckListener(this.model);
        PauseMenuListener pauseGame = new PauseMenuListener(this.model, this);
        System.out.println("REGISTERED");
        // set listeners
        for (KeyListener listener : this.getKeyListeners()) {
            this.removeKeyListener(listener);
        }
        for (ActionListener listener : this.pauseMenu.resumeButton.getActionListeners()) {
            this.pauseMenu.quitToMainMenuButton.removeActionListener(listener);
            this.pauseMenu.quitGameButton.removeActionListener(listener);
            this.pauseMenu.resumeButton.removeActionListener(listener);

        }
        if (this.model.getState() == model.GAME) { // if rounds have been chosen
            System.out.println("REGISTERED2.0");

            for (MouseListener listener : this.deck.getMouseListeners()) { // remove prior listeners
                this.deck.removeMouseListener(listener);
            } // SHOULD CHANGE LATER

            for (int x = 0; x < cards.size(); x++) { // for each card
                for (MouseListener listener : this.cards.get(x).getMouseListeners()) { // remove prior listeners
                    this.cards.get(x).removeMouseListener(listener);
                }
                this.cards.get(x).addMouseListener(setup);
            }

            this.deck.addMouseListener(addCard);
            this.addKeyListener(pauseGame);

            this.pauseMenu.quitGameButton.addActionListener(pauseGame);
            this.pauseMenu.quitToMainMenuButton.addActionListener(pauseGame);
            this.pauseMenu.resumeButton.addActionListener(pauseGame);

        }

    }

    /**
     * Assigns a controller to the buttons in main menu and game selection
     */
    private void registerStarterControllers() {
        MenuListener mSelect = new MenuListener(this.model);
        this.playGame.addActionListener(mSelect);
        this.startGame.addActionListener(mSelect);// tba
        this.quitGame.addActionListener(mSelect);
    }

    /**
     * Assigns a controller to the buttons in post game menu
     */
    private void registerPostGameControllers() {
        MenuListener mSelect = new MenuListener(this.model);
        PauseMenuListener pMenu = new PauseMenuListener(this.model, this);

        this.returnToMainMenu.addActionListener(pMenu);
        this.restartGame.addActionListener(mSelect);// tba
        this.quitGame.addActionListener(mSelect);
    }

    /**
     * getCards
     * returns all the card visuals
     *
     * @author Thivagar
     */
    public ArrayList<RoundedJPane> getCards() {
        return this.cards;
    }

    /**
     * raiseCard
     * raises the hovered card
     *
     * @author Thivagar
     */
    private void raiseCard(Object aCard) {
        for (int x = 0; x < this.cards.size(); x++) {
            if (this.cards.get(x).equals(aCard)) {
                this.cards.get(x).setBounds(this.cards.get(x).getX(), 750, 211, 336);
            }
        }
        this.refresh();
    }

    /**
     * dropCard
     * lowers the hovered card if mouse exists from it
     *
     * @author Thivagar
     */
    private void dropCard(Object aCard) {
        for (int x = 0; x < this.cards.size(); x++) {
            if (this.cards.get(x).equals(aCard)) {
                this.cards.get(x).setBounds(this.cards.get(x).getX(), 850, 211, 336);
            }
        }
        this.refresh();
    }

    /**
     * raises or lowers selected card depends on mouse position
     *
     * @author Thivagar
     */
    public void updateCard(boolean raise, Object card) {
        if (raise) {
            this.raiseCard(card);
        } else {
            this.dropCard(card);
        }
    }

    public void nextRound(){
        this.roundCount++;
        JTextField display = new JTextField("Round "+(this.roundCount+1)+" Starts Now!");
        //Timer timer = new Timer();
        if (roundCount == this.model.getNumberOfRound()){
            this.model.reset();
        }
        else{
        this.removeAll();
        this.model.startGame();
        display.setBounds(400,300,1000,200);
        display.setBackground(new Color(0,0,0,0));
        display.setEditable(false);
        display.setBorder(BorderFactory.createEmptyBorder());
            this.model.setState(this.model.GAME);
        display.setFont(new Font("Times New Roman",1,30));
        this.model.placeStarterCard();
        this.update();
        this.add(display);
        this.refresh();
        }
    }

    /**
     * Removes all components when returning to main menu
     */
    private void removeComp() {
        System.out.println("RESET OK");
        this.deck.removeAll();
        this.cards.clear();
        this.registerControllers();
    }

    /**
     * update
     * updates GUI based on changes
     *
     * @author Thivagar
     */
    public void update() {

        switch (this.model.getState()) {
            case 0:
                this.mainMenu();
                break;
            case 1:
                this.gameSetup();
                break;
            case 2:
                if (!this.playerTurn){
                this.addAccess();
                }
                this.displayCards();
                this.displayDeck();
                this.displayCurrentCard();
                this.displayIcons();
                this.displayUnoButtons();
                System.out.println("TESTTT3");
                break;
            case 3:
                this.setPauseState();
                break;
            case 4:
                model.checkIfRoundIsOver();
                this.postGameMenu();
                break;
            case 5:
                this.removeComp();
                break;
                case 6:
                this.displayCards();
                this.displayDeck();
                this.displayCurrentCard();
                this.displayIcons();
                this.displayUnoButtons();
                this.addAccess();
                this.model.nextUser();
                break;

            default:
                break;
        }

        this.refresh();
    }

    public void displayColourSelectors(){

        red.setBackground(Color.red);
        blue.setBackground(Color.blue);
        yellow.setBackground(Color.yellow);
        green.setBackground(Color.green);

        red.setBounds(300,300,100,50);
        blue.setBounds(500,300,100,50);
        yellow.setBounds(300,500,100,50);
        green.setBounds(500,500,100,50);

        this.add(red);
        this.add(green);
        this.add(yellow);
        this.add(blue);
        this.registerColourControllers();
        this.refresh();

    }

    public void registerColourControllers(){
        colourListener cSelect = new colourListener(this.model);
        this.red.addActionListener(cSelect);
        this.blue.addActionListener(cSelect);// tba
        this.yellow.addActionListener(cSelect);
        this.green.addActionListener(cSelect);
    }

    public void removeAccess(){
            for (JComponent comp : this.aiCards) {
                comp.setEnabled(false);
            }
                deck.setEnabled(false);
                playerTurn = true;
        
    }

    public void addAccess(){
        for (JComponent comp : this.aiCards) {
            comp.setEnabled(true);
        }
            deck.setEnabled(true);
            playerTurn = false;
        }   

    /**
     * Pauses the game and displays the pause menu
     */
    private void setPauseState() {

        this.pauseMenu.setBounds(0, 0, 1920, 1080);
        for (Component comp : this.getComponents()) {
            comp.setVisible(!comp.isVisible());
        }
        this.pauseMenu.setVisibility();
        this.add(pauseMenu);
        this.refresh();
    }

    /**
     * refresh
     * updates gui to show new changes
     *
     * @author Thivagar
     */
    public void refresh() {
        this.repaint();
        this.revalidate();
    }
}
