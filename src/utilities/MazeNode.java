package utilities;

/**
 * Created by anirudh on 10/3/15.
 */
public class MazeNode {

    public int row;
    public int col;

    private char val;
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
        if (val == '.')
            return false;
        return true;
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

    public void setH(int h) {
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
