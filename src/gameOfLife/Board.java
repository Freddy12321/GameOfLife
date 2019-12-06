package gameOfLife;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Board extends JPanel{
	boolean[][] oldBoard, newBoard;
	//method used to update boolean[][]s
	public void getArrays(boolean[][] oldArray, boolean[][] newArray) {
		oldBoard=oldArray;
		newBoard=newArray;
	}
	//method used to print board with black circles for old live cells, blue circles for recently live cells. and red circles for freshly dead cells
	@Override public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int largestD;
		if(oldBoard.length>oldBoard[0].length) {
			largestD=oldBoard.length;
		}
		else {
			largestD=oldBoard[0].length;
		}
		int heightOffset=0;
		int widthOffset=0;
		if(oldBoard.length<largestD) {
			heightOffset=(600/largestD)*(largestD-oldBoard.length)/2;
		}
		if(oldBoard[0].length<largestD) {
			widthOffset=(600/largestD)*(largestD-oldBoard[0].length)/2;
		}
		g.setColor(Color.black);
		for(int i=1; i<oldBoard.length; i++) {
			g.drawLine(widthOffset, heightOffset+600/largestD*i, this.getWidth()-widthOffset, heightOffset+600/largestD*i);
		}
		for(int i=1; i<oldBoard[0].length; i++) {
			g.drawLine(widthOffset+600/largestD*i, heightOffset, widthOffset+600/largestD*i, this.getHeight()-heightOffset);
		}
		for(int i=0; i<oldBoard.length; i++) {
			for(int j=0; j<oldBoard[0].length; j++) {
				if(oldBoard[i][j]&&newBoard[i][j]) {
					g.setColor(Color.BLACK);
					if(600/largestD>20) {
						g.fillOval(widthOffset+600/largestD*j+600/largestD/10, heightOffset+600/largestD*i+600/largestD/10, 600/largestD/10*8, 600/largestD/10*8);
					}
					else {
						g.fillOval(widthOffset+600/largestD*j+1, heightOffset+600/largestD*i+1, 600/largestD-2, 600/largestD-2);
					}
				}
				else if(oldBoard[i][j]&&!newBoard[i][j]) {
					g.setColor(Color.RED);
					if(600/largestD>20) {
						g.fillOval(widthOffset+600/largestD*j+600/largestD/10, heightOffset+600/largestD*i+600/largestD/10, 600/largestD/10*8, 600/largestD/10*8);
					}
					else {
						g.fillOval(widthOffset+600/largestD*j+1, heightOffset+600/largestD*i+1, 600/largestD-2, 600/largestD-2);
					}
				}
				else if(!oldBoard[i][j]&&newBoard[i][j]) {
					g.setColor(Color.BLUE);
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
