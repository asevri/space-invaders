import javax.swing.JFrame;

public class GameController {
    // GameController wires the Model and View together.
    // It handles user input (keyboard) and updates the Model, then triggers View updates.
    
    private GameModel model;
    private GameView view;

    public GameController() {
        model = new GameModel();
        view = new GameView(model);

        setupWindow();
    }

    private void setupWindow() {
        JFrame frame = new JFrame("Space Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view);
        frame.pack();
        frame.setSize(800, 600); // Default size for now
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new GameController();
    }
}
