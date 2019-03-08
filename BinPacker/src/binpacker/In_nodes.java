/***************************************************************************************
 *  Filename       : In_nodes.java
 *  Included Files : 
 *  Description    : 
 *  Author         : Thomas Rudolph
 *  Date           : 
 *  Functions      : public void add_job(int _len)
 *                   get_space_left()
 *                   
 **************************************************************************************/
package binpacker;

/**
 *
 * @author Tom
 */
public class In_nodes {
    /************************************************************************************
     *                                   Variables
     ***********************************************************************************/
    private int size;
    private int in_sizes[] = new int[50];
    private int space_used;
    private int num_in = 0;
    private int bin_number;
    
    private In_nodes next;
    
    /************************************************************************************
     *                                   Constructor
     ***********************************************************************************/
    public In_nodes(int _s, int _n)
    {
        size       = _s; 
        bin_number = _n;
        next       = null;
    }
    /************************************************************************************
     *                                   Functions
     ***********************************************************************************/
    
    /***********************************************************************************
     * Function Name : add_job()
     * Input(s)      : None
     * Output        : None
     * Description   : Function to run first-fit function
     **********************************************************************************/
    public void add_job(int _len)
    {
       //System.out.println("Job : " + _len + " added to bin " + bin_number);
       in_sizes[num_in++]  = _len;
       space_used         += _len;
    }
    
    /***********************************************************************************
     * Function Name : fill_arr()
     * Input(s)      : None
     * Output        : int[] - filled array
     * Description   : Return filled array
     **********************************************************************************/
    public int[] fill_arr()
    {
        int[] ret_arr = new int[num_in];
        
        for(int i = 0; i < num_in; i++)
        {
            ret_arr[i] = in_sizes[i];
        }
        
        return ret_arr;
    }
    
    /***********************************************************************************
     * Function Name : get_bin()
     * Input(s)      : None
     * Output        : int - bin number
     * Description   : Return bin's number
     **********************************************************************************/
    public int get_bin()
    {
        return bin_number;
    }
    
    /***********************************************************************************
     * Function Name : get_next()
     * Input(s)      : None
     * Output        : In_nodes - next node
     * Description   : Return next node
     **********************************************************************************/
    public In_nodes get_next()
    {
        return next;
    }
    
    /***********************************************************************************
     * Function Name : get_num_inputs()
     * Input(s)      : None
     * Output        : int - number of inputs
     * Description   : Method to fill a pointer to an int array with the jobs
     **********************************************************************************/
    public int get_num_inputs()
    {
        return num_in;
    }
    /***********************************************************************************
     * Function Name : get_space_left()
     * Input(s)      : None
     * Output        : int - space left in 
     * Description   : Return space that is still available 
     **********************************************************************************/
    public int get_space_left()
    {
        return (size - space_used);
    }
    
    /***********************************************************************************
     * Function Name : set_next()
     * Input(s)      : In_nodes _next - pointer to next node
     * Output        : None 
     * Description   : Function to set pointer to  
     **********************************************************************************/
    public void set_next(In_nodes _next)
    {
        next = _next;
    }
}
