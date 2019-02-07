/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab1;


public class jobs {    
        
    /***********************************************************************************
     *                               Variables
     **********************************************************************************/
    private int job_num;
    private int priority;
    private int arrival_time;
    private int duration;
    private int time_left;
    
    
    
        
    /***********************************************************************************
     *                               Constructor
     **********************************************************************************/
	    
    /***********************************************************************************
     * Function Name : jobs
     * Input(s)      : _jobNom      - int to fill job_num
     *                 _priority    - int to fill priority
     *                 _arrivalTime - int to fill arrival_time
     *                 _duration    - int to fill duration
     * Description   : Object to keep track of information about jobs
     **********************************************************************************/
    public jobs(int _jobNom, int _priority, int _arrivalTime, int _duration)
    {
        job_num      = _jobNom;
        priority     = _priority;
        arrival_time = _arrivalTime;
        duration     = _duration;
        
        time_left    = duration;
    }
    
    /***********************************************************************************
     *                                Methods
     **********************************************************************************/
    /***********************************************************************************
     * Function Name : get_job_num()
     * Input(s)      : None
     * Output        : int - return number of job
     * Description   : Function to return
     **********************************************************************************/
    public int get_job_num()
    {
        return job_num;
    }
    
    /***********************************************************************************
     * Function Name : get_priority()
     * Input(s)      : None
     * Output        : int - return priority of job
     * Description   : Function to return priority of jobs
     **********************************************************************************/
    public int get_priority()
    {
        return priority;
    }
    
    /***********************************************************************************
     * Function Name : get_arrival_time()
     * Input(s)      : None
     * Output        : int - arrival time of job
     * Description   : Function to return arrival time of jobs
     **********************************************************************************/
    public int get_arrival_time()
    {
        return arrival_time;
    }
    
    /***********************************************************************************
     * Function Name : get_duration()
     * Input(s)      : None
     * Output        : int - duration of job
     * Description   : Function to return duration
     **********************************************************************************/
    public int get_duration()
    {
        return duration;
    }
    
    /***********************************************************************************
     * Function Name : time_left()
     * Input(s)      : None
     * Output        : None
     * Description   : Function to return time left to run job
     **********************************************************************************/
    public int time_left()
    {
        return time_left;
    }
    
    /***********************************************************************************
     * Function Name : print_job()
     * Input(s)      : None
     * Output        : None
     * Description   : Function to print job info
     **********************************************************************************/
    public void print_job()
    {
        System.out.println(job_num + " " + priority + " " + arrival_time + " " + duration);
    }
    
    /***********************************************************************************
     * Function Name : run()
     * Input(s)      : None
     * Output        : None
     * Description   : Function to run job for 1 second. Decrements by 1 second to say 
     *                    that it has been run for a second
     **********************************************************************************/
    public void run()
    {
        time_left--;
    }
    
    
}
