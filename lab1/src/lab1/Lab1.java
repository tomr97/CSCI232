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
    //static jobs j_done[]     = new jobs[30]; // Jobs that have been completed
    
    static int num_jobs;  // Number of jobs that there are
    static int jobs_left; // Jobs left to be performed
    
    static int jobs_queue = 0; 
    static int time       = 0;  // Current time 
    
    
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
        num_jobs = read_file(file /*args[0]*/);
        
        
    }
    
    /***********************************************************************************
     *                                Methods
     **********************************************************************************/
    
    /***********************************************************************************
     * Function Name : prioritize()
     * Input(s)      : None
     * Output        : None
     * Description   : Organizes jobs by priority in priority queue (j_queue[]). Sets 
     *                    order within the queue and adds jobs to the queue when they 
     *                    arrive
     **********************************************************************************/
    private static void prioritize()
    {
        // Adds jobs when they arrive
        for ( int i = 0; i < num_jobs; i++ )
        {
            if(j_waiting[i] != null)
            {
                if(j_waiting[i].get_arrival_time() >= time)
                {
                    j_queue[jobs_queue] = j_waiting[i];
                    j_waiting[i]        = null;
                }
            }
        }
        
        jobs j_temp;
        
        // organize jobs in queue
        if ( jobs_queue > 1 ) // If more than 1 job in queue, it needs to be organized
        {
            for ( int i = 0; i < ( jobs_queue - 1 ); i++ )
            {
                for ( int j = i + 1; j < jobs_queue; j++ )
                {
                    if( j_queue[i].get_priority() > j_queue[j].get_priority() )
                    {
                        j_temp     = j_queue[i];
                        j_queue[i] = j_queue[j];
                        j_queue[j] = j_temp;
                    }
                }
            }
        }
    }
    
    /***********************************************************************************
     * Function Name : read_file()
     * Input(s)      : file_read - String of the file name
     * Output        : None
     * Description   : Function to read in file and set up jobs in waiting array
     **********************************************************************************/
    
    private static int read_file(String file_read)
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
                num_jobs++;
                jobs_left++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Lab1.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return num;
    }
    
    /***********************************************************************************
     * Function Name : run()
     * Input(s)      : None
     * Output        : None
     * Description   : Function to run code
     **********************************************************************************/
    private static void run()
    {
        while(num_jobs > 0)
        {
            // Calls function to set priority for jobs
            prioritize();
            
            time++;
        }
    }
    
    /***********************************************************************************
     * Function Name : run_job()
     * Input(s)      : None
     * Output        : None
     * Description   : Function to run code
     **********************************************************************************/
    private static void run_job()
    {
        // Runs job for 1 second
        j_queue[0].run();
        
        if ( j_queue[0].get_time_left() <= 0 )
        {
            j_queue[0] = null;
            jobs_left--;
            
            //Shifts jobs left in queue
            for ( int i = 0; i < jobs_left; i++ )
            {
                j_queue[i] = j_queue[i+1];
            }
        }
    }
}
