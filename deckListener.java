import java.awt.event.*;

/**
 * deckListener
 * MouseListener that enables user to draw a card
 * 
 * @author Thivagar Kesavan
 * @since 2024/06/12
 */
public class deckListener implements MouseListener {
    private UnoModel model;// model of UNO game

    /**
     * deckListener
     * deckListener Constructor
     * 
     * @author Thivagar
     * @param model - model of game
     */
    public deckListener(UnoModel model) {
        this.model = model;
    }

    /**
     * mouseExited
     * detects if mouse exits a component with this listener - STUD
     * 
     * @author Thivagar
     * @param e - event user preformed
     */
    public void mouseExited(MouseEvent e) {
    }

    /**
     * mouseReleased
     * detects if mouse released a component with this listener - STUD
     * 
     * @author Thivagar
     * @param e - event user preformed
     */
    public void mouseReleased(MouseEvent e) {
    }

    /**
     * mouseClicked
     * detects if mouse clicked a component with this listener
     * 
     * @author Thivagar
     * @param e - event user preformed
     */
    public void mouseClicked(MouseEvent e) {
        System.out.println("CLICK");
        this.model.drawCard();
    }

    /**
     * mouseEntered
     * detects if mouse entered a component with this listener - STUD
     * 
     * @author Thivagar
     * @param e - event user preformed
     */
    public void mouseEntered(MouseEvent e) {
    }

    /**
     * mousePressed
     * detects if mouse pressed a component with this listener - STUD
     * 
     * @author Thivagar
     * @param e - event user preformed
     */
    public void mousePressed(MouseEvent e) {

    }

}
