
package spellcheck;


public class LinkedList {
    
    word first = null;
    word curr;
    word pointer;
    
    public LinkedList()
    {
        
    }
    
    /***********************************************************************************
     * Function Name : addNode()
     * Input(s)      : _s - String variable to say what word we are putting in the 
     *                      dictionary
     * Output        : None
     * Description   : Adds words to the dictionary 
     * @param _s
     **********************************************************************************/
    public void addNode(String _s)
    {
        if(first == null) // handle if first word
        {
            first = new word(_s);
            curr  = first;
            pointer = first;
        }
        else // Handle if not first
        {
            word new_word = new word(_s);
            curr.setNext(new_word);
            curr = curr.getNext();
        }
    }
    /***********************************************************************************
     * Function Name : get_pointer()
     * Input(s)      : None
     * Output        : String - String that pointer points at
     * Description   : Function to return word that pointer points at
     * @return 
     **********************************************************************************/
    public String get_pointer()
    {
        return pointer.getWord();
    }   
    
    /***********************************************************************************
     * Function Name : has_more()
     * Input(s)      : None
     * Output        : boolean - variable to say if there are more words in list
     * Description   : Function to say if there are more words if list. Returns true if
     *                    curr.getNext() != null
     * @return 
     **********************************************************************************/
    public boolean has_more(){
        
        if(pointer.getNext() == null)
        {
            return false;
        }
        return true;
    }
    /***********************************************************************************
     * Function Name : move_pointer()
     * Input(s)      : None
     * Output        : boolean - says if pointer had been moved
     * Description   : Function to move pointer to next index
     * @return 
     **********************************************************************************/
    public boolean move_pointer()
    {
        if( pointer.getNext() != null)
        {
            pointer = pointer.getNext();
            return true;
        }
        return false;
    }
    /***********************************************************************************
     * Function Name : reset_pointer()
     * Input(s)      : None
     * Output        : None
     * Description   : Function to move pointer back to start
     **********************************************************************************/
    public void reset_pointer()
    {
        pointer = first;
    }
}
