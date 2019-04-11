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
    
    private static LinkedList[] dictionary = new LinkedList[27];

    public static void main(String[] args) {
        System.out.println("Working Directory = " + System.getProperty("user.dir"));
        System.out.println(file);
        System.out.println(path);
        
        setUp();
        
        try {read_file();} 
        catch (IOException ex) {Logger.getLogger(SpellCheck.class.getName()).log(Level.SEVERE, null, ex);}
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

        // Reads in from file
        while((line = br.readLine()) != null)
        {
            String[] c       = line.split(" ");
            
            String s = c[0];
            if( Character.isLetter(s.charAt(0)) )
            {
                String _s = s.toLowerCase();
                char _c = _s.charAt(0); // Get first letter
                int  i  = _c - 'a';    // Subtract 'a' to get useable index
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
