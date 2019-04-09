/*******************************************************************************
 * Authors     : Thomas Rudolph, Connor Van Meter
 * Lab         : 4
 * Date        : 4/12/2019
 * Description : This lab spell checks a file and puts the spell checks words
 *                  into a new file.
*******************************************************************************/

package spellcheck;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class SpellCheck {

    private static String file = "C:\\Users\\Tom\\Documents\\CSCI232\\MyGPS\\src\\mygps\\gz.gr";
    private static String path = "C:\\Users\\Tom\\Documents\\CSCI232\\MyGPS\\src\\mygps\\";

    private static BufferedReader br;

    public static void main(String[] args) {
        // TODO code application logic here
    }

    /***********************************************************************************
     * Function Name : read_file()
     * Input(s)      : None
     * Output        : None
     * Description   : Function to read in from file
     **********************************************************************************/
    private static void read_file()
    {

        try {
            br = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        String line;

        /*// Reads in from file
        while((line = br.readLine()) != null)
        {
            String[] c       = line.split(" ");
        }*/

    }
    
}
