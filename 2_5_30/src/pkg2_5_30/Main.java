/*******************************************************************************
 * Author:  Tom Rudolph
 * Date:    4/5/2019
 * HW:      3
 * Problem: 2.5.30
 * 
 * Comments: True. Since all rows are sorted, they will remain so.
 ******************************************************************************/
package pkg2_5_30;


public class Main {
    
    static int[][] matrix = {{24,  6, 12, 25, 36},
                             { 9,  7, 23, 14,  8},
                             { 2, 16, 44, 59, 41},
                             { 18, 27, 62, 33, 45},
                             { 31, 18, 21, 17, 19}};

    
    public static void main(String[] args) {
        System.out.println("Unsorted array:");
        display();
        System.out.println();
        
        System.out.println("Rows sorted:");
        sort_row();
        display();
        System.out.println();
        
        System.out.println("COlumns sorted:");
        sort_column();
        display();
    }
    
    public static void display()
    {
        for ( int i = 0; i < 5; i++ )
        {
            for ( int j = 0; j < 5; j++ )
            {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }
    
    public static void sort_row()
    {
        int temp;
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                for(int k = j+1; k < 5; k++ )
                {
                    if(matrix[i][j] > matrix[i][k])
                    {
                        temp         = matrix[i][j];
                        matrix[i][j] = matrix[i][k];
                        matrix[i][k] = temp;
                    }
                }
            }
        }
    }
    
    public static void sort_column()
    {
        int temp;
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                for(int k = j+1; k < 5; k++ )
                {
                    if(matrix[j][i] > matrix[k][i])
                    {
                        temp         = matrix[j][i];
                        matrix[j][i] = matrix[k][i];
                        matrix[k][i] = temp;
                    }
                }
            }
        }
    }
}
