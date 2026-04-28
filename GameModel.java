import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameModel {
    // Board constants
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    
    // Player state
    private int playerX = WIDTH / 2;
    private final int playerY = HEIGHT - 50;
    private final int playerWidth = 40;
    private final int playerHeight = 20;
    private int lives = 3;
    private int score = 0;
    private int shields = 3; // 3 segments per life
    
    // Alien state
    private List<Alien> aliens;
    private int alienDirection = 1; // 1 for right, -1 for left
    private final int alienWidth = 30;
    private final int alienHeight = 20;
    private final int alienSpacing = 15;
    
    // Bullet state
    private Bullet playerBullet = null;
    private List<Bullet> alienBullets = new ArrayList<>();
    
    private Random random = new Random();

    public GameModel() {
        resetGame();
    }

    public void resetGame() {
        score = 0;
        lives = 3;
        playerBullet = null;
        alienBullets.clear();
        shields = 3;
        initAliens();
    }

    private void initAliens() {
        aliens = new ArrayList<>();
        for (int row = 0; row < 5; row++) {
            for (int col = 0; col < 11; col++) {
                int x = 100 + col * (alienWidth + alienSpacing);
                int y = 50 + row * (alienHeight + alienSpacing);
                aliens.add(new Alien(x, y));
            }
        }
    }

    // Player Actions
    public void movePlayer(int deltaX) {
        playerX += deltaX;
        if (playerX < 0) playerX = 0;
        if (playerX > WIDTH - playerWidth) playerX = WIDTH - playerWidth;
    }

    public void firePlayerBullet() {
        if (playerBullet == null) {
            // Bullet starts from top center of player
            playerBullet = new Bullet(playerX + playerWidth / 2, playerY, -8);
        }
    }

    // Main Update Logic
    public void update() {
        // 1. Update player bullet
        if (playerBullet != null) {
            playerBullet.y += playerBullet.speed;
            if (playerBullet.y < 0) playerBullet = null;
        }

        // 2. Update alien bullets
        for (int i = 0; i < alienBullets.size(); i++) {
            Bullet b = alienBullets.get(i);
            b.y += b.speed;
            if (b.y > HEIGHT) {
                alienBullets.remove(i--);
            }
        }

        // 3. Move aliens
        boolean edgeHit = false;
        for (Alien a : aliens) {
            a.x += alienDirection * 2;
            if (a.x <= 0 || a.x >= WIDTH - alienWidth) {
                edgeHit = true;
            }
        }

        if (edgeHit) {
            alienDirection *= -1;
            for (Alien a : aliens) {
                a.y += 15;
            }
        }

        // 4. Alien random firing
        if (random.nextInt(100) < 2 && !aliens.isEmpty()) {
            Alien shooter = aliens.get(random.nextInt(aliens.size()));
            alienBullets.add(new Bullet(shooter.x + alienWidth / 2, shooter.y + alienHeight, 5));
        }

        // 5. Collision Detection
        checkCollisions();
    }

    private void checkCollisions() {
        // Player bullet vs Aliens
        if (playerBullet != null) {
            for (int i = 0; i < aliens.size(); i++) {
                Alien a = aliens.get(i);
                if (rectCollide(playerBullet.x - 2, playerBullet.y, 4, 10, 
                               a.x, a.y, alienWidth, alienHeight)) {
                    aliens.remove(i);
                    playerBullet = null;
                    score += 10;
                    break; // Only one alien per bullet
                }
            }
        }

        // Alien bullets vs Player/Shields
        for (int i = 0; i < alienBullets.size(); i++) {
            Bullet b = alienBullets.get(i);
            boolean hit = false;

            // Check Shields first
            if (shields > 0) {
                for (int s = 0; s < shields; s++) {
                    // Divide player width into 3 segments
                    int sw = playerWidth / 3;
                    int sx = playerX + s * sw;
                    int sy = playerY - 15; // In front of player
                    if (rectCollide(b.x - 2, b.y, 4, 10, sx, sy, sw - 2, 10)) {
                        alienBullets.remove(i--);
                        shields--;
                        hit = true;
                        if (shields == 0) {
                            lives--;
                            if (lives > 0) shields = 3; // Reset for next life
                        }
                        break;
                    }
                }
            }

            if (hit) continue;

            // Check direct hit (if shields are somehow bypassed or already gone)
            if (rectCollide(b.x - 2, b.y, 4, 10, 
                           playerX, playerY, playerWidth, playerHeight)) {
                alienBullets.remove(i--);
                lives--;
                if (lives > 0) shields = 3;
            }
        }
    }

    private boolean rectCollide(int x1, int y1, int w1, int h1, int x2, int y2, int w2, int h2) {
        return x1 < x2 + w2 && x1 + w1 > x2 && y1 < y2 + h2 && y1 + h1 > y2;
    }

    // Getters for View
    public int getPlayerX() { return playerX; }
    public int getPlayerY() { return playerY; }
    public int getPlayerWidth() { return playerWidth; }
    public int getPlayerHeight() { return playerHeight; }
    public List<Alien> getAliens() { return aliens; }
    public int getAlienWidth() { return alienWidth; }
    public int getAlienHeight() { return alienHeight; }
    public Bullet getPlayerBullet() { return playerBullet; }
    public List<Bullet> getAlienBullets() { return alienBullets; }
    public int getScore() { return score; }
    public int getLives() { return lives; }
    public int getShields() { return shields; }

    // Helper Classes
    public static class Alien {
        public int x, y;
        public Alien(int x, int y) { this.x = x; this.y = y; }
    }

    public static class Bullet {
        public int x, y, speed;
        public Bullet(int x, int y, int speed) { this.x = x; this.y = y; this.speed = speed; }
    }
}
