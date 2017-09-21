public class DataDisc {
	private int x;
	private int y;
	private int xa = 0;
	private int ya = 0;
	private int direct;
	private int id;
	private Grid grid;
	private boolean shooty = false;

	public DataDisc(int xa, int ya, int x, int y, int id, Player p, Grid grid) {
		this.grid = grid;
		// start position is position of bike
		this.x = x;
		this.y = y;
		// color of disc
		this.id = id;
		// checks direction of bike
		if (xa < 0)
			this.direct = 1;
		if (ya < 0)
			this.direct = 2;
		if (xa > 0)
			this.direct = 3;
		if (ya > 0)
			this.direct = 4;
	}

	// these next two methods take a number from 1-4 and set the discs ya xa to
	// correspond to the direction 1-4 being respectively left up right down
	// arrow keys

	// shoot left of car
	public void Port() {
		if (direct == 1) {
			ya = 1;
			xa = 0;
		}
		if (direct == 2) {
			ya = 0;
			xa = -1;
		}
		if (direct == 3) {
			ya = -1;
			xa = 0;
		}
		if (direct == 4) {
			ya = 0;
			xa = 1;
		}
	}

	// Shoot right of car
	public void StarBoard() {
		if (direct == 1) {
			ya = -1;
			xa = 0;
		}
		if (direct == 2) {
			ya = 0;
			xa = 1;
		}
		if (direct == 3) {
			ya = 1;
			xa = 0;
		}
		if (direct == 4) {
			ya = 0;
			xa = -1;
		}
	}

	// To move disc
	public void move() {
		if ((x < 99) && (x > 0) && (y < 99) && (y > 0)) {
			if ((x > 0 && xa < 0) || (x < 99 && xa > 0))
				x = x + xa;
			if ((y > 0 && ya < 0) || (y < 99 && ya > 0))
				y = y + ya;

			// Draws the discs
			grid.discOnGrid(id + 3, x, y, direct);
		} else
			shooty = false;
	}
}