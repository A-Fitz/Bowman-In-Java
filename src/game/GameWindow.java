package game;

import application.Main;
import application.Settings;
import data.BowmanConstants;
import data.Colors;
import entities.ArrowEntity;
import entities.PlayerEntity;
import entities.SunEntity;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import models.InfoBar;
import models.PauseMenu;
import models.ShotVector;

/**
 * The Class GameWindow.
 */
public class GameWindow extends GameLoop {
	/** The on key pressed handler. Used to check if the user wants to pause the game */
	private final EventHandler<KeyEvent> onKeyPressedHandler = event -> {
		if (event.getCode() == KeyCode.ESCAPE) {
			if (!isGamePaused) // pause game
			{
				isGamePaused = true;
				createPauseMenu();
			}
		}
	};

	/** The on mouse released handler. Used to shoot an arrow if allowable. */
	private final EventHandler<MouseEvent> onMouseReleasedHandler = event -> {

		if (event.getButton() == MouseButton.PRIMARY && isArrowNotBeingShot() && !isGamePaused) {
			// set old liveArrow to not live
			if (liveArrow != null) {
				liveArrow.setLive(false);
			}

			if (isOtherPlayerP2())
				shotVector.setBackwards(true);
			else
				shotVector.setBackwards(false);

			// shoot the arrow
			liveArrow = new ArrowEntity(componentsGroup);
			liveArrow.reposition(turnPlayer.getMaxX(), turnPlayer.getY());
			liveArrow.shoot(shotVector.getMagnitude(), shotVector.getAngle());

			arrowQueue.add(liveArrow);

			// removes from front of arrow queue if there are MAX_ARROWS or more, and removes it from the view
			if (arrowQueue.size() >= BowmanConstants.MAX_ARROWS) {
				ArrowEntity tempArrow = arrowQueue.poll();
				tempArrow.remove();
			}

			gameLoop = new AnimationTimer() {

				@Override
				public void handle(long now) {
					if (hasArrowHitPlayer()) {
						liveArrow.setStopped(true);

						otherPlayer.hitByArrow(liveArrow.getY());
						infoBar.updateHealth();

					}
					if (Settings.isSunObstacle() && liveArrow.getX() >= sunEntity.getX() && liveArrow.getY() >= sunEntity.getY()
							&& liveArrow.getX() <= sunEntity.getMaxX() && liveArrow.getY() <= sunEntity.getMaxY())
					{
						sunIsHit();
					}
					if (liveArrow.getX() <= playerOneXPosition - 3000 || liveArrow.getX() >= playerTwoXPosition + 3000)
					{
						liveArrow.setStopped(true);
					}
					// ALSO
					if (liveArrow.isStopped()) {
						switchTurns();

						shotVector.setStartPos(getShotVectorX(), getShotVectorY());
						shotVector.reset();

						// reset scale
						try {
							Thread.sleep(600);
							componentsGroup.setTranslateX(-getTranslateResetX());
						} catch (InterruptedException e) {
							Alert alert = new Alert(AlertType.ERROR, "Error resetting camera position.", ButtonType.OK);
							alert.show();
						}

						gameLoop.stop();

						if (playerOne.isDead() || playerTwo.isDead()) {
							showGameOverAlert();
						}

					}
				}
			};

			gameLoop.start();
		}

	};

	/** The on drag handler. Used to start a shot vector, updates the end position of the shot vector. */
	private final EventHandler<MouseEvent> onDragHandler = event -> {
		if (event.getButton() == MouseButton.PRIMARY && isArrowNotBeingShot() && !isGamePaused) {
			shotVector.setEndPos(event.getX(), event.getY());
		}
	};

