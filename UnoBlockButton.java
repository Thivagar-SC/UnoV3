import java.awt.event.*;
public class UnoBlockButton extends UnoButton implements MouseListener
{
    public UnoBlockButton()
    {
        super();
    }

    @Override
    public void update()
    {
        isActive = false;
        if (player.getUnoState() == model.NOT_SAFE && player.getHand().size() ==1)
        {
            isActive = true;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("Uno BLOCK CALLED!");
        if (player.getUnoState() == model.NOT_SAFE && player.getHand().size() ==1)
        {
            model.addTwoFromUnoBlock(player);
        }
    }
}
