package Algos;

import utilities.MazeBox;
import utilities.MazeNode;
import utilities.PriorityQueue;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anirudh on 10/9/15.
 */
public class RepeatedForwardAStar {

    private static int counter = 0;
    private static final int INFINITY = 1000;
    private static MazeNode[][] maze;
    private static PriorityQueue openList = new PriorityQueue();
    private static List<MazeNode> closedList = new ArrayList<MazeNode>();

    public static void search(MazeBox mazeBox, MazeNode start, MazeNode goal) {
        maze = mazeBox.getMaze();

        if (start.isBlocked() || goal.isBlocked()) {
            System.out.println("Start/Goal given is a blocked cell");
            return;
        }

        /* While start != goal */
        while (!start.equals(goal)) {
            counter++;
            start.setG(0);
            start.setSearch(counter);
            goal.setG(INFINITY);
            goal.setSearch(counter);
            openList.clearAll();
            closedList.clear();
            start.setH(goal);
            openList.insert(start);
            computePath();
            if (openList.getSize() == 0) {
                System.out.println("Cannot reach target!!!!");
                return;
            }
            retracePath();
            start = moveAgent();
        }

        System.out.println("Reached Target!");
    }

    private static MazeNode moveAgent() {
        return null;
    }

    private static void retracePath() {

    }

    private static void computePath() {
    }
}
