package gameOfLife;

public class Main {
	//main class responsible for calling  various GUI's and calling tick repeatedly once starting board is entered
	public static void main(String[] args) {
		StartUpGUI start= new StartUpGUI();
		//main waits until a button of startingGUI has been pressed
		while(!start.getRandomPressed()&&!start.getImportPressed()&&!start.getPresetPressed()) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int getExpand=start.getExpand();
		int delay=start.getDelay();
		boolean[][] startingGrid=new boolean[0][0];
		//if randomOK was pressed a random board is generated with the desired with, height, and live cell frequency
		if(start.getRandomPressed()) {
			int startingWidth=start.getArrayWidth();
			int startingHeight=start.getArrayHeight();
			double random=(double)(start.getRandom())/100;
			startingGrid= new boolean[startingHeight][startingWidth];
			for(int i=0; i<startingGrid.length; i++) {
				for(int j=0; j<startingGrid[0].length; j++) {
					if(Math.random()<random) {
						startingGrid[i][j]=true;
					}
					else {
						startingGrid[i][j]=false;
					}
				}
			}
		}
		//if presetOk is pressed then ManualGUI is used to allow the user to create a starting board
		else if(start.getPresetPressed()) {
			int startingWidth=start.getArrayWidth();
			int startingHeight=start.getArrayHeight();
			ManualGUI manual= new ManualGUI(startingHeight, startingWidth);
			while(!manual.getOkPressed()) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			startingGrid=manual.getArray();
		}
		//if importOk is pressed then ImportFile is used to import the starting board
		else if(start.getImportPressed()) {
			ImportFile imp= new ImportFile(start.getPath());
			startingGrid=imp.getArray();
		}
		//once the starting board is created a new Grid and GUIBoard are created with it
		Grid grid= new Grid(startingGrid,getExpand);
		GUIBoard board=new GUIBoard(startingGrid);
		//once start is pressed Grid ticks continuously and GUIBoard is updated every tick
		//alternatively tick can be pressed to make Grid tick once
		while(true) {
			if(board.getStep()||board.getStart()) {
				grid.tick();
				board.updateBoards(grid.getOldGrid(), grid.getNewGrid());
				board.drawBoard();
				if(board.getStep()) {
					board.setStep(false);
				}
			}
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
