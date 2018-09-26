import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import java.io.File;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
public class Paddle {

	public static final int paddleSizeX = 50;
	public static final int paddleSizeY = 7;
	public static final int paddleLocY = 450;
	private static int paddleLocX = 200;

	private boolean right;
	private boolean left;

	public Paddle() {
		Rectangle paddle = new Rectangle (paddleLocX, paddleLocY, paddleSizeX, paddleSizeY);
	}

	public static int getPaddleLocX() {
		return paddleLocX;
	}

	public static void setPaddleLocX(int paddleLocX) {
		Paddle.paddleLocX = paddleLocX;
	}

	public static int getPaddleSizeX() {
		return paddleSizeX;
	}

	public static int getPaddleSizeY() {
		return paddleSizeY;
	}

	public static int getPaddleLocY() {
		return paddleLocY;
	}

	public void keyReleased (KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			right = false;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			left = false;
	}

	public void keyPressed (KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			right = true;
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			left = true;
	}

	public void move(){
		if (right == true && paddleLocX < (brickBreaker.innerBoardX-paddleSizeX))
			paddleLocX+=4;
		else if (left == true && paddleLocX > 6)
			paddleLocX-=4;
	}

	public void paintComponent (Graphics g){
		g.setColor (Color.WHITE);
		g.fillRect (paddleLocX, paddleLocY, paddleSizeX, paddleSizeY);
	}

}