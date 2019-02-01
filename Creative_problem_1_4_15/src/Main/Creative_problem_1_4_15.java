/***************************************************************************************
 *  Filename       : Commands.java
 *  Included Files : None
 *  Homework number: 1
 *  Problem        : Creative Problem 1.1.15
 *  Description    : Faster 3 Sum
 *  Author         : Tom Rudolph
 *  Date           : October 12, 2018
 *  Class          : CSCI 232
 *  Lab Section    : 004
 **************************************************************************************/
package Main;

import java.lang.Math;

public class Creative_problem_1_4_15 {

/************************************************************************************
*                                   Variables
***********************************************************************************/
   private static final double a[]       = {0.2, -2.6, 2.4, -4.6, 2.6, 2.2, 7.3, -9.9, 3.3, 2.4, -2.2, 0.2, -3.8, 3.6, 2.6, -3.3, 3.4, 5.5, -11.6, 4.6, 6.1, 3.6, -4.8, 1.2}; 
   private static final double tolerance = 0.000001; // tolerance to say close enough to zero 
   static double arr[]                   = new double[a.length];
/************************************************************************************
*                                   Main
***********************************************************************************/

/***********************************************************************************
* Constructor Name : main()
* Input(s)         : args - String[] from command line
* Description      : 
**********************************************************************************/    
    public static void main(String[] args) {
        sort();
        twoSumFaster();
        threeSum();
    }
    /***********************************************************************************
    *                               Functions
    **********************************************************************************/
	
    /***********************************************************************************
     * Function Name : sort()
     * Input(s)      : None
     * Output        : None
     * Description   : Sorts a[] into arr[] from lowest to highest
     **********************************************************************************/
    private static void sort()
    {
        for ( int i = 0; i < a.length; i++ )
        {
            arr[i] = a[i];
        }
        
        for ( int i = 0; i < ( arr.length - 1 ); i++ )
        {
            for ( int j = i + 1; j < arr.length; j++)
            {
                if( arr[i] > arr[j] )
                {
                    double temp = arr[i];
                    arr[i]      = arr[j];
                    arr[j]      = temp;
                }         
            }
        }
        
        for ( int i = 0; i < a.length; i++ )
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
    
    /***********************************************************************************
     * Function Name : twoSumFaster()
     * Input(s)      : None
     * Output        : None
     * Description   : Uses linear algorithm to count pairs to sum to zero
     **********************************************************************************/
    private static void twoSumFaster()
    {
        int sum0 = 0;
        double temp;
        for ( int i = 0; i < ( arr.length - 1 ); i++ )
        {
            temp = arr[i];
            for ( int j = i + 1; j < arr.length; j++)
            {
                if ( Math.abs((temp + arr[j])) <= tolerance )
                {
                    sum0++;
                }
            }
        }
        System.out.println("Pairs that sum to zero: " + sum0);
    }
    
    
    /***********************************************************************************
     * Function Name : threeSum()
     * Input(s)      : None
     * Output        : None
     * Description   : Quadratic algorithm to find sum of three 
     **********************************************************************************/
    private static void threeSum()
    {
        int sum0 = 0;
        double temp_i, temp_j;
        for ( int i = 0; i < ( arr.length - 2 ); i++ )
        {
            temp_i = arr[i];
            for ( int j = i + 1; j < ( arr.length - 1 ); j++)
            {
                temp_j = arr[j];
                for( int k = j + 1; k < arr.length; k++ )
                {
                    if ( Math.abs((temp_i + temp_j + arr[j])) <= tolerance )
                    {
                        sum0++;
                    }
                }
            }
        }
        System.out.println("Triples that sum to zero: " + sum0);    
    }
}
