import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class GameView extends JPanel {
    private GameModel model;

    public GameView(GameModel model) {
        this.model = model;
        setPreferredSize(new Dimension(GameModel.WIDTH, GameModel.HEIGHT));
        setBackground(Color.BLACK);
        setFocusable(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw Player
        g.setColor(Color.GREEN);
        g.fillRect(model.getPlayerX(), model.getPlayerY(), model.getPlayerWidth(), model.getPlayerHeight());

        // Draw Aliens
        g.setColor(new Color(150, 0, 255)); // Vibrant Purple
        for (GameModel.Alien alien : model.getAliens()) {
            g.fillRect(alien.x, alien.y, model.getAlienWidth(), model.getAlienHeight());
            // Small highlight for "WOW" effect
            g.setColor(Color.WHITE);
            g.drawRect(alien.x, alien.y, model.getAlienWidth(), model.getAlienHeight());
            g.setColor(new Color(150, 0, 255));
        }

        // Draw Player Bullet
        GameModel.Bullet pb = model.getPlayerBullet();
        if (pb != null) {
            g.setColor(Color.YELLOW);
            g.fillRect(pb.x - 2, pb.y, 4, 10);
        }

        // Draw Alien Bullets
        g.setColor(Color.RED);
        for (GameModel.Bullet ab : model.getAlienBullets()) {
            g.fillRect(ab.x - 2, ab.y, 4, 10);
        }

        // Draw HUD (Score and Lives)
        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.BOLD, 20));
        g.drawString("SCORE: " + model.getScore(), 20, 30);
        
        String livesStr = "LIVES: " + model.getLives();
        FontMetrics fmHUD = g.getFontMetrics();
        g.drawString(livesStr, GameModel.WIDTH - fmHUD.stringWidth(livesStr) - 20, 30);

        // Game Over Check
        if (model.getLives() <= 0 || model.getAliens().isEmpty()) {
            drawGameOver(g);
        }
    }

    private void drawGameOver(Graphics g) {
        // Semi-transparent overlay
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, GameModel.WIDTH, GameModel.HEIGHT);

        String msg = model.getLives() <= 0 ? "GAME OVER" : "YOU WIN!";
        g.setColor(model.getLives() <= 0 ? Color.RED : Color.GREEN);
        g.setFont(new Font("Monospaced", Font.BOLD, 60));
        
        FontMetrics fm = g.getFontMetrics();
        int x = (GameModel.WIDTH - fm.stringWidth(msg)) / 2;
        int y = GameModel.HEIGHT / 2;
        g.drawString(msg, x, y);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.PLAIN, 25));
        String scoreMsg = "FINAL SCORE: " + model.getScore();
        fm = g.getFontMetrics();
        x = (GameModel.WIDTH - fm.stringWidth(scoreMsg)) / 2;
        g.drawString(scoreMsg, x, y + 50);
    }
}
