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
public class ImgComponent extends JComponent {
    ImageIcon image; // imageIcon requested to show
    File holder; // File of image

    /**
     * ImgComponent
     * ImgComponent Constructor
     * 
     * @author Thivagar
     * @param imageFile - image file being requested
     */
    public ImgComponent(String imageFile) {
        super();
        this.image = new ImageIcon(imageFile);// g
        this.setPreferredSize(new Dimension(200, 300));
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
        g.drawImage(this.image.getImage(), 0, 0, 200, 300, null);
    }
}
