package game;

import java.util.LinkedList;
import java.util.Queue;

import data.BowmanConstants;
import entities.ArrowEntity;
import entities.PlayerEntity;
import entities.SunEntity;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import models.InfoBar;
import models.PauseMenu;
import models.ShotVector;

/**
 * The Class GameLoop.
 */
public abstract class GameLoop {

	/** The stage. */
	protected final Stage stage;

	/** The scene. */
	protected final Scene scene;

	/** The root in which componentsGroup is added to, allows us to display a welcome animation. */
	protected final Group root;

	/** The components group to place players and arrows into. */
	protected final Group componentsGroup;

	/** The sky portion of the background. */
	protected Rectangle backgroundSky;

	/** The grass portion of the background. */
	protected Rectangle backgroundGrass;

	/** The info bar, shows player names, colors, and health. */
	protected InfoBar infoBar;

	/** The pause menu. */
	protected PauseMenu pauseMenu;

	/** The shot vector. */
	protected ShotVector shotVector;

	/** The live arrow (currently being shot). */
	protected ArrowEntity liveArrow;

	/** The player one entity. */
	protected PlayerEntity playerOne;

	/** The player two entity. */
	protected PlayerEntity playerTwo;

	/** Used as a pointer to the player entity who has the turn. */
	protected PlayerEntity turnPlayer;

	/** Used as a pointer to the player entity who does not have the turn. */
	protected PlayerEntity otherPlayer;

	/** The game loop AnimationTimer. */
	protected AnimationTimer gameLoop;

	/** The arrow queue, used to delete arrows if over the max. */
	protected final Queue<ArrowEntity> arrowQueue;

	/** The player one X position. */
	protected int playerOneXPosition;

	/** The player two X position. */
	protected int playerTwoXPosition;

	/** Is game paused. */
	protected boolean isGamePaused = false;

	/** The sun entity */
	protected SunEntity sunEntity;

	/**
	 * Instantiates a new game loop.
	 *
	 * @param primaryStage the primary stage
	 */
	protected GameLoop(Stage primaryStage) {
		primaryStage.setOnCloseRequest(event -> System.exit(0));

		arrowQueue = new LinkedList<>();

		stage = primaryStage;
		root = new Group();
		scene = new Scene(root, BowmanConstants.STAGE_WIDTH, BowmanConstants.STAGE_HEIGHT);
		componentsGroup = new Group();
		initBackground();

		root.getChildren().add(componentsGroup);
	}

	/**
	 * Display the stage with the scene, center it on the screen.
	 */
	public void display() {
		stage.setScene(scene);
		stage.centerOnScreen();
	}

	/**
	 * Initializes the background.
	 */
	protected abstract void initBackground();

	/**
	 * Sets the boolean for if the game is paused
	 *
	 * @param p the new paused boolean
	 */
	public void setPaused(boolean p) {
		this.isGamePaused = p;
	}
}