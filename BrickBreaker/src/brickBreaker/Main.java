package brickBreaker;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
//		create JFrame object & set its properties. First you have created the empty frame
		JFrame obj = new JFrame();
//		Create the object of gameplay class
		Gameplay gamePlay= new Gameplay();
//		Add the background size
		obj.setBounds(10, 10, 700, 600);
		obj.setTitle("Breakout Ball");
		obj.setResizable(false);
        obj.add(gamePlay);
        obj.setVisible(true);
		
	}

}
