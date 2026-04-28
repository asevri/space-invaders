import javax.swing.JFrame;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GameController {
    private GameModel model;
    private GameView view;
    private Timer gameTimer;
    
    private boolean leftPressed = false;
    private boolean rightPressed = false;

    public GameController() {
        model = new GameModel();
        view = new GameView(model);

        setupInput();
        setupGameLoop();
        setupWindow();
    }

    private void setupInput() {
        view.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        leftPressed = true;
                        break;
                    case KeyEvent.VK_RIGHT:
                        rightPressed = true;
                        break;
                    case KeyEvent.VK_SPACE:
                        model.firePlayerBullet();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_LEFT:
                        leftPressed = false;
                        break;
                    case KeyEvent.VK_RIGHT:
                        rightPressed = false;
                        break;
                }
            }
        });
    }

    private void setupGameLoop() {
        // ~60 FPS
        gameTimer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGame();
            }
        });
        gameTimer.start();
    }

    private void updateGame() {
        // Handle smooth movement
        if (leftPressed) model.movePlayer(-5);
        if (rightPressed) model.movePlayer(5);

        model.update();
        view.repaint();

        // Check for game over
        if (model.getLives() <= 0 || model.getAliens().isEmpty()) {
            gameTimer.stop();
        }
    }

    private void setupWindow() {
        JFrame frame = new JFrame("Space Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(view);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // Ensure the view has focus for the KeyListener
        view.requestFocusInWindow();
    }

    public static void main(String[] args) {
        // Run Swing GUI on the Event Dispatch Thread
        javax.swing.SwingUtilities.invokeLater(() -> new GameController());
    }
}
