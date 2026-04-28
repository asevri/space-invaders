public class ModelTester {
    public static void main(String[] args) {
        System.out.println("Starting GameModel Tests...\n");
        
        testPlayerBoundaries();
        testDoubleFire();
        testBulletRemoval();
        testScoring();
        testLivesDepletion();
        
        System.out.println("\nTests Completed.");
    }

    private static void testPlayerBoundaries() {
        GameModel model = new GameModel();
        
        // Test Left Boundary
        for (int i = 0; i < 200; i++) {
            model.movePlayer(-10);
        }
        boolean leftPass = model.getPlayerX() == 0;
        
        // Test Right Boundary
        for (int i = 0; i < 200; i++) {
            model.movePlayer(10);
        }
        boolean rightPass = model.getPlayerX() == (GameModel.WIDTH - model.getPlayerWidth());
        
        printResult("Player Boundaries", leftPass && rightPass);
    }

    private static void testDoubleFire() {
        GameModel model = new GameModel();
        model.firePlayerBullet();
        Object firstBullet = model.getPlayerBullet();
        
        // Try to fire again immediately
        model.firePlayerBullet();
        Object secondBullet = model.getPlayerBullet();
        
        printResult("Double Fire Prevention", firstBullet == secondBullet && firstBullet != null);
    }

    private static void testBulletRemoval() {
        GameModel model = new GameModel();
        model.firePlayerBullet();
        
        // Update until bullet should be off-screen
        // Bullet speed is -8, Height is 600. 600/8 = 75 ticks.
        for (int i = 0; i < 100; i++) {
            model.update();
        }
        
        printResult("Bullet Removal at Top", model.getPlayerBullet() == null);
    }

    private static void testScoring() {
        GameModel model = new GameModel();
        int initialScore = model.getScore();
        
        // We will try for a number of ticks to hit an alien
        for (int i = 0; i < 1000; i++) {
            if (model.getAliens().isEmpty()) break;
            
            // Track the first alien
            GameModel.Alien target = model.getAliens().get(0);
            int targetCenterX = target.x + model.getAlienWidth() / 2;
            int playerCenterX = model.getPlayerX() + model.getPlayerWidth() / 2;
            
            // Move player to stay aligned with the moving alien
            if (playerCenterX < targetCenterX) model.movePlayer(2);
            else if (playerCenterX > targetCenterX) model.movePlayer(-2);
            
            model.firePlayerBullet();
            model.update();
            
            if (model.getScore() > initialScore) break;
        }
        
        printResult("Scoring (Alien Destruction)", model.getScore() > initialScore);
    }

    private static void testLivesDepletion() {
        GameModel model = new GameModel();
        int initialLives = model.getLives();
        
        // To test lives depletion, we need an alien bullet to hit the player.
        // Since firing is random, we'll run updates until a hit occurs or a timeout.
        // This is a bit non-deterministic but usually happens quickly if we wait.
        boolean hitOccurred = false;
        for (int i = 0; i < 10000; i++) {
            model.update();
            if (model.getLives() < initialLives) {
                hitOccurred = true;
                break;
            }
        }
        
        printResult("Lives Depletion (Hit Detection)", hitOccurred);
    }

    private static void printResult(String testName, boolean pass) {
        System.out.printf("%-30s: %s%n", testName, pass ? "PASS" : "FAIL");
    }
}
