package application;

import java.net.URL;
import java.util.ResourceBundle;

import data.BowmanConstants;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Handles main menu events.
 */
public class MainMenuController {

	/** The ResourceBundle used to load the FXML. */
	@FXML
	private ResourceBundle resources;

	/** SceneBuilder wanted this included, not used. */
	@FXML
	private URL location;

	/** The button for the settings menu. */
	@FXML
	private Button buttonSettings;

	/** The button for the launch game menu. */
	@FXML
	private Button buttonPlayGame;

	/**
	 * Initializes components. Unused.
	 */
	@FXML
	void initialize() {
		
	}

	/**
	 * Handles when the user presses the play game button.
	 *
	 * @param MouseEvent for click
	 */
	@FXML
	void playGameClicked(MouseEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/stylesheets/LaunchGameMenu.fxml"));
			Parent root = fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root, BowmanConstants.MENU_WIDTH, BowmanConstants.MENU_HEIGHT));
			stage.show();
			stage.setResizable(false);
			stage.setTitle(BowmanConstants.LAUNCH_MENU_TITLE);
			Main.stg.close();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, "Issue loading LaunchGameMenu.", ButtonType.OK);
			alert.show();
		}
	}

	/**
	 * Handles when the user presses the settings button.
	 *
	 * @param MouseEvent for click
	 */
	@FXML
	void settingsClicked(MouseEvent event) {
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/stylesheets/SettingsMenu.fxml"));
			Parent root = fxmlLoader.load();
			Stage stage = new Stage();
			stage.setScene(new Scene(root, BowmanConstants.MENU_WIDTH, BowmanConstants.MENU_HEIGHT));
			stage.show();
			stage.setResizable(false);
			stage.setTitle(BowmanConstants.SETTINGS_TITLE);
			Main.stg.close();
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, "Issue loading SettingsMenu.", ButtonType.OK);
			alert.show();
		}
	}
}
