import javax.swing.*;
import java.awt.event.*;

public class UnoButton extends JButton implements MouseListener
{
    public UnoModel model; // model of UNO game
    public Player player; // player of UNO game
    public boolean isActive;

    /**
     * Default constructor
     */
    public UnoButton()
    {
        isActive = false;
    }

    /**
     * Enables the UNO Button only when the player is not safe or safe and has 2 cards in hand
     */
    public void update()
    {
        isActive = player.getUnoState() == model.NOT_SAFE
                || player.getUnoState() == model.SAFE && player.getHand().size() == 2;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("Uno CALLED!");
        model.setUNOState(model.CALLED);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}
