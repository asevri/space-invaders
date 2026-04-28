## Prompt 1: The MVC Skeleton
I'm building Space Invaders in Java using Swing, split into three files: GameModel.java, GameView.java, and GameController.java. GameView should extend JPanel and be hosted in a JFrame. GameController should have the main method and wire the three classes together. GameModel must have no Swing imports. For now, just create the three class shells with placeholder comments describing what each class will do. The program should compile and open a blank window.

Result: created 3 seperate files as requested.

Fixes: no fixes needed

Observation: GameModel is very simple in comparison to the other files.

## Prompt 2: Building the Model
Fill in GameModel.java. The model should track: the player's horizontal position, the alien formation (5 rows of 11), the player's bullet (one at a time), alien bullets, the score, and lives remaining (start with 3). Add logic to: move the player left and right, fire a player bullet if one isn't already in flight, advance the player's bullet each tick, move the alien formation right until the edge then down and reverse, fire alien bullets at random intervals, and detect collisions between bullets and aliens or the player. No Swing imports.

Result: GameModel was updated with above directions.

Fixes: no fixes needed 

Observation: I am unsure if this is correct as it is not updating anything.

## Prompt 3: Building the View
Fill in GameView.java. It should take a reference to the model and draw everything the player sees: the player, the alien formation, both sets of bullets, the score, and remaining lives. Show a centered game-over message when the game ends. The view should only read from the model — it must never change game state.

Result: GameView was updated to draw everything the player sees.

Fixes: No fixes needed

Observation: It rendered the player green and aliens on top of the screen. There was no instances of modified model such as updating score or moving bullet. Nothing moves in the game yet.

## Prompt 4: Wiring the Controller 
Fill in GameController.java. Add keyboard controls so the player can move left and right with the arrow keys and fire with the spacebar. Add a game loop using a Swing timer that updates the model each tick and redraws the view. Stop the loop when the game is over.

Result: GameController was updated as per prompt.

Fixes: No fixes needed

Observation: I was able to play it as expected. I thought space invaders generate one spaceship at a time not the whole formation. 

## Prompt 5:
Create a separate file called ModelTester.java with a main method. It should create a GameModel, call its methods directly, and print PASS or FAIL for each check. Write tests for at least five behaviors: the player cannot move past the left or right edge, firing while a bullet is already in flight does nothing, a bullet that reaches the top is removed, destroying an alien increases the score, and losing all lives triggers the game-over state. No testing libraries — just plain Java.

Result: created test file. Originally it failed scoring test. AI remade the testss to pass.

Fixes: no fixes needed from my side.

Observation: AI run through tests and repeated changes on its own until it passed all tests.

## Prompt 6:
I want to make the aliens look more like classic Invaders. In GameView.java, replace the green rectangles with drawings. Draw an alien as a 3-row grid: top row solid black, middle row two black squares separated by empty space, bottom row solid black. Leave a one-pixel gap between cells. Keep the overall width and height the same, and keep the aliens aligned in columns. The player should stay a green rectangle. Make sure the alien drawing code is inside the aliens' existing loop and doesn't break the layout or scale.

Result: the aliens are now have more design to them

Fixes: no fixes needed, logic of the game was not touched

Observation: only made modifications to the alien view.

## Prompt 7:


Result: 

Fixes: 

Observation: 

## Prompt 8:


Result: 

Fixes: 

Observation: 

## Prompt 9:


Result: 

Fixes: 

Observation: 

## Prompt 10:


Result: 

Fixes: 

Observation: 
