package utilities;

import java.util.Random;

/**
 * Created by anirudh on 10/3/15.
 */
public class Maze {

    public static MazeNode[][] maze;
    private int rows;
    private int cols;

    public Maze(int row, int col) {
        maze = new MazeNode[row][col];
        this.rows = row;
        this.cols = col;
        initialize();

    }

    private void initialize() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = new MazeNode('.');
                System.out.print(maze[i][j].getVal() + " ");
            }
            System.out.println();
        }
    }

    /**
     * Uses 30% probablity to mark a cell as blocked
     */
    private boolean isBlocked() {
        int random = new Random().nextInt(100);
        if (random < 30)
            return true;
        return false;
    }

    public MazeNode[][] getMaze() {
        return this.maze;
    }
}
