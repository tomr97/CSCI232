/*
 * Author     : Thomas Rudolph
 * Lab        : 2
 * Date       : 3/8/2018
 * Description: This lab tests various bin packing strategies
 */
package binpacker;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BinPacker {

    private static int   capacity = 75; // Will be replaced with first val of args[]
    private static int   num_vals = 0;
    private static int[] read_in = new int[200];
    private static int[] firstFitArr;
    private static int[] bestFitDecArr;
    private static int[] worstFitDecArr;
    
    private static BufferedReader br;
    
    private static first_fit ff;
    
    private static String file = "C:\\Users\\Tom\\Documents\\CSCI232\\BinPacker\\src\\binpacker\\input.txt";
    private static String path = "C:\\Users\\Tom\\Documents\\CSCI232\\BinPacker\\src\\binpacker\\";
    
    public static void main(String[] args) {
        
        read_file();
        fill_arrs();
        
        capacity = Integer.parseInt(args[0]);
        file     = path + args[1];
        
        
        first_fit_function();
        bestFitDecreasing bfd = new bestFitDecreasing(bestFitDecArr, capacity);
        WorstFitDecreasing wfd = new WorstFitDecreasing(bestFitDecArr, capacity);
    }
    
    /***********************************************************************************
     * Function Name : fill_arrs()
     * Input(s)      : None
     * Output        : None
     * Description   : Function to fill arrays
     **********************************************************************************/
    private static void fill_arrs()
    {
        firstFitArr    = new int[num_vals];
        bestFitDecArr  = new int[num_vals];
        worstFitDecArr = new int[num_vals];
        
        for(int i = 0; i < num_vals; i++)
        {
            firstFitArr[i]    = read_in[i];
            bestFitDecArr[i]  = read_in[i];
            worstFitDecArr[i] = read_in[i]; 
        }
    }
    /***********************************************************************************
     * Function Name : first_fit_function()
     * Input(s)      : None
     * Output        : None
     * Description   : Function to run first-fit function
     **********************************************************************************/
    private static void first_fit_function()
    {
        ff = new first_fit(firstFitArr, (capacity-1));
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
        } catch (FileNotFoundException ex) {Logger.getLogger(BinPacker.class.getName()).log(Level.SEVERE, null, ex);}
        
        String line; 
        int num = 0; // Keeps track of the job number that was just read in
        try {
            // Reads in from file
            while((line = br.readLine()) != null)
            {
                String[] c       = line.split(" ");
                read_in[num_vals++] = Integer.parseInt(c[0]);
                                
            }
        } catch (IOException ex) {
            Logger.getLogger(BinPacker.class.getName()).log(Level.SEVERE, null, ex);
        }
                
    }
}
