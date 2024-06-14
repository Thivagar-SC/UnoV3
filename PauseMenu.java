import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * pauseMenu
 * displays a pause menu component
 * 
 * @author tba
 * @since 2024/06/12
 */
public class PauseMenu extends JPanel {

    private String pausedMessage = "PAUSED"; // Message displayed to user
    private boolean isPaused; // if game is paused

    public JButton resumeButton; // Interactable buttons
    public JButton quitToMainMenuButton;
    public JButton quitGameButton;
    public JPanel buttonsBackground;

    /**
     * pauseMenu
     * pauseMenu Constructor
     * 
     * @author tba
     */
    public PauseMenu() {
        super();
        isPaused = false;
        this.setVisible(this.isPaused);
        this.setLayout(null);

        // setting text
        resumeButton = new JButton("Resume");
        resumeButton.setFont(new Font("Arial", Font.BOLD, 20));
        resumeButton.setMaximumSize(new Dimension(250,50));
        quitToMainMenuButton = new JButton("Return to Main Menu");
        quitToMainMenuButton.setFont(new Font("Arial", Font.BOLD, 20));
        quitToMainMenuButton.setMaximumSize(new Dimension(250,50));
        quitGameButton = new JButton("Quit Game");
        quitGameButton.setFont(new Font("Arial", Font.BOLD, 20));
        quitGameButton.setMaximumSize(new Dimension(250,50));

        resumeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitToMainMenuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        quitGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Background for pause menu
        buttonsBackground = new JPanel();
        this.add(buttonsBackground);
        buttonsBackground.setPreferredSize(new Dimension(300,180));
        buttonsBackground.setLayout(new BoxLayout(buttonsBackground, BoxLayout.Y_AXIS));
        this.buttonsBackground.setBounds(350, 350, 300, 180);

        // OUTPUT
        buttonsBackground.add(Box.createVerticalStrut(40));
        buttonsBackground.add(resumeButton);
        buttonsBackground.add(Box.createVerticalStrut(10));
        buttonsBackground.add(quitToMainMenuButton);
        buttonsBackground.add(Box.createVerticalStrut(10));
        buttonsBackground.add(quitGameButton);
        buttonsBackground.add(Box.createVerticalStrut(20));

    }

    public void setVisibility(){
        this.isPaused = !this.isPaused;
        this.setVisible(this.isPaused);
    }


    public void update() {
    }

    public boolean isPaused(){
        return this.isPaused;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        g.setColor(new Color(144, 143, 143, 204));
        g.fillRect(0, 0, 1000, 1000);
    }
}
