package models;

import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;

/**
 * The Class ShotVector. Used to display a line showing the angle and magnitude pre-shot.
 */
public class ShotVector {

	/** The line in which to visually display the angle and magnitude. */
	private final Line line;

	/** The label. Shows text for the angle and magnitude. */
	private final Label label;
	
	/** The numerical angle of the shot. (radians) */
	private double angle;
	
	/** The magnitude of the shot. */
	private double magnitude;
	
	/** Used to display and calculate the shot vector for player two. */
	private boolean backwards;

	/** The max line length. */
	private final int MAX_LINE_LENGTH = 200;

	/**
	 * Instantiates a new shot vector.
	 *
	 * @param the components group to place this in
	 */
	public ShotVector(Group componentsGroup) {
		this.line = new Line();
		this.line.setFill(Color.BLACK);

		this.label = new Label();
		label.setFont(new Font("Arial", 12));
		label.setTextFill(Color.BLACK);

		this.backwards = false;

		componentsGroup.getChildren().addAll(line, label);

		reset();
	}

	/**
	 * Gets the angle.
	 *
	 * @return the angle
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * Gets the angle given the starting xy and ending xy. Used to get an angle for the line.
	 *
	 * @param x1 the starting x position
	 * @param y1 the starting y position
	 * @param x2 the ending x position
	 * @param y2 the ending y position
	 * @return the angle in radians
	 */
	private double getAngle(double x1, double y1, double x2, double y2) {
		double xDiff = x2 - x1;
		double yDiff = y2 - y1;

		return Math.atan2(yDiff, xDiff);
	}

	/**
	 * Gets the angle string. Displayed as such: "Angle: xx.xx"
	 *
	 * @return the angle string, in degrees.
	 */
	private String getAngleString() {
		double temp_angle = -Math.toDegrees(angle);
		if (temp_angle < 0)
			temp_angle += 360;

		return "Angle: " + String.valueOf(String.format("%.2f", temp_angle));
	}

	/**
	 * Gets the length given the starting xy and ending xy.
	 *
	 * @param x1 the starting x position
	 * @param y1 the starting y position
	 * @param x2 the ending x position
	 * @param y2 the ending y position
	 * @return the length
	 */
	private double getLength(double x1, double y1, double x2, double y2) {
		double ac = Math.abs(y2 - y1);
		double cb = Math.abs(x2 - x1);

		return Math.hypot(ac, cb);
	}

	/**
	 * Gets the magnitude.
	 *
	 * @return the magnitude
	 */
	public double getMagnitude() {
		return magnitude;
	}

	/**
	 * Gets the magnitude string. "Magnitude: xx.xx"
	 *
	 * @return the magnitude string
	 */
	private String getMagnitudeString() {
		return "Magnitude: " + String.valueOf(String.format("%.2f", this.magnitude));
	}

	/**
	 * Gets the vector info string. "MAG & ANGLE"
	 *
	 * @return the vector info string
	 */
	private String getVectorInfoString() {
		return getMagnitudeString() + " & " + getAngleString();
	}

	/**
	 * Resets the line by setting its end position to its starting position, and setting the label to empty.
	 */
	public void reset() {
		setEndPos(line.getStartX(), line.getStartY());
		this.label.setText("");
	}

	/**
	 * Sets if player two is using the shot vector.
	 *
	 * @param b the new backwards boolean
	 */
	public void setBackwards(boolean b) {
		this.backwards = b;
	}

	/**
	 * Sets the end position of the line.
	 *
	 * @param x the ending x position
	 * @param y the ending y position
	 */
	public void setEndPos(double x, double y) {
		double temp_length = getLength(line.getStartX(), line.getStartY(), x, y);

		this.angle = getAngle(line.getStartX(), line.getStartY(), x, y);

		/* Making sure the max magnitude can only be 100 and the line won't be longer than 200px */
		if (temp_length > MAX_LINE_LENGTH) {
			this.magnitude = 100;
			this.line.setEndX(line.getStartX() + MAX_LINE_LENGTH * Math.cos(angle));
			this.line.setEndY(line.getStartY() + MAX_LINE_LENGTH * Math.sin(angle));
		} else {
			this.magnitude = temp_length / 2;
			this.line.setEndX(x);
			this.line.setEndY(y);
		}

		this.label.setText(getVectorInfoString());

		if (!backwards)
			this.label.setRotate(Math.toDegrees(angle));
		else
			this.label.setRotate(Math.toDegrees(angle) + 180);
	}

	/**
	 * Sets the start position of the line. Shows the label in correct rotation.
	 *
	 * @param x the starting x position
	 * @param y the starting y position
	 */
	public void setStartPos(double x, double y) {
		this.line.setStartX(x);
		this.line.setStartY(y);

		if (!backwards)
			this.label.setTranslateX(x);
		else
			this.label.setTranslateX(x - MAX_LINE_LENGTH);

		this.label.setTranslateY(y);
	}
}
