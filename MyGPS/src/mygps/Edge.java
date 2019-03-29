
package mygps;

import java.util.ArrayList;


public class Edge {
    int len = 5;
    private int source;
    private int[] destination = new int[len], distance = new int[len];
    int num_nodes = 0;
    
    private ArrayList<Integer> edge = new ArrayList<Integer>();
    
    public Edge( int _s)
    {
        source      = _s;
    }
    
    public void addEdge(int _de, int _di)
    {
        destination[num_nodes] = _de;
        distance[num_nodes++]  = _di;
        
        if(len == num_nodes)
        {
            len *= 2;
            int[] tempDe = new int[len/2], tempDi = new int[len/2];
            
            for (int i = 0; i < len/2; i++)
            {
                tempDi[i] = distance[i];
                tempDe[i] = destination[i];                
            }
            
            destination = new int[len];
            distance    = new int[len];
            
            for (int i = 0; i < len/2; i++)
            {
                distance[i]    = tempDi[i];
                destination[i] = tempDe[i];                
            }
        }
    }
    
    public int getSource()
    {return source;}
    
    public int[] getDestination()
    {return destination;}
    
    public int num_nodes_fnc()
    {return num_nodes;}
    
    public int[] getDistance()
    {return distance;}
}
