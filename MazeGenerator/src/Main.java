public class Main {

    public static void main(String[] args) {

        
        // Create a 5x5 maze with debugging
        Maze maze = new Maze(2,3,true);
        maze.display();
        System.out.println();
        
        // Create a larger maze with debugging off:
        maze = new Maze(10,10,false);
        maze.display();
        System.out.println();
        
        // Testing and debugging:
        
    }

}
