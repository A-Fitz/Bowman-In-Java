package data;

/**
 * Constants used in the game at different points
 */
public class BowmanConstants {
	/* Strings */
	public static final String GAME_TITLE = "Bowman In Java";
	public static final String SETTINGS_TITLE = "Settings";
	public static final String LAUNCH_MENU_TITLE = "Launch Game";

	/* Game Model Displaying */
	public static final int MENU_WIDTH = 330;
	public static final int MENU_HEIGHT = 450;
	public static final int INFOBAR_HEIGHT = 100;
	public static final int STAGE_WIDTH = 1280;
	public static final int STAGE_HEIGHT = 720;

	/* Entity Displaying */
	public static final int PLAYER_SPRITE_WIDTH = 58;
	public static final int PLAYER_SPRITE_HEIGHT = 100;
	public static final int ARROW_SPRITE_WIDTH = 40;
	public static final int ARROW_SPRITE_HEIGHT = 40;
	public static final int SUN_SPRITE_WIDTH = 100;
	public static final int SUN_SPRITE_HEIGHT = 100;
	public static final int SUN_SPRITE_Y_POSITION = 215;
	public static final int PLAYER_ONE_XPOSITION = 200;
	public static final int PLAYER_TWO_XPOSITION = 2800;
	public static final int PLAYER_XPOSITION_EASY_MARGIN = 200;
	public static final int PLAYER_XPOSITION_NORMAL_MARGIN = 600;
	public static final int PLAYER_XPOSITION_HARD_MARGIN = 1000;
	public static final int PLAYER_MARGIN = 40;

	/* Game Constants */
	public static final int MAX_ARROWS = 100;
	public static final double DEFAULT_GRAVITY = 9.8;

	/* Damage Constants */
	public static final int DAMAGE_EASY_HEAD = 30;
	public static final int DAMAGE_NORMAL_HEAD = 40;
	public static final int DAMAGE_HARD_HEAD = 50;
	public static final int DAMAGE_EASY_TORSO = 20;
	public static final int DAMAGE_NORMAL_TORSO = 25;
	public static final int DAMAGE_HARD_TORSO = 30;
	public static final int DAMAGE_EASY_LEGS = 10;
	public static final int DAMAGE_NORMAL_LEGS = 15;
	public static final int DAMAGE_HARD_LEGS = 20;
}
