/*
 * Duy Huynh
 * TCSS 342 - Spring '15
 * Assignment 5 - Maze Generator
 * Maze.java
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generates n x m maze with solution.
 * 
 * @author Duy Huynh
 * @version 01 June 2015
 *
 */
public class Maze {

    /** Width of maze. */
    private int width;

    /** Depth of maze. */
    private int depth;

    /** Whether to show maze debugging. */
    private boolean debug;

    /** Random generator for choosing adjacent cells. */
    private Random random;

    /** Matrix containing vertices (cells) of maze. */
    private Cell cells[][];

    /** Matrix containing debug display data. */
    private char[][] debugDisplay;

    /**
     * Construct n x m maze with solution.
     * 
     * @param width Width of maze.
     * @param depth Depth of maze.
     * @param debug Whether or not maze debugging is shown.
     */
    public Maze(int width, int depth, boolean debug) {

        // Initialize variables:
        random = new Random();
        this.width = width;
        this.depth = depth;
        this.debug = debug;

        // Setup display grid
        generateDisplayGrid();

        // Create grid of cells
        generateGrid();

        // Create adjacency list for each cell
        createAdjacencyLists();

        // Seed the recursive depth-first-search with top-left cell
        explore(cells[0][0]);

        // If debugging was turned on, now turn it off:
        this.debug = false;

    }

    /** Generate the matrix that holds display data for the console. */
    private void generateDisplayGrid() {

        // 2D char array for displaying maze in console:
        int displayDepth = depth * 2 + 1;
        int displayWidth = width * 2 + 1;
        debugDisplay = new char[depth * 2 + 1][width * 2 + 1];
        for (int r = 0; r < displayDepth; r++) {
            for (int c = 0; c < displayWidth; c++) {
                debugDisplay[r][c] = 'X';
            }
        }
        debugDisplay[0][1] = ' '; // Create entrance
        debugDisplay[displayDepth - 1][displayWidth - 2] = ' '; // Create exit
    }

    /** Generate the matrix that holds a vertex in each cell. */
    private void generateGrid() {

        // 2D array; each element is a vertex of the maze
        cells = new Cell[depth][width];
        for (int r = 0; r < depth; r++) {
            for (int c = 0; c < width; c++) {
                cells[r][c] = new Cell(r, c);
                debugDisplay[r * 2 + 1][c * 2 + 1] = ' ';
            }
        }
    }

    /** Create a list of adjacent vertices for each vertex. */
    private void createAdjacencyLists() {

        // For each cell in the matrix, create an adjacency list.
        for (int r = 0; r < depth; r++) {
            for (int c = 0; c < width; c++) {
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

    /**
     * Explore adjacent, unvisited cells, recursively until all have been explored.
     * 
     * @param curCell The current vertex/cell in the maze.
     */
    private void explore(Cell curCell) {

        // Mark this cell as visited
        curCell.visited = true;
        debugDisplay[curCell.row * 2 + 1][curCell.col * 2 + 1] = 'V';

        // Display debugging in console:
        if (debug) display();

        // Retrieve list of unvisited adjacent cells for the current cell
        ArrayList<Cell> unvisitedCells = new ArrayList<Cell>();
        for (Cell c : cells[curCell.row][curCell.col].adjacentCells) {
            if (!c.visited) unvisitedCells.add(c);
        }

        // If unvisited adjacent cells exist, randomly choose one and explore it
        // Also knock-down the walls between current cell and the chosen cell to explore
        if (unvisitedCells.isEmpty()) { // no more unvisited adjacent cells
            if (curCell.parent != null) explore(curCell.parent);// Backtrack
        } else {
            // Randomly choose an unvisited cell to explore:
            int cellToExplore = random.nextInt(unvisitedCells.size());
            Cell temp = unvisitedCells.get(cellToExplore);
            temp.parent = curCell; // set parent cell so we can backtrack later

            // Knock-down walls before exploring next cell:
            if (curCell.row < temp.row) {
                curCell.south = temp.north = false;
                debugDisplay[curCell.row * 2 + 2][curCell.col * 2 + 1] = ' ';
            } else if (curCell.row > temp.row) {
                curCell.north = temp.south = false;
                debugDisplay[curCell.row * 2][curCell.col * 2 + 1] = ' ';
            } else if (curCell.col < temp.col) {
                curCell.east = temp.west = false;
                debugDisplay[curCell.row * 2 + 1][curCell.col * 2 + 2] = ' ';
            } else if (curCell.col > temp.col) {
                curCell.west = temp.east = false;
                debugDisplay[curCell.row * 2 + 1][curCell.col * 2] = ' ';
            }

            // After knocking down walls, go ahead and move to next cell
            explore(temp);
        }
    }

    /** Clear debugging matrix of path-finding or solution indicators. */
    private void clearIndicators() {

        // Clear all path-finding characters from debug matrix:
        for (int r = 0; r < depth; r++) {
            for (int c = 0; c < width; c++) {
                debugDisplay[r * 2 + 1][c * 2 + 1] = ' ';
            }
        }
    }

    /** Indicate solution path in debugging matrix. */
    private void loadSolution() {

        // Start at the end and backtrack through the parents, marking the solution path
        Cell solution = cells[depth - 1][width - 1];
        while (solution != null) {
            debugDisplay[solution.row * 2 + 1][solution.col * 2 + 1] = '+';
            solution = solution.parent;
        }

    }

    /** Takes the debug data from debugging matrix. */
    public void display() {

        // Clear path-finding indicators before displaying maze
        if(!debug){
            clearIndicators();
            loadSolution();
        }
        
        // Go through each cell of the debugging matrix and print data to console
        for (int r = 0; r < depth * 2 + 1; r++) {
            for (int j = 0; j < width * 2 + 1; j++) {
                System.out.print(debugDisplay[r][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Cells represent a vertex in the graph.
     * 
     * @author Duy Huynh
     * @version 06/03/2015
     *
     */
    private class Cell {

        /** This cell's parent in traversing the maze.*/
        private Cell parent; // Useful for backtracking
        
        /** List of this cell's adjacent vertices.*/
        private List<Cell> adjacentCells; // Neighboring cells to this one
        
        /** Row and column indices.*/
        private int row, col;
        
        /** Whether the walls are open or not.*/
        private boolean visited, north, south, east, west;

        /** Create a cell (vertex) belonging to the maze.
         * @param row Row index of cell.
         * @param col Column index of cell.
         */
        private Cell(int row, int col) {
            this.parent = null;
            this.adjacentCells = null;
            this.row = row;
            this.col = col;
            visited = false;
            north = south = east = west = true;
        }
    }
}
