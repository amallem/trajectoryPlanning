package utilities;

import java.io.PrintWriter;
import java.util.Random;
import java.util.Stack;

import static utilities.Constants.*;

/**
 * Created by anirudh on 10/3/15.
 */
public class MazeBox {

    public static MazeNode[][] maze;
    private int rows;
    private int cols;

    public MazeBox(int row, int col) {
        maze = new MazeNode[row][col];
        this.rows = row;
        this.cols = col;
        initialize();
        generate();
    }

    public MazeBox(int num) {
        maze = new MazeNode[num][num];
        this.rows = num;
        this.cols = num;
        initialize();


        maze[1][2].setVal(BLOCKED);
        maze[2][3].setVal(BLOCKED);
        maze[3][3].setVal(BLOCKED);
        maze[4][3].setVal(BLOCKED);
        maze[3][2].setVal(BLOCKED);
        maze[2][2].setVal(BLOCKED);
    }

    public MazeNode[][] getMaze() {
        return this.maze;
    }

    private void generate() {
        int pos_row = new Random().nextInt(rows);
        int pos_col = new Random().nextInt(cols);
        Stack<MazeNode> child = new Stack<MazeNode>();
        int count = 0;
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

            if (count == rows * cols)
                break;

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
            maze[temp.row - 1][temp.col].setVal(FREE);
            child.push(maze[temp.row - 1][temp.col]);
        }
        if ((temp.row + 1 < rows) && (maze[temp.row + 1][temp.col].getVal() == NOT_VISITED)) {
            maze[temp.row + 1][temp.col].setVal(FREE);
            child.push(maze[temp.row + 1][temp.col]);
        }
        if ((temp.col - 1 >= 0) && (maze[temp.row][temp.col - 1].getVal() == NOT_VISITED)) {
            maze[temp.row][temp.col - 1].setVal(FREE);
            child.push(maze[temp.row][temp.col - 1]);
        }
        if ((temp.col + 1 < cols) && (maze[temp.row][temp.col + 1].getVal() == NOT_VISITED)) {
            maze[temp.row][temp.col + 1].setVal(FREE);
            child.push(maze[temp.row][temp.col + 1]);
        }
    }

    private void initialize() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j] = new MazeNode('*', i, j);
            }
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

    public void printMaze(PrintWriter writer) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                writer.print(maze[i][j].getVal());
            }
            writer.println();
        }
    }

    public MazeNode getNode(int row, int col) {

        if (row < rows && row >= 0 &&
                col < cols && col >= 0) {
            return maze[row][col];
        }
        return null;
    }

    public MazeNode getRandomMazeNode() {
        int rowPos = new Random().nextInt(rows);
        int colPos = new Random().nextInt(cols);
        return maze[rowPos][colPos];
    }

    public void setHvalues(MazeNode goal) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                maze[i][j].setH(goal);
            }
        }
    }
}
