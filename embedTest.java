import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.*;

public class embedTest {
    public static void main(String[] args) {
        JFrame test = new JFrame();
        test.setSize(new Dimension(100,100));
        test.setVisible(true);
        File te = new File("temp");
        while (true) {
            
        
        for (int x = 1; x<=10;x++) {
            String num = "";
            String number = String.valueOf(x);
            for (int y =0; y<3-number.length();y++){
                num+="0";
            }
            File test2 = new File(te.getName(),"ezgif-frame-"+num+x+".png");
        ImgComponent testing = new ImgComponent(test2.getAbsolutePath());
        testing.setSize(new Dimension(1000,1000));
        test.setContentPane(testing);
        //test.repaint();
        test.revalidate();
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
        
    }
}
