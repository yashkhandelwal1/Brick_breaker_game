package brickBreaker;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.Timer;

import javax.swing.JPanel;

public class Gameplay extends JPanel implements KeyListener, ActionListener{
	//when the game starts, it shouldn't start by itself
	private boolean play= false;
	//	Initial score should be zero
	private int score = 0;

	private int totalBricks = 21;
	//	Setting the time of ball ,how fast it should move
	private Timer timer;
	//	Timer speed
	private int delay = 8;
	//	x-axis and y-axis of the slider and ball both
	//	Starting position of slider
	private int playerX = 310;
	//	Starting position of ball for x
	private int ballposX = 120;
	//	for y
	private int ballposY = 350;
	//	Set the direction of the ball
	private int ballXdir = -1;
	private int ballYdir = -2;
	
	private MapGenerator map;

	//	First add a constructor

	public Gameplay() {
		map = new MapGenerator(3, 7);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		timer = new Timer(delay, this);
		timer.start();
	}

	//	precedes the graphics object, draw different shapes like ball,slider and tiles
	public void paint(Graphics g) {
		//		 background
		g.setColor(Color.black);
		g.fillRect(1, 1, 692, 592);
		
//		drawing map
		map.draw((Graphics2D)g);

		//		borders
		g.setColor(Color.yellow);
		g.fillRect(0, 0, 3, 592);
		g.fillRect(0, 0, 692, 3);
		g.fillRect(691, 0, 3, 592);	
		//		do not add bottom border , so the game can end 
	
//		scores
		g.setColor(Color.white);
		g.setFont(new Font("serif", Font.BOLD,25));
	    g.drawString(""+score, 590, 30);	
		
		
		
		//		create the paddle

		g.setColor(Color.green);
		g.fillRect(playerX, 550, 100, 8);

		//		create the ball
		g.setColor(Color.yellow);
		g.fillOval(ballposX, ballposY, 20, 20);  //size of  ball 20 by 20

		
		if(totalBricks <= 0) {
			play =false;
			ballXdir =0;
			ballYdir =0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD,30));
		    g.drawString("You won:: ", 260, 300);	
		    
		    g.setFont(new Font("serif", Font.BOLD,20));
		    g.drawString("Press Enter to Restart", 230, 350);
		}
		
		if(ballposY > 570) {
			play =false;
			ballXdir =0;
			ballYdir =0;
			g.setColor(Color.RED);
			g.setFont(new Font("serif", Font.BOLD,30));
		    g.drawString("Game over, Scores: ", 190, 300);	
		    
		    g.setFont(new Font("serif", Font.BOLD,20));
		    g.drawString("Press Enter to Restart", 230, 350);
		}
		
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		timer.start();
		//	2.code for the ball
		//start and check if the variable play is true
		//	if the play is true i need to detect whether the ball is touching the top left right bottom or not
		if(play) {
//		add a code for detecting intersection between pedal and panel	
		if(new Rectangle(ballposX, ballposY, 20,20).intersects(new Rectangle(playerX, 550, 100,8))) {
			ballYdir = -ballYdir;
		}
		
		A: for(int i=0;i<map.map.length;i++) {
			for(int j=0;j<map.map[0].length;j++) {
				if(map.map[i][j] > 0) {
					int brickX = j*map.brickWidth + 80;
					int brickY = i* map.brickHeight + 50;
					int brickWidth = map.brickWidth;
					int brickHeight = map.brickHeight;
					
					Rectangle rect = new Rectangle(brickX ,brickY ,brickWidth , brickHeight);
					Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);
					Rectangle brickRect = rect;
					
					if(ballRect.intersects(brickRect)) {
						map.setBrickValue(0, i, j);
						totalBricks--;
						score += 5;
						
						if(ballposX + 19 <= brickRect.x || ballposX +1 >= brickRect.x + brickRect.width) {
							ballXdir = -ballXdir;
						}else {
							ballYdir = -ballYdir;
						}
						
						break A;
					}
				}
			}
		}
			
			ballposX += ballXdir;
			ballposY += ballYdir;
//			left border
			if(ballposX < 0) {
				ballXdir = -ballXdir;
			}
//			top
			if(ballposY < 0) {
				ballYdir = -ballYdir;
			}
//			right border
			if(ballposX > 670) {
				ballXdir = -ballXdir;
			}
		}


		repaint();
		//1.It will recall the paint method and draw each and everything again in order to redraw the panel and to show the change after moveright and moveleft

	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		//If the right arrow key is pressed, and set its conditions and parameters that it shouldn't go outside the panel and keep it to the border
		if(e.getKeyCode()== KeyEvent.VK_RIGHT) {
			if(playerX >= 600) {
				playerX = 600;
			}else {
				moveRight();
			}
		}
		if(e.getKeyCode()== KeyEvent.VK_LEFT) {
			if(playerX < 10) {
				playerX = 10;
			}else {
				moveLeft();
			}
		}
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(!play) {
				play = true;
				ballposX =120;
				ballposY =350;
				ballXdir = -1;
				ballYdir = -2;
				playerX = 310;
				score =0;
				totalBricks =21;
				map = new MapGenerator(3, 7);
				
				repaint();
				
			}
		}

	}

	public void moveRight() {
		//		slider will move 20px to the right
		play = true;
		playerX+=20;
	}

	public void moveLeft() {
		//		slider will move 20px to the left
		play = true;
		playerX-=20;
	}



}
