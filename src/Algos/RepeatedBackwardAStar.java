package Algos;

import utilities.MazeBox;
import utilities.MazeNode;

/**
 * Created by anirudh on 10/11/15.
 */
public class RepeatedBackwardAStar {

    public static void search(MazeBox mazeBox, MazeNode start, MazeNode goal) {
        RepeatedForwardAStar.search(mazeBox, goal, start);
    }
}