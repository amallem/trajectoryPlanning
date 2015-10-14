package utilities;

import java.util.ArrayList;

/**
 * @author Mohan Varma
 */
public class PriorityQueue {

    /**
     * ArrayList of MazeNodes representing the pq
     */
    private ArrayList<MazeNode> pq;
    
    public PriorityQueue()
    {
         pq = new ArrayList<MazeNode>();
    }
    
    public int getSize()
    {
        return pq.size();
    }

    public ArrayList<MazeNode> getAllNodes() {
        return pq;
    }

    /* TODO : Need to implement various logic to break ties */
    private boolean isGreater(int i, int j)
    {
        int fi = pq.get(i).getG() + pq.get(i).getH();
        int fj = pq.get(j).getG() + pq.get(j).getH();
        if (fi > fj) {
            return true;
        } else if (fi == fj) {
            return pq.get(i).getG() < pq.get(j).getG();
        }
        return false;
    }
    
    private void swap(int i, int j)
    {
        //Index check
        if(i > (pq.size()-1) || j > (pq.size()-1))
            return;
        
        MazeNode tmp = pq.get(i);
        pq.set(i, pq.get(j));
        pq.set(j, tmp);
    }

    /** Starting from the index x, retain minHeap
     in bottom Up way */
    private void bottomUpMinHeapify(int index)
    {
        while(index > 0 && isGreater((index-1)/2, index))
        {
            // Swap index with parent
            swap(index, (index-1)/2);
            // Repeatedly check at parent
            index = (index-1)/2;
        }
    }

    /** Starting at parent, */
    private void topDownMinHeapify(int index)
    {
        while((2*index+1) <= pq.size()-1)
        {
            int child = (2*index+1);
            if (child < (pq.size()-1) && isGreater(child, child+1))
            {
                //Exchange it with the lesser element
                child = child+1;
            }

            // Right position
            if (!isGreater(index, child))
                break;
            
            swap(index, child);
            
            index = child;
        }
    }
    
    public void insert(MazeNode node)
    {
        // Add at the end
        pq.add(node);
        
        //Bottom up minHeapify
        bottomUpMinHeapify(pq.size() - 1);
    }
    
    // Deletes min value and retains min PQ
    public MazeNode deleteMin()
    {
        if(!pq.isEmpty())
        {
            MazeNode min = pq.get(0);
            //Exchange this with the last element
            swap(0, pq.size()-1);
            // Remove the last element
            pq.remove(pq.size()-1);
            
            topDownMinHeapify(0);
            return min;
        }
        return null;
    }

    /**
     * @return the f-value of the min MazeNode
     */
    public int getMinFValue() {
        return (pq.get(0).getG() + pq.get(0).getH());
    }

    /**
     * @param node
     * @return "True" if the priorityQueue contains the @param
     * else returns "False"
     */
    public boolean contains(MazeNode node) {
        return pq.contains(node);
    }

    /**
     * @param node Deletes the given MazeNode from the priorityQueue
     *             TODO : Check if this works!
     */
    public void remove(MazeNode node) {
        int index = pq.indexOf(node);
        swap(index, pq.size() - 1);
        pq.remove(pq.size() - 1);
        topDownMinHeapify(index);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Tests
        PriorityQueue pq = new PriorityQueue();

        MazeNode n10 = new MazeNode('.', 1, 2);
        n10.setG(3);
        n10.setHnew(7);
        pq.insert(n10);

        MazeNode n8 = new MazeNode('.', 2, 3);
        n8.setG(4);
        n8.setHnew(4);
        pq.insert(n8);

        MazeNode n7 = new MazeNode('.', 3, 4);
        n7.setG(3);
        n7.setHnew(4);
        pq.insert(n7);

        MazeNode n6 = new MazeNode('.', 4, 5);
        n6.setG(3);
        n6.setHnew(3);
        pq.insert(n6);


        MazeNode tmp = pq.deleteMin();
        int g = tmp.getG();
        int h = tmp.getH();
        System.out.println(g + "+" + h);

        MazeNode n1 = new MazeNode('.', 7, 6);
        n1.setG(0);
        n1.setHnew(1);
        pq.insert(n1);

        tmp = pq.deleteMin();
        g = tmp.getG();
        h = tmp.getH();
        System.out.println(g + "+" + h);
        tmp = pq.deleteMin();
        g = tmp.getG();
        h = tmp.getH();
        System.out.println(g + "+" + h);
        System.out.println(pq.getSize());
    }

    public void clear() {
        pq.clear();
    }

}
