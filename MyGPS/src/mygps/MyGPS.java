/*
 * Author     : Thomas Rudolph
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
                    vertex = Integer.parseInt(c[1]) - 1;                    
                    v[vertex].addEdge(Integer.parseInt(c[2]),Integer.parseInt(c[3]));
                    
                    vertex = Integer.parseInt(c[2]) - 1;
                    v[vertex].addEdge(Integer.parseInt(c[1]),Integer.parseInt(c[3]));
                    
                }
                else if( c[0].equals("p") )
                {
                    num_edges    = Integer.parseInt(c[2]);
                    num_vertices = Integer.parseInt(c[3]);
                    
                    v    = new Edge[num_vertices];
                    for (int i = 0; i < num_vertices; i++)
                    {
                        v[i] = new Edge(i+1);
                    }
                }
            }
        } catch (IOException ex) {Logger.getLogger(MyGPS.class.getName()).log(Level.SEVERE, null, ex);}
                
    }
}
