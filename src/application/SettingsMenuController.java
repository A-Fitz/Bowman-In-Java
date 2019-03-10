package application;

import java.net.URL;
import java.util.ResourceBundle;

import data.Difficulty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * Handles setting menu events.
 */
public class SettingsMenuController {

	/** The ResourceBundle used to load the FXML. */
	@FXML
	private ResourceBundle resources;

	/** SceneBuilder wanted this included, not used. */
	@FXML
	private URL location;

	/** The easy difficulty button. */
	@FXML
	private Button easyButton;

	/** The normal difficulty button. */
	@FXML
	private Button normalButton;

	/** The hard difficulty button. */
	@FXML
	private Button hardButton;

	/** The gravity slider. */
	@FXML
	private Slider gravitySlider;

	/** The main menu button. */
	@FXML
	private Button buttonMainMenu;
	
	/** Sun obstacle checkbox. */
	@FXML
	private CheckBox checkBoxSunObstacle;

	/**
	 * Handles when the user selects easy difficulty.
	 *
	 * @param event the event
	 */
	@FXML
	void easyButtonClicked(MouseEvent event) {
		Settings.setDifficulty(Difficulty.EASY);
		setAllButtonsNonDefault();
		easyButton.setDefaultButton(true);
	}
	
	/**
	 * Handles when the user selects normal difficulty.
	 *
	 * @param event the event
	 */
	@FXML
	void normalButtonClicked(MouseEvent event) {
		Settings.setDifficulty(Difficulty.NORMAL);
		setAllButtonsNonDefault();
		normalButton.setDefaultButton(true);
	}

	/**
	 * Handles when the user selects hard difficulty.
	 *
	 * @param event the event
	 */
	@FXML
	void hardButtonClicked(MouseEvent event) {
		Settings.setDifficulty(Difficulty.HARD);
		setAllButtonsNonDefault();
		hardButton.setDefaultButton(true);
	}

	/**
	 * Initializes components.
	 */
	@FXML
	void initialize() {
		
		gravitySlider.valueProperty().addListener((observable, oldValue, newValue) -> Settings.setGravity(newValue.doubleValue()));
		gravitySlider.setValue(Settings.getGravity());
		
		checkBoxSunObstacle.selectedProperty().addListener((observable, oldValue, newValue) -> Settings.setSunObstacle(newValue));
		checkBoxSunObstacle.setSelected(Settings.isSunObstacle());
		
		switch (Settings.getDifficulty()) {
		case EASY:
			easyButton.setDefaultButton(true);
			break;
		case NORMAL:
			normalButton.setDefaultButton(true);
			break;
		case HARD:
			hardButton.setDefaultButton(true);
			break;
		}
	}

	/**
	 * Handles when the user presses the main menu button.
	 *
	 * @param event the event
	 */
	@FXML
	void mainMenuClicked(MouseEvent event) {
		Stage stage = (Stage) buttonMainMenu.getScene().getWindow();
		stage.close();
		Main.stg.show();
	}

	/**
	 * Sets buttons to be not default.
	 */
	private void setAllButtonsNonDefault() {
		easyButton.setDefaultButton(false);
		normalButton.setDefaultButton(false);
		hardButton.setDefaultButton(false);
	}
}
