package entities;

import java.io.InputStream;

import application.Settings;
import data.BowmanConstants;
import data.Colors;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

/**
 * The Class PlayerEntity.
 */
public class PlayerEntity extends Entity {
	
	/** The name of the player. */
	private final String name;
	
	/** The health of the player. */
	private float health;

	/** The color for the player. */
	private final Colors color;
	
	/** The player texture. */
	private final Circle texture;

	/**
	 * Instantiates a new player entity.
	 *
	 * @param componentsGroup the components group
	 * @param name the name
	 * @param color the color
	 */
	public PlayerEntity(Group componentsGroup, String name, Colors color) {
		this.name = name;
		this.color = color;
		this.health = 100.0f;

		InputStream input = getClass().getResourceAsStream("/resources/archer.png");
		Image image = new Image(input, BowmanConstants.PLAYER_SPRITE_WIDTH, BowmanConstants.PLAYER_SPRITE_HEIGHT, false,
				true);
		this.sprite = new ImageView(image);

		this.texture = new Circle();
		this.texture.setRadius(25);
		this.texture.setFill(Color.web(color.getHexColor()));

		componentsGroup.getChildren().addAll(this.texture, this.sprite);
	}

	/**
	 * Gets the color.
	 *
	 * @return the color
	 */
	public Colors getColor() {
		return color;
	}

	/**
	 * Gets the health.
	 *
	 * @return the health
	 */
	public float getHealth() {
		return health;
	}

	/**
	 * Gets the max X.
	 *
	 * @return the max X
	 */
	public double getMaxX() {
		return sprite.getTranslateX() + BowmanConstants.PLAYER_SPRITE_WIDTH;
	}

	/**
	 * Gets the max Y.
	 *
	 * @return the max Y
	 */
	public double getMaxY() {
		return sprite.getTranslateY() + BowmanConstants.PLAYER_SPRITE_HEIGHT;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Hit by arrow.
	 *
	 * @param hitYPos the hit Y pos
	 */
	public void hitByArrow(double hitYPos) {
		if (hitYPos < getMaxY() && hitYPos >= getMaxY() - 39) // legs
		{
			switch (Settings.getDifficulty()) {
			case HARD:
				this.health -= BowmanConstants.DAMAGE_HARD_LEGS;
				break;
			case NORMAL:
				this.health -= BowmanConstants.DAMAGE_NORMAL_LEGS;
				break;
			case EASY:
				this.health -= BowmanConstants.DAMAGE_EASY_LEGS;
				break;
			}
		} else if (hitYPos < getMaxY() - 39 && hitYPos >= getMaxY() - 71) // torso
		{
			switch (Settings.getDifficulty()) {
			case HARD:
				this.health -= BowmanConstants.DAMAGE_HARD_TORSO;
				break;
			case NORMAL:
				this.health -= BowmanConstants.DAMAGE_NORMAL_TORSO;
				break;
			case EASY:
				this.health -= BowmanConstants.DAMAGE_EASY_TORSO;
				break;
			}
		} else if (hitYPos < getMaxY() - 71) // head
		{
			switch (Settings.getDifficulty()) {
			case HARD:
				this.health -= BowmanConstants.DAMAGE_HARD_HEAD;
				break;
			case NORMAL:
				this.health -= BowmanConstants.DAMAGE_NORMAL_HEAD;
				break;
			case EASY:
				this.health -= BowmanConstants.DAMAGE_EASY_HEAD;
				break;
			}
		}
	}

	/**
	 * Checks if is dead.
	 *
	 * @return true, if health is <= 0
	 */
	public boolean isDead() {
      return health <= 0.0f;
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

		texture.setTranslateX(x + 29);
		texture.setTranslateY(sprite.getTranslateY() + 100);
	}

	
}
