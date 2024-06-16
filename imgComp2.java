import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * ImgComponent
 * obtains images requested for uno game
 * 
 * @author Thivagar Kesavan
 * @since 2024/06/12
 */
public class imgComp2 extends JComponent {
    private ImageIcon image; // imageIcon requested to show
    private File holder; // File of image
    private int width;
    private int height;

    /**
     * ImgComponent
     * ImgComponent Constructor
     * 
     * @author Thivagar
     * @param imageFile - image file being requested
     */
    public imgComp2(String imageFile, int width, int hight) {
        super();
        this.image = new ImageIcon(imageFile);// g
        this.width = width;
        this.height = hight;
        this.setPreferredSize(new Dimension(width, hight));
    }

    /**
     * paintComponent
     * Overrides paintComponent method of JComponent to display image
     * 
     * @author Thivagar
     * @param g - graphics to draw images
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image.getImage(), 0, 0, this.width, this.height, null);
    }
}
