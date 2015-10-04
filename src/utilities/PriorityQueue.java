
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author mohan
 */
public class PriorityQueue {

    // ArrayList of values representing the pq
    private ArrayList<Integer> pq;
    
    public PriorityQueue()
    {
         pq = new ArrayList<Integer>();
    }
    
    public int getSize()
    {
        return pq.size();
    }
    
    private boolean isGreater(int i, int j)
    {
        return (pq.get(i) > pq.get(j));
    }
    
    private void swap(int i, int j)
    {
        //Index check
        if(i > (pq.size()-1) || j > (pq.size()-1))
            return;
        
        int tmp = pq.get(i);
        pq.set(i, pq.get(j));
        pq.set(j, tmp);
    }
    
    // Starting from the index x, retain minHeap
    // in bottom Up way
    private void bottomUpMinHeapify(int index)
    {
        int k = index;
        while(k > 0 && isGreater((k-1)/2, k))
        {
            // Swap index with parent
            swap(k, (k-1)/2);
            // Repeatedly check at parent
            k = (k-1)/2;
        }
    }
    
    // Starting at parent, 
    private void topDownMinHeapify(int index)
    {
        int k = index;
        while((2*k+1) <= pq.size()-1)
        {
            int j = (2*k+1);
            if (j < (pq.size()-1) && isGreater(j, j+1))
            {
                //Exchange it with the lesser element
                j = j+1;
            }
            
            if (!isGreater(k, j))
                break;
            
            swap(k, j);
            
            k = j;
        }
    }
    
    public void insert(int x)
    {
        // Add at the end
        pq.add(x);
        
        //Bottom up minHeapify
        bottomUpMinHeapify(pq.size()-1);
    }
    
    // Deletes min value and retains min PQ
    public int deleteMin()
    {
        if(!pq.isEmpty())
        {
            int min = pq.get(0);   
            //Exchange this with the last element
            swap(0, pq.size()-1);
            // Remove the last element
            pq.remove(pq.size()-1);
            
            topDownMinHeapify(0);
            return min;
        }
        return -1;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        PriorityQueue pq = new PriorityQueue();
        pq.insert(10);
        pq.insert(8);
        pq.insert(7);
        pq.insert(7);
        pq.insert(6);
        System.out.println(pq.deleteMin());
        System.out.println(pq.deleteMin());
        pq.insert(1);
        System.out.println(pq.deleteMin());
        System.out.println(pq.getSize());
    }
    
}
