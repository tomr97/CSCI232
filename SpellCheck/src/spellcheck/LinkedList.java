
package spellcheck;


public class LinkedList {
    
    word first = null;
    word curr;
    
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
        }
        else // Handle if not first
        {
            word new_word = new word(_s);
            curr.setNext(new_word);
            curr = curr.getNext();
        }
    }
}
