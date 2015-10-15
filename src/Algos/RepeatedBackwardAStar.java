package Algos;

import utilities.MazeBox;
import utilities.MazeNode;

/**
 * Created by anirudh on 10/11/15.
 */
public class RepeatedBackwardAStar {

    public static int exploredNodes = 0;

    public static boolean search(MazeBox mazeBox, MazeNode start, MazeNode goal, int comparePolicy) {
        boolean ans = RepeatedForwardAStar.search(mazeBox, goal, start, comparePolicy);
        exploredNodes = RepeatedForwardAStar.exploredNodes;
        return ans;
    }
}
