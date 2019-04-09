/*
 * Authors    : Thomas Rudolph, Connor Van Meter
 * Lab        : 3
 * Date       : 3/29/2018
 * Description: This lab finds a shortest path algorithm
 */
package mygps;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;
import java.util.Scanner;

import mygps.Edge;

public class MyGPS {
    
    private static String file = "C:\\Users\\Tom\\Documents\\CSCI232\\MyGPS\\src\\mygps\\gz.gr";
    private static String path = "C:\\Users\\Tom\\Documents\\CSCI232\\MyGPS\\src\\mygps\\";
    
    private static int num_vertices, num_edges, num_read = 0, vertex = 0;      
    private static int start_node, end_node, read;    
    private static Scanner scan = new Scanner(System.in);    
    private static BufferedReader br;    
    private static Edge[] v; 
    
    //Variables for dijkstra
    private static int[][][] values; //Matrix to define the process for finding the distance
    private static int[] def_vertex; //Vector to define vertex distance was calculate from
    private static int[] def_values;  //Parallel vector for distance to above vector
    
    private static long start_time;
    private static long end_time;
    
    public static void main(String[] args) {
        
        read_file();
        
        
        
        do{
            System.out.println("The current graph has vertices from " + 1 + " to " + num_vertices + ".");
            System.out.println("Would you like to:");
            System.out.println("   1. Find a new route");
            System.out.println("   2. Exit");
            read = scan.nextInt();
            if (read == 1)
            {
                dijkstra();
            }
        }while(read != 2);
    }
    /***********************************************************************************
     * Function Name : dijkstra()
     * Input(s)      : None
     * Output        : None
     * Description   : Function to run dijkstra's algorithm
     **********************************************************************************/
    private static void dijkstra()
    {
        System.out.println("Enter source: ");
        start_node = scan.nextInt();
        System.out.println("Enter destination: ");
        end_node   = scan.nextInt();
        
        //Start measuring time elapsed
        start_time = System.nanoTime();
        
        dStart();
        try {Thread.sleep(500);} catch (InterruptedException ex) {
            Logger.getLogger(MyGPS.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        dLoop();
        dPrint();
    }
    
    /***********************************************************************************
     * Function Name : dStart()
     * Input(s)      : None
     * Output        : None
     * Description   : Function to start the dijkstra 
     **********************************************************************************/
    private static void dStart()
    {
        //Initialize size of vectors
       values     = new int[num_vertices][num_vertices][3]; 
       def_vertex = new int[num_vertices];
       def_values = new int[num_vertices];
       
       //Set up initial values for vectors
       def_vertex[0] = start_node;
       def_values[0] = 0;
       
       //Get the vertices from the start point
       int[] start_connect = v[start_node].getDestination();
       int[] start_dist    = v[start_node].getDistance();
       int   num_connect   = v[start_node].num_nodes_fnc(); // Gets number of connections
       
       int closest_vertex = Integer.MAX_VALUE;
       int cv_value       = 0;
       
       //Fill out first row of matrix
       for( int i = 0; i < num_connect; i++ )
       {
           values[0][start_connect[i]][0] = start_dist[i];
           // Says the above is the distance from start_node
           values[0][start_connect[i]][1] = start_node; 
           
           //check if it is the closest value
           if(start_dist[i] < closest_vertex)
           {
               closest_vertex = start_dist[i];
               cv_value       = start_connect[i];
           }
       }
       
       for(int i = 0; i < num_vertices; i++)
       {
           if(i != start_node)
           {
               if(values[0][i][0] == 0)
               {
                   values[0][i][0] = Integer.MAX_VALUE; // MAX_VALUE approximates infinity, saying it can't reach
               }
           }
           else
           {
               values[0][i][0] = 0;
               
               //Say that the value of the start node has been boxed, in accordance to the standards used by
               //   example videos
               for(int j = 0; j < num_vertices; j++)
               {
                   values[j][start_node][2] = 1; 
               }
           }
       }
       
       def_vertex[1] = cv_value;
       def_values[1] = closest_vertex;
       
       values[0][closest_vertex][0] = cv_value; 
       
       //Says that this is the shortest distance to that specific node
       for( int i = 1; i < num_vertices; i++)
       {
           values[i][cv_value][2]   = 1;
           values[i][start_node][2] = 1;
       }
       //System.out.println( "\tcv_value " + cv_value );
       //System.out.println( "closest_vertex " + closest_vertex + "\t");
       
       test();
    }
    
    /***********************************************************************************
     * Function Name : dLoop()
     * Input(s)      : None
     * Output        : None
     * Description   : Function to run dijkstra until end is reached
     **********************************************************************************/
    private static void dLoop()
    {
        //Sets initial index to check to be 1
        int index = 1;
        
        int x_node   = Integer.MAX_VALUE;
        int node_val = 0; // What the node is
        
        //Sets up loop to run forever
        do{
            //System.out.println();
            int[] node_connect = v[def_vertex[index-1]].getDestination();
            int[] node_dist    = v[def_vertex[index-1]].getDistance();
            int   num_connect  = v[def_vertex[index-1]].num_nodes_fnc(); // Gets number of connections
            
            
            //Fill out approriate row
            for( int i = 0; i < num_connect; i++ )
            {                
                //System.out.println("^^^^^" + (def_values[def_vertex[index-1]]));
                if((values[index-1][node_connect[i]][0] > (node_dist[i] + def_values[index])) && (values[index][node_connect[i]][2] != 1) )
                {
                    
                    values[index][node_connect[i]][0] = node_dist[i] + def_values[def_vertex[index-1]];
                    // Says the above is the distance from start_node
                    values[index][node_connect[i]][1] = def_vertex[index-1]; 
                }
                else
                {
                    values[index][node_connect[i]][0] = values[index-1][node_connect[i]][0];
                    values[index][node_connect[i]][1] = values[index-1][node_connect[i]][1];
                }
            }
            
            //Fill empty positions
            for(int i = 0; i < num_vertices; i++)
            {
                if(i != start_node)
                {
                    if(values[index][i][2] != 1)
                    {
                        if(values[index][i][0] == 0)
                        {
                            if(values[index][i][0] < Integer.MAX_VALUE)
                            {
                                values[index][i][0] = values[index-1][i][0];
                                values[index][i][1] = values[index-1][i][1];
                            }
                            else
                            {
                                values[index][i][0] = Integer.MAX_VALUE;
                            }
                        }
                    }
                }
            }
            
            x_node = values[index][0][0];
            
            //System.out.println("-------\n " + values[index][7][0] + "\n------");
            
            int temp_index = 0;
            for(int j = 0; j < num_vertices; j++)
            {
                if( (start_node == 0) || ((values[index][j][2] == 0) && (x_node > values[index][j][0]) ) )
                {
                    x_node     = values[index][j][0];
                    node_val   = j;
                    temp_index = j;
                }
            }
            
            //Set boxed
            for( int i = index; i < num_vertices; i++ )
            {
                values[i][temp_index][2] = 1;
            }
            
            
            //Fill out definition arrays
            //System.out.println("x_node: " + x_node + " " + node_val);
            def_vertex[index] = node_val;
            def_values[index] = values[index][temp_index][1];
            
            System.out.println(/*"def_vertex: " + def_vertex[index]*/);
            test();
            scan.next();
            /*
            for(int i = 0; i< num_vertices; i++)
            {
                System.out.println(" " + def_vertex[i] + " " + def_values[i] );
            }
            */
        }while(def_vertex[index++] != end_node);
    }
    
    /***********************************************************************************
     * Function Name : dPrint()
     * Input(s)      : None
     * Output        : None
     * Description   : Function to provide the appropriate print out
     **********************************************************************************/
    private static void dPrint()
    {        
        end_time = System.nanoTime();
        
        
        
        
        System.out.println("Time elapsed = " + ((double)end_time - (double)start_time) / 1000000000.0 + "s");
        
    }
    
    /***********************************************************************************
     * Function Name : read_file()
     * Input(s)      : None
     * Output        : None
     * Description   : Function to read in from file
     **********************************************************************************/
    private static void read_file()
    {
        
        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {Logger.getLogger(MyGPS.class.getName()).log(Level.SEVERE, null, ex);}
                
        String line; 
        try {
            // Reads in from file
            while((line = br.readLine()) != null)
            {
                String[] c       = line.split(" ");
                if( c[0].equals("a") )                
                {
                    vertex = Integer.parseInt(c[1]);  
                    
                    //System.out.println(vertex);
                    
                    v[vertex].addEdge(Integer.parseInt(c[2]),Integer.parseInt(c[3]));
                    
                    //vertex = Integer.parseInt(c[2]) - 1;
                    //v[vertex].addEdge(Integer.parseInt(c[1]),Integer.parseInt(c[3]));
                    
                }
                else if( c[0].equals("p") )
                {
                    num_edges    = Integer.parseInt(c[3]);
                    num_vertices = Integer.parseInt(c[2]);
                    
                    v    = new Edge[num_vertices];
                    for (int i = 0; i < num_vertices; i++)
                    {
                        v[i] = new Edge(i+1);
                    }
                }
            }
        } catch (IOException ex) {Logger.getLogger(MyGPS.class.getName()).log(Level.SEVERE, null, ex);}
                
    }
    
    private static void test()
    {
        for(int i = 0; i < num_vertices; i++)
        {
            for( int j = 0; j < num_vertices; j++)
            {
                System.out.print( values[i][j][0]+ ":" + values[i][j][1] + ":" + values[i][j][2]  + " ");
            }
            System.out.println();
        }
    }
}
