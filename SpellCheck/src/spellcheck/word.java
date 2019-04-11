/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spellcheck;


public class word {
    
    private String word;
    private word nextWord;
    
    public word(String _w)
    {
        word     = _w;
        nextWord = null;
    }
    
    /***********************************************************************************
     * Function Name : getNext()
     * Input(s)      : None
     * Output        : Word variable to say what the next word is
     * Description   : Getter function for next node in linked list
     * 
     **********************************************************************************/
    public word getNext()
    {
        return nextWord;
    }
    
    /***********************************************************************************
     * Function Name : getWord()
     * Input(s)      : None
     * Output        : String variable for word
     * Description   : Getter function for word
     **********************************************************************************/
    public String getWord()
    {
        return word;
    }
    /***********************************************************************************
     * Function Name : setNext()
     * Input(s)      : _n - word variable to say next node in linked list
     * Output        : None
     * Description   : Setter function for next node in linked list 
     **********************************************************************************/
    public void setNext(word _n)
    {
        nextWord = _n;
    }
}
