import java.awt.event.*;

/**
 * UnoBlockButton
 * Button to counter UNO from being called
 *
 * @author Tanner
 * @since 2024/06/12
 */
public class UnoBlockButton extends UnoButton implements MouseListener
{
    /** Default constructor */
    public UnoBlockButton()
    {
        super();
    }

    /**
     * update
     * Button can only be active when user has 1 card in hand and is NOT SAFE
     *
     */
    @Override
    public void update()
    {
        isActive = false;
        if (player.getUnoState() == model.NOT_SAFE && player.getHand().size() == 1)
        {
            isActive = true;
        }
    }

    /**
     * Sends information to the model from UNO block button
     *
     * @param e - mouse event
     */
    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("Uno BLOCK CALLED!");
        if (player.getUnoState() == model.NOT_SAFE && player.getHand().size() == 1)
        {
            model.addTwoFromUnoBlock(player);
        }
    }
}
