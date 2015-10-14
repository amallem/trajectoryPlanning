import Algos.AdaptiveAStar;
import Algos.RepeatedBackwardAStar;
import Algos.RepeatedForwardAStar;
import utilities.MazeBox;
import utilities.MazeNode;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

/**
 * Created by anirudh on 10/3/15.
 */
public class Main {

    public static void main(String args[]){


        try {
            PrintWriter writer = new PrintWriter("Sample.txt", "UTF-8");


            MazeBox mBox = new MazeBox(5);
            MazeNode[][] maze = mBox.getMaze();

            writer.println("Performing Repeated Forward A-Star");
            RepeatedForwardAStar.search(mBox, maze[4][2], maze[4][4]);
            mBox.printMaze(writer);
            writer.println("Explored Nodes: " + RepeatedForwardAStar.exploredNodes);
            writer.print("--------------------------------");
            writer.println();

            reinitialize(maze);

            writer.println("Performing Repeated Backward A-Star");
            RepeatedBackwardAStar.search(mBox, maze[4][4], maze[4][2]);
            mBox.printMaze(writer);
            writer.println(" Explored Nodes : " + RepeatedBackwardAStar.exploredNodes);

            writer.print("--------------------------------");
            writer.println();
            writer.flush();

            reinitialize(maze);

            writer.println("Performing Adaptive A-Star");
            AdaptiveAStar.search(mBox, maze[4][2], maze[4][4]);
            mBox.printMaze(writer);
            writer.println(" Explored Nodes : " + AdaptiveAStar.exploredNodes);

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
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
