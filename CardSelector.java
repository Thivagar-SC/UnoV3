import java.awt.event.*;

/**
 * CardSelector
 * MouseListener that enables user to select their cards
 * 
 * @author Thivagar Kesavan
 * @since 2024/06/12
 */
public class CardSelector implements MouseListener {
    private UnoModel model; // model of UNO game

    /**
     * CardSelector
     * CardSelector Constructor
     * 
     * @author Thivagar
     * @param model - model of game
     */
    public CardSelector(UnoModel model) {
        this.model = model;
    }

    /**
     * mouseExited
     * detects if mouse exits a component with this listener
     * 
     * @author Thivagar
     * @param e - event user preformed
     */
    public void mouseExited(MouseEvent e) {
        this.model.dropCard(e.getSource());

    }

    /**
     * mouseReleased
     * detects if mouse released a component with this listener
     * 
     * @author Thivagar
     * @param e - event user preformed
     */
    public void mouseReleased(MouseEvent e) {
        System.out.println("Mouse Released");
    }

    /**
     * mouseClicked
     * detects if mouse clicked a component with this listener
     * 
     * @author Thivagar
     * @param e - event user preformed
     */
    public void mouseClicked(MouseEvent e) {
        System.out.println("Mouse Clicked");
        this.model.placeCard(0, 0);
    }

    /**
     * mouseEntered
     * detects if mouse entered a component with this listener
     * 
     * @author Thivagar
     * @param e - event user preformed
     */
    public void mouseEntered(MouseEvent e) {
        this.model.raiseCard(e.getSource());
    }

    /**
     * mousePressed
     * detects if mouse pressed a component with this listener
     * 
     * @author Thivagar
     * @param e - event user preformed
     */
    public void mousePressed(MouseEvent e) {

    }
}
