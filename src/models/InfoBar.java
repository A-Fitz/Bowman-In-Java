package models;

import entities.PlayerEntity;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

/**
 * The Class InfoBar. Shows player names, colors, and health.
 */
public class InfoBar {

    /** Label for player one's health. */
	private Label playerOneHealth;
	
	/** Label for player two's health. */
	private Label playerTwoHealth;
	
	/** The gridpane. Used to format the info bar correctly. */
	private final GridPane gridpane;
	
	/** The player one entity. */
	private final PlayerEntity p1;
	
	/** The player two entity. */
	private final PlayerEntity p2;

	/**
	 * Instantiates a new info bar.
	 *
	 * @param root the root group, InfoBar is placed in this.
	 * @param width the width of the info bar
	 * @param height the height of the info bar
	 * @param player1 the player one entity
	 * @param player2 the player two entity
	 */
	public InfoBar(Group root, double width, double height, PlayerEntity player1, PlayerEntity player2) {
		this.p1 = player1;
		this.p2 = player2;

		/* The HUD group. */
		Group HUDGroup = new Group();
		root.getChildren().add(HUDGroup);

		Rectangle rect = new Rectangle(width, height);
		rect.setFill(Color.GRAY);
		HUDGroup.getChildren().add(rect);

		gridpane = new GridPane();
		gridpane.setPrefWidth(width);
		gridpane.setPrefHeight(height);

		/* Sets up 9 columns */
		ColumnConstraints column1 = new ColumnConstraints();
		column1.setHgrow(Priority.ALWAYS);
		ColumnConstraints column2 = new ColumnConstraints();
		column2.setHgrow(Priority.ALWAYS);
		ColumnConstraints column3 = new ColumnConstraints();
		column3.setHgrow(Priority.ALWAYS);
		ColumnConstraints column4 = new ColumnConstraints();
		column4.setHgrow(Priority.ALWAYS);
		ColumnConstraints column5 = new ColumnConstraints();
		column5.setHgrow(Priority.ALWAYS);
		ColumnConstraints column6 = new ColumnConstraints();
		column6.setHgrow(Priority.ALWAYS);
		ColumnConstraints column7 = new ColumnConstraints();
		column7.setHgrow(Priority.ALWAYS);
		ColumnConstraints column8 = new ColumnConstraints();
		column8.setHgrow(Priority.ALWAYS);
		ColumnConstraints column9 = new ColumnConstraints();
		column9.setHgrow(Priority.ALWAYS);

		/* Adds the 9 columns to the GridPane. */
		gridpane.getColumnConstraints().addAll(column1, column2, column3, column4, column5, column6, column7, column8,
				column9);

		/* Sets up two rows */
		RowConstraints row1 = new RowConstraints();
		row1.setVgrow(Priority.ALWAYS);
		RowConstraints row2 = new RowConstraints();
		row2.setVgrow(Priority.ALWAYS);

		/* Adds the two rows to the GridPane. */
		gridpane.getRowConstraints().addAll(row1, row2);

		/* Sets up the label for player one's name. Sets its color and text. */
		Label player_one_name = new Label();
		player_one_name.setAlignment(Pos.CENTER);
		player_one_name.setContentDisplay(ContentDisplay.CENTER);
		player_one_name.setTextAlignment(TextAlignment.CENTER);
		GridPane.setHalignment(player_one_name, HPos.CENTER);
		GridPane.setValignment(player_one_name, VPos.BOTTOM);
		player_one_name.setText(p1.getName());
		player_one_name.setFont(new Font("Arial", 23));
		player_one_name.setTextFill(Color.web(p1.getColor().getHexColor()));
		gridpane.add(player_one_name, 1, 0);

		/* Sets up the label for player one's health. Sets its color and text. */
		playerOneHealth = new Label();
		playerOneHealth.setAlignment(Pos.CENTER);
		playerOneHealth.setContentDisplay(ContentDisplay.CENTER);
		playerOneHealth.setTextAlignment(TextAlignment.CENTER);
		GridPane.setHalignment(playerOneHealth, HPos.CENTER);
		GridPane.setValignment(playerOneHealth, VPos.TOP);
		playerOneHealth.setText(getHealthString(p1.getHealth()));
		playerOneHealth.setFont(new Font("Arial", 23));
		playerOneHealth.setTextFill(Color.web(p1.getColor().getHexColor()));
		gridpane.add(playerOneHealth, 1, 1);

		/* Static label for "vs" in the center of the GridPane. */
		Label vs = new Label();
		vs.setAlignment(Pos.CENTER);
		vs.setContentDisplay(ContentDisplay.CENTER);
		vs.setTextAlignment(TextAlignment.CENTER);
		GridPane.setHalignment(vs, HPos.CENTER);
		GridPane.setValignment(vs, VPos.TOP);
		vs.setText("vs");
		vs.setFont(new Font("Arial", 23));
		vs.setTextFill(Color.BLACK);
		gridpane.add(vs, 4, 1);

		/* Sets up the label for player two's name. Sets its color and text. */
		Label player_two_name = new Label();
		player_two_name.setAlignment(Pos.CENTER);
		player_two_name.setContentDisplay(ContentDisplay.CENTER);
		player_two_name.setTextAlignment(TextAlignment.CENTER);
		GridPane.setHalignment(player_two_name, HPos.CENTER);
		GridPane.setValignment(player_two_name, VPos.BOTTOM);
		player_two_name.setText(p2.getName());
		player_two_name.setFont(new Font("Arial", 23));
		player_two_name.setTextFill(Color.web(p2.getColor().getHexColor()));
		gridpane.add(player_two_name, 7, 0);

		/* Sets up the label for player two's health. Sets its color and text. */
		playerTwoHealth = new Label();
		playerTwoHealth.setAlignment(Pos.CENTER);
		playerTwoHealth.setContentDisplay(ContentDisplay.CENTER);
		playerTwoHealth.setTextAlignment(TextAlignment.CENTER);
		GridPane.setHalignment(playerTwoHealth, HPos.CENTER);
		GridPane.setValignment(playerTwoHealth, VPos.TOP);
		playerTwoHealth.setText(getHealthString(p2.getHealth()));
		playerTwoHealth.setFont(new Font("Arial", 23));
		playerTwoHealth.setTextFill(Color.web(p2.getColor().getHexColor()));
		gridpane.add(playerTwoHealth, 7, 1);

		HUDGroup.getChildren().add(gridpane);
	}

