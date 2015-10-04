package utilities;

import java.util.Random;
import java.util.Stack;

/**
 * Created by anirudh on 10/3/15.
 */
public class Maze {

    public static MazeNode[][] maze;
    private static final char BLOCKED = '#';
    private static final char FREE = '.';
    private static final char NOT_VISITED = '*';
    private int rows;
    private int cols;

    public Maze(int row, int col) {
        maze = new MazeNode[row][col];
        this.rows = row;
        this.cols = col;
        initialize();
        generate();
    }

    public MazeNode[][] getMaze() {
        return this.maze;
    }

    private void generate() {
        int pos_row = new Random().nextInt(rows);
        int pos_col = new Random().nextInt(cols);
        int count = 0;
        Stack<MazeNode> child = new Stack<MazeNode>();
        child.push(maze[pos_row][pos_col]);
        while (true) {
            MazeNode temp;
            while (!child.empty()) {
                temp = child.pop();
                count++;
                if (isBlocked()) {
                    temp.setVal(BLOCKED);
                } else {
                    temp.setVal(FREE);
                }
                addChildToStack(child, temp);
            }

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (maze[i][j].getVal() == NOT_VISITED) {
                        child.push(maze[i][j]);
                        break;
                    }
                }
                if (!child.empty()) {
                    break;
                }
            }
        }
    }

    private void addChildToStack(Stack<MazeNode> child, MazeNode temp) {
        if ((temp.row - 1 >= 0) && (maze[temp.row - 1][temp.col].getVal() == NOT_VISITED)) {
            child.push(maze[temp.row - 1][temp.col]);
        }
        if ((temp.row + 1 < rows) && (maze[temp.row + 1][temp.col].getVal() == NOT_VISITED)) {
            child.push(maze[temp.row + 1][temp.col]);
        }
        if ((temp.col - 1 >= 0) && (maze[temp.row][temp.col - 1].getVal() == NOT_VISITED)) {
            child.push(maze[temp.row][temp.col - 1]);
        }
        if ((temp.col + 1 < cols) && (maze[temp.row][temp.col + 1].getVal() == NOT_VISITED)) {
            child.push(maze[temp.row][temp.col + 1]);
        }
    }

    private void initialize() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = new MazeNode('*', i, j);
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

    public void printMaze() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(maze[i][j].getVal() + " ");
            }
            System.out.println();
        }
    }

}
