/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binpacker;

/**
 *
 * @author Tom
 */
public class In_nodes {
    private int size;
    private int in_sizes[] = new int[10];
    private int space_used;
    private int num_in = 0;
    
    public In_nodes(int _s)
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
