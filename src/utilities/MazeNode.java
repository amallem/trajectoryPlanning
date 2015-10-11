package utilities;

/**
 * Created by anirudh on 10/3/15.
 */
public class MazeNode {

    private static final char BLOCKED = '#';
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

    public MazeNode(char val, int row, int col) {
        this.val = val;
        this.row = row;
        this.col = col;
        this.cost = 1;
        this.parent = null;
        this.search = 0;
    }

    public boolean isBlocked() {
        if (val == BLOCKED)
            return true;
        return false;
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

    public void setH_temp(int h) {
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
}
