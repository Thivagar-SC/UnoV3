import java.awt.event.*;

/**
 * MenuListener
 * Detects what user wants to do in main menu
 *
 * @author Thivagar Kesavan
 * @since 2024/06/12
 */
public class colourListener implements ActionListener {
    private UnoModel model; // model of game

    /**
     * MenuListener
     * MenuListener Constructor
     *
     * @param model - model of game
     * @author Thivagar
     */
    public colourListener(UnoModel model) {
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
        if (e.getActionCommand().equals("Red")) { // if user hits start button
            System.out.println("red");
            this.model.setColour(0);
        } else if (e.getActionCommand().equals("Blue")) { // if user hits play button
            System.out.println("blue");
            this.model.setColour(1);

        } else if (e.getActionCommand().equals("Green")) {// if user hits quit game button {
            System.out.println("green");
            this.model.setColour(3);

        }
        else if (e.getActionCommand().equals("Yellow")) {// if user hits quit game button {
        System.out.println("Yellow");
        this.model.setColour(2);

        }
    }

}
