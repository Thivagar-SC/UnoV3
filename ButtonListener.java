import java.awt.event.*;
/**
 * ButtonListener for the UNO and Block game.
 * This class listens for mouse events and updates the UNO game model accordingly.
 *
 * @author Tanner
 * @since 2024/06/12
 */
public class ButtonListener implements MouseListener
{
    private UnoModel model; // The UNO game model that this listener updates.

    /**
     * Constructs a new ButtonListener with the given UNO game model.
     *
     * @param model The UNO game model to update.
     */
    public ButtonListener(UnoModel model)
    {
        this.model = model;
    }

    /**
     * Updates the UNO game model.
     * This method is currently empty and does nothing.
     */
    public void update()
    {

    }

    /**
     * Called when the mouse button has been clicked (pressed and released) on a component.
     * Sets the UNO game state to CALLED.
     *
     * @param e The event object.
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("Uno CALLED!");
        model.setUNOState(model.CALLED);
    }

    /**
     * Not set
     *
     * @param e Not set
     */
    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    /**
     * Not set
     *
     * @param e Not set
     */
    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    /**
     * Not set
     *
     * @param e Not set
     */
    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    /**
     * Not set
     *
     * @param e Not set
     */
    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}
