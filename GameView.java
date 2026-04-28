import javax.swing.JPanel;
import java.awt.Graphics;

public class GameView extends JPanel {
    // GameView will handle the rendering of the game.
    // It will draw the player, invaders, and bullets based on the state from GameModel.
    private GameModel model;

    public GameView(GameModel model) {
        this.model = model;
        // Initialize view properties (e.g., preferred size)
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Rendering logic will go here
    }
}
