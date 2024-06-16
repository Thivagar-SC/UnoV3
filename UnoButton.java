import javax.swing.*;

public class UnoButton extends JButton
{
    private UnoModel model; // model of UNO game
    private Player player; // player of UNO game
    private boolean isActive;

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
}