	/**
	 * Instantiates a new game window.
	 *
	 * @param primaryStage the primary stage
	 * @param p1Name player one's name
	 * @param p1Color player one's color
	 * @param p2Name player two's name
	 * @param p2Color player two's color
	 */
	public GameWindow(Stage primaryStage, String p1Name, Colors p1Color, String p2Name, Colors p2Color) {
		super(primaryStage);

		setupDifficulty();
		playerOne = new PlayerEntity(componentsGroup, p1Name, p1Color);
		playerOne.reposition(playerOneXPosition,
				scene.getHeight() - playerOne.getMaxY() - BowmanConstants.PLAYER_MARGIN);

		playerTwo = new PlayerEntity(componentsGroup, p2Name, p2Color);
		playerTwo.reposition(playerTwoXPosition,
				scene.getHeight() - playerTwo.getMaxY() - BowmanConstants.PLAYER_MARGIN);

		turnPlayer = playerOne;
		otherPlayer = playerTwo;

		infoBar = new InfoBar(root, BowmanConstants.STAGE_WIDTH, BowmanConstants.INFOBAR_HEIGHT, playerOne, playerTwo);

		shotVector = new ShotVector(componentsGroup);
		shotVector.setStartPos(getShotVectorX(), getShotVectorY());

		shotVector.reset();

		componentsGroup.setOnMouseReleased(onMouseReleasedHandler);
		componentsGroup.setOnMouseDragged(onDragHandler);
		scene.setOnKeyPressed(onKeyPressedHandler);

		sunEntity = new SunEntity(componentsGroup, getSunXPosition());
		
		animateStartGame();
	}

	/**
	 * Animate start game. Shows a welcome message and scrolls the "camera" to show the player distance.
	 */
	private void animateStartGame() {

		switchTurns();

		componentsGroup.setTranslateX(-getTranslateResetX());

		Label welcome = new Label();
		welcome.setText("Welcome to Bowman in Java!");
		welcome.setFont(new Font("Arial", 45));
		welcome.setTextFill(Color.BLACK);
		welcome.setMinWidth(BowmanConstants.STAGE_WIDTH);
		welcome.setMinHeight(BowmanConstants.STAGE_HEIGHT);
		welcome.setAlignment(Pos.CENTER);
		root.getChildren().add(welcome);
		int welcomeindex = root.getChildren().indexOf(welcome);

		gameLoop = new AnimationTimer() {

			@Override
			public void handle(long now) {
				if (componentsGroup.getTranslateX() != -(playerOneXPosition - 200)) {
					double temp = componentsGroup.getTranslateX();
					componentsGroup.setTranslateX(temp + 10);
				}

				if (componentsGroup.getTranslateX() >= -(playerOneXPosition - 200)) {
					gameLoop.stop();
					switchTurns();
					componentsGroup.setTranslateX(-getTranslateResetX());
					root.getChildren().remove(welcomeindex);
				}

			}
		};

		gameLoop.start();
	}

	/**
	 * Gets the x position in which the sun entity should be positioned at. Used because the game
	 * has varying player distances.
	 * 
	 * @return
	 */
	private double getSunXPosition() {
		return (playerOne.getMaxX() + playerTwo.getX() / 2);
	}

	/**
	 * This function will see if an arrow entity collides with otherPlayer.
	 *
	 * @return true, if the arrow has hit the non-turn player
	 */
	private boolean hasArrowHitPlayer() {
		return liveArrow.getX() <= otherPlayer.getMaxX() && liveArrow.getX() >= otherPlayer.getX()
				&& liveArrow.getY() >= otherPlayer.getY() && liveArrow.getY() <= otherPlayer.getMaxY();
	}

	/**
	 * Creates the pause menu.
	 */
	private void createPauseMenu() {
		pauseMenu = new PauseMenu(root, this, BowmanConstants.STAGE_WIDTH, BowmanConstants.STAGE_HEIGHT, playerOne,
				playerTwo, stage);
	}

	/**
	 * Ends the game, returns to the main menu.
	 */
	private void endGame() {
		stage.close();
		Main.stg.show();
	}

	/**
	 * Gets where the shot vector should start its line on the x-axis.
	 *
	 * @return the shot vector X
	 */
	private double getShotVectorX() {
		if (turnPlayer == playerOne)
			return playerOneXPosition;
		else
			return playerTwoXPosition;
	}

	/**
	 * Gets where the shot vector should start its line on the y-axis.
	 *
	 * @return the shot vector Y
	 */
	private double getShotVectorY() {
		if (turnPlayer == playerOne)
			return playerOne.getMaxY() - BowmanConstants.PLAYER_MARGIN - 100;
		else
			return playerTwo.getMaxY() - BowmanConstants.PLAYER_MARGIN - 100;
	}

