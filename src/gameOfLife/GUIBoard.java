package gameOfLife;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class GUIBoard extends JFrame implements ActionListener{
	boolean startVal=false;
	boolean stepVal=false;
	boolean[][] oldBoard, newBoard;
	JButton start, step;
	Board canvas;
	//GUI class for displaying board during play, has a start and step button as well as a JPannel
	public GUIBoard(boolean[][] oB) {
		oldBoard=oB;
		newBoard=oB;
		this.setSize(630, 700);
		this.setLayout(null);
		canvas= new Board();
		this.getContentPane().add(canvas);
		canvas.setBackground(Color.WHITE);
		canvas.setSize(600, 600);
		canvas.setLocation(5, 5);
		canvas.setVisible(true);
		start= new JButton("Start");
		start.addActionListener(this);
		this.add(start);
		start.setSize(65,26);
		start.setLocation(180,610);
		step= new JButton("Step");
		step.addActionListener(this);
		this.add(step);
		step.setSize(65,26);
		step.setLocation(380, 610);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		drawBoard();
		this.setVisible(true);
	}
	//updates the JPannels boolean[][]s and then tells is to repaint using them
	public void drawBoard() {
		canvas.getArrays(oldBoard, newBoard);
		canvas.repaint();
	}
	//method used to update GUIBoards boolean[][]s
	public void updateBoards(boolean[][] oldArray, boolean[][] newArray){
		oldBoard=oldArray;
		newBoard=newArray;
	}
	//returns if stepVal is true
	public boolean getStep() {
		return stepVal;
	}
	//returns if startVal is true
	public boolean getStart() {
		return startVal;
	}
	//sets stepVal
	public void setStep(boolean set) {
		stepVal=set;
	}
	//actionListener for buttons
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(step)) {
			stepVal=true;
			startVal=false;
		}
		if(arg0.getSource().equals(start)) {
			startVal=!startVal;
		}
	}

}
