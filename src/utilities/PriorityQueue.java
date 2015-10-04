package utilities;

import java.util.ArrayList;

/**
 * @author Mohan Varma
 */
public class PriorityQueue {

    // ArrayList of MazeNodes representing the pq
    private ArrayList<MazeNode> pq;
    
    public PriorityQueue()
    {
         pq = new ArrayList<MazeNode>();
    }
    
    public int getSize()
    {
        return pq.size();
    }
    
    private boolean isGreater(int i, int j)
    {
        int fi = pq.get(i).getG() + pq.get(i).getH();
        int fj = pq.get(j).getG() + pq.get(j).getH();
        return fi > fj;
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
    
    // Starting from the index x, retain minHeap
    // in bottom Up way
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
    
    // Starting at parent, 
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
        bottomUpMinHeapify(pq.size()-1);
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
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Tests
        PriorityQueue pq = new PriorityQueue();

        MazeNode n10 = new MazeNode('.');
        n10.setG(3);
        n10.setH(7);
        pq.insert(n10);

        MazeNode n8 = new MazeNode('.');
        n8.setG(4);
        n8.setH(4);
        pq.insert(n8);

        MazeNode n7 = new MazeNode('.');
        n7.setG(3);
        n7.setH(4);
        pq.insert(n7);

        MazeNode n6 = new MazeNode('.');
        n6.setG(3);
        n6.setH(3);
        pq.insert(n6);


        MazeNode tmp = pq.deleteMin();
        int g = tmp.getG();
        int h = tmp.getH();
        System.out.println(g + "+" + h);

        MazeNode n1 = new MazeNode('.');
        n1.setG(0);
        n1.setH(1);
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
    
}
