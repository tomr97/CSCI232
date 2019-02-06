/*
 * Author     : Thomas Rudolph
 * Lab        : 1
 * Date       : 1/18/218
 * Description: This lab sets up a priority queue for different jobs that will 
 *                 be performed and give an output that is consistent with the
 *                 output required in lab handout
 */
package lab1;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import lab1.jobs;

public class Lab1 {
    /***********************************************************************************
     *                               Variables
     **********************************************************************************/
    static String file       = "C:\\Users\\d92r471\\Documents\\CSCI232\\lab1\\src\\lab1\\commands.txt";
    
    static BufferedReader br = null;
    static jobs j_queue[]    = new jobs[30]; // Priority queue for jobs
    static jobs j_waiting[]  = new jobs[30]; // Jobs waiting to be placed in queue
    
    /***********************************************************************************
     *                               Main Method
     **********************************************************************************/
	    
    /***********************************************************************************
     * Function Name : main()
     * Input(s)      : String[] args - args[0] is the command line argument
     * Output        : None
     * Description   : Main method to initialize code
     **********************************************************************************/
    public static void main(String[] args) {
        read_file(file /*args[0]*/);
        
        
    }
    
    /***********************************************************************************
     *                                Methods
     **********************************************************************************/
    /***********************************************************************************
     * Function Name : read_file()
     * Input(s)      : file_read - String of the file name
     * Output        : None
     * Description   : Function to read in file and set up 
     **********************************************************************************/
    
    private static void read_file(String file_read)
    {
        try {
            br = new BufferedReader(new FileReader(file_read));
        } catch (FileNotFoundException ex) {Logger.getLogger(Lab1.class.getName()).log(Level.SEVERE, null, ex);}
        
        String line; 
        int num = 0; // Keeps track of the job number that was just read in
        try { // Reads in from file
            while((line = br.readLine()) != null)
            {
                String[] c       = line.split(" ");
                j_waiting[num++] = new jobs(Integer.parseInt(c[0]), Integer.parseInt(c[1]), Integer.parseInt(c[2]), Integer.parseInt(c[3])); 
            }
        } catch (IOException ex) {
            Logger.getLogger(Lab1.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
