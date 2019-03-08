/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacker;

import binpacker.In_nodes;


public class bestFitDecreasing {
    
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
     * Constructor Name : bestFitDecreasing()
     * Input(s)         : int[] - _in 
     * Description      : Sets up best fit decreasing function
     **********************************************************************************/
    public bestFitDecreasing(int[] _in, int _c)
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
        System.out.println(" Best Fit Decreasing Results:");
        System.out.println("-----------------------------");
        
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
        
        
        int best_diff;
        int i_best_diff;
        
        for(int i = 1; i < length; i++)
        {
            best_diff   = 100;
            i_best_diff = -1; // Sets index for best difference to be out of bounds
            pointer = start;
            
            do
            {                
                int temp = pointer.get_space_left();
                if(( temp - input_arr[i] ) > 0 && ( temp - input_arr[i] ) < best_diff )
                {
                    best_diff   = temp;
                    i_best_diff = pointer.get_bin();
                    
                }
                pointer = pointer.get_next();
            }while(pointer != null);
            pointer = start;
            
            if(i_best_diff == 0)
            {
                pointer.add_job(input_arr[i]);
            }
            else if(i_best_diff > 0)
            {
                for(int j = 0; j < i_best_diff; j++)
                {
                    pointer = pointer.get_next();
                }
                pointer.add_job(input_arr[i]);
            }
            else
            {
                while(pointer.get_next() != null)
                {
                    pointer = pointer.get_next();
                }
                In_nodes temp = new In_nodes(capacity, num_bins++);
                temp.add_job(input_arr[i]);
                pointer.set_next(temp);
            }
            
        }
        
        display();
        
        
    }
}

