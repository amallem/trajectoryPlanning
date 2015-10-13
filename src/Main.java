import Algos.RepeatedBackwardAStar;
import Algos.RepeatedForwardAStar;
import utilities.Constants;
import utilities.MazeBox;
import utilities.MazeNode;

import static utilities.Constants.FREE;

/**
 * Created by anirudh on 10/3/15.
 */
public class Main {

    public static void main(String args[]){


        MazeBox mBox = new MazeBox(11, 11);
        MazeNode[][] maze = mBox.getMaze();
        RepeatedForwardAStar.search(mBox, maze[0][0], maze[10][10]);
        mBox.printMaze();

        reinitialize(maze);

        RepeatedBackwardAStar.search(mBox, maze[0][0], maze[10][10]);
        mBox.printMaze();

        /*MazeBox c = new MazeBox(11, 11);
        MazeNode[][] m3 = mBox.getMaze();
        RepeatedForwardAStar.search(c, maze[0][0], maze[10][10]);
        c.printMaze();

        RepeatedBackwardAStar.search(c, m3[10][10], m3[0][0]);
        c.printMaze();*/

            /*
            RepeatedForwardAStar.search(a,maze[0][0],maze[9][9]);
            a.printMaze();*/

    }

    private static void reinitialize(MazeNode[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j].reinitialize();
            }
        }
    }
}
