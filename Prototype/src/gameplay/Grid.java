package gameplay;

public class Grid {
	
//	private int gridSizeX;
//	private int gridSizeY;
//	private int dimension;
    private Cell[][] gridMap;
    
    
    public Grid() {
		
		//gridSizeX = Bomberman.getDimensionX();
		//gridSizeY = Bomberman.getDimensionY();
		//dimension = 25;
		//gridMap = new Cell[gridSizeX/dimension][gridSizeY/dimension];
		gridMap = new Cell[33][15];
		

	}
    
   
    
//	public void setGrid(int x, int y, Cell cell){
//		gridMap[x][y] = cell;
//	}
	
	public Cell[][] getGridMap(){
		for(int i = 0; i < 33; i++){
			for(int j = 0; j < 15; j++){
				gridMap[i][j] = Cell.EMPTY;
			}
		}
		return gridMap;
	}

	
	
}