import java.awt.event.KeyEvent;

public class Player {
	private int plNum;
	private int wins;
	private int x;
	private int y;
	private int ya;
	private int xa;
	private int numOfShields = 0;
	private int shieldTimer;
	private int changeDirection = 1;
	private int discs = 4;
	private int mDisc = discs - 1;
	private int speedBuffTimer;
	private boolean shooty = false;
	private boolean isShieldActive = false;
	private boolean isAlive = true;
	private boolean speedBuff = false;
	private boolean comP;
	private PickUps powerUps;
	private Grid grid;
	private DataDisc[] list = new DataDisc[4];
	private static final int shieldTime = 60;
	private static final int speedBuffTime = 50;
	private GridCycles gc;

	public boolean getIsSpeedBoosted() {
		return speedBuff;
	}

	public int getShields() {
		return numOfShields;
	}

	public void resetWins() {
		wins = 0;
	}

	public int getDiscs() {
		return discs;
	}

	public int getWins() {
		return wins;
	}

	public void noDisk() {
		discs = 0;
	}

	public void comPFlip() {
		if (comP)
			comP = false;
		else
			comP = true;
	}

	public Player(int plNum, Grid grid, PickUps powerUps, GridCycles gc, boolean comP) {
		this.gc = gc;
		this.powerUps = powerUps;
		this.grid = grid;
		// Either player 1 or 2
		this.plNum = plNum;
		// The number of wins the player has
		this.wins = 0;
		// auto player on
		this.comP = comP;
		// Player starting position
		if (plNum == 1) {
			this.x = 19;
			this.y = 50;
		} else {
			this.x = 80;
			this.y = 50;
		}
	}

	private int isSafe(int a, int b) {
		if ((grid.getValueOf(a, b) == 1) || (grid.getValueOf(a, b) == 9) || (grid.getValueOf(a, b) == 8))
			return 1;
		else
			return 0;
	}

	// auto movement
	private void autoMate() {
		int di = 0;

		if (numOfShields > 0) {
			isShieldActive = true;
			numOfShields--;
			shieldTimer = shieldTime;
			System.out.println("1");
		}
		// horizontal
		if ((x < 99) && (y < 99)) {
			if ((x > 0) && (y > 0)) {

				int t = (int) (Math.random() * 500);
				if (t == 1) {
					if (xa != 0)
						di = 2;
					else
						di = 3;
				}

				if (isSafe(x + xa, y + ya) != 1) {
					if (xa != 0) {
						di = 2;
						if ((x >= 97) || (x <= 2))
							di = 4;
					}

					// vertical
					else {
						di = 3;
						if ((y >= 97) || (y <= 2))
							di = 1;
					}

					// will adjust direction if possible
				}
				switch (di) {
				case 1:
					if (x >= 50)
						di = 3;
					break;
				case 2:
					if (y >= 50)
						di = 4;
					break;
				case 3:
					if (x < 50)
						di = 1;
					break;
				case 4:
					if (y < 50)
						di = 2;
					break;
				default:
					break;
				}
			} else {
				if (xa != 0) {
					if (y <= 50)
						di = 4;
					else
						di = 2;
				} else {
					if (x <= 50)
						di = 3;
					else
						di = 1;
				}
			}
		} else {
			if (xa != 0) {
				if (y <= 50)
					di = 4;
				else
					di = 2;
			} else {
				di = 1;
			}

		}
		switch (di) {
		case 1:
			if (isSafe(x - 1, y) != 1)
				di = 3;
			break;
		case 2:
			if (isSafe(x, y - 1) != 1)
				di = 4;
			break;
		case 3:
			if (isSafe(x + 1, y) != 1)
				di = 1;
			break;
		case 4:
			if (isSafe(x, y + 1) != 1)
				di = 2;
			break;
		default:
			break;
		}
		switch (di) {
		case 1:
			xa = -1;
			ya = 0;
			break;
		case 2:
			xa = 0;
			ya = -1;
			break;
		case 3:
			xa = 1;
			ya = 0;
			break;
		case 4:
			xa = 0;
			ya = 1;
			break;
		default:
			break;
		}
		changeDirection--;
	}

	// Moves the player in the direction last pressed and moved the disks
	public void move() {
		// runs autoMate
		if (plNum == 2 && comP == true) {
			autoMate();
		}

		if (speedBuffTimer > 0) { // If speed buff is active, shorten the delay
									// time to move
			speedBuff = true;
			speedBuffTimer--;
		} else {
			speedBuff = false; // Else make the delay time normal
		}
		x = x + xa;
		y = y + ya;
		isAlive = grid.gridAlive(x, y, isShieldActive);
		if (shooty == true) {
			for (int i = mDisc; i >= discs; i--) {
				list[i].move();
				list[i].move();
			}
		}
		// Checks to see if powerup is picked up
		if (grid.getValueOf(x, y) == 8 || grid.getValueOf(x, y) == 9) {
			powerUps.pickUpItem(x, y, this);
		}
	}

