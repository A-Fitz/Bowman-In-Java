package application;

import data.BowmanConstants;
import data.Difficulty;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Handles launching the program.
 */
public class Main extends Application {

	/** The stage, static so we can return to the main menu */
	public static Stage stg;

	/**
	 * The main method, launches the application.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		Application.launch(Main.class, args);
	}

	/**
	 * Initializes settings for the game.
	 */
	private void init_settings() {
		Settings.setDifficulty(Difficulty.NORMAL);
		Settings.setGravity(BowmanConstants.DEFAULT_GRAVITY);
	}

	/**
	 * Starts the system by loading the main menu.
	 *
	 * @param stage the stage
	 * @throws Exception
	 */
	@Override
	public void start(Stage stage) throws Exception {
		Main.stg = stage;
		Parent root = FXMLLoader.load(getClass().getResource("/stylesheets/MainMenu.fxml"));
		stage.setTitle(BowmanConstants.GAME_TITLE);
		stage.setScene(new Scene(root, BowmanConstants.MENU_WIDTH, BowmanConstants.MENU_HEIGHT));
		stage.show();
		init_settings();
	}
}
