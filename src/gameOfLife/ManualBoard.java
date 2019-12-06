package gameOfLife;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class ManualBoard  extends JPanel{
	boolean[][] board;
	//method constructor, takes in boolean[][]
	public ManualBoard(boolean[][] b) {
		board=b;
	}
	//method used to update boolean[][]
	public void updateBoard(boolean[][] b) {
		board=b;
	}
	//method used to draw board 
	@Override public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int largestD;
		if(board.length>board[0].length) {
			largestD=board.length;
		}
		else {
			largestD=board[0].length;
		}
		int heightOffset=0;
		int widthOffset=0;
		if(board.length<largestD) {
			heightOffset=(600/largestD)*(largestD-board.length)/2;
		}
		if(board[0].length<largestD) {
			widthOffset=(600/largestD)*(largestD-board[0].length)/2;
		}
		g.setColor(Color.black);
		for(int i=1; i<board.length; i++) {
			g.drawLine(widthOffset, heightOffset+600/largestD*i, this.getWidth()-widthOffset, heightOffset+600/largestD*i);
		}
		for(int i=1; i<board[0].length; i++) {
			g.drawLine(widthOffset+600/largestD*i, heightOffset, widthOffset+600/largestD*i, this.getHeight()-heightOffset);
		}
		for(int i=0; i<board.length; i++) {
			for(int j=0; j<board[0].length; j++) {
				if(board[i][j]) {
					g.setColor(Color.BLACK);
					if(600/largestD>20) {
						g.fillOval(widthOffset+600/largestD*j+600/largestD/10, heightOffset+600/largestD*i+600/largestD/10, 600/largestD/10*8, 600/largestD/10*8);
					}
					else {
						g.fillOval(widthOffset+600/largestD*j+1, heightOffset+600/largestD*i+1, 600/largestD-2, 600/largestD-2);
					}
				}
			}
		} 
   }
}