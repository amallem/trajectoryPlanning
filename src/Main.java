import Algos.RepeatedForwardAStar;
import utilities.MazeBox;
import utilities.MazeNode;

/**
 * Created by anirudh on 10/3/15.
 */
public class Main {

    public static void main(String args[]){


        MazeBox b = new MazeBox(101, 101);
        MazeNode[][] m2 = b.getMaze();
        RepeatedForwardAStar.search(b, m2[0][0], m2[100][100]);
        b.printMaze();

            /*
            RepeatedForwardAStar.search(a,maze[0][0],maze[9][9]);
            a.printMaze();*/

    }
}
