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
    
    private In_nodes next;
    
    /************************************************************************************
     *                                   Constructor
     ***********************************************************************************/
    public In_nodes(int _s)
    {
        size = _s; 
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
       in_sizes[num_in++]  = _len;
        space_used        += _len;
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
