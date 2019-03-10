package models;

import application.Main;
import entities.PlayerEntity;
import game.GameWindow;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * The Class PauseMenu. Allows user to pause the current game, and gives options to resume, restart, or quit.
 */
public class PauseMenu {
	
	/** The root group in which the PauseMenu is placed in. */
	private final Group root;
	
	/** The HUD group, PauseMenu components are put here. */
	private final Group HUDGroup = new Group();
	
	/** The game window, so we can return to it. */
	private GameWindow gameWindow;
	
	/** The stage. */
	private final Stage stage;
	
	/** The player entities, so we can restart with the same names and colors. */
	private final PlayerEntity playerOne;
    private final PlayerEntity playerTwo;
	
	/** Called when the resume button is clicked. Removes the PauseMenu and sets gameWindow to not paused.*/
   private final EventHandler<MouseEvent> onResumeClicked = new EventHandler<MouseEvent>()
	{
		@Override
		public void handle(MouseEvent event)
		{
			remove();
			gameWindow.setPaused(false);
		}
	};
	
	/** Called when the restart button is clicked. Creates a new game, displays it, and removes the PauseMenu.  */
   private final EventHandler<MouseEvent> onRestartClicked = new EventHandler<MouseEvent>()
	{
		@Override
		public void handle(MouseEvent event)
		{
			gameWindow = new GameWindow(stage, playerOne.getName(), playerOne.getColor(), playerTwo.getName(), playerTwo.getColor());
			gameWindow.display();
			remove();
		}
	};
	
	/** Called when the quit button is clicked. Completely removes the stage in 
	 * 	which the game and pause menu are on, shows the main menu. */
   private final EventHandler<MouseEvent> onQuitClicked = new EventHandler<MouseEvent>()
	{
		@Override
		public void handle(MouseEvent event)
		{
			stage.close();
			Main.stg.show();
		}
	};
	
	/**
	 * Instantiates a new pause menu.
	 *
	 * @param root the root group
	 * @param gameWindow the game window
	 * @param width the width of this
	 * @param height the height of this
	 * @param playerOne the player one entity
	 * @param playerTwo the player two entity
	 * @param stage the stage, used when quitting to the main menu
	 */
	public PauseMenu(Group root, GameWindow gameWindow, double width, double height, PlayerEntity playerOne, PlayerEntity playerTwo, Stage stage) {
		this.root = root;
		this.gameWindow = gameWindow;
		this.stage = stage;
		this.playerOne = playerOne;
		this.playerTwo = playerTwo;
		root.getChildren().add(HUDGroup);
		
		Rectangle rect = new Rectangle(width, height);
		rect.setFill(Color.web("#C5C5C5"));
		HUDGroup.getChildren().add(rect);
		
		Button buttonResume = new Button("Resume");
		buttonResume.setMinWidth(250);
		buttonResume.setStyle("-fx-background-color: #909090");
		Button buttonRestart = new Button("Restart");
		buttonRestart.setMinWidth(250);
		buttonRestart.setStyle("-fx-background-color: #909090");
		Button buttonQuit = new Button("Quit");
		buttonQuit.setMinWidth(250);
		buttonQuit.setStyle("-fx-background-color: #909090");
		
		VBox vbox = new VBox(5);
		vbox.getChildren().addAll(buttonResume, buttonRestart, buttonQuit);
		HUDGroup.getChildren().add(vbox);
		vbox.setMinWidth(width);
		vbox.setMinHeight(height);
		vbox.setAlignment(Pos.CENTER);
		
		buttonResume.setOnMouseClicked(onResumeClicked);
		buttonRestart.setOnMouseClicked(onRestartClicked);
		buttonQuit.setOnMouseClicked(onQuitClicked);
	}
	
	/**
	 * Removes this menu from the root Group.
	 */
	private void remove()
	{
		root.getChildren().remove(HUDGroup);
	}
}
