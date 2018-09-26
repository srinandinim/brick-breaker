import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import java.awt.event.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Rectangle;
public class brickBreaker extends JPanel implements KeyListener {

	public static final int innerBoardX = 500;
	public static final int innerBoardY = 500;
	public static final int initialLives = 3;

	Paddle paddle = new Paddle();
	List<Brick> bricks = new ArrayList <Brick>();
	Ball ball = new Ball();

	private int score;
	private int lives;
	private int highScore;
	boolean gameRunning = true;
	private BufferedImage newGame;
	private int game = 1;

	public brickBreaker (){
		setFocusable (true);
		JFrame frame = new JFrame ("Brick Breaker");
		frame.setSize (innerBoardX+200, innerBoardY);
		frame.add(this);
		frame.setVisible (true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		lives = initialLives;
		createBricks();
		addKeyListener (this);
		while (true){
			game=1;
			while (gameRunning == true && lives > 0){
				try {
					Thread.sleep(8);
				} catch (Exception e){}
				paddle.move ();
				ballBrick();
				move();
				if (bricks.size()==0 && game == 1){
					createBricks2();
					game++;
					paddle = new Paddle();
					paddle.setPaddleLocX(200);
					ball = new Ball();
				}
				else if (bricks.size()==0 && game !=1){
					gameRunning = false;
				}
				repaint();
			}
			System.out.print("");
		}
	}

	public void createBricks (){
		int baseX = 75;
		int baseY = 50;
		int color = 0;
		for (int j = 0; j < 60; j++){
			bricks.add(new Brick());
			if ((j)%12==0){
				baseY+=16;
				baseX=75;
				color++;
			}
			bricks.get(j).setBrickLocX(baseX);
			baseX+=30;
			bricks.get(j).setBrickLocY(baseY);
			bricks.get(j).setColor(color);
		}
	}

	public void createBricks2() {
		int baseX = 100;
		int baseY = 50;
		int color = 0;
		for (int i = 0; i < 27; i++){
			bricks.add(new Brick());
			bricks.get(i).setBrickLocX(baseX);
			bricks.get(i).setBrickLocY(baseY);
			bricks.get(i).setColor(color);
			baseX+=30;
			if ((i+1)%3==0){
				baseY+=16;
				baseX=100;
			}
			if ((i+1)%9==0){
				baseY+=32;
				color++;
			}
			int x = i+1;
			if (x%9 == 0 || x%3 == 0){
				color++;
				if (color > 2)
					color = 0;
			}
		}
		baseX = 300;
		baseY = 50;
		color = 0;
		for (int i = 27; i < 54; i++){
			bricks.add(new Brick());
			bricks.get(i).setBrickLocX(baseX);
			bricks.get(i).setBrickLocY(baseY);
			bricks.get(i).setColor(color);
			baseX+=30;
			if ((i+1)%3==0){
				baseY+=16;
				baseX=300;
			}
			if ((i+1)%9==0){
				baseY+=32;
			}
			int x = i+1;
			if (x%9 == 0 || x%3 == 0){
				color++;
				if (color > 2)
					color = 0;
			}
		}
	}

	public void move() {
		Rectangle ballRect = new Rectangle (ball.getBallLocX(), ball.getBallLocY(), Ball.ballSizeX, Ball.ballSizeY);
		Rectangle paddleRect = new Rectangle (paddle.getPaddleLocX(), paddle.getPaddleLocY(), paddle.getPaddleSizeX(), paddle.getPaddleSizeY());
		if (ballRect.intersects(paddleRect)){
			ball.setBallDirY(ball.getBallDirY()*-1);
		}
		ball.setBallLocX(ball.getBallLocX()+ball.getBallDirX());
		ball.setBallLocY(ball.getBallLocY()+ball.getBallDirY());
		if (ball.getBallLocX() <= 0)
			ball.setBallDirX(ball.getBallDirX()*-1);
		if (ball.getBallLocY() <= 0)
			ball.setBallDirY(ball.getBallDirY()*-1);
		if (ball.getBallLocX() >= 485)
			ball.setBallDirX(ball.getBallDirX()*-1);
		if (ball.getBallLocY() > 443) {
			lives--;
			if (lives <= 0){
				gameRunning = false;
				paddle.setPaddleLocX(200);
			}
			reset();
		}
	}

	public void reset(){
		paddle.setPaddleLocX(200);
		ball = new Ball();
	}

	public void compReset(){
		bricks = new ArrayList<Brick>();
		gameRunning = true;
		score = 0;
		paddle.setPaddleLocX(200);
		ball = new Ball();
		lives = initialLives;
		createBricks();
	}

	public void ballBrick (){
		for (int i = 0; i < bricks.size(); i++){
			Rectangle brickI = new Rectangle (bricks.get(i).getBrickLocX(), bricks.get(i).getBrickLocY(), bricks.get(i).brickSizeX, bricks.get(i).brickSizeY);
			Rectangle ballI = new Rectangle (ball.getBallLocX(), ball.getBallLocY(), ball.ballSizeX, ball.ballSizeY);
			if (brickI.intersects(ballI)){
				bricks.remove(i);
				score++;
				ball.setBallDirY(ball.ballDirY*-1);
			}
		}
	}

	public void keyReleased (KeyEvent e){
		paddle.keyReleased(e);
	}

	public void keyPressed (KeyEvent e){
		if (e.getKeyCode() == KeyEvent.VK_SPACE){
			compReset();
		}
		paddle.keyPressed(e);
	}

	public void keyTyped (KeyEvent e){}

	public void paintComponent (Graphics g){
		g.setColor (Color.black);
		g.fillRect (1, 1, innerBoardX, innerBoardY);
		paddle.paintComponent(g);
		ball.paintComponent(g);
		g.setColor(Color.BLACK);
		for (int i = 0; i < bricks.size(); i++){
			bricks.get(i).paintComponent(g);
		}
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(innerBoardX, 1, 200, 500);
		g.setColor(Color.BLACK);
		g.drawString("Score:", 550, 100);
		g.drawString (""+score, 650, 100);
		if (lives <= 0 || gameRunning == false){
			g.setColor (Color.WHITE);
			g.drawString ("Game Over!", 200,250);
			if (highScore < score){
				highScore = score;
			}
		}
		g.setColor(Color.BLACK);
		g.drawString("High Score:", 550, 150);
		g.drawString ("" + highScore, 650, 150);
		g.drawString("Lives:", 550, 200);
		g.drawString ("" + lives, 650, 200);
		g.drawString ("Press spacebar to reset", 540, 300);
	}

	public static void main (String [] args){
		brickBreaker game = new brickBreaker();
	}

}
