import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import java.io.File;
import java.awt.Rectangle;
public class Brick {
	
	public static final int brickSizeX = 28;
	public static final int brickSizeY = 14;
	
	private int brickLocX;
	private int brickLocY;
	
	int color;
	Color value;
	
	public Brick () {
		Rectangle brick = new Rectangle (100, 100, brickSizeX, brickSizeY);
	}
	
	public void setColor (int color){
		this.color = color;
		if (color == 1){
			value = Color.WHITE;
		}
		else if (color == 2){
			value = Color.PINK;
		}
		else if (color == 3){
			value = Color.RED;
		}
		else if (color == 4){
			value = Color.ORANGE;
		}
		else
			value = Color.YELLOW;
	}
	
	public int getBrickLocX() {
		return brickLocX;
	}

	public void setBrickLocX(int brickLocX) {
		this.brickLocX = brickLocX;
	}

	public int getBrickLocY() {
		return brickLocY;
	}

	public void setBrickLocY(int brickLocY) {
		this.brickLocY = brickLocY;
	}

	public void paintComponent (Graphics g){
		g.setColor (value);
		g.fillRect (brickLocX,brickLocY, brickSizeX, brickSizeY);
	}
	
}