	public void addShield() {
		numOfShields++;
	}

	public void initSpeedBuffTimer() {
		speedBuffTimer = speedBuffTime;
		gc.playWarpSpeed();
	}

	public boolean testAlive() {
		return grid.gridAlive(x, y, isShieldActive);
	}

	public boolean getIsAlive() {
		return isAlive;
	}

	public void draw() {
		if (isShieldActive) {
			if (shieldTimer > 0) {
				drawShield();
				shieldTimer--;
			} else {
				grid.removeColorFromBoard(plNum + 5);
				isShieldActive = false;
			}
		} else {
			grid.drawPlayer(plNum + 1, x, y);
		}
		changeDirection = 1;
	}

	private void drawShield() {
		grid.drawOnGrid(1, x - xa, y - ya);
		grid.drawOnGrid(plNum + 5, x, y);
	}

	public void won() {
		wins++;
	}

	public void resetPlayer() {
		list = new DataDisc[4];
		shooty = false;
		discs = 4;
		if (plNum == 1) {
			this.x = 19;
			this.y = 50;
			this.xa = 1;
			this.ya = 0;
		} else {
			this.x = 80;
			this.y = 50;
			this.xa = -1;
			this.ya = 0;
		}
		isShieldActive = false;
		numOfShields = 0;
		// moveDelay = 3;
		isAlive = true;
		speedBuffTimer = 0;
	}

	// shield active
	public void activateShield() {
		isShieldActive = true;
		shieldTimer = shieldTime;
		gc.playShieldHum();

	}

	// controls for the players
	public void keyPressed(KeyEvent e, int screen) {
		if (plNum == 1 || !comP) {
			if (e.getKeyCode() == KeyEvent.VK_W && ya != 1 && screen == 2 && changeDirection == 1) {
				xa = 0;
				ya = -1;
				changeDirection--;
			}
			if (e.getKeyCode() == KeyEvent.VK_A && xa != 1 && screen == 2 && changeDirection == 1) {
				xa = -1;
				ya = 0;
				changeDirection--;
			}
			if (e.getKeyCode() == KeyEvent.VK_S && ya != -1 && screen == 2 && changeDirection == 1) {
				xa = 0;
				ya = 1;
				changeDirection--;
			}
			if (e.getKeyCode() == KeyEvent.VK_D && xa != -1 && screen == 2 && changeDirection == 1) {
				xa = 1;
				ya = 0;
				changeDirection--;
			}
			if (e.getKeyCode() == KeyEvent.VK_UP && ya != 1 && screen == 2 && changeDirection == 1) {
				xa = 0;
				ya = -1;
				changeDirection--;
			}
			if (e.getKeyCode() == KeyEvent.VK_LEFT && xa != 1 && screen == 2 && changeDirection == 1) {
				xa = -1;
				ya = 0;
				changeDirection--;
			}
			if (e.getKeyCode() == KeyEvent.VK_DOWN && ya != -1 && screen == 2 && changeDirection == 1) {
				xa = 0;
				ya = 1;
				changeDirection--;
			}
			if (e.getKeyCode() == KeyEvent.VK_RIGHT && xa != -1 && screen == 2 && changeDirection == 1) {
				xa = 1;
				ya = 0;
				changeDirection--;
			}
			if (((e.getKeyCode() == KeyEvent.VK_Q) || (e.getKeyCode() == KeyEvent.VK_COMMA) && screen == 2)
					&& (discs > 0) && changeDirection == 1) {
				discs--;
				gc.playPew();
				list[discs] = new DataDisc(xa, ya, x, y, plNum, this, grid);
				list[discs].Port();
				shooty = true;
				changeDirection--;
			}
			if (((e.getKeyCode() == KeyEvent.VK_E) || (e.getKeyCode() == KeyEvent.VK_PERIOD) && screen == 2)
					&& (discs > 0) && changeDirection == 1) {
				discs--;
				gc.playPew();
				list[discs] = new DataDisc(xa, ya, x, y, plNum, this, grid);
				list[discs].StarBoard();
				shooty = true;
				changeDirection--;
			}
			if (e.getKeyCode() == KeyEvent.VK_SHIFT && screen == 2 && numOfShields != 0) {
				if (!isShieldActive) {
					activateShield();
					numOfShields--;
				}
			}
			if (e.getKeyCode() == KeyEvent.VK_M && screen == 2 && numOfShields != 0) {
				if (!isShieldActive) {
					activateShield();
					numOfShields--;
				}
			}
		}
	}
}
