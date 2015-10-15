import Algos.AdaptiveAStar;
import Algos.RepeatedBackwardAStar;
import Algos.RepeatedForwardAStar;
import utilities.MazeBox;
import utilities.MazeNode;

import java.io.PrintWriter;

import static utilities.Constants.DEFAULT;
import static utilities.Constants.GREATER_G;
import static utilities.Constants.LESSER_G;

/**
 * Created by anirudh on 10/3/15.
 */
public class Main {

    public static void main(String args[]){


        boolean answer = false;
        for (int i = 0; i < 6; i++) {
            try {
                PrintWriter writer = new PrintWriter("Result" + i + ".txt", "UTF-8");

                MazeBox mBox = new MazeBox(101, 101);
                MazeNode[][] maze = mBox.getMaze();
                MazeNode start = mBox.getRandomMazeNode();
                MazeNode goal = mBox.getRandomMazeNode();

                //            MazeNode start = maze[4][2];
                //              MazeNode goal = maze[4][4];
                writer.println("Start: " + start.row + "," + start.col);
                writer.println("Goal: " + goal.row + "," + goal.col);
                writer.println("Performing Repeated Forward A-Star");
                answer = RepeatedForwardAStar.search(mBox, start, goal, GREATER_G);
                mBox.printMaze(writer);
                writer.println("Explored Nodes: " + RepeatedForwardAStar.exploredNodes);
                RepeatedForwardAStar.exploredNodes = 0;
                writer.println(answer);
                writer.print("--------------------------------");
                writer.println();

                reinitialize(maze);

      /*          writer.println("Performing Repeated Backward A-Star");
                answer = RepeatedBackwardAStar.search(mBox, start, goal, GREATER_G);
                mBox.printMaze(writer);
                writer.println(" Explored Nodes : " + RepeatedBackwardAStar.exploredNodes);
                RepeatedBackwardAStar.exploredNodes = 0;
                writer.println(answer);

                writer.print("--------------------------------");
                writer.println();
      */
                reinitialize(maze);

                writer.println("Performing Adaptive A-Star");
                int x = AdaptiveAStar.search(mBox, start, goal, GREATER_G);
                mBox.printMaze(writer);
                writer.println(" Explored Nodes : " + x);
                writer.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private static void reinitialize(MazeNode[][] maze) {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                maze[i][j].reinitialize();
            }
        }
    }
}
