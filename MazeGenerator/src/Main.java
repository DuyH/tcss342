/*
 * Duy Huynh
 * TCSS 342 - Spring '15
 * Assignment 5 - Maze Generator
 * Main.java
 */

/**
 * Main that creates mazes of various sizes.
 * 
 * @author Duy Huynh
 * @version 01 June 2015
 *
 */
public class Main {

    /**
     * Main.
     * 
     * @param args Command line arguments.
     */
    public static void main(String[] args) {

        // Create a 5x5 maze with debugging
        Maze maze = new Maze(5, 5, true);
        maze.display();

        // Create 5x5 maze without debugging
        maze = new Maze(5, 5, false);
        maze.display();

        // Create 10x10 maze without debugging
        maze = new Maze(10, 10, false);
        maze.display();

    }

}
