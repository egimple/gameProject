package gameplay;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;

import Astar.*;

public class Enemy implements Mover {

	private String Balloom = "Balloom.png";
	private String Oneal = "Oneal.png";
	private String Doll = "Doll.png";
	private String Minvo = "Minvo.png";
	private String Kondoria = "Kondoria.png";
	private String Ovapi = "Ovapi.png";
	private String Pass = "Pass.png";
	private String Pontan = "Pontan.png";

	private Image enemy1;
	private Image enemy2;
	private Image enemy3;
	private Image enemy4;
	private Image enemy5;
	private Image enemy6;
	private Image enemy7;
	private Image enemy8;

	private Grid grid;
	private Grid tempGrid;

	private Render render;
	private Level level;

	private static int enemyCount;

	private int nEnemy1;
	private int nEnemy2;
	private int nEnemy3;
	private int nEnemy4;
	private int nEnemy5;
	private int nEnemy6;
	private int nEnemy7;
	private int nEnemy8;

	private Path path;
	PathFinder finder;

	public Enemy(Grid grid, Render render, Level level) {
		this.level = level;
		enemyCount = 0;
		this.render = render;
		loadImage();
		this.grid = grid;
		this.tempGrid = new Grid();

		System.out.println("Current Level..: " + level.getLevel());

		nEnemy1 = level.getnEnemy1();
		System.out.println("Number of enemyType1..: " + level.getnEnemy1());

		nEnemy2 = level.getnEnemy2();
		System.out.println("Number of enemyType2..: " + level.getnEnemy2());

		nEnemy3 = level.getnEnemy3();
		System.out.println("Number of enemyType3..: " + level.getnEnemy3());

		nEnemy4 = level.getnEnemy4();
		System.out.println("Number of enemyType4..: " + level.getnEnemy4());

		nEnemy5 = level.getnEnemy5();
		System.out.println("Number of enemyType5..: " + level.getnEnemy5());

		nEnemy6 = level.getnEnemy6();
		System.out.println("Number of enemyType6..: " + level.getnEnemy6());

		nEnemy7 = level.getnEnemy7();
		System.out.println("Number of enemyType7..: " + level.getnEnemy7());

		nEnemy8 = level.getnEnemy8();
		System.out.println("Number of enemyType8..: " + level.getnEnemy8());

		validateEnemies();

	}

	private void validateEnemies() {

		if (nEnemy1 > 0) {
			placeEnemies(Cell.BALLOOM, nEnemy1);
		}

		if (nEnemy2 > 0) {
			placeEnemies(Cell.ONEAL, nEnemy2);
		}

		if (nEnemy3 > 0) {
			placeEnemies(Cell.DOLL, nEnemy3);
		}

		if (nEnemy4 > 0) {
			placeEnemies(Cell.MINVO, nEnemy4);
		}

		if (nEnemy5 > 0) {
			placeEnemies(Cell.KONDORIA, nEnemy5);
		}

		if (nEnemy6 > 0) {
			placeEnemies(Cell.OVAPI, nEnemy6);
		}

		if (nEnemy7 > 0) {
			placeEnemies(Cell.PASS, nEnemy7);
		}

		if (nEnemy8 > 0) {
			placeEnemies(Cell.PONTAN, nEnemy8);
		}

		// grid.setContents(1,11,Cell.ENEMY);
		// grid.setContents(2,11,Cell.ENEMY);

		System.out.println("Number of Enemies..: " + enemyCount);

	}

	private void placeEnemies(Cell enemyType, int numberToPlace) {

		int numberOfEnemies = 0;
		while (numberOfEnemies < numberToPlace) {
			int randX = randInt(4, 30);
			int randY = randInt(1, 12);

			if (grid.getContents(randX, randY) == Cell.EMPTY) {
				grid.setContents(randX, randY, enemyType);
				numberOfEnemies++;
			}
		}
	}

