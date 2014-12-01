package gameplay;

import java.awt.Image;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Bomb implements Runnable,Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int range;
	private int numberOfBombs;
	private int currentRange;

	private int positionX;
	private int positionY;

	private String imageNameBomb = "bomb.png";
	private String imageNameBombAndBomberman = "bomb&Bomberman.jpg";
	private String imageNameExplosion = "explosion.png";

	private static int numberOfEnemiesKilled;

	List<Integer> scoreForEachEnemyKilledWithThisBomb;

	private Image imageBomb;
	private Image imageBombAndBomberman;
	private Image imageExplosion;

	private static final int BALLOOM_SCORE = 100;
	private static final int ONEAL_SCORE = 200;
	private static final int DOLL_SCORE = 400;
	private static final int MINVO_SCORE = 800;
	private static final int KONDORIA_SCORE = 1000;
	private static final int OVAPI_SCORE = 2000;
	private static final int PASS_SCORE = 4000;
	private static final int PONTAN_SCORE = 8000;

	private int pointsScoredWithThisBomb;
	private int totalGameScore;

	private Grid grid;

	private Enemy enemy;
	private Player player;

	private int bombNumber;
	ImageIcon firstImageIcon;
	ImageIcon secondImageIcon;
	ImageIcon thirdImageIcon;

	private static final int BOMB_TIMER_IN_MILLISECONDS = 2000;

	//private boolean detonatePressed;

	/*
	 * RANGE IS DEFAULTED TO 1 IN CONSTRUCTORS. TO SET RANGE USE SETTER ON BOMB
	 * OBJECT. USE THE SAME BOMB OBJECT SUPPLIED TO THE THREAD.
	 */

	// Constructor, Just for images

	/**
	 * Initializes all the values pertaining to bomb.
	 * @param grid The map on which bombs appear.
	 */
	public Bomb(Grid grid) {

		setTotalGameScore(0);
		scoreForEachEnemyKilledWithThisBomb = new ArrayList<Integer>();
		loadImage();
		this.range = 1;
		this.setBombs(1);
		this.grid = grid;
		//this.setDenotePressed(false);
		Bomb.numberOfEnemiesKilled = 0;

	}

	public Bomb(Grid grid, Enemy enemy, Player player) {
		
		this.player = player;
		this.enemy = enemy;
		setTotalGameScore(0);
		scoreForEachEnemyKilledWithThisBomb = new ArrayList<Integer>();
		loadImage();
		this.range = 1;
		this.setBombs(1);
		this.grid = grid;
		//this.setDenotePressed(false);
	}

	/**
	 * Loads the images related to bombs.
	 */
	
	private void loadImage() {
		firstImageIcon = new ImageIcon(getClass().getResource(
				imageNameBomb));
		

		secondImageIcon = new ImageIcon(getClass().getResource(
				imageNameBombAndBomberman));
		

		thirdImageIcon = new ImageIcon(getClass().getResource(
				imageNameExplosion));	
	}

	/**
	 * Gets the image for the bomb.
	 * 
	 * @return Image shown when bomb is in a tile by itself.
	 */
	public Image getImageBomb() {
		
		return firstImageIcon.getImage();
	}

	/**
	 * Gets the image shown when Bomberman and the bomb are in the same tile.
	 * 
	 * @return The image shown when Bomberman and the bomb are in the same tile.
	 */
	public Image getImageBombPlayer() {
		return secondImageIcon.getImage();
		
	}

	/**
	 * Gets the image shown when a bomb EXPLOSIONs.
	 * 
	 * @return The image shown when a bomb EXPLOSIONs.
	 */

	public Image getImageExplosion() {
		return thirdImageIcon.getImage();
		
	}

	public static int getNumberOfEnemiesKilled() {
		return numberOfEnemiesKilled;
	}
	
	/**
	 * 
	 */

	public void explode() {

		pointsScoredWithThisBomb = 0;
		currentRange = 0;

		while (currentRange <= range) {

			if (grid.getContents(positionX + currentRange, positionY) != Tile.CONCRETE) {

				if (grid.getContents(positionX + currentRange, positionY) == Tile.BRICKANDPOWERUPS) {
					grid.setContents(positionX + currentRange, positionY, Tile.POWERUPS);
				} else if (grid.getContents(positionX + currentRange, positionY) == Tile.BRICKANDEXITWAY) {
					grid.setContents(positionX + currentRange, positionY, Tile.EXITWAY);
				} else if (grid.getContents(positionX + currentRange, positionY) == Tile.EXITWAY) {
					grid.setContents(positionX + currentRange, positionY, Tile.EXITWAY);
					enemy.setIsExitwayBlownUp(true);
				} else if (grid.getContents(positionX + currentRange, positionY) == Tile.BALLOOM
						|| grid.getContents(positionX + currentRange, positionY) == Tile.ONEAL
						|| grid.getContents(positionX + currentRange, positionY) == Tile.DOLL
						|| grid.getContents(positionX + currentRange, positionY) == Tile.MINVO
						|| grid.getContents(positionX + currentRange, positionY) == Tile.KONDORIA
						|| grid.getContents(positionX + currentRange, positionY) == Tile.OVAPI
						|| grid.getContents(positionX + currentRange, positionY) == Tile.PASS
						|| grid.getContents(positionX + currentRange, positionY) == Tile.PONTAN) {

					if (grid.getContents(positionX + currentRange, positionY) == Tile.BALLOOM) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(BALLOOM_SCORE);
						grid.setContents(positionX + currentRange, positionY,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX + currentRange, positionY) == Tile.ONEAL) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(ONEAL_SCORE);
						grid.setContents(positionX + currentRange, positionY,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX + currentRange, positionY) == Tile.DOLL) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(DOLL_SCORE);
						grid.setContents(positionX + currentRange, positionY,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX + currentRange, positionY) == Tile.MINVO) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(MINVO_SCORE);
						grid.setContents(positionX + currentRange, positionY,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX + currentRange, positionY) == Tile.KONDORIA) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(KONDORIA_SCORE);
						grid.setContents(positionX + currentRange, positionY,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX + currentRange, positionY) == Tile.OVAPI) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(OVAPI_SCORE);
						grid.setContents(positionX + currentRange, positionY,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX + currentRange, positionY) == Tile.PASS) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(PASS_SCORE);
						grid.setContents(positionX + currentRange, positionY,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX + currentRange, positionY) == Tile.PONTAN) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(PONTAN_SCORE);
						grid.setContents(positionX + currentRange, positionY,
								Tile.EXPLOSION);
					}

				}
				// FLAME PASS POWERUP
				else if (grid.getContents(positionX + currentRange, positionY) == Tile.PLAYER) {
					if (PowerUps.getFlamepass() == true) {
						grid.setContents(positionX + currentRange, positionY, Tile.PLAYER);
					} else {
						grid.setContents(positionX + currentRange, positionY,
								Tile.EXPLOSION);
					}
				} else {

					grid.setContents(positionX + currentRange, positionY, Tile.EXPLOSION);
				}

				currentRange++;
			}

			else if (grid.getContents(positionX + currentRange, positionY) == Tile.CONCRETE) {
				// Come out of the loop, we don't want to EXPLOSION across the
				// concrete
				break;
			}
		}

		currentRange = 0;

		while (currentRange <= range) {

			if (grid.getContents(positionX - currentRange, positionY) != Tile.CONCRETE) {

				if (grid.getContents(positionX - currentRange, positionY) == Tile.BRICKANDPOWERUPS) {
					grid.setContents(positionX - currentRange, positionY, Tile.POWERUPS);

				} else if (grid.getContents(positionX - currentRange, positionY) == Tile.BRICKANDEXITWAY) {
					grid.setContents(positionX - currentRange, positionY, Tile.EXITWAY);

				} else if (grid.getContents(positionX - currentRange, positionY) == Tile.EXITWAY) {
					grid.setContents(positionX - currentRange, positionY, Tile.EXITWAY);
					enemy.setIsExitwayBlownUp(true);

				} else if (grid.getContents(positionX - currentRange, positionY) == Tile.BALLOOM
						|| grid.getContents(positionX - currentRange, positionY) == Tile.ONEAL
						|| grid.getContents(positionX - currentRange, positionY) == Tile.DOLL
						|| grid.getContents(positionX - currentRange, positionY) == Tile.MINVO
						|| grid.getContents(positionX - currentRange, positionY) == Tile.KONDORIA
						|| grid.getContents(positionX - currentRange, positionY) == Tile.OVAPI
						|| grid.getContents(positionX - currentRange, positionY) == Tile.PASS
						|| grid.getContents(positionX - currentRange, positionY) == Tile.PONTAN
						|| grid.getContents(positionX - currentRange, positionY) == Tile.KONDORIAANDBRICK
						|| grid.getContents(positionX - currentRange, positionY) == Tile.OVAPIANDBRICK
						|| grid.getContents(positionX - currentRange, positionY) == Tile.PONTANANDBRICK) {

					if (grid.getContents(positionX - currentRange, positionY) == Tile.BALLOOM) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(BALLOOM_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}
					
					if (grid.getContents(positionX - currentRange, positionY) == Tile.ONEAL) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(ONEAL_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}
					
					if (grid.getContents(positionX - currentRange, positionY) == Tile.DOLL) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(DOLL_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}
					
					if (grid.getContents(positionX - currentRange, positionY) == Tile.MINVO) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(MINVO_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}
					
					if (grid.getContents(positionX - currentRange, positionY) == Tile.KONDORIA) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(KONDORIA_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}
					
					if (grid.getContents(positionX - currentRange, positionY) == Tile.OVAPI) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(OVAPI_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}
					
					if (grid.getContents(positionX - currentRange, positionY) == Tile.PASS) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(PASS_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}
					
					if (grid.getContents(positionX - currentRange, positionY) == Tile.PONTAN) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(PONTAN_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}

					if (grid.getContents(positionX - currentRange, positionY) == Tile.KONDORIAANDBRICK) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(KONDORIA_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}
					
					if (grid.getContents(positionX - currentRange, positionY) == Tile.OVAPIANDBRICK) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(OVAPI_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}
					
					if (grid.getContents(positionX - currentRange, positionY) == Tile.PONTANANDBRICK) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(PONTAN_SCORE);
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}
					
				} else if (grid.getContents(positionX - currentRange, positionY) == Tile.PLAYER) {
					if (PowerUps.getFlamepass() == true) {
						grid.setContents(positionX - currentRange, positionY, Tile.PLAYER);
					} else {
						grid.setContents(positionX - currentRange, positionY,
								Tile.EXPLOSION);
					}
				} else {
					grid.setContents(positionX - currentRange, positionY, Tile.EXPLOSION);
				}

				currentRange++;
			}

			else if (grid.getContents(positionX - currentRange, positionY) == Tile.CONCRETE) {
				// Come out of the loop, we don't want to EXPLOSION across the
				// concrete
				break;
			}
		}

		currentRange = 0;

		while (currentRange <= range) {
			if (grid.getContents(positionX, positionY + currentRange) != Tile.CONCRETE) {

				if (grid.getContents(positionX, positionY + currentRange) == Tile.BRICKANDPOWERUPS) {
					grid.setContents(positionX, positionY + currentRange, Tile.POWERUPS);

				} else if (grid.getContents(positionX, positionY + currentRange) == Tile.BRICKANDEXITWAY) {
					grid.setContents(positionX, positionY + currentRange, Tile.EXITWAY);
				} else if (grid.getContents(positionX, positionY + currentRange) == Tile.EXITWAY) {
					grid.setContents(positionX, positionY + currentRange, Tile.EXITWAY);
					enemy.setIsExitwayBlownUp(true);

				} else if (grid.getContents(positionX, positionY + currentRange) == Tile.BALLOOM
						|| grid.getContents(positionX, positionY + currentRange) == Tile.ONEAL
						|| grid.getContents(positionX, positionY + currentRange) == Tile.DOLL
						|| grid.getContents(positionX, positionY + currentRange) == Tile.MINVO
						|| grid.getContents(positionX, positionY + currentRange) == Tile.KONDORIA
						|| grid.getContents(positionX, positionY + currentRange) == Tile.OVAPI
						|| grid.getContents(positionX, positionY + currentRange) == Tile.PASS
						|| grid.getContents(positionX, positionY + currentRange) == Tile.PONTAN) {

					if (grid.getContents(positionX, positionY + currentRange) == Tile.BALLOOM) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(BALLOOM_SCORE);
						grid.setContents(positionX, positionY + currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY + currentRange) == Tile.ONEAL) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(ONEAL_SCORE);
						grid.setContents(positionX, positionY + currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY + currentRange) == Tile.DOLL) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(DOLL_SCORE);
						grid.setContents(positionX, positionY + currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY + currentRange) == Tile.MINVO) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(MINVO_SCORE);
						grid.setContents(positionX, positionY + currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY + currentRange) == Tile.KONDORIA) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(KONDORIA_SCORE);
						grid.setContents(positionX, positionY + currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY + currentRange) == Tile.OVAPI) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(OVAPI_SCORE);
						grid.setContents(positionX, positionY + currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY + currentRange) == Tile.PASS) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(PASS_SCORE);
						grid.setContents(positionX, positionY + currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY + currentRange) == Tile.PONTAN) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(PONTAN_SCORE);
						grid.setContents(positionX, positionY + currentRange,
								Tile.EXPLOSION);
					}
				} else if (grid.getContents(positionX, positionY + currentRange) == Tile.PLAYER) {
					if (PowerUps.getFlamepass() == true) {
						grid.setContents(positionX, positionY + currentRange, Tile.PLAYER);
					} else {
						grid.setContents(positionX, positionY + currentRange,
								Tile.EXPLOSION);
					}
				} else {
					grid.setContents(positionX, positionY + currentRange, Tile.EXPLOSION);
				}

				currentRange++;
			}

			else if (grid.getContents(positionX, positionY + currentRange) == Tile.CONCRETE) {
				// Come out of the loop, we don't want to EXPLOSION across the
				// concrete
				break;
			}
		}

		currentRange = 0;

		while (currentRange <= range) {

			if (grid.getContents(positionX, positionY - currentRange) != Tile.CONCRETE) {

				if (grid.getContents(positionX, positionY - currentRange) == Tile.BRICKANDPOWERUPS) {
					grid.setContents(positionX, positionY - currentRange, Tile.POWERUPS);

				} else if (grid.getContents(positionX, positionY - currentRange) == Tile.BRICKANDEXITWAY) {
					grid.setContents(positionX, positionY - currentRange, Tile.EXITWAY);
				} else if (grid.getContents(positionX, positionY - currentRange) == Tile.EXITWAY) {
					grid.setContents(positionX, positionY - currentRange, Tile.EXITWAY);
					enemy.setIsExitwayBlownUp(true);

				}

				else if (grid.getContents(positionX, positionY - currentRange) == Tile.BALLOOM
						|| grid.getContents(positionX, positionY - currentRange) == Tile.ONEAL
						|| grid.getContents(positionX, positionY - currentRange) == Tile.DOLL
						|| grid.getContents(positionX, positionY - currentRange) == Tile.MINVO
						|| grid.getContents(positionX, positionY - currentRange) == Tile.KONDORIA
						|| grid.getContents(positionX, positionY - currentRange) == Tile.OVAPI
						|| grid.getContents(positionX, positionY - currentRange) == Tile.PASS
						|| grid.getContents(positionX, positionY - currentRange) == Tile.PONTAN) {

					if (grid.getContents(positionX, positionY - currentRange) == Tile.BALLOOM) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(BALLOOM_SCORE);
						grid.setContents(positionX, positionY - currentRange,
								Tile.EXPLOSION);

					}
					if (grid.getContents(positionX, positionY - currentRange) == Tile.ONEAL) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(ONEAL_SCORE);
						grid.setContents(positionX, positionY - currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY - currentRange) == Tile.DOLL) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(DOLL_SCORE);
						grid.setContents(positionX, positionY - currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY - currentRange) == Tile.MINVO) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(MINVO_SCORE);
						grid.setContents(positionX, positionY - currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY - currentRange) == Tile.KONDORIA) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(KONDORIA_SCORE);
						grid.setContents(positionX, positionY - currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY - currentRange) == Tile.OVAPI) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(OVAPI_SCORE);
						grid.setContents(positionX, positionY - currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY - currentRange) == Tile.PASS) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(PASS_SCORE);
						grid.setContents(positionX, positionY - currentRange,
								Tile.EXPLOSION);
					}
					if (grid.getContents(positionX, positionY - currentRange) == Tile.PONTAN) {
						numberOfEnemiesKilled++;
						scoreForEachEnemyKilledWithThisBomb.add(PONTAN_SCORE);
						grid.setContents(positionX, positionY - currentRange,
								Tile.EXPLOSION);
					}
				}

				else if (grid.getContents(positionX, positionY - currentRange) == Tile.PLAYER) {
					if (PowerUps.getFlamepass() == true) {
						grid.setContents(positionX, positionY - currentRange, Tile.PLAYER);
					} else {
						grid.setContents(positionX, positionY - currentRange,
								Tile.EXPLOSION);
					}
				}

				else {
					grid.setContents(positionX, positionY - currentRange, Tile.EXPLOSION);
				}

				currentRange++;
			}

			else if (grid.getContents(positionX, positionY - currentRange) == Tile.CONCRETE) {
				// Come out of the loop, we don't want to EXPLOSION across the
				// concrete
				break;
			}
		}

		currentRange = 0;

		if (scoreForEachEnemyKilledWithThisBomb.size() == 1) {
			pointsScoredWithThisBomb = scoreForEachEnemyKilledWithThisBomb
					.get(0);
		} else if (scoreForEachEnemyKilledWithThisBomb.size() == 2) {

			int temp = scoreForEachEnemyKilledWithThisBomb.get(0);

			if (temp < scoreForEachEnemyKilledWithThisBomb.get(1)) {
				pointsScoredWithThisBomb = temp * 2
						+ scoreForEachEnemyKilledWithThisBomb.get(1);
			} else {
				pointsScoredWithThisBomb = temp + 2
						* scoreForEachEnemyKilledWithThisBomb.get(1);
			}

		} else {
			for (int i = 0; i < scoreForEachEnemyKilledWithThisBomb.size(); i++) {
				int temp = (int) ((int) scoreForEachEnemyKilledWithThisBomb
						.get(i) * Math.pow(2, i));
				pointsScoredWithThisBomb += temp;
			}

		}

		setTotalGameScore(getTotalGameScore() + pointsScoredWithThisBomb);
		PlayerInfo.playerScore += getPointsScoredWithThisBomb();
		scoreForEachEnemyKilledWithThisBomb.clear();

	}

	@Override
	public void run() {

		long startTime;
		startTime = System.currentTimeMillis();

		if (player.hasDetonate()) {
			while (true) {

				if (player.getDetonatePressed()
						&& Player.getBombNumber() == bombNumber) {
					Player.setBombsOnGround(Player.getBombsOnGround() - 1);
					player.setDetonatePressed(false);
					Player.setBombNumber(Player.getBombNumber() - 1);
					Player.setCurrentBombCounter(Player.getCurrentBombCounter() + 1);
					this.explode();
					break;
				}
			}
		}

		else {
			while (true) {

				long currentTime = System.currentTimeMillis() - startTime;
				if (currentTime >= BOMB_TIMER_IN_MILLISECONDS) {
					Player.setBombsOnGround(Player.getBombsOnGround() - 1);
					this.explode();
					break;
				}

			}
		}

	}
	
	/**
	 * Sets the range of the bomb.
	 * @param range The range of the bomb.
	 */

	public void setRange(int range) {
		this.range = range;
	}
	
	/**
	 * Sets the bomb's position in x and y.
	 * @param positionX Bomb's x position.
	 * @param positionY Bomb's y position.
	 */

	public void setPosition(int positionX, int positionY) {
		this.positionX = positionX;
		this.positionY = positionY;

	}
	
	/**
	 * Gets the points scored with this one particular bomb.
	 * @return
	 */

	public int getPointsScoredWithThisBomb() {
		return pointsScoredWithThisBomb;
	}
	
	/**
	 * Gets points scored with one bomb.
	 * @param pointsScoredWithThisBomb Points scored with this one bomb.
	 */

	public void setPointsScoredWithThisBomb(int pointsScoredWithThisBomb) {
		this.pointsScoredWithThisBomb = pointsScoredWithThisBomb;
	}
	
	/**
	 * Gets the total score earned by the player in the current game.
	 * @return Score for the current game.
	 */

	public int getTotalGameScore() {
		return totalGameScore;
	}

	/**
	 * Sets the total score earned by the player in the current game.
	 * @param totalGameScore Score for the current game.
	 */
	public void setTotalGameScore(int totalGameScore) {
		this.totalGameScore = totalGameScore;
	}

	/**
	 * Gets the number of bombs available to the player.
	 * @return Number of bombs.
	 */
	public int getNumberOfBombs() {
		return numberOfBombs;
	}
	
	/**
	 * Sets the number of bombs available to the player.
	 * @param bombs Number of bombs available to the player.
	 */

	public void setBombs(int numberOfBombs) {
		this.numberOfBombs = numberOfBombs;
	}
	
	/**
	 * 
	 * @return
	 */

//	public boolean isDenotePressed() {
//		return denotePressed;
//	}
//
//	public void setDenotePressed(boolean denotePressed) {
//		this.denotePressed = denotePressed;
//	}

	public void setBombNumber(int bombNumber) {
		this.bombNumber = bombNumber;

	}

	public static void setNumberOfEnemiesKilled(int i) {
		Bomb.numberOfEnemiesKilled = i;

	}

}