	/**
	 * Gets the health string. Formats as "Health: xx%".
	 *
	 * @param the health
	 * @return the health string
	 */
	private String getHealthString(float health) {
		String start = "Health: ";
		String mid = String.format("%.2f", health);
		String end = "%";

		return start + mid + end;
	}

	/**
	 * Update health. Simply updates the text of each player's health label.
	 */
	public void updateHealth() {
		gridpane.getChildren().remove(playerOneHealth);
		gridpane.getChildren().remove(playerTwoHealth);

		playerOneHealth = new Label();
		playerOneHealth.setAlignment(Pos.CENTER);
		playerOneHealth.setContentDisplay(ContentDisplay.CENTER);
		playerOneHealth.setTextAlignment(TextAlignment.CENTER);
		GridPane.setHalignment(playerOneHealth, HPos.CENTER);
		GridPane.setValignment(playerOneHealth, VPos.TOP);
		float tempP1Health = p1.getHealth();
		if (tempP1Health >= 0)
			playerOneHealth.setText(getHealthString(tempP1Health));
		else
			playerOneHealth.setText(getHealthString(0));
		playerOneHealth.setFont(new Font("Arial", 23));
		playerOneHealth.setTextFill(Color.web(p1.getColor().getHexColor()));
		gridpane.add(playerOneHealth, 1, 1);

		playerTwoHealth = new Label();
		playerTwoHealth.setAlignment(Pos.CENTER);
		playerTwoHealth.setContentDisplay(ContentDisplay.CENTER);
		playerTwoHealth.setTextAlignment(TextAlignment.CENTER);
		GridPane.setHalignment(playerTwoHealth, HPos.CENTER);
		GridPane.setValignment(playerTwoHealth, VPos.TOP);
		float tempP2Health = p2.getHealth();
		if (tempP2Health >= 0)
			playerTwoHealth.setText(getHealthString(tempP2Health));
		else
			playerTwoHealth.setText(getHealthString(0));
		playerTwoHealth.setFont(new Font("Arial", 23));
		playerTwoHealth.setTextFill(Color.web(p2.getColor().getHexColor()));
		gridpane.add(playerTwoHealth, 7, 1);
	}
}