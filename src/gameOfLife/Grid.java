package gameOfLife;

public class Grid {
	int tickCounter=0;
	boolean[][] oldGrid, newGrid;
	int expand;
	//method constructor, takes a starting boolean array and an integer for whether the play area should be expanded, 1 for yes and 0 for no
	public Grid(boolean[][] start, int e) {
		oldGrid=start;
		newGrid=start;
		expand=e;
	}
	//tick method, makes oldGrid equal newGrid, expands the play area if necessary, then generates a new newGrid using oldGrid
	public void tick() {
		oldGrid=newGrid;
		if(expand==1) {
			oldGrid=expandPlayArea();
		}
		newGrid=new boolean[oldGrid.length][oldGrid[0].length];
		for(int i=0; i<oldGrid.length; i++) {
			for(int j=0; j<oldGrid[i].length; j++) {
				newGrid[i][j]=checkNeighbors(i,j);
			}
		}
		tickCounter++;
	}
	//method to print grid in console for debugging
	private void printGrid() {
		System.out.println("\nTick Number: "+tickCounter+"\n");
		for(int i=0; i<oldGrid.length; i++) {
			for(int j=0; j<oldGrid[i].length; j++) {
				if(oldGrid[i][j]) {
					System.out.print("1");
				}
				else {
					System.out.print("0");
				}
			}
			System.out.println();
		}
	}
	//method used to find out the number of neighbours a cell has
	private boolean checkNeighbors(int i, int j) {
		int neighbors=0;
		if(i>0) {
			if(oldGrid[i-1][j]) {
				neighbors++;
			}
		}
		if(j>0) {
			if(oldGrid[i][j-1]) {
				neighbors++;
			}
		}
		if(oldGrid.length-1>i) {
			if(oldGrid[i+1][j]) {
				neighbors++;
			}
		}
		if(oldGrid[i].length-1>j) {
			if(oldGrid[i][j+1]) {
				neighbors++;
			}
		}
		if(i>0&&j>0) {
			if(oldGrid[i-1][j-1]) {
				neighbors++;
			}
		}
		if(oldGrid.length-1>i&&j>0) {
			if(oldGrid[i+1][j-1]) {
				neighbors++;
			}
		}
		if(i>0&&oldGrid[i].length-1>j) {
			if(oldGrid[i-1][j+1]) {
				neighbors++;
			}
		}
		if(oldGrid.length-1>i&&oldGrid[i].length-1>j) {
			if(oldGrid[i+1][j+1]) {
				neighbors++;
			}
		}
		//depending on the number of neighbours and the state of cell, true of false is retuned for alive or dead
		if(oldGrid[i][j]) {
			if(neighbors>3||neighbors<2) {
				return false;
			}
			else {
				return true;
			}
		}
		else {
			if(neighbors==3) {
				return true;
			}
			else {
				return false;
			}
		}
	}
	//method used to expand play area if there is a live cell of the play area
	public boolean[][] expandPlayArea(){
		boolean[][] expandedGrid;
		int expandY=oldGrid.length;
		int expandX=oldGrid[0].length;
		boolean expandUp=false;
		boolean expandDown=false;
		boolean expandLeft=false;
		boolean expandRight=false;
		for(int i=0; i<oldGrid[0].length; i++) {
			if(oldGrid[0][i]) {
				expandUp=true;
			}
			if(oldGrid[oldGrid.length-1][i]) {
				expandDown=true;
			}
			if(expandUp&&expandDown) {
				i=oldGrid.length;
			}
		}
		for(int i=0; i<oldGrid.length; i++) {
			if(oldGrid[i][0]) {
				expandLeft=true;
			}
			if(oldGrid[i][oldGrid[0].length-1]) {
				expandRight=true;
			}
			if(expandRight&&expandLeft) {
				i=oldGrid[0].length;
			}
		}
		if(expandRight||expandLeft||expandUp||expandDown) {
			int x=0;
			int y=0;
			if(expandUp) {
				y=1;
				expandY++;
			}
			if(expandDown) {
				expandY++;
			}
			if(expandLeft) {
				x=1;
				expandX++;
			}
			if(expandRight) {
				expandX++;
			}
			expandedGrid=new boolean[expandY][expandX];
			for(int i=0; i<oldGrid.length; i++) {
				for(int j=0; j<oldGrid[i].length; j++) {
					expandedGrid[i+y][j+x]=oldGrid[i][j];
				}
			}
			return expandedGrid;
		}
		else {
			return oldGrid;
		}
	}
	public boolean[][] getOldGrid(){
		return oldGrid;
	}
	public boolean[][] getNewGrid(){
		return newGrid;
	}
}
