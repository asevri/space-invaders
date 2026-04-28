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

## Prompt 4:


Result: 

Fixes: 

Observation: 

## Prompt 5:


Result: 

Fixes: 

Observation: 

## Prompt 6:


Result: 

Fixes: 

Observation: 
