package utilities;

import static utilities.Constants.BLOCKED;
import static utilities.Constants.FREE;

/**
 * Created by anirudh on 10/3/15.
 */
public class MazeNode {


    public int row;
    public int col;

    private char val;

    /**
     * Cost to reach this node from neighbour.
     * c(s,a)
     */
    private int cost;


    private int g;
    private int h;
    private MazeNode parent;
    private int search;
    private boolean visited;

    public MazeNode(char val, int row, int col) {
        this.val = val;
        this.row = row;
        this.col = col;
        this.cost = 1;
        this.parent = null;
        this.search = 0;
        this.visited = false;
    }

    public boolean isBlocked() {
        if (val == BLOCKED)
            return true;
        return false;
    }

    public boolean isVisited() {
        return this.visited;
    }

    public void setVisited(boolean value) {
        this.visited = value;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCost() {
        return this.cost;
    }

    public char getVal() {
        return this.val;
    }

    public void setVal(char val) {
        this.val = val;
    }

    public int getG() {
        return this.g;
    }

    public void setG(int val) {
        this.g = val;
    }

    public int getH() {
        return this.h;
    }

    public void setH(MazeNode goal) {
        this.h = Math.abs(goal.row - this.row)
                + Math.abs(goal.col - this.col);
    }

    public void setHnew(int h) {
        this.h = h;
    }

    public void setParent(MazeNode parent) {
        this.parent = parent;
    }

    public MazeNode getParent() {
        return this.parent;
    }

    public void setSearch(int search) {
        this.search = search;
    }

    public int getSearch() {
        return this.search;
    }

    public void reinitialize() {
        if (!this.isBlocked()) {
            this.setVal(FREE);
        }
        this.cost = 1;
        this.parent = null;
        this.search = 0;
        this.g = 0;
        this.h = 0;
        this.visited = false;
    }
}