	/**
	 * Gets where the "camera" should position itself for the turnPlayer.
	 *
	 * @return the translate reset X
	 */
	private double getTranslateResetX() {
		// don't mess with these constants [AF]
		if (turnPlayer == playerOne)
			return playerOneXPosition - 200;
		else
			return playerTwoXPosition - 1000;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * Initializes the background, displays the grass and sky backgrounds in componentsGroup.
	 */
	@Override
	public void initBackground() {
		backgroundSky = new Rectangle(20000, 19900);
		backgroundSky.setFill(Color.LIGHTBLUE);

		backgroundGrass = new Rectangle(20000, 1000);
		backgroundGrass.setFill(Color.GREEN);

		backgroundSky.setX(-10000);
		backgroundGrass.setX(-10000);

		backgroundSky.setY(-12000);
		backgroundGrass.setY(BowmanConstants.STAGE_HEIGHT - BowmanConstants.PLAYER_MARGIN);

		componentsGroup.getChildren().addAll(backgroundSky, backgroundGrass);
	}

	/**
	 * Checks if liveArrow is being shot.
	 *
	 * @return true, if liveArrow is not stopped.
	 */
	private boolean isArrowNotBeingShot() {
		return (liveArrow == null || liveArrow.isStopped());
	}

	/**
	 * Checks if otherPlayer is playerTwo.
	 *
	 * @return true, if otherPlayer is playerTwo.
	 */
	private boolean isOtherPlayerP2() {
		return otherPlayer != playerOne;
	}

	/**
	 * Uses Math.random() to decide a random x position in which to calculate the playerEntities margins.
	 */
	private void setupDifficulty() {
		int decision = (int) (Math.random());

		switch (Settings.getDifficulty()) {
		case EASY: {
			int x = (int) (Math.random() * BowmanConstants.PLAYER_XPOSITION_EASY_MARGIN + 1);
			int x2 = (int) (Math.random() * BowmanConstants.PLAYER_XPOSITION_EASY_MARGIN + 1);
			setupDifficultyHelper(decision, x, x2);
			break;
		}
		case NORMAL: {
			int x = (int) (Math.random() * BowmanConstants.PLAYER_XPOSITION_NORMAL_MARGIN + 1);
			int x2 = (int) (Math.random() * BowmanConstants.PLAYER_XPOSITION_NORMAL_MARGIN + 1);
			setupDifficultyHelper(decision, x, x2);
			break;
		}
		case HARD: {
			int x = (int) (Math.random() * BowmanConstants.PLAYER_XPOSITION_HARD_MARGIN + 1);
			int x2 = (int) (Math.random() * BowmanConstants.PLAYER_XPOSITION_HARD_MARGIN + 1);
			setupDifficultyHelper(decision, x, x2);
			break;
		}
		}
	}

	/**
	 * Sets playerOne and playerTwo's position values based on the random decision calculated by the caller method.
	 * @param decision, 0 or 1
	 * @param x player one's x position margin
	 * @param x2 player two's x position margin
	 */
	private void setupDifficultyHelper(int decision, int x, int x2) {
		if (decision == 0) {
			playerOneXPosition = BowmanConstants.PLAYER_ONE_XPOSITION - x;
			playerTwoXPosition = BowmanConstants.PLAYER_TWO_XPOSITION + x2;
		} else {
			playerOneXPosition = BowmanConstants.PLAYER_ONE_XPOSITION + x;
			playerTwoXPosition = BowmanConstants.PLAYER_TWO_XPOSITION - x2;
		}
	}

	/**
	 * Show game over alert. When it is closed call endGame().
	 */
	private void showGameOverAlert() {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game Over");

		if (playerOne.isDead())
			alert.setContentText("Player 2 has won the game.");
		else if (playerTwo.isDead())
			alert.setContentText("Player 1 has won the game.");
		alert.setOnHidden(evt -> endGame());
		alert.show();
	}

	/**
	 * Switch turns. Switches turnPlayer and otherPlayer.
	 */
	private void switchTurns() {
		PlayerEntity temp = turnPlayer;
		turnPlayer = otherPlayer;
		otherPlayer = temp;
	}
	
	/**
	 * Bounce the arrow back when it hits sunEntity. Only called if the sunObstacle setting is set to true.
	 */
	private void sunIsHit()
	{
		if(isOtherPlayerP2())
			liveArrow.shoot(100, Math.atan(sunEntity.getY() / sunEntity.getX())+180);
		else
			liveArrow.shoot(100, Math.atan(sunEntity.getY() / sunEntity.getX()));
	}
}
