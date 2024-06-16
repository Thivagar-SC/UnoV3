import java.awt.*;
import javax.swing.*;

/**
 * RoundedJPane
 * Rounded layered pane to hold card img
 *
 * @author Thivagar Kesavan
 * @since 2024/06/12
 */
public class RoundedJPane extends JLayeredPane
{
    private int radius; // radius of card rounded corners

    // Value of each colour
    private final int RED = 0;
    private final int BLUE = 1;
    private final int YELLOW = 2;
    private final int GREEN = 3;
    private final int BLACK = 4;

    private int currentColour; // cards current colour
    private int height = 336; // height of card
    private int width = 211; // width of card

    /**
     * RoundedJPane
     * RoundedJPane Constructor
     *
     * @param radius - radius of card edges
     * @param colour - colour of card background
     * @author Thivagar
     */
    public RoundedJPane(int radius, int colour)
    {
        this.radius = radius;
        this.currentColour = colour;
        this.setPreferredSize(new Dimension(width, height));

    }

    /**
     * paintComponent
     * Overrides paintComponent method of JComponent to display image
     *
     * @param g - graphics to draw images
     * @author Thivagar
     */
    public void paintComponent(Graphics g)
    {
        setColor(this.currentColour, g);
        g.fillRoundRect(0, 0, 195, 295, this.radius, this.radius);
        super.paintComponent(g);

    }

    /**
     * setColor
     * sets background colour
     *
     * @param g     - graphics to draw images
     * @param color - colour to be set
     * @author Thivagar
     */
    public void setColor(int color, Graphics g)
    {
        switch (color)
        {
            case RED:
                g.setColor(Color.RED);
                break;

            case BLUE:
                g.setColor(Color.BLUE);
                break;

            case YELLOW:
                g.setColor(new Color(240, 207, 41)); //less eye blinding yellow
                break;

            case GREEN:
                g.setColor(Color.GREEN);
                break;

            case BLACK:
                g.setColor(Color.BLACK);
                break;
        }
    }
}
