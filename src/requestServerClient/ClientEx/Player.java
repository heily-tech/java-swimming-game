package ClientEx;

import java.awt.Image;

public class Player {
	private String name;
	private int x, y;
	private Image stroke;
	
	public Player(int x, int y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.stroke = null;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getStroke() {
		return stroke;
	}

	public void setStroke(Image stroke) {
		this.stroke = stroke;
	}
}
