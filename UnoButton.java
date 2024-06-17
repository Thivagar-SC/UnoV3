import javax.swing.*;
import java.awt.event.*;

/**
 * UnoButton
 * Button to call UNO when player has one hand in hand
 *
 * @author Tanner
 * since 2024/06/13
 */
public class UnoButton extends JButton implements MouseListener {
    public UnoModel model; // model of UNO game
    public Player player; // player of UNO game
    public boolean isActive;

    /**
     * Default constructor
     * @author Tanner
     */
    public UnoButton() {
        isActive = false;
    }

    /**
     * Enables the UNO Button only when the player is not safe or safe and has 2
     * cards in hand
     * 
     * @author Tanner
     */
    public void update() {
        isActive = player.getUnoState() == model.NOT_SAFE
                || player.getUnoState() == model.SAFE && player.getHand().size() == 2;
    }

    /**
     * Handles the input of the UNO Button
     * @author Tanner
     * @param e
     * 
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("Uno CALLED!");
        model.setUNOState(model.CALLED);
    }

    /**
 * Not set
 * @author Tanner
 * @param e Not set
 */
    @Override
    public void mousePressed(MouseEvent e) {

    }
/**
 * Not set
 * @author Tanner
 * @param e Not set
 */
    @Override
    public void mouseReleased(MouseEvent e) {

    }
/**
 * Not set
 * @author Tanner
 * @param e Not set
 */
    @Override
    public void mouseEntered(MouseEvent e) {

    }
/**
 * @author Tanner
 */
    @Override
    public void mouseExited(MouseEvent e) {

    }
}
