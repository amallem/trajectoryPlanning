package Algos;

import static utilities.Constants.FREE;
import static utilities.Constants.INFINITY;
import static utilities.Constants.MOVE;

import utilities.MazeBox;
import utilities.MazeNode;
import utilities.PriorityQueue;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.Stack;


/**
 * Created by mohan on 10/11/2015.
 */
public class AdaptiveAStar {

    private static int counter = 0;
    private static MazeNode[][] maze;
    private static PriorityQueue openList = new PriorityQueue();
    private static List<MazeNode> closedList = new ArrayList<MazeNode>();
    private static Stack<MazeNode> path = new Stack<MazeNode>();


    public static int search(MazeBox mazeBox, MazeNode start, MazeNode goal, int comparePolicy) {
        maze = mazeBox.getMaze();
        openList.setComparePolicy(comparePolicy);
        int exploredNodes = 0;

        if (start.isBlocked()) {
            start.setVal(FREE);
        }

        if (goal.isBlocked()) {
            goal.setVal(FREE);
        }

        /* While start != goal */
        while (!start.equals(goal)) {
            counter++;
            discoverNeighbours(start);
            start.setG(0);
            start.setSearch(counter);
            goal.setG(INFINITY);
            goal.setSearch(counter);
            openList.clear();
            closedList.clear();
            start.setH(goal);
            openList.insert(start);
            computePath(goal);
            exploredNodes += closedList.size();

            if (openList.getSize() == 0) {
                System.out.println("Cannot reach target!!!!");
                return exploredNodes;
            }
            updateHnew(goal);
            retracePath(start, goal);
            start = moveAgent();

        }

        System.out.println("Reached Target!");
        return exploredNodes;
    }

    private static void updateHnew(MazeNode goal) {
        for (MazeNode node : closedList) {
            node.setHnew(goal.getG() - node.getG());
            node.setVisited(true);
        }
    }

    private static void discoverNeighbours(MazeNode start) {
        for (MazeNode neighbour : getNeighbours(start)) {
            if (neighbour.isBlocked()) {
                neighbour.setCost(INFINITY);
            }
        }
    }

    private static MazeNode moveAgent() {
        MazeNode current = null, next;
        while (!path.isEmpty()) {
            current = path.pop();
            try {
                next = path.peek();
                if (next != null && next.isBlocked()) {
                    path.clear();
                    break;
                }
            } catch (EmptyStackException e) {
            }
            current.setVal(MOVE);
        }
        return current;
    }

    private static void retracePath(MazeNode start, MazeNode goal) {
        MazeNode current = goal;
        while (current != null && (!current.equals(start))) {
            path.push(current);
            current = current.getParent();
        }
        if (current == null || !current.equals(start)) {
            System.out.println("Something went Wrong!!!");
        } else {
            path.push(current);
        }
    }

    private static void computePath(MazeNode goal) {
        while (openList.getSize() > 0 && goal.getG() > openList.getMinFValue()) {
            MazeNode current = openList.deleteMin();

            if (!closedList.contains(current)) {
                closedList.add(current);
            }
            for (MazeNode neighbour : getNeighbours(current)) {
                if (neighbour.getSearch() < counter) {
                    neighbour.setG(INFINITY);
                    neighbour.setSearch(counter);
                }
                if (neighbour.getG() > (current.getG() + neighbour.getCost())) {
                    neighbour.setG(current.getG() + neighbour.getCost());
                    neighbour.setParent(current);
                    if (openList.contains(neighbour)) {
                        openList.remove(neighbour);
                    }
                    if (!neighbour.isVisited()) {
                        neighbour.setH(goal);
                    }
                    openList.insert(neighbour);
                }
            }
        }
    }

    private static List<MazeNode> getNeighbours(MazeNode current) {
        List<MazeNode> neighbours = new ArrayList<MazeNode>();
        if (current.row - 1 >= 0) {
            neighbours.add(maze[current.row - 1][current.col]);
        }
        if (current.row + 1 < maze.length) {
            neighbours.add(maze[current.row + 1][current.col]);
        }
        if (current.col - 1 >= 0) {
            neighbours.add(maze[current.row][current.col - 1]);
        }
        if (current.col + 1 < maze[0].length) {
            neighbours.add(maze[current.row][current.col + 1]);
        }
        return neighbours;
    }
}

