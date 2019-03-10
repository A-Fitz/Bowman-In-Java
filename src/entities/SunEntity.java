package entities;

import java.io.InputStream;

import data.BowmanConstants;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * The Class SunEntity.
 */
public class SunEntity extends Entity {

	/**
	 * Instantiates a new sun entity.
	 *
	 * @param componentsGroup the components group
	 * @param the x position to place the sun entity at
	 */
	public SunEntity(Group componentsGroup, double x) {
		InputStream input = getClass().getResourceAsStream("/resources/sun.png");
		Image image = new Image(input, BowmanConstants.SUN_SPRITE_WIDTH, BowmanConstants.SUN_SPRITE_HEIGHT, false,
				true);
		this.sprite = new ImageView(image);

		reposition(x, BowmanConstants.SUN_SPRITE_Y_POSITION);

		componentsGroup.getChildren().add(this.sprite);
	}

	/**
	 * Sets the position of the sprite in the componentsGroup.
	 *
	 * @param the x position to place the entity
	 * @param the y position to place the entity
	 */
	private void reposition(double x, double y) {
		sprite.setTranslateX(x);
		sprite.setTranslateY(y);
	}
	
	/**
	 * Gets the max X position of the sprite.
	 *
	 * @return the max X
	 */
	public double getMaxX()
	{
		return sprite.getTranslateX() + BowmanConstants.SUN_SPRITE_WIDTH;
	}
	
	/**
	 * Gets the max Y position of the sprite.
	 *
	 * @return the max Y
	 */
	public double getMaxY()
	{
		return sprite.getTranslateY() + BowmanConstants.SUN_SPRITE_HEIGHT;
	}
}
