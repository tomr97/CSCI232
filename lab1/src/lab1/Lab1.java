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
import lab1.jobs;

public class Lab1 {
    /***********************************************************************************
     *                               Variables
     **********************************************************************************/
    static String file      = "commands.txt";
    
    static Scanner scan     = new Scanner(System.in);
    static jobs j_queue[]   = new jobs[20]; // Priority queue for jobs
    static jobs j_waiting[] = new jobs[20]; // Jobs waiting to be placed in queue
    
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
     * Output        : 
     * Description   : 
     **********************************************************************************/
    
    private static void read_file(String file_read)
    {
        
    }
}
