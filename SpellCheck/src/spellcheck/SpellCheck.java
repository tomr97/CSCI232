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
import java.util.logging.Level;
import java.util.logging.Logger;

public class SpellCheck {

    private static String file = System.getProperty("user.dir") + "\\src\\spellcheck\\words.txt";
    private static String path = System.getProperty("user.dir") + "\\src\\spellcheck\\";

    private static BufferedReader br;
    private static BufferedReader br2;
    
    private static LinkedList[] dictionary = new LinkedList[27];
    private static LinkedList   myDoc      = new LinkedList();  // LL for document with misspelled words

    public static void main(String[] args) throws IOException {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        System.out.println(file);
        System.out.println(path);
        
        setUp();
        
        try {read_file();} 
        catch (IOException ex) {Logger.getLogger(SpellCheck.class.getName()).log(Level.SEVERE, null, ex);}

        read_mydoc();
        checker();
        //check_function();
    }
    
    private static void checker()
    {/*
        myDoc.reset_pointer();
        
        System.out.println(myDoc.get_pointer());
        
        while(myDoc.has_more())
        {
            myDoc.move_pointer();
            System.out.println(myDoc.get_pointer());
        }*/
        
        dictionary[6].reset_pointer();
        System.out.println(dictionary[6].get_pointer());
        
        while(dictionary[6].has_more())
        {
            dictionary[6].move_pointer();
            System.out.println(dictionary[6].get_pointer());
        }
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
            s = s.toLowerCase();
            if( ((s.charAt(0) - 'a') > 0 && ((s.charAt(0) - 'a') < 26 )))
            {
                i = s.charAt(0) - 'a';
            }
            else
            {
                i = 26;
            }
            
            dictionary[i].reset_pointer();
            word_exists = false;
            
            do{
                if(dictionary[i].get_pointer().equals(myDoc.get_pointer()))
                {
                    word_exists = true;
                }
                else
                {
                    dictionary[i].move_pointer();
                }
            }while((dictionary[i].get_pointer() != null) && !word_exists);
            
            if(word_exists)
            {
                System.out.println(myDoc.get_pointer());
            }
            else
            {
                System.out.println("Wrong word: " + myDoc.get_pointer());
            }
            
            myDoc.move_pointer();
            
        }while(myDoc.get_pointer() != null);
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
