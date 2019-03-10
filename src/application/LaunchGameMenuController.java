package application;

import java.net.URL;
import java.util.ResourceBundle;

import data.Colors;
import game.GameWindow;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * The Class LaunchGameMenuController.
 */
public class LaunchGameMenuController {

	/** The ResourceBundle used to load the FXML. */
	@FXML
	private ResourceBundle resources;

	/** SceneBuilder wanted this included, not used. */
	@FXML
	private URL location;

	/** The label for difficulty. */
	@FXML
	private Label labelDifficulty;

	/** The label for gravity. */
	@FXML
	private Label labelGravity;
	
	/** The label if the sun is an obstacle. */
	@FXML
	private Label labelSunObstacle;

	/** The textfield for player one name. */
	@FXML
	private TextField textfieldPlayerOneName;

	/** The combobox for player one color. */
	@FXML
	private ComboBox<Colors> comboboxPlayerOneColor;

	/** The textfield for player two name. */
	@FXML
	private TextField textfieldPlayerTwoName;

	/** The combobox for player two color. */
	@FXML
	private ComboBox<Colors> comboboxPlayerTwoColor;

	/** The button for launch game. */
	@FXML
	private Button buttonLaunchGame;

	/** The button for main menu. */
	@FXML
	private Button buttonMainMenu;

	/**
	 * Checks to see if the players have names and colors.
	 *
	 * @return true if text fields are not empty
	 */
	private boolean checkInput() {
		boolean playerOneFieldsFilled = !(textfieldPlayerOneName.getText() == null
				|| textfieldPlayerOneName.getText().trim().isEmpty());
		boolean playerTwoFieldsFilled = !(textfieldPlayerTwoName.getText() == null
				|| textfieldPlayerTwoName.getText().trim().isEmpty());

		boolean playerOneColorChosen = !comboboxPlayerOneColor.getSelectionModel().isEmpty();
		boolean playerTwoColorChosen = !comboboxPlayerTwoColor.getSelectionModel().isEmpty();

		return playerOneFieldsFilled && playerTwoFieldsFilled && playerOneColorChosen && playerTwoColorChosen;

	}

	/**
	 * Initializes components.
	 */
	@FXML
	void initialize() {
		
		try {
			labelDifficulty.setText(Settings.getDifficulty().toString());
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, "Settings.difficulty has not been initialized.", ButtonType.OK);
			alert.show();
		}

		try {
			labelGravity.setText(String.format("%.2f", Settings.getGravity()));
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, "Settings.gravity has not been initialized.", ButtonType.OK);
			alert.show();
		}
		
		try {
			labelSunObstacle.setText(String.valueOf(Settings.isSunObstacle()));
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, "Settings.sunObstacle has not been initialized.", ButtonType.OK);
			alert.show();
		}

		// Fill comboboxes for colors with color enums
		comboboxPlayerOneColor.getItems().setAll(Colors.values());
		comboboxPlayerTwoColor.getItems().setAll(Colors.values());
	}

	/**
	 * Event occurs when the launch game button is clicked by the user.
	 *
	 * @param event the event
	 */
	@FXML
	void launchGameClicked(MouseEvent event) {
		if (checkInput()) {
			try {

				Stage stage = (Stage) buttonMainMenu.getScene().getWindow();


				String p1Name = textfieldPlayerOneName.getText();
				Colors p1Color = comboboxPlayerOneColor.getValue();

				String p2Name = textfieldPlayerTwoName.getText();
				Colors p2Color = comboboxPlayerTwoColor.getValue();
				
				GameWindow game = new GameWindow(stage, p1Name, p1Color, p2Name, p2Color);

				game.display();
				Main.stg.close();
			} catch (Exception e) {
				//e.printStackTrace();
				Alert alert = new Alert(AlertType.ERROR, "Issue loading GameWindow", ButtonType.OK);
				alert.show();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR, "Both player names and colors must be filled.", ButtonType.OK);
			alert.show();
		}

	}

	/**
	 * Event occurs when the main menu button is clicked by the user.
	 *
	 * @param event the event
	 */
	@FXML
	void mainMenuClicked(MouseEvent event) {
		Stage stage = (Stage) buttonMainMenu.getScene().getWindow();
		stage.close();
		Main.stg.show();
	}
}
