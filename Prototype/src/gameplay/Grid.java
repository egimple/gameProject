package gameplay;

import Astar.ClosestHeuristic;
import Astar.TileBasedMap;

public class Grid implements TileBasedMap {
	
//	private int gridSizeX;
//	private int gridSizeY;
//	private int dimension;
    
	
	public Cell[][] gridMap;
    
    
    public Grid() {
		
		//gridSizeX = Bomberman.getDimensionX();
		//gridSizeY = Bomberman.getDimensionY();
		//dimension = 25;
		//gridMap = new Cell[gridSizeX/dimension][gridSizeY/dimension];
		gridMap = new Cell[Bomberman.WIDTH][Bomberman.HEIGHT];
	}
    
   
	public Cell[][] initializeGridMap(){
		for(int i = 0; i < Bomberman.WIDTH; i++){
			for(int j = 0; j < Bomberman.HEIGHT; j++){
				gridMap[i][j] = Cell.EMPTY;
			}
		}
		return gridMap;
	}



	@Override
	public int getWidthInTiles() {
		return Bomberman.WIDTH;
	}



	@Override
	public int getHeightInTiles() {
		return Bomberman.HEIGHT;
	}

	/**
	 * Notification that the path finder visited a given tile. This is 
	 * used for debugging new heuristics.
	 * 
	 * @param x The x coordinate of the tile that was visited
	 * @param y The y coordinate of the tile that was visited
	 */

	@Override
	public void pathFinderVisited(int x, int y) {
		
	}
	
	

	@Override
	public boolean blocked(Cell cellType, int x, int y) {
		
		if(cellType == Cell.KONDORIA || cellType == Cell.OVAPI || cellType == Cell.PONTAN ||cellType == Cell.KONDORIAANDBRICK || cellType == Cell.OVAPIANDBRICK || cellType == Cell.PONTANANDBRICK){
			if (gridMap[x][y] == Cell.PONTANANDBRICK ||gridMap[x][y] == Cell.OVAPIANDBRICK || gridMap[x][y] == Cell.KONDORIAANDBRICK || gridMap[x][y] == Cell.BRICKANDPOWERUPS || gridMap[x][y] == Cell.BRICKANDEXITWAY || gridMap[x][y] == Cell.CONCRETE  || gridMap[x][y] == Cell.POWERUPS ||  gridMap[x][y] == Cell.ENEMY || gridMap[x][y] == Cell.BRICKANDPOWERUPS || gridMap[x][y] == Cell.BOMB || gridMap[x][y] == Cell.PLAYERANDBOMB || gridMap[x][y] == Cell.BALLOOM || gridMap[x][y] == Cell.ONEAL || gridMap[x][y] == Cell.DOLL || gridMap[x][y] == Cell.MINVO || gridMap[x][y] == Cell.KONDORIA || gridMap[x][y] == Cell.OVAPI  || gridMap[x][y] == Cell.PASS  || gridMap[x][y] == Cell.PONTAN){
				return true;
			}
			return false;
		}
		
		else if (gridMap[x][y] == Cell.PONTANANDBRICK ||gridMap[x][y] == Cell.OVAPIANDBRICK || gridMap[x][y] == Cell.KONDORIAANDBRICK || gridMap[x][y] == Cell.BRICKANDPOWERUPS || gridMap[x][y] == Cell.BRICKANDEXITWAY || gridMap[x][y] == Cell.CONCRETE || gridMap[x][y] == Cell.BRICK || gridMap[x][y] == Cell.POWERUPS ||  gridMap[x][y] == Cell.ENEMY || gridMap[x][y] == Cell.BRICKANDPOWERUPS || gridMap[x][y] == Cell.BOMB || gridMap[x][y] == Cell.PLAYERANDBOMB || gridMap[x][y] == Cell.BALLOOM || gridMap[x][y] == Cell.ONEAL || gridMap[x][y] == Cell.DOLL || gridMap[x][y] == Cell.MINVO || gridMap[x][y] == Cell.KONDORIA || gridMap[x][y] == Cell.OVAPI  || gridMap[x][y] == Cell.PASS  || gridMap[x][y] == Cell.PONTAN){
			return true;
		}

		return false;
		
		
	/*	if(gridMap[x][y] != Cell.EMPTY || gridMap[x][y] != Cell.PLAYER){
			System.out.println("true");
			return true;
		}*/
		
		
	}



	@Override
	public float getCost(Cell cellType, int sx, int sy, int tx, int ty) {
		ClosestHeuristic manhattan = new ClosestHeuristic();
		return manhattan.getCost(cellType, sx, sy, tx, ty);
		
	}
	
	public void setContents(int x, int y, Cell celltype){
		this.gridMap[x][y] = celltype;
	}
	
	public Cell getContents(int x, int y){
		return this.gridMap[x][y];
	}
	
	

	
	
}
