import javax.swing.*;

public class UnoButton extends JButton
{
    private UnoModel model; // model of UNO game
    private Player player; // player of UNO game
    private boolean isActive;
    public UnoButton()
    {

        isActive = false;
    }

    public void update()
    {
        isActive = player.getUnoState() == model.NOT_SAFE
                || player.getUnoState() == model.SAFE && player.getHand().size() == 2;
    }
}
