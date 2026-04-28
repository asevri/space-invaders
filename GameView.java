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

        // Draw Shields
        g.setColor(Color.CYAN); 
        int numShields = model.getShields();
        for (int s = 0; s < numShields; s++) {
            int sw = model.getPlayerWidth() / 3;
            int sx = model.getPlayerX() + s * sw;
            int sy = model.getPlayerY() - 15;
            g.fillRect(sx, sy, sw - 2, 10);
        }

        // Draw Aliens
        for (GameModel.Alien alien : model.getAliens()) {
            // Draw as a 3x3 grid of cells with 1px gaps
            // Using White for the "black squares" described (to be visible on black background)
            g.setColor(Color.WHITE); 
            int cellW = (model.getAlienWidth() - 2) / 3;
            int cellH = (model.getAlienHeight() - 2) / 3;
            int gap = 1;

            for (int r = 0; r < 3; r++) {
                for (int c = 0; c < 3; c++) {
                    // Middle row has two squares separated by empty space (skip middle)
                    if (r == 1 && c == 1) continue;
                    
                    int x = alien.x + c * (cellW + gap);
                    int y = alien.y + r * (cellH + gap);
                    g.fillRect(x, y, cellW, cellH);
                }
            }
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
