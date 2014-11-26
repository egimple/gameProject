package gameplay;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player {

	private String playerImage = "bomberman.png";

	private int dx;
	private int dy;
	private int x;
	private int y;
	private int posX;
	private int posY;
	private static int score;
	private int livesLeft;

	private Image image;
	Grid grid;
	private Bomb bomb;

	private long startTime;

	private Boolean isBombPlaced;

	public Player() {

	}

	public Player(Grid grid, Bomb bomb) {
		loadImage();
		this.setX(25);
		this.setY(25);
		this.posX = 1;
		this.posY = 1;
		this.grid = grid;
		this.bomb = bomb;
		this.grid.setContents(1, 1, Cell.PLAYER);
		setScore(0);
		this.isBombPlaced = false;
	}

	public void move() {

		if (dx != 0) {

			// Player dies if walking into enemy
			if (grid.getContents(posX + (dx / 25), posY) == Cell.ENEMY) {
				grid.setContents(posX, posY, Cell.EMPTY);
			}

			//
			if (grid.getContents(posX + (dx / 25), posY) == Cell.EMPTY
					|| grid.getContents(posX + (dx / 25), posY) == Cell.POWERUPS
					|| grid.getContents(posX + (dx / 25), posY) == Cell.EXITWAY) {

				if (grid.getContents(posX, posY) == Cell.PLAYERANDBOMB) {
					grid.setContents(posX, posY, Cell.BOMB);
		
				} 
				
				else if (grid.getContents(posX, posY) == Cell.PLAYERANDEXITWAY) {
					grid.setContents(posX, posY, Cell.EXITWAY);
				} 
				
				else if (grid.getContents(posX+(dx/25), posY) == Cell.EXITWAY) {
					grid.setContents(posX, posY, Cell.PLAYERANDEXITWAY);
					
				} 
							
				else {
					grid.setContents(posX, posY, Cell.EMPTY);
				}
				
				if(dx < 0){
					posX--;
				}
				else{
					posX++;
				}
				
				x += dx;
				grid.setContents(posX, posY, Cell.PLAYER);

			}
		}


		if (dy != 0) {
			
			// Player dies if walking into enemy
			if (grid.getContents(posX , posY + (dy / 25)) == Cell.ENEMY) {
				grid.setContents(posX, posY, Cell.EMPTY);
			}

			//
			if (grid.getContents(posX, posY + (dy / 25)) == Cell.EMPTY
					|| grid.getContents(posX, posY + (dy / 25)) == Cell.POWERUPS
					|| grid.getContents(posX, posY + (dy / 25)) == Cell.EXITWAY) {

				if (grid.getContents(posX, posY) == Cell.PLAYERANDBOMB) {
					grid.setContents(posX, posY, Cell.BOMB);
		
				} 
				
				else if (grid.getContents(posX, posY) == Cell.PLAYERANDEXITWAY) {
					grid.setContents(posX, posY, Cell.EXITWAY);
				} 
				
				else if (grid.getContents(posX+(dx/25), posY) == Cell.EXITWAY) {
					grid.setContents(posX, posY, Cell.PLAYERANDEXITWAY);
					
				} 
							
				else {
					grid.setContents(posX, posY, Cell.EMPTY);
				}
				
				if(dy < 0){
					posY--;
				}
				
				else{
					posY++;
				}
				y += dy;
				grid.setContents(posX, posY, Cell.PLAYER);

			}
		}
	

	}

	public Boolean getBombStatus() {
		return isBombPlaced;
	}

	public void setBombStatus(Boolean x) {
		isBombPlaced = x;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int xNew) {
		x = xNew;
	}

	public void setY(int yNew) {
		y = yNew;
	}

	private void loadImage() {
		ImageIcon ii = new ImageIcon(getClass().getResource(playerImage));
		image = ii.getImage();
	}

	public Image getImage() {
		return image;
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (dx != 0 || dy != 0) {
			return;
		}

		// Pausing
		if (key == KeyEvent.VK_SPACE) {
			if (GameState.getState() == State.RUNNING) {
				GameState.setState(State.PAUSE);
			} else {
				GameState.setState(State.RUNNING);
			}
			System.out.println("STATE: " + GameState.getState());
		}

		// Bomb Logic
		if (key == KeyEvent.VK_X) {
			if (GameState.getState() == State.RUNNING && isBombPlaced == false) {
				if (grid.getContents(posX, posY) != Cell.PLAYERANDBOMB) {

					grid.setContents(posX, posY, Cell.PLAYERANDBOMB);

					// THIS IS WHERE WE SET THE RANGE!!!!!!!!!!!!!!!!!!! SET IT
					// AS HIGH AS YOU WANT, FRIENDS

					isBombPlaced = true;
					bomb.setRange(1);
					bomb.setPosition(posX, posY);
					initializeTimer();
				}
			}

		}

		if (key == KeyEvent.VK_LEFT) {
			dx = -25;
		}

		else if (key == KeyEvent.VK_RIGHT) {
			dx = 25;
		}

		else if (key == KeyEvent.VK_UP) {
			dy = -25;
		}

		else if (key == KeyEvent.VK_DOWN) {
			dy = 25;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_X) {

		}
		if (key == KeyEvent.VK_ESCAPE) {

		}

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}

	public static int getScore() {
		return score;
	}

	public void initializeTimer() {
		startTime = System.currentTimeMillis();
	}

	public long getInitialTime() {
		return startTime;
	}

	public static void setScore(int score) {
		Player.score = score;
	}

	public int getLivesLeft() {
		return livesLeft;
	}

	public void setLivesLeft(int livesLeft) {
		this.livesLeft = livesLeft;
	}

	// public void setUsername(String name) {
	// this.username = name;
	// }

}
