import java.awt.event.*;

/**
 * escListener
 * Detects whether user hits escape key to open pause menu
 *
 * @author Tanner
 * @since 2024/06/12
 */
public class PauseMenuListener implements KeyListener, ActionListener {
    private UnoView view;
    private UnoModel model;

    public PauseMenuListener (UnoModel model, UnoView view) {
        this.model = model;
        this.view = view;
    }

    /**
     * Handles the input of the pause menu
     *
     * @author Tanner
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        System.out.println("RUNNING");
        if (e.getActionCommand().equals("Resume")) {
            model.pauseGame();
        } else if (e.getActionCommand().equals("Return to Main Menu"))// something
        {
            model.reset();
        } else if (e.getActionCommand().equals("Quit Game")) // something else
        {
            model.quitGame();
        }
    }

    /**
     * keyPressed
     * detects user key pressed
     * 
     * @author Tanner
     * @param e - Not set
     */
    @Override
    public void keyPressed(KeyEvent e) {
        model.pauseByESC(e.getKeyCode());
    }

    /**
     * keyTyped
     * Not set
     * 
     * @author Tanner
     * @param e - Not set
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * keyReleased
     * Not set
     * 
     * @author Tanner
     * @param e - Not set
     */
    @Override
    public void keyReleased(KeyEvent e) {
    }
}
