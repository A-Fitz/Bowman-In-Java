package entities;

import java.io.InputStream;

import application.Settings;
import data.BowmanConstants;
import javafx.animation.AnimationTimer;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The Class ArrowEntity.
 */
public class ArrowEntity extends Entity {

	/** Boolean for if the arrow is stopped */
	private boolean stopped;
	
	/** Boolean for if the arrow is the live arrow being shot. Can still be moving even if not live. */
	private boolean live;

	/** The game loop. */
	private AnimationTimer gameLoop;

	/** The flight time. */
	private double flightTime = 0;

	/** The initial velocity. */
	private double initialVelocity;

	/** The velocity in the x direction. */
	private double vx;

	/** The velocity in the y direction. */
	private double vy;

	/**
	 * Instantiates a new arrow entity.
	 *
	 * @param componentsGroup to place the sprite in
	 */
	public ArrowEntity(Group componentsGroup) {
		InputStream input = getClass().getResourceAsStream("/resources/arrow.png");
		Image image = new Image(input, BowmanConstants.ARROW_SPRITE_WIDTH, BowmanConstants.ARROW_SPRITE_HEIGHT, false,
				true);
		this.sprite = new ImageView(image);

		this.stopped = false;
		this.live = true;
		this.componentsGroup = componentsGroup;

		componentsGroup.getChildren().add(this.sprite);
	}
	/**
	 * Checks if the sprite is not in bounds.
	 *
	 * @return true, if not in bounds
	 */
	private boolean isNotInBounds() {

		return ! (sprite.getTranslateY() < 660);
	}
	
	/**
	 * Removes the sprite from the componentsGroup.
	 */
	public void remove()
	{
		componentsGroup.getChildren().remove(this.sprite);
	}

	/**
	 * Checks if is stopped.
	 *
	 * @return true, if is stopped
	 */
	public boolean isStopped() {
		return stopped;
	}
	
	/**
	 * Magnitude to velocity.
	 *
	 * @param magnitude
	 * @return the velocity
	 */
	private double magnitudeToVelocity(double magnitude) {
		if(magnitude >= 90)
		{
			return magnitude / 6.5;
		}
		else if(magnitude < 90 && magnitude > 75)
		{
			return magnitude / 6.0;
		}
		else if(magnitude < 75 && magnitude > 50)
		{
			return magnitude / 5.5;
		}
		else
		{
			return magnitude / 5.0;
		}
	}

	/**
	 * Reposition the sprite in the componentsGroup.
	 *
	 * @param the x position to reposition to
	 * @param the y position to reposition to
	 */
	public void reposition(double x, double y) {
		sprite.setTranslateX(x);
		sprite.setTranslateY(y);
	}

	/**
	 * Sets if the arrow is live.
	 *
	 * @param the new live boolean
	 */
	public void setLive(boolean live) {
		this.live = live;
	}

	/**
	 * Sets if the arrow is stopped.
	 *
	 * @param the new stopped boolean
	 */
	public void setStopped(boolean stopped) {
		this.stopped = stopped;
	}

	/**
	 * Shoot the arrow. Contains an AnimationTimer used to treat the sprite as a projectile and move it
	 * parabolically. 
	 *
	 * @param magnitude the magnitude to shoot with
	 * @param angle the angle to shoot at
	 */
	public void shoot(double magnitude, double angle) {
		initialVelocity = magnitudeToVelocity(magnitude);
		vx = initialVelocity * Math.cos(angle);
		vy = initialVelocity * Math.sin(angle);

		gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (stopped) {
					gameLoop.stop();
				}

				if (!stopped) {
					flightTime++;
					vy = initialVelocity * Math.sin(angle) + Settings.getGravity() * flightTime / 180;

					if (isNotInBounds()) {
						stopped = true;
					} else { // actually move
						if (!stopped) {
							sprite.setTranslateX(sprite.getTranslateX() + vx);
							sprite.setTranslateY(sprite.getTranslateY() + vy);
						}
						if (live && !stopped) {
							componentsGroup.setTranslateX(-sprite.getTranslateX() + 500);
						}
						// Set sprite to be Northwest direction
						if (vx < 0 && vy < 0) {
							InputStream input = getClass().getResourceAsStream("/resources/arrowNW.png");
							Image image = new Image(input, BowmanConstants.ARROW_SPRITE_WIDTH,
									BowmanConstants.ARROW_SPRITE_HEIGHT, false, true);
							sprite.setImage(image);
						}
						// Set sprite to be North direction
						else if (vx < 1 && vx > -1 && vy < 0) {
							InputStream input = getClass().getResourceAsStream("/resources/arrowN.png");
							Image image = new Image(input, BowmanConstants.ARROW_SPRITE_WIDTH,
									BowmanConstants.ARROW_SPRITE_HEIGHT, false, true);
							sprite.setImage(image);
						}
						// Set sprite to be Northeast direction
						else if (vx > 0 && vy < 0) {
							InputStream input = getClass().getResourceAsStream("/resources/arrowNE.png");
							Image image = new Image(input, BowmanConstants.ARROW_SPRITE_WIDTH,
									BowmanConstants.ARROW_SPRITE_HEIGHT, false, true);
							sprite.setImage(image);
						}
						// Set sprite to be East direction
						else if (vx > 0 && vy < 4 && vy > -4) {
							InputStream input = getClass().getResourceAsStream("/resources/arrowE.png");
							Image image = new Image(input, BowmanConstants.ARROW_SPRITE_WIDTH,
									BowmanConstants.ARROW_SPRITE_HEIGHT, false, true);
							sprite.setImage(image);
						}
						// Set sprite to be South Direction
						else if (vx < 1 && vx > -1 && vy > 0) {
							InputStream input = getClass().getResourceAsStream("/resources/arrowS.png");
							Image image = new Image(input, BowmanConstants.ARROW_SPRITE_WIDTH,
									BowmanConstants.ARROW_SPRITE_HEIGHT, false, true);
							sprite.setImage(image);
						}
						// Set sprite to be Southeast direction
						else if (vx > 0 && vy > 0) {
							InputStream input = getClass().getResourceAsStream("/resources/arrowSE.png");
							Image image = new Image(input, BowmanConstants.ARROW_SPRITE_WIDTH,
									BowmanConstants.ARROW_SPRITE_HEIGHT, false, true);
							sprite.setImage(image);
						}
						// Set sprite to be west direction
						else if (vx < 0 && vy < 4 && vy > -4) {
							InputStream input = getClass().getResourceAsStream("/resources/arrowW.png");
							Image image = new Image(input, BowmanConstants.ARROW_SPRITE_WIDTH,
									BowmanConstants.ARROW_SPRITE_HEIGHT, false, true);
							sprite.setImage(image);
						}
						// Set sprite to be Southwest direction
						else if (vx < 0 && vy > 0) {
							InputStream input = getClass().getResourceAsStream("/resources/arrowSW.png");
							Image image = new Image(input, BowmanConstants.ARROW_SPRITE_WIDTH,
									BowmanConstants.ARROW_SPRITE_HEIGHT, false, true);
							sprite.setImage(image);
						}
					}
				}
			}
		};

		gameLoop.start();
	}

}
