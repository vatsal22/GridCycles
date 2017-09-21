import java.util.Random;

public class PickUps {
	private GridCycles gc;
	private Grid grid;
	private Random rand = new Random();
	private boolean isInitialized = false;

	// just some items on grid for use
	public PickUps(GridCycles gc, Grid grid) {
		this.gc = gc;
		this.grid = grid;
	}

	public void initPickUps() {

		for (int i = 0; i < 10; i++) {
			if (getRandBool(75)) {
				int tempX = rand.nextInt(99) + 1;
				int tempY = rand.nextInt(99) + 1;
				while (grid.getValueOf(tempX, tempY) * grid.getValueOf(tempX - 1, tempY)
						* grid.getValueOf(tempX, tempY - 1) * grid.getValueOf(tempX - 1, tempY - 1) != 1) {
					tempX = rand.nextInt(99) + 1;
					tempY = rand.nextInt(99) + 1;
				}
				int type = rand.nextInt(2) + 8;
				grid.drawOnGrid(type, tempX, tempY);
				grid.drawOnGrid(type, tempX - 1, tempY);
				grid.drawOnGrid(type, tempX, tempY - 1);
				grid.drawOnGrid(type, tempX - 1, tempY - 1);
			}
		}
		setInitStatus(true);
	}

	// returns either true or false based on chance value (e.g. if chance=5, the
	// chance of returning true is 5 percent)
	private boolean getRandBool(int chance) {
		return rand.nextInt(100) < chance;
	}

	public boolean getIsInitialized() {
		return isInitialized;
	}

	public void setInitStatus(boolean status) {
		isInitialized = status;
	}

	// this is just people obtaining the items
	public void pickUpItem(int x, int y, Player p) {
		int xa;
		int ya;
		if (grid.getValueOf(x, y) == 8) {
			if (!(p.getShields() >= 3))
				p.addShield();
		} else if (grid.getValueOf(x, y) == 9) {
			p.initSpeedBuffTimer();
		}
		if (grid.getValueOf(x, y - 1) != grid.getValueOf(x, y)) {
			grid.drawOnGrid(1, x, y + 1);
			ya = 1;
		} else {
			grid.drawOnGrid(1, x, y - 1);
			ya = -1;
		}
		if (grid.getValueOf(x - 1, y) != grid.getValueOf(x, y)) {
			grid.drawOnGrid(1, x + 1, y);
			xa = 1;
		} else {
			grid.drawOnGrid(1, x - 1, y);
			xa = -1;
		}
		grid.drawOnGrid(1, x + xa, y + ya);
		grid.drawOnGrid(1, x, y);
	}
}