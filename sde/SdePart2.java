package sde;

import java.util.ArrayList;

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



    // Print Pascal Triangle

    public static ArrayList<ArrayList<Integer>> pascalTriangle(int n) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        if(n==0) return result;

        ArrayList<Integer> curList = new ArrayList<>(1), prevList;

        curList.add(1);
        result.add(curList);
        prevList = curList;

        for (int i=1; i<n; i++) {
            curList = new ArrayList<>(i+1);

            for (int j=0; j<=i; j++) {
                if(j==0 || j==i) curList.add(1);
                else curList.add(prevList.get(j-1) + prevList.get(j));
            }

            result.add(curList);
            prevList = curList;
        }

        return result;
    }


    // Print particular row in Pascal's Triangle

    public static void printRowPascal(int row) {
        for (int i=0; i<=row; i++) System.out.print(comb(row,i) + " ");
        System.out.println();
    }

    private static int comb(int n, int r) {
        if(r==n || r==0) return 1;
        if(r==n-1 || r==1) return n;

        if(r>(n>>1)) return comb(n, n-r);

        int num = 1, denom = 1;
        while (r>0) {
            num *= n--;
            denom *= r--;
        }

        return (int) (num/denom);
    }


    // Print number at particular row and column in pascal's triangle

    public static int numberAtRowColumn(int r, int c) {
        return comb(r,c);
    }



    // Next permutation
    // [1,2,3] => [1,3,2]

    public static int[] nextPermutation(int[] a, int n) {
        if(a==null || a.length<=1) return a;

        int i = n-2;
        while (i>=0 && a[i]>=a[i+1]) i--;
        if(i>=0) {
            int j = n-1;
            while (a[j]<=a[i]) j--;
            swap(a, i, j);
        }
        reverse(a,i+1,n-1);
        return a;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void reverse(int[] a, int i, int j) {
        while (i<j) swap(a,i++,j--);
    }

}
