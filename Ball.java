import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import java.io.File;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
public class Ball {

	public static final int ballSizeX = 15;
	public static final int ballSizeY = 15;

	private int ballLocX = 218;
	private int ballLocY = 433;
	public int ballDirX = -1;
	public int ballDirY = -2;

	public Ball() {
		Rectangle ball = new Rectangle (ballLocX, ballLocY, ballSizeX, ballSizeY);
	}

	public static int getBallSizeX() {
		return ballSizeX;
	}

	public static int getBallSizeY() {
		return ballSizeY;
	}

	public int getBallLocX() {
		return ballLocX;
	}

	public void setBallLocX(int ballLocX) {
		this.ballLocX = ballLocX;
	}

	public int getBallLocY() {
		return ballLocY;
	}

	public void setBallLocY(int ballLocY) {
		this.ballLocY = ballLocY;
	}

	public int getBallDirX() {
		return ballDirX;
	}

	public void setBallDirX(int ballDirX) {
		this.ballDirX = ballDirX;
	}

	public int getBallDirY() {
		return ballDirY;
	}

	public void setBallDirY(int ballDirY) {
		this.ballDirY = ballDirY;
	}

	public void paintComponent (Graphics g){
		g.setColor(Color.GREEN);
		g.fillOval(ballLocX, ballLocY, ballSizeX, ballSizeY);
	}
}