/***************************************************************************************
 *  Filename       : first_fit.java
 *  Included Files : 
 *  Description    : 
 *  Author         : Thomas Rudolph
 *  Date           : 
 *  Functions      : 
 *                   
 **************************************************************************************/
package binpacker;

import binpacker.In_nodes;


public class first_fit {
    /************************************************************************************
     *                                   Variables
     ***********************************************************************************/
    private int[] input_arr;
    private int   length;
    private int   capacity;
    private int   num_bins = 0;
    
    
    private In_nodes start;
    private In_nodes pointer;
    /************************************************************************************
     *                                   Constructor
     ***********************************************************************************/

    /***********************************************************************************
     * Constructor Name : first_fit()
     * Input(s)         : int[] - _in 
     * Description      : Sets up first fit function
     **********************************************************************************/
    public first_fit(int[] _in, int _c)
    {
        input_arr = _in;
        length    = input_arr.length;
        capacity  = _c;
        
        run();
    }
    
    /************************************************************************************
     *                                   Methods
     ***********************************************************************************/
	
    /***********************************************************************************
     * Function Name : display()
     * Input(s)      : None
     * Output        : None
     * Description   : Method to print out which bin each job is in
     **********************************************************************************/
    private void display()
    {
        System.out.println("\n");
        System.out.println(" First Fit Results:");
        System.out.println("--------------------");
        
        pointer = start;
        
        do{
            int length = pointer.get_num_inputs();
            int[] val = pointer.fill_arr();
            
            System.out.print("Values in bin " + pointer.get_bin() + " : ");
            
            for(int i = 0; i < length; i++)
            {
                System.out.print(val[i] + " ");
            }
            System.out.println();
            
            pointer = pointer.get_next();
            
        }while(pointer != null);
    }
    
    /***********************************************************************************
     * Function Name : run()
     * Input(s)      : None
     * Output        : None
     * Description   : Function to run first fit
     **********************************************************************************/
    private void run()
    {
        start   = new In_nodes(capacity, num_bins++);
        start.add_job(input_arr[0]);
        pointer = start;
        
        boolean job_added; 
        
        for (int i = 1; i < length; i++)
        {
            job_added = false;
            // Point at first bin
            pointer = start;
            do
            {
                // If enough space in pointer
                if( (pointer.get_space_left() - input_arr[i]) >= 0 )
                {
                    pointer.add_job(input_arr[i]);
                    job_added = true;
                }
                else
                {
                    if(pointer.get_next() != null) // Getting stuck here
                    {
                        pointer = pointer.get_next();
                        
                    }
                    else
                    {
                        In_nodes temp = new In_nodes(capacity, num_bins++); // Create new In_nodes
                        temp.add_job(input_arr[i]); // Adds job to In_nodes
                        pointer.set_next(temp);       // Sets next 
                        pointer = pointer.get_next();
                        job_added = true;
                    }
                }
            }while(!job_added);
        }
        display();
    }
}
