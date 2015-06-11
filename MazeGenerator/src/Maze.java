import java.util.ArrayList;
import java.util.List;

public class Maze {

	// Walls
	private static final int NORTH = 8; // 1000
	private static final int EAST = 2; // 0010
	private static final int SOUTH = 1; // 0001
	private static final int WEST = 4; // 0100

	private int width;
	private int depth;
	private boolean debug;
	private int verticies;
	private int verticiesVisited;

	private Cell cells[][];

	/*
	 * W x H maze r = row, c = columns v = vertex = r*W+c Given v: r = v/W c =
	 * v%W
	 */

	public Maze(int width, int depth, boolean debug) {

		// Initialize variables:
		this.width = width;
		this.depth = depth;
		this.debug = debug;
		verticies = width * depth;
		verticiesVisited = 0;

		generateGrid(); // create grid of cells

		createAdjencyLists(); // Create adjacency list for each cell

	}

	private void generateGrid() {
		// 2D array; each element is a Cell
		cells = new Cell[depth][width];
		for (int r = 0; r < depth; r++) {
			for (int c = 0; c < width; c++) {
				System.out.println("row: " + r + " col: " + c);
				cells[r][c] = new Cell(r, c);
			}
		}
	}

	private void createAdjencyLists() {

		for (int r = 0; r < depth; r++) {
			for (int c = 0; c < width; c++) {
				System.out.println("row: " + r + " col: " + c);
				ArrayList<Cell> temp = new ArrayList<Cell>();
				// If cell is in first column, no adjacent left cell:
				if (c != 0)
					temp.add(cells[r][c - 1]);
				// If cell is in last column, no adjacent right cell:
				if (c != width - 1)
					temp.add(cells[r][c + 1]);
				// If cell is in top row, no adjacent cell above:
				if (r != 0)
					temp.add(cells[r - 1][c]);
				// If cell is in bottom row, no adjacent cell below:
				if (r != depth - 1)
					temp.add(cells[r + 1][c]);
				// Set this cell's adjacency list:
				cells[r][c].adjacentCells = temp;
			}
		}
	}

	private void explore(Cell parent, Cell adjacent, int wall) {

		if (verticiesVisited < verticies) {
			if (parent != null) {
				// Knockdown parent's wall
				parent.knockDownWall(wall);
				// Knockdown child's wall
				adjacent.knockDownWall(Integer.reverse(wall) >> 28);
				// as long as arraylist has elements, select one of them, else return
			}
		}

	}

	public void display() {

	}

	/**
	 * Cells represent a vertex in the graph.
	 * 
	 * @author Duy Huynh
	 * @version 06/03/2015
	 *
	 */
	public class Cell {

		private int walls;
		private Cell parent; // Useful for backtracking
		private int vertexNum; // Calculated: v = vertex = r*W+c
		private List<Cell> adjacentCells; // Neighboring cells to this one
		private boolean visited; // Record if cell has been visited during
									// pathfinding
		private int row;
		private int col;

		public Cell(int row, int col) {
			walls = 0xf; // 1111 (walls intact)
			this.parent = null;
			this.visited = false;
			this.adjacentCells = null;
			this.row = row;
			this.col = col;
		}

		public int getWalls() {
			return walls;
		}

		public void knockDownWall(int wall) {
			this.walls &= ~wall; // Knock down a wall in binary
		}

		public int getRow() {
			return vertexNum / width;
		}

		public int getCol() {
			return vertexNum % width;
		}

		public boolean getVisited() {
			return visited;
		}

		public void setVisited(boolean visited) {
			this.visited = visited;
		}

	}

}
