package data;

/**
 * The Enum Colors used for player models.
 */
public enum Colors {

	RED("Red", "#FF0000"),
	ORANGE("Orange", "#FF7F00"),
	YELLOW("Yellow", "#FFFF00"),
	GREEN("Green", "#00FF00"),
	BLUE("Blue", "#0000FF"),
	INDIGO("Indigo", "#4B0082"),
	VIOLET("Violet", "#9400D3"),
	BLACK("Black", "#000000");

	/** The name. */
	private final String name;

	/** The hex color. */
	private final String hexColor;

	/**
	 * Instantiates a new colors.
	 *
	 * @param name  the name
	 * @param color the color (string)
	 */
	Colors(String name, String color) {
		this.name = name;
		this.hexColor = color;
	}

	/**
	 * Gets the hex color.
	 *
	 * @return the hex color
	 */
	public String getHexColor() {
		return this.hexColor;
	}

	/*
	 * (non-Javadoc)
	 * Returns the Color name
	 * 
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return this.name;
	}
}
