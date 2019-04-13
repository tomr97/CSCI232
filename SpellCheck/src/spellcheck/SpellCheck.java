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
        
        if( count >= 1 )
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
                
                if(count < 1)
                {
                    suggested += c;
                }
            }
            
            System.out.println("2 Suggested: " + suggested);
            
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
            
            System.out.println("Suggested: " + suggested);
            
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
     * Function Name : fix_word()
     * Input(s)      : _s - string that holds incorrect word
     * Output        : None
     * Description   : Function to find correct word
     **********************************************************************************/
    private static void fix_word(String _s)
    {
        suggestion = 0;
        
        String triple_suggestion = check_triple(_s);
        System.out.println("3 Suggestion " + triple_suggestion);
        
        if(triple_suggestion == null)
        {
            String double_suggestion = check_double(_s);
            System.out.println("2 Suggestion " + double_suggestion);
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

        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


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

        try {
            String file2 = path + "mydoc.txt";
            br2 = new BufferedReader(new FileReader(file2));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

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