	public static int randInt(int min, int max) {

		Random rand = new Random();

		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	private void loadImage() {
		ImageIcon first = new ImageIcon(getClass().getResource(Balloom));
		enemy1 = first.getImage();

		ImageIcon second = new ImageIcon(getClass().getResource(Oneal));
		enemy2 = second.getImage();

		ImageIcon third = new ImageIcon(getClass().getResource(Doll));
		enemy3 = third.getImage();

		ImageIcon fourth = new ImageIcon(getClass().getResource(Minvo));
		enemy4 = fourth.getImage();

		ImageIcon fifth = new ImageIcon(getClass().getResource(Kondoria));
		enemy5 = fifth.getImage();

		ImageIcon sixth = new ImageIcon(getClass().getResource(Ovapi));
		enemy6 = sixth.getImage();

		ImageIcon seventh = new ImageIcon(getClass().getResource(Pass));
		enemy7 = seventh.getImage();

		ImageIcon eighth = new ImageIcon(getClass().getResource(Pontan));
		enemy8 = eighth.getImage();

	}

	public Image getImageBalloom() {
		return enemy1;
	}

	public Image getImageOneal() {
		return enemy2;
	}

	public Image getImageDoll() {
		return enemy3;
	}

	public Image getImageMinvo() {
		return enemy4;
	}

	public Image getImageKondoria() {
		return enemy5;
	}

	public Image getImageOvapi() {
		return enemy6;
	}

	public Image getImagePass() {
		return enemy7;
	}

	public Image getImagePontan() {
		return enemy8;
	}

	public static int getEnemyCount() {
		return enemyCount;
	}

	public void copyGrid() {
		for (int posX = 0; posX < Bomberman.WIDTH; posX++) {
			for (int posY = 0; posY < Bomberman.HEIGHT; posY++) {
				tempGrid.setContents(posX, posY, grid.getContents(posX, posY));
			}
		}
	}

	public void move(int targetX, int targetY) {
		copyGrid();

		for (int posX = 1; posX < Bomberman.WIDTH - 1; posX++) {
			for (int posY = 1; posY < Bomberman.HEIGHT - 1; posY++) {

				switch (tempGrid.getContents(posX, posY)) {
				case BALLOOM:
					continue;
				case ONEAL:
					continue;
				case DOLL:
					continue;
				case MINVO:
					continue;
				case KONDORIA:
					continue;
				case OVAPI:
					continue;
				case PASS:
					continue;
				case PONTAN:
					continue;
				default:
					break;
				}

			}
		}

	}

	public void aStarMovement(int targetX, int targetY, Cell cellType) {

		finder = new AStarPathFinder(grid, 3);

		// Create the needed enemy types, when we find an enemy on the grid we
		// call the appropriate move method.. Kappa

		copyGrid();

		for (int posX = 1; posX < Bomberman.WIDTH - 1; posX++) {
			for (int posY = 1; posY < Bomberman.HEIGHT - 1; posY++) {
				if (tempGrid.getContents(posX, posY) == cellType) {

					path = finder.findPath(this, posX, posY, targetX, targetY);

					if (path != null) {

						grid.setContents(posX, posY, Cell.EMPTY);
						// System.out.println("Player position: X: " + targetX +
						// " Y: " + targetY + " xTarget IS: " + path.getX(1) +
						// " yTarget IS: " + path.getY(1));
						
						// player killing
						if (grid.getContents(path.getX(1), path.getY(1)) == Cell.PLAYER) {
							grid.setContents(path.getX(1), path.getY(1),
									cellType);
							GameState.setState(State.PLAYERDEAD);
							render.setIsPlayerAlive(false);
						}

						grid.setContents(path.getX(1), path.getY(1), cellType);
						path = null;

						// ENEMYMOVE

					}

					else {
						// System.out.println("Path is null");
					}
				}
			}
		}
	}

	// public void move() {
	//
	// // for (int posX = 1; posX < Bomberman.WIDTH - 1; posX++) {
	// // for (int posY = 1; posY < Bomberman.HEIGHT - 1; posY++) {
	// // if (grid.getContents(posX, posY) == Cell.ENEMY) {
	// // int rand = randInt(1, 2);
	// // if (rand == 1) {
	// // if (grid.getContents(posX + enemyDirection, posY) != Cell.BRICK
	// // && grid.getContents(posX + enemyDirection, posY) != Cell.CONCRETE) {
	// // enemyDirection = 1;
	// // // System.out.println("X IS POSITIVE");
	// // } else {
	// // enemyDirection = -1;
	// // // System.out.println("X IS NEGATIVE");
	// // }
	// //
	// // if (grid.getContents(posX + enemyDirection, posY) == Cell.EMPTY
	// // || grid.getContents(posX + enemyDirection, posY) == Cell.PLAYER) {
	// // grid.setContents(posX, posY, Cell.EMPTY);
	// // grid.setContents(posX + enemyDirection, posY,
	// // Cell.ENEMY);
	// // }
	// //
	// // }
	// //
	// // else {
	// // if (grid.getContents(posX, posY + enemyDirection) != Cell.BRICK
	// // && grid.getContents(posX, posY + enemyDirection) != Cell.CONCRETE) {
	// //
	// // enemyDirection = 1;
	// // // System.out.println("Y IS POSITIVE");
	// //
	// // } else {
	// // enemyDirection = -1;
	// // // System.out.println("Y IS NEGATIVE");
	// //
	// // }
	// // if (grid.getContents(posX, posY + enemyDirection) == Cell.EMPTY
	// // || grid.getContents(posX, posY + enemyDirection) == Cell.PLAYER) {
	// // grid.setContents(posX, posY, Cell.EMPTY);
	// // grid.setContents(posX, posY + enemyDirection,
	// // Cell.ENEMY);
	// // }
	// // }
	// // }
	// // }
	// }
	// }
}
