package brickBreaker;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public class MapGenerator {
//       add 2d array
	public int map[][];
	public int brickWidth;
	public int brickHeight;
	public MapGenerator(int row,int col) {
		map = new int[row][col];
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				map[i][j] = 1;
//				1 will detect that the particular brick have not been intersected with ball
			}
		}
		
		brickWidth = 540/col;
		brickHeight = 150/row;
	}
	
	public void draw(Graphics2D g) {
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				if(map[i][j] > 0) {
//					then cretae particular breake inside that particular position
				g.setColor(Color.white);
				g.fillRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				
//				create a black border
				g.setStroke(new BasicStroke(3));
				g.setColor(Color.black);
				g.drawRect(j * brickWidth + 80, i * brickHeight + 50, brickWidth, brickHeight);
				
				}

			}
		}
	} 
//	intersection of ball with bricks
	public void setBrickValue(int value, int row, int col) {
		map[row][col] = value;
	}
}
