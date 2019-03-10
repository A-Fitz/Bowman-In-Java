package application;

import data.Difficulty;

/**
 * Game settings used.
 */
public class Settings {
	
	/** The difficulty. */
	private static Difficulty difficulty;
	
	/** The gravity. */
	private static double gravity;
	
	/** If the sun is an obstacle. */
	private static boolean sunObstacle;
	
	/**
	 * Gets the difficulty.
	 *
	 * @return the difficulty
	 */
	public static Difficulty getDifficulty() {
		return difficulty;
	}
	
	/**
	 * Gets the gravity.
	 *
	 * @return the gravity
	 */
	public static double getGravity() {
		return gravity;
	}
	
	/**
	 * Checks if sun is an obstacle.
	 *
	 * @return true, if sun is an obstacle
	 */
	public static boolean isSunObstacle() {
		return sunObstacle;
	}

	/**
	 * Sets the difficulty.
	 *
	 * @param the new difficulty
	 */
	public static void setDifficulty(Difficulty difficulty) {
		Settings.difficulty = difficulty;
	}

	/**
	 * Sets the gravity.
	 *
	 * @param the new gravity
	 */
	public static void setGravity(double gravity) {
		Settings.gravity = gravity;
	}
	
	/**
	 * Sets the sun obstacle boolean.
	 *
	 * @param new sun obstacle boolean
	 */
	public static void setSunObstacle(boolean sunObstacle) {
		Settings.sunObstacle = sunObstacle;
	}
}
