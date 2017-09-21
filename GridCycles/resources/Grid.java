import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Grid {
	private int[][] gridPos;
	private static BufferedImage hud;

	public Grid() {
		// Creates 2D array 100x100
		int[][] gridPos = new int[100][100];

		// Filling in 2D Array with all 1 (Basic Grid)
		int y = 0;
		for (int x = 0; x < 100;) {
			gridPos[x][y] = 1;
			if (y >= 99) {
				x++;
				y = 0;
			} else
				y++;
		}
		this.gridPos = gridPos;
		// Image for the HUD for the players
		initHudImage();
	}

	public static void initHudImage() {
		try {
			hud = ImageIO.read(new File("hud.png"));
		} catch (IOException e) {
			System.out.println("Error: Image not found");
			hud = null;
		}
	}

	// Changes grid array position to player ID
	public void drawPlayer(int plID, int x, int y) {
		if (gridPos[x][y] == 1)
			gridPos[x][y] = plID;
	}

	// Sets a specific array space to a specific ID
	public void drawOnGrid(int id, int x, int y) {
		gridPos[x][y] = id;
	}

	public void discOnGrid(int id, int x, int y, int direct) {
		if (gridPos[x][y] != 1) {
			if (direct == 1 || direct == 3) {
				int k = x;
				int j = x;
				for (int i = 1; i < 8; i++) {
					if ((j < 100) && (k >= 0)) {
						if (gridPos[j][y] != 1)
							gridPos[j][y] = 1;
						else
							j = x;
						if (gridPos[k][y] != 1)
							gridPos[k][y] = 1;
						else
							k = x;
						j--;
						k++;
					}
				}
			} else if (direct == 2 || direct == 4) {
				int k = y;
				int j = y;
				for (int i = 1; i < 8; i++) {
					if ((j < 100) && (k >= 0)) {
						if (gridPos[x][j] != 1)
							gridPos[x][j] = 1;
						else
							j = y;
						if (gridPos[x][k] != 1)
							gridPos[x][k] = 1;
						else
							k = y;
						j--;
						k++;
					}
				}
			}
		}
		gridPos[x][y] = id;
	}

	// Checks if a player will die on a space they moved to
	public boolean gridAlive(int x, int y, boolean isShieldActive) {
		if (x < 0 || y < 0 || y > 99 || x > 99) {
			return false;
		} else if ((gridPos[x][y] == 2 || gridPos[x][y] == 3 || gridPos[x][y] == 4 || gridPos[x][y] == 5)
				&& !isShieldActive) {
			return false;
		} else {
			return true;
		}
	}

	public int getValueOf(int x, int y) {
		try {
			return gridPos[x][y];
		} catch (IndexOutOfBoundsException e) {
			return -1;
		}
	}

	public void removeColorFromBoard(int colValue) {
		int y = 0;
		for (int x = 0; x < 100;) {
			if (gridPos[x][y] == colValue)
				gridPos[x][y] = 1;

			if (y >= 99) {
				x++;
				y = 0;
			} else
				y++;
		}
	}

	// Reset the entire grid values to 1
	public void resetGrid() {
		int y = 0;
		for (int x = 0; x < 100;) {
			gridPos[x][y] = 1;

			if (y >= 99) {
				x++;
				y = 0;
			} else
				y++;
		}
	}

	// Prints the grid in all its glory
	public void printGrid(Graphics g2d) {
		g2d.drawImage(hud, 0, 0, null);

		g2d.setColor(Color.darkGray);
		g2d.fillRect(204, 0, 611, 611);
		// y is pixel location
		// x is pixel location
		// xA is array location
		// yA is array location
		int x = 0;
		int yA = 0;
		int y = 0;

		for (int xA = 0; xA < 100; xA++) {
			for (yA = 0; yA < 100; yA++) {
				if (gridPos[xA][yA] == 1)
					g2d.setColor(new Color(150, 150, 150)); // Default Colour
				else if (gridPos[xA][yA] == 2)
					g2d.setColor(new Color(0, 255, 255)); // Player 1
				else if (gridPos[xA][yA] == 3)
					g2d.setColor(new Color(255, 128, 0)); // Player 2
				else if (gridPos[xA][yA] == 4)
					g2d.setColor(new Color(51, 153, 255)); // Player 1 Data Disk
				else if (gridPos[xA][yA] == 5)
					g2d.setColor(new Color(255, 51, 51)); // Player 2 Data Disk
				else if (gridPos[xA][yA] == 6)
					g2d.setColor(new Color(0, 128, 255)); // Blue Shield
				else if (gridPos[xA][yA] == 7)
					g2d.setColor(new Color(255, 0, 0)); // Red Shield
				else if (gridPos[xA][yA] == 8)
					g2d.setColor(new Color(255, 255, 255)); // Shield Pickup
				else if (gridPos[xA][yA] == 9)
					g2d.setColor(new Color(255, 255, 0)); // Speed Boost
				else
					g2d.setColor(Color.GREEN); // If something is wrong
				g2d.fillRect(x + 210, y + 6, 5, 5);
				y = y + 6;
			}
			yA = 0;
			y = 0;
			x = x + 6;
		}
	}
}