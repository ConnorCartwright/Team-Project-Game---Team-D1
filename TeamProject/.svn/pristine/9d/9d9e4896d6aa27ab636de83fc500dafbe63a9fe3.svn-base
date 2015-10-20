package core;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Area;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

/**
 * Class used to model an Arena - the Arena is what the characters will fight in and what they will
 * move around in.
 * 
 * The class uses 3 files in order to generate a map, the map.bmp file, the map_x.bmp file (spawn
 * points) and the map.map file which contains the information for mapping pixel colours to specific
 * textures from the map.bmp file.
 * 
 * @author Team D1
 * @author Anh D Pham
 */
public class Arena {
	
	private static final int T1_COLOR = 0xff0000; // red spawn colour
	private static final int T2_COLOR = 0x0000ff; // blue spawn colour
	private static final int LIGHT_COLOR = 0xffff00; // light colour
	private static final int LIGHT_RANGE = 250;

	private String name; // the name of the map
	private Tile[][] tileMap; // represents each tile and their position, X and Y
	private List<Point> t1Spawns; // spawn points of team 1
	private List<Point> t2Spawns; // spawn points of team 2
	private List<Point> lightList;
	private Area lightMap;
	private Image light;

	/**
	 * Creating a new Arena
	 * 
	 * @param name
	 *            the name of the file that will be read in to generate the arena.
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public Arena(String name, boolean loadGraphics) throws FileNotFoundException, IOException {
		FileInputStream fileInputStream = new FileInputStream("resource/map/" + name + ".map");
		Hashtable<Integer, Tile> tileTable = new Hashtable<Integer, Tile>();
		Scanner sc = new Scanner(fileInputStream);
		this.name = sc.nextLine();

		// load number of tiles
		int n = sc.nextInt();

		// load tile information
		for (int i = 0; i < n; i++) {
			int color = sc.nextInt(16); // reads the hex image
			String filename = sc.next(); // reads the light tile image
			boolean walkable = sc.nextBoolean(); // reads the walkable bool
			boolean transparent = sc.nextBoolean(); // reads the transparent bool
			if (loadGraphics) {
				BufferedImage tileImage = ImageIO.read(new FileInputStream("resource/tile/" + filename)); // sets
																											// the
																											// light
																											// tile
																											// image
				// Image tileImageDark = ImageIO.read(new FileInputStream("resource/tile/" +
				// filename2)); // sets the dark tile image
				tileTable.put(color, new Tile(walkable, transparent, tileImage)); // and applies
																					// them all to
																					// create a new
																					// tile
			} else {
				tileTable.put(color, new Tile(walkable, transparent)); // and applies them all to
																		// create a new tile
			}
		}

		sc.close(); // after all tiles are read, close the scanner

		loadTileMap(name, tileTable); // load the tile map
		loadPositionMap(name, loadGraphics); // and load the position map
		if (loadGraphics)
			light = ImageIO.read(new FileInputStream("resource/light.png"));
	}

	/**
	 * This function is used to load the tile map.
	 * 
	 * @param name
	 *            the name of the map file from which we will load the tile map.
	 * @param tileTable
	 *            the tileTable maps hex colours to the crucial information.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void loadTileMap(String name, Hashtable<Integer, Tile> tileTable) throws IOException, FileNotFoundException {
		// load tile map
		BufferedImage image = ImageIO.read(new FileInputStream("resource/map/" + name + ".bmp"));
		tileMap = new Tile[image.getWidth()][image.getHeight()];

		for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
			for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
				Tile tile = tileTable.get(image.getRGB(xPixel, yPixel) & 0xFFFFFF);

				if (tile == null) {
					System.out.println("Invalid color " + String.format("%x", (image.getRGB(xPixel, yPixel) & 0xFFFFFF)) + " in tile " + xPixel
							+ ", " + yPixel);
					System.exit(-1);
				}

				tileMap[xPixel][yPixel] = tile;
			}
		}
	}

	/**
	 * This function is used to load the position map.
	 * 
	 * @param name
	 *            the name of the map file from which we will load the tile map.
	 * 
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	private void loadPositionMap(String name, boolean loadGraphics) throws IOException, FileNotFoundException {
		// load position map
		BufferedImage image = ImageIO.read(new FileInputStream("resource/map/" + name + "_x.bmp"));

		t1Spawns = new ArrayList<Point>();
		t2Spawns = new ArrayList<Point>();
		if (loadGraphics) {
			lightMap = new Area();
			lightList = new LinkedList<Point>();
		}

		for (int xPixel = 0; xPixel < image.getWidth(); xPixel++) {
			for (int yPixel = 0; yPixel < image.getHeight(); yPixel++) {
				int color = image.getRGB(xPixel, yPixel) & 0xFFFFFF;
				if (color == T1_COLOR) {
					t1Spawns.add(new Point(xPixel, yPixel));
				} else if (color == T2_COLOR) {
					t2Spawns.add(new Point(xPixel, yPixel));
				} else if (loadGraphics && color == LIGHT_COLOR) {
					int x = (int) ((xPixel + 0.5) * Tile.tileSize);
					int y = (int) ((yPixel + 0.5) * Tile.tileSize);
					LineOfSight los = new LineOfSight();
					Area light = new Area(los.generateLoS(x, y, LIGHT_RANGE, 360, 0, this));
					lightMap.add(light);
					lightList.add(new Point(x, y));
				}
			}
		}
	}

	/**
	 * Return the spawn list for a given team.
	 * @param team The team to get the spawn list for.
	 */
	public List<Point> getSpawn(int team) {
		return (team == 0 ? t1Spawns : t2Spawns);
	}

	/**
	 * Used to return a specific Tile.
	 * 
	 * @param x
	 *            the x coordinate of the tile
	 * @param y
	 *            the y coordinate of the tile
	 * 
	 * @return the tile located at x, y.
	 */
	public Tile get(int x, int y) {
		if (x >= getWidth() || y >= getHeight() || x < 0 || y < 0)
			return tileMap[0][0];
		else
			return tileMap[x][y];
	}

	/**
	 * Used to return a specific Tile.
	 * 
	 * @param p
	 *            The point with x and y coordinate of the tile.
	 * 
	 * @return the tile located at given point.
	 */
	public Tile get(Point p) {
		return get(p.x, p.y);
	}

	/**
	 * Returns the height of the tilemap.
	 * 
	 * @return the height of the tilemap.
	 */
	public int getHeight() {
		return tileMap[0].length;
	}

	/**
	 * Returns the width of the tilemap.
	 * 
	 * @return the width of the tilemap.
	 */
	public int getWidth() {
		return tileMap.length;
	}

	/**
	 * Returns the width of the tilemap in pixels.
	 * 
	 * @return the width of the tilemap in pixels.
	 */
	public int getWidthPixel() {
		return getWidth() * Tile.tileSize;
	}

	/**
	 * Returns the height of the tilemap in pixels.
	 * 
	 * @return the height of the tilemap in pixels.
	 */
	public int getHeightPixel() {
		return getHeight() * Tile.tileSize;
	}

	/**
	 * Render the dark area of the Arena, i.e. the area outside the characters line of sight.
	 * 
	 * @param g
	 * @param window
	 *            the whole game window.
	 */
	public void renderDark(Graphics g, Rectangle window) {
		int tileSize = Tile.tileSize;
		int x1 = Math.max(0, (int) (window.getX() / tileSize));
		int y1 = Math.max(0, (int) (window.getY() / tileSize));
		int x2 = Math.min(getWidth() - 1, x1 + (int) (window.getWidth() / tileSize) + 1);
		int y2 = Math.min(getHeight() - 1, y1 + (int) (window.getHeight() / tileSize) + 1);
		for (int x = x1; x <= x2; x++) {
			for (int y = y1; y <= y2; y++) {
				Image image = get(x, y).getImageDark();
				g.drawImage(image, x * tileSize, y * tileSize, tileSize, tileSize, null);
			}
		}
	}

	/**
	 * Render the Arena.
	 * 
	 * @param g
	 * @param window
	 *            the whole game window.
	 */
	public void render(Graphics g, Rectangle window) {
		int tileSize = Tile.tileSize;
		int x1 = Math.max(0, (int) (window.getX() / tileSize));
		int y1 = Math.max(0, (int) (window.getY() / tileSize));
		int x2 = Math.min(getWidth() - 1, x1 + (int) (window.getWidth() / tileSize) + 1);
		int y2 = Math.min(getHeight() - 1, y1 + (int) (window.getHeight() / tileSize) + 1);
		for (int x = x1; x <= x2; x++) {
			for (int y = y1; y <= y2; y++) {
				Image image = get(x, y).getImage();
				g.drawImage(image, x * tileSize, y * tileSize, tileSize, tileSize, null);
			}
		}
		Shape currentClip = g.getClip();
		Area tempClip = new Area(currentClip);
		tempClip.intersect(lightMap);
		g.setClip(tempClip);

		for (int x = x1; x <= x2; x++) {
			for (int y = y1; y <= y2; y++) {
				Image image = get(x, y).getImageLight();
				g.drawImage(image, x * tileSize, y * tileSize, tileSize, tileSize, null);
			}
		}
		for (Point l : lightList) {
			int x0 = l.x - LIGHT_RANGE;
			int y0 = l.y - LIGHT_RANGE;
			int d = LIGHT_RANGE * 2;
			if (window.intersects(x0, y0, d, d)) {
				g.drawImage(light, x0, y0, d, d, null);
			}
		}
		g.setClip(currentClip);
	}

	/**
	 * Returns the name of the arena.
	 * 
	 * @return the name of the arena.
	 */
	public String getName() {
		return name;
	}
	
}
