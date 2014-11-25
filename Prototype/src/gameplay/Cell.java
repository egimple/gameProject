package gameplay;

import Astar.ClosestHeuristic;
import Astar.Mover;
import Astar.TileBasedMap;

public enum Cell {
	EMPTY, PLAYER, CONCRETE, BRICK, ENEMY, BOMB, PLAYERANDBOMB, EXPLODE, BOMBANDEXITWAY, EXITWAY, BRICKANDEXITWAY, PLAYERANDEXITWAY, POWERUPS, BRICKANDPOWERUPS, PLAYERONBRICK, PLAYERONBOMB;

}
