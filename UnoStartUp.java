import java.awt.Dimension;
import javax.swing.*;

/**
 * UnoStartUp
 * Uno program runner
 * 
 * @author tba 
 * @since 2024/06/12
 */
public class UnoStartUp
{
    /**
 * mains
 * runs uno game
 * 
 * @author tba 
 */
    public static void main(String[] args)
    {
        UnoModel b = new UnoModel();
        UnoView a = new UnoView(b);
        JFrame test = new JFrame();
        test.setSize(new Dimension(1000, 1000));
        test.setResizable(false);
        test.setContentPane(a);
        test.setVisible(true);
        
        // Code from https://docs.oracle.com/javase/tutorial/uiswing/lookandfeel/nimbus.html
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
    }
}