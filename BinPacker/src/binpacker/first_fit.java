/*
 * Class to run first fit algorithm
 */
package binpacker;


public class first_fit {
    
    private int size;
    private int in_sizes[] = new int[10];
    private int space_used;
    private int num_in = 0;
    
    public first_fit(int _s)
    {
        size = _s;        
    }
    /***********************************************************************************
     * Function Name : add_job()
     * Input(s)      : None
     * Output        : None
     * Description   : Function to run first-fit function
     **********************************************************************************/
    public void add_job(int _len)
    {
        
        
        num_in++;
    }
}
