/*******************************************************************************
 * Authors     : Thomas Rudolph, Connor Van Meter
 * Lab         : 4
 * Date        : 4/12/2019
 * Description : This lab spell checks a file and puts the spell checked words
 *                  into a new file.
*******************************************************************************/

package spellcheck;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpellCheck {

    private static String file = System.getProperty("user.dir") + "\\src\\spellcheck\\words.txt";
    private static String path = System.getProperty("user.dir") + "\\src\\spellcheck\\";
    
    private static S2tring f1 = "", f2 = "";

    private static BufferedReader br;
    private static BufferedReader br2;
    
    private static LinkedList[] dictionary = new LinkedList[27];
    private static LinkedList   myDoc      = new LinkedList();  // LL for document with misspelled words
    
    private static PrintWriter outputFile;
    private static Scanner     scan;
    
    //int to keep track of suggestion number
    private static int suggestion;
    

    public static void main(String[] args) throws IOException {
        //System.out.println("Working Directory = " + System.getProperty("user.dir"));
        //System.out.println(file);
        //System.out.println(path);
        
        f1 = path + args[0];
        f2 = path + args[1];
        
        //Sets up output file
        outputFile = new PrintWriter("mydoc-checked.txt", "UTF-8");
        
        //Set up scanner
        scan = new Scanner(System.in);
        
        setUp();
        
        try {read_file();} 
        catch (IOException ex) {Logger.getLogger(SpellCheck.class.getName()).log(Level.SEVERE, null, ex);}

        read_mydoc();
        
        check_function();
        
        //closes output file
        outputFile.close();
    }
    
    
    /***********************************************************************************
     * Function Name : check_function()
     * Input(s)      : None
     * Output        : None
     * Description   : Function to make sure all of the words are in the dictionary
     **********************************************************************************/
    private static void check_function()
    {
        boolean word_exists = true;
        int i = 0;
        String s;
        
        myDoc.reset_pointer();
            
        
        do{
            s = myDoc.get_pointer();
            String _s = s.toLowerCase();
            //Find index of dictionary array to read from
            if(_s.charAt(0) == 'a')
            {
                i = 0;
            }
            else if( ((_s.charAt(0) - 'a') > 0 && ((_s.charAt(0) - 'a') < 26 )))
            {
                i = _s.charAt(0) - 'a';
            }
            else
            {
                i = 26;
            }
            
            //have dictionary point to beginning
            dictionary[i].reset_pointer();
            word_exists = false;
            
            // Compare to words in dictionary while there are more words and it
            //    has not found specified word
            do{
                if(dictionary[i].get_pointer().equals(myDoc.get_pointer()))
                {
                    word_exists = true;
                }
            }while((dictionary[i].move_pointer()) && !word_exists);
            
            // Prints word if it exists, otherwise calls function to find correct 
            //    word
            if(word_exists)
            {
                //System.out.println(myDoc.get_pointer());
                outputFile.println(myDoc.get_pointer());
            }
            else
            {
                //System.out.println("Wrong word: " + myDoc.get_pointer());
                fix_word(myDoc.get_pointer());
            }
            
        }while(myDoc.move_pointer());
    }  
    
    /***********************************************************************************
     * Function Name : check_double
     * Input(s)      : _s - the word to check
     * Output        : String - suggested word
     * Description   : Check if double letters
     **********************************************************************************/
    private static String check_double(String _s)
    {
        char prevLetter = _s.charAt(0);
        int count = 0;
        
        
        for(int k = 1; k < _s.length(); k++)
        {
            if(_s.charAt(k) == prevLetter)
            {
                count++;
                if(count == 1)
                {
                    k = _s.length(); // Break out of for loop
                    //System.out.println("double letters");
                }
            }
            else
            {
                count = 0;
                prevLetter = _s.charAt(k);
            }
        }
        
        if( count >= 1 )
        {
            //System.out.println("double letters");
            
            
            //Find suggestion
            String suggested = "";
            suggested       += _s.charAt(0);
            
            int letter_index;
            if ( ((_s.charAt(0) - 'a') >= 0 ) && ((_s.charAt(0) - 'a') < 26 ) )
            {
                letter_index = _s.charAt(0) - 'a';
            }
            else
            {
                letter_index = 26;
            }
            
            prevLetter = _s.charAt(0);
            count = 0;  
            char c;
            
            //find word suggestion;
            for (int k = 1; k < _s.length(); k++ )
            {
                c = _s.charAt(k);
                if( c == prevLetter)
                {
                    count++;
                }
                else
                {
                    count      = 0;
                    prevLetter = c;
                }
                
                if(count < 1)
                {
                    suggested += c;
                }
            }
            
            //System.out.println("2 Suggested: " + suggested);
            
            //Check if suggested word exists
            //have dictionary point to beginning
            dictionary[letter_index].reset_pointer();
            boolean word_exists = false;
            
            // Compare to words in dictionary while there are more words and it
            //    has not found specified word
            do{
                if(dictionary[letter_index].get_pointer().equals(suggested))
                {
                    word_exists = true;
                }
            }while((dictionary[letter_index].move_pointer()) && !word_exists);
            
            // Returns word if it exists
            if(word_exists)
            {
                return suggested;
            }           
            
        }
        
        return null;
    }
    
    /***********************************************************************************
     * Function Name : check_triple
     * Input(s)      : _s - the word to check
     * Output        : String - suggested word
     * Description   : Check if triple letters
     **********************************************************************************/
    private static String check_triple(String _s)
    {
        char prevLetter = _s.charAt(0);
        int count = 0;
        
        
        for(int k = 1; k < _s.length(); k++)
        {
            if(_s.charAt(k) == prevLetter)
            {
                count++;
                if(count == 2)
                {
                    k = _s.length(); // Break out of for loop
                    //System.out.println("Triple letters");
                }
            }
            else
            {
                count = 0;
                prevLetter = _s.charAt(k);
            }
        }
        
        if( count == 2 )
        {
            //System.out.println("triple letters");
            
            
            //Find suggestion
            String suggested = "";
            suggested       += _s.charAt(0);
            
            int letter_index;
            if ( ((_s.charAt(0) - 'a') >= 0 ) && ((_s.charAt(0) - 'a') < 26 ) )
            {
                letter_index = _s.charAt(0) - 'a';
            }
            else
            {
                letter_index = 26;
            }
            
            prevLetter = _s.charAt(0);
            count = 0;  
            char c;
            
            //find word suggestion;
            for (int k = 1; k < _s.length(); k++ )
            {
                c = _s.charAt(k);
                if( c == prevLetter)
                {
                    count++;
                }
                else
                {
                    count      = 0;
                    prevLetter = c;
                }
                
                if(count < 2)
                {
                    suggested += c;
                }
            }
            
            //System.out.println("Suggested: " + suggested);
            
            //Check if suggested word exists
            //have dictionary point to beginning
            dictionary[letter_index].reset_pointer();
            boolean word_exists = false;
            
            // Compare to words in dictionary while there are more words and it
            //    has not found specified word
            do{
                if(dictionary[letter_index].get_pointer().equals(suggested))
                {
                    word_exists = true;
                }
            }while((dictionary[letter_index].move_pointer()) && !word_exists);
            
            // Returns word if it exists
            if(word_exists)
            {
                return suggested;
            }           
            
        }
        
        return null;
    }

    /***********************************************************************************
     * Function Name : check_typo
     * Input(s)      : _s    - the word to check
     *                 _up   - when the word increments
     *                 _down - when the word decrements
     * Output        : None
     * Description   : Check if typo
     **********************************************************************************/
    private static String[] check_typo(String _s, String _up, String _down)
    {
        char prevLetter = _s.charAt(0);
        int count = 0;

        _up   = "";
        _down = "";

        //store copy of String _s
        for(int k = 0; k < _s.length(); k++)
        {
            _up   += _s.charAt(k);
            _down += _s.charAt(k);
        }

        for(int k = 1; k < _s.length(); k++)
        {
            if(_s.charAt(k) == prevLetter)
            {
                count = k;
                k     = _s.length();
            }
            else
            {
                prevLetter = _s.charAt(k);
            }
        }

        if (count > 1)
        {
            boolean word_exists = false;
            boolean stay        = true;
            //System.out.println("Char: " + (_down.charAt(0) - 'a'));
            dictionary[_down.charAt(0) - 'a'].reset_pointer();
            while(_down.charAt(count + 1) > 'a' && stay)
            {
                //System.out.println(_down.charAt(count));
                _down = _down.substring(0, count + 1) + (char)(_down.charAt(count + 1) - 1) + _down.substring(count + 2);
                //System.out.println(_down);


                //Check if suggested word exists
                //have dictionary point to beginning
                dictionary[_down.charAt(0) - 'a'].reset_pointer();


                // Compare to words in dictionary while there are more words and it
                //    has not found specified word
                do{
                    if(dictionary[_down.charAt(0) - 'a'].get_pointer().equals(_down))
                    {
                        word_exists = true;
                        stay = false;
                        //System.out.println("Word exists" + _down);
                    }
                }while((dictionary[_down.charAt(0) - 'a'].move_pointer()) && !word_exists);
            }

            // Returns word if it exists
            if(!word_exists)
            {
                //System.out.println("Null");
                _down = null;
            }
            
            stay = true;
            dictionary[_up.charAt(0) - 'a'].reset_pointer();
            while(_up.charAt(count+1) < 'z' && stay)
            {
                _up = _up.substring(0, count + 1) + (char)(_up.charAt(count + 1) + 1);// + _up.substring(count + 2);
                //System.out.println("Up: " + _up);

                //Check if suggested word exists
                //have dictionary point to beginning
                dictionary[_up.charAt(0) - 'a'].reset_pointer();
                
                word_exists = false;

                // Compare to words in dictionary while there are more words and it
                //    has not found specified word
                do{
                    if(dictionary[_up.charAt(0) - 'a'].get_pointer().equals(_up))
                    {
                        word_exists = true;
                        stay        = false;
                    }
                }while((dictionary[_up.charAt(0) - 'a'].move_pointer()) && !word_exists);
            }//*/

            // Returns word if it exists
            if(!word_exists)
            {
                _up = null;
            }
        }
        else
        {
            _up   = null;
            _down = null;
        }
        
        String ret_val[] = {_up, _down};
        return ret_val;
    }
    
    /***********************************************************************************
     * Function Name : fix_word()
     * Input(s)      : _s - string that holds incorrect word
     * Output        : None
     * Description   : Function to find correct word
     **********************************************************************************/
    private static void fix_word(String _s)
    {
        suggestion = 0;
        
        String triple_suggestion = check_triple(_s);
        //System.out.println("3 Suggestion " + triple_suggestion);
        

        String double_suggestion = check_double(_s);
        //System.out.println("2 Suggestion " + double_suggestion);


        String check_up = "", check_down = "";

        String checker[] = check_typo(_s, check_up, check_down );
        
        //System.out.println("-----\n" + check_down + "\n-----");
        
        System.out.println(_s + ": did you mean:");
        
        if(triple_suggestion != null && !triple_suggestion.equals(""))
        {
            System.out.println("1. " + triple_suggestion);
        }
        if(double_suggestion != null && !double_suggestion.equals("")) 
        {
            System.out.println("2. " + double_suggestion);
        }
        if(checker[0] != null && !checker[0].equals(""))
        {
            System.out.println("3. " + checker[0]);
        }
        if(checker[1] != null && !checker[1].equals(""))
        {
            System.out.println("4. " + checker[1]);
        }
        System.out.println("0. something else\n");
        
        String val = scan.next();
        
        if(val.equals("1"))
        {
            outputFile.println(triple_suggestion);
        }
        else if(val.equals("2"))
        {
            outputFile.println(double_suggestion);
        }
        else if(val.equals("3"))
        {
            outputFile.println(checker[0]);
        }
        else if(val.equals("4"))
        {
            outputFile.println(checker[1]);
        }
        else if(val.equals("0"))
        {
            System.out.println("Write correct word: ");
            String word = scan.next();
            outputFile.println(word);
        }
        
        
    }
    
    /***********************************************************************************
     * Function Name : read_file()
     * Input(s)      : None
     * Output        : None
     * Description   : Function to read in from file
     **********************************************************************************/
    private static void read_file() throws IOException
    {

        br = new BufferedReader(new FileReader(f1));


        String line;
        int j = 0;

        // Reads in from file
        while((line = br.readLine()) != null)
        {
            //System.out.println(++j);
            String[] c       = line.split(" ");
            
            String s = c[0];
            if( Character.isLetter(s.charAt(0)) )
            {
                String _s = s.toLowerCase();
                char   _c = _s.charAt(0); // Get first letter
                int    i  = _c - 'a';    // Subtract 'a' to get useable index
                dictionary[i].addNode(s); // add the letter to the dictionary               
            }
            else
            {
                dictionary[dictionary.length-1].addNode(s); // Add non letters 
                                                            //   to end of the dictionary
            }
        }//*/

    }
      
    
    /***********************************************************************************
     * Function Name : read_mydoc()
     * Input(s)      : None
     * Output        : None
     * Description   : Function to read in file that we want spell checked
     **********************************************************************************/
    private static void read_mydoc() throws IOException {

        String file2 = path + "mydoc.txt";
        br2 = new BufferedReader(new FileReader(f2));

        String line;

        // Reads in from file
        while((line = br2.readLine()) != null)
        {
            String[] c       = line.split(" ");
            
            myDoc.addNode(c[0].toLowerCase());
            //System.out.println(c[0]);
            
        }


    }

    
    /***********************************************************************************
     * Function Name : setUp()
     * Input(s)      : None
     * Output        : None
     * Description   : Function to set up array for the dictionary
     **********************************************************************************/
    private static void setUp()
    {
        for( int i = 0; i < dictionary.length; i++ )
        {
            dictionary[i] = new LinkedList();
        }
    }
    
}
