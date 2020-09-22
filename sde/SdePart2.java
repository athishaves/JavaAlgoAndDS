package sde;

public class SdePart2 {

    // In an mxn matrix, if a cell contains 0 then update the particular row and column to 0
    // Time Complexity : O(2 * m*n)
    // Space Complexity : O(1)

    public static void setZeroMatrix(int[][] a) {
        int col = 1, rows = a.length, columns = a[0].length;

        for (int i=0; i<rows; i++) {
            if(a[i][0]==0) col = 0;
            for (int j=1; j<columns; j++)
                if(a[i][j]==0) a[i][0] = a[0][j] = 0;
        }

        for (int i=rows-1; i>=0; i--) {
            for(int j=columns-1; j>=1; j--)
                if(a[i][0]==0 || a[0][j]==0) a[i][j] = 0;
            if(col==0) a[i][0] = 0;
        }

        for (int[] array : a) {
            for (int z : array) System.out.print(z + " ");
            System.out.println();
        }
    }



    // Rotate a given array
    // Clockwise => Transpose -> Vertically flip
    // Anti-clockwise => Transpose -> Horizontally flip
    // Time Complexity : O(2 * n*n)
    // Space Complexity : O(1)

    public static void rotateArray(int[][] a, boolean isClockwise) {
        int row = a.length, column = a[0].length;
        int temp;

        // Transpose of a matrix
        for (int i=0; i<row; i++) {
            for (int j=i+1; j<column; j++) {
                // Swap a[i][j] and a[j][i]
                temp = a[i][j];
                a[i][j] = a[j][i];
                a[j][i] = temp;
            }
        }

        if(isClockwise) {

            // Flip the matrix vertically
            for (int j = 0; j < (column >> 1); j++) {
                for (int i = 0; i < row; i++) {
                    temp = a[i][j];
                    a[i][j] = a[i][column - j - 1];
                    a[i][column - j - 1] = temp;
                }
            }

        } else {

            // Flip the matrix horizontally
            for(int i=0; i<(row>>1); i++) {
                for(int j=0; j<column; j++) {
                    temp = a[i][j];
                    a[i][j] = a[row-i-1][j];
                    a[row-i-1][j] = temp;
                }
            }

        }

        for(int[] array : a) {
            for (int z : array) System.out.print(z + " ");
            System.out.println();
        }

    }

}
