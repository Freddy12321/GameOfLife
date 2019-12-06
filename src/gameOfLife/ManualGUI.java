package gameOfLife;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ManualGUI extends JFrame  implements ActionListener, MouseListener{
	boolean[][] array;
	boolean okPressed=false;
	ManualBoard board;
	JButton ok;
	//GUI used to manually generate boards, creates an interactable JPanel for the user to click on
	public ManualGUI(int h, int w) {
		array= new boolean[h][w];
		board= new ManualBoard(array);
		board.setBackground(Color.WHITE);
		board.addMouseListener(this);
		this.setBounds(200,200, 630, 700);
		this.setLayout(null);
		this.add(board);
		board.setBounds(10, 10, 600, 600);
		ok= new JButton("OK");
		ok.setSize(ok.getPreferredSize());
		ok.setLocation(300, 620);
		ok.addActionListener(this);
		this.add(ok);
		board.repaint();
		this.setVisible(true);
	}
	//method used to detect if the user has clicked on the ok button
	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource().equals(ok)) {
			okPressed=true;
			this.setVisible(false);
		}
	}
	//returns the boolean[][]
	public boolean[][] getArray(){
		return array;
	}
	//returns if the ok button has been pressed
	public boolean getOkPressed() {
		return okPressed;
	}
	//method used to convert mouse x and y to array index locations, then flips the boolean in that location
	//once this is done ManualBoard.repaint() is called to show the change
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		int mouseX=arg0.getX();
		int mouseY=arg0.getY();
		if(mouseX>=0&&mouseX<600&&mouseY>=0&&mouseY<600) {
			int largestD;
			if(array.length>array[0].length) {
				largestD=array.length;
			}
			else {
				largestD=array[0].length;
			}
			int heightOffset=0;
			int widthOffset=0;
			if(array.length<largestD) {
				heightOffset=(600/largestD)*(largestD-array.length)/2;
			}
			if(array[0].length<largestD) {
				widthOffset=(600/largestD)*(largestD-array[0].length)/2;
			}
			int row=(mouseY-widthOffset)/(600/largestD);
			int colum=(mouseX-widthOffset)/(600/largestD);
			System.out.println(row+" "+colum);
			array[row][colum]=!array[row][colum];
			board.updateBoard(array);
			board.repaint();
		}
	}
	//unneeded mouseEvents
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
