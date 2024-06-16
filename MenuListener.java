import java.awt.event.*;

/**
 * MenuListener
 * Detects what user wants to do in main menu
 *
 * @author Thivagar Kesavan
 * @since 2024/06/12
 */
public class MenuListener implements ActionListener {
    private UnoModel model; // model of game

    /**
     * MenuListener
     * MenuListener Constructor
     *
     * @param model - model of game
     * @author Thivagar
     */
    public MenuListener(UnoModel model) {
        this.model = model;
    }

    /**
     * actionPerformed
     * detects if user interacts with a component with this listener
     *
     * @param e - event preformed by user
     * @author Thivagar
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Start")) { // if user hits start button
            model.startSelection();
        } else if (e.getActionCommand().equals("Play!")) { // if user hits play button
            model.startGame();
        } else if (e.getActionCommand().equals("Quit Game")) {// if user hits quit game button {
            model.quitGame();
        }
    }

}
