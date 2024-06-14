import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseListener; //recode later if time so this is unneeded
import java.util.*;
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
  private JPanel gameSelect = new JPanel();
  private JButton startGame = new JButton();
  private JTextArea numberOfRounds = new JTextArea();
  private JTextArea playerName = new JTextArea();
  private JTextField roundInput = new JTextField();
  private JTextField nameInput = new JTextField();
  private JButton playGame = new JButton();
  private RoundedJPane deck= new RoundedJPane(50, 4);;
  private RoundedJPane currentCard;
  private PauseMenu pauseMenu;
  private File cardFile = new File("Cards");

  /**
   * UnoView
   * UnoView Constructor
   * 
   * @author Thivagar
   * @param uModel - model of game
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
   * 
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

    this.setBackground(Color.LIGHT_GRAY);
    this.menu.add(this.startGame);
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
    this.refresh();

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
    try{
    ImgComponent img = new ImgComponent((new File(cardFile,"_HiddenCard.png")).getCanonicalPath());
    this.deck.setBounds(50, 100, 211, 336);
    img.setBounds(0, 0, 211, 336);
    this.deck.add(img);
    this.add(deck);
    this.deck.setVisible(true);
    }
    catch(Exception e){
      
    }
  }

  /**
   * displayCurrentCard
   * displays card in play
   * 
   * @author Thivagar
   */
  private void displayCurrentCard() {
    this.currentCard = new RoundedJPane(50, this.model.getCurrentCard().getColour());
    ImgComponent a = new ImgComponent(new File(cardFile,(this.model.getCurrentCard().getValue() + ".png").trim()).getAbsolutePath());
    a.setBounds(0, 0, 211, 336);
    this.currentCard.add(a);
    this.currentCard.setBounds(500, 100, 211, 336);
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
    this.removeAll();
    this.setLayout(null);

    ImgComponent img;


    for (int x = 0; x < this.model.getCurrentPlayer().getHand().size(); x++) { // for each card
      Card currentCard = this.model.getCurrentPlayer().getHand().get(x);
      this.cards.get(x).setBounds(
          ((this.getWidth() - 200) / (this.model.getCurrentPlayer().getHand().size() + 1) * x) + 20, 500, 211, 336);
      img = new ImgComponent(new File(cardFile,(currentCard.getValue() + ".png".trim())).getAbsolutePath());
      img.setBounds(0, 0, 211, 336);
      this.cards.get(x).add(img);
      this.add(cards.get(x));
    }

    this.registerControllers(); // new card also needs to be clickable
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
    escListener pauseGame = new escListener(this.model, this);

    // set listeners

    System.out.println(this.model.getNumberOfRound());
    for (KeyListener listener : this.getKeyListeners()) {
      this.removeKeyListener(listener);
    }
    for (ActionListener listener : this.pauseMenu.resumeButton.getActionListeners()) {
      this.pauseMenu.quitToMainMenuButton.removeActionListener(listener);
      this.pauseMenu.quitGameButton.removeActionListener(listener);
      this.pauseMenu.resumeButton.removeActionListener(listener);
    
    }
    if (this.model.getState()==2) { // if rounds have been chosen
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

  public void registerStarterControllers(){
    MenuListener mSelect = new MenuListener(this.model);
    this.playGame.addActionListener(mSelect);
    this.startGame.addActionListener(mSelect);// tba
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
   * raises selected card
   * 
   * @author Thivagar
   */
  private void raiseCard(Object aCard) {
    for (int x = 0; x < this.cards.size(); x++) {
      if (this.cards.get(x).equals(aCard)) {
        this.cards.get(x).setBounds(this.cards.get(x).getX(), 420, 211, 336);
      }
    }
    this.refresh();
  }


  /**
   * dropCard
   * lowers selected card
   * 
   * @author Thivagar
   */
  private void dropCard(Object aCard) {
    for (int x = 0; x < this.cards.size(); x++) {
      if (this.cards.get(x).equals(aCard)) {
        this.cards.get(x).setBounds(this.cards.get(x).getX(), 500, 211, 336);
      }
    }
    this.refresh();
  }


  public void updateCard(boolean raise, Object card){
    if(raise){
      this.raiseCard(card);
    }
    else{
      this.dropCard(card);
    }
  }

  private void removeComp(){
    System.out.println("RESET OK");
    this.deck.removeAll();
    this.cards.clear();
    this.registerControllers();
  }
  /**
   * update
   * updates gui based on changes
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
      this.displayCards();
      this.displayDeck();
      this.displayCurrentCard();
        break;
      case 3:
      this.setPauseState();
        break;
        case 4:
      //this.setPauseState();
        break;
        case 5:
      this.removeComp();
        break;

      default:
        break;
    }

    this.refresh();
  }

  private void setPauseState() {
    this.pauseMenu.setBounds(0, 0, 1000, 1000);
    // this.pauseMenu.setBackground(Color.BLACK);
    this.pauseMenu.setVisibility();

    this.currentCard.setVisible(!this.currentCard.isVisible());
    this.deck.setVisible(!this.deck.isVisible());
    for (int x = 0; x < this.cards.size(); x++) {
      this.cards.get(x).setVisible(!this.cards.get(x).isVisible());
    } 
    this.add(pauseMenu);
    this.refresh();
  }

  /**
   * refresh
   * updates gui to show new changes
   * 
   * @author Thivagar
   */
  private void refresh() {
    this.repaint();
    this.revalidate();
  }
}
