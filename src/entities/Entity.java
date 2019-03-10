package entities;

import javafx.scene.Group;
import javafx.scene.image.ImageView;

public abstract class Entity {
	/** The sprite for an entity */
	ImageView sprite;
	
	/** The components group to place the sprite in. */
	Group componentsGroup;
	
	/**
	 * Gets the x position of the sprite.
	 *
	 * @return the x position
	 */
	public double getX() {
		return sprite.getTranslateX();
	}

	/**
	 * Gets the y position of the sprite.
	 *
	 * @return the y position
	 */
	public double getY() {
		return sprite.getTranslateY();
	}

}
