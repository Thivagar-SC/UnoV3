import java.awt.event.*;
/**
 * ButtonListener
 * tba
 * 
 * @author tba
 * @since 2024/06/12
 */
public class ButtonListener implements MouseListener
{
    private UnoModel model; // model of UNO game

    public ButtonListener(UnoModel model)
    {
        this.model = model;
    }

    public void update()
    {

    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.out.println("Uno CALLED!");
        model.setUNOState(1);
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
