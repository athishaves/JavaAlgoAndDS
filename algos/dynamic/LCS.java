package algos.dynamic;

public class LCS {

    // Longest Common Subsequence
    public static int longestCSRecursion(char[] a, char[] b, int m, int n) {
        if(m==0 || n==0) return 0;
        if(a[m-1]==b[n-1]) return 1 + longestCSRecursion(a, b, m-1, n-1);
        return Math.max(longestCSRecursion(a, b, m-1, n), longestCSRecursion(a, b, m, n-1));
    }


    public static int[][] longestCSgetTable(char[] a, char[] b, int m, int n) {
        int[][] table = new int[m+1][n+1];
        for(int i=0; i<=m; i++) {
            for(int j=0; j<=n; j++) {
                if(i==0 || j==0) table[i][j] = 0;
                else if(a[i-1]==b[j-1]) table[i][j] = 1 + table[i-1][j-1];
                else table[i][j] = Math.max(table[i-1][j], table[i][j-1]);
            }
        }
        return table;
    }


    public static int longestCS(char[] a, char[] b, int m, int n) {
        return longestCSgetTable(a, b, m, n)[m][n];
    }


    public static String printLCS(char[] a, char[] b, int m, int n) {
        int[][] table = longestCSgetTable(a, b, m, n);
        int k = table[m][n];
        char[] lcs = new char[k];
        printMatrix(table);
        while (m>0 && n>0) {
            if(a[m-1]==b[n-1]) {
                lcs[k-1] = a[m-1];
                k--;    m--;    n--;
            } else if(table[m-1][n]>table[m][n-1])  m--;
            else n--;
        }
        return String.valueOf(lcs);
    }



    // Shortest Common SuperString
    public static int shortestCommonSuperstring(char[] a, char[] b, int m, int n) {
        return m + n - longestCS(a, b, m, n);
    }


    public static String printShortestCommonSuperstring(char[] a, char[] b, int m, int n) {
        int[][] table = longestCSgetTable(a, b, m, n);
        int k = m + n - table[m][n];
        char[] lcs = new char[k];
        printMatrix(table);
        while (m>0 && n>0) {
            if(a[m-1]==b[n-1]) {
                lcs[k-1] = a[m-1];
                k--;    m--;    n--;
            } else if(table[m-1][n]>table[m][n-1]) {
                lcs[k-1] = a[m-1];
                k--;    m--;
            } else{
                lcs[k-1] = b[n-1];
                k--;    n--;
            }
        }
        while (m>0) {
            lcs[k-1] = a[m-1];
            k--;    m--;
        }
        while (n>0) {
            lcs[k-1] = a[n-1];
            k--;    n--;
        }
        return String.valueOf(lcs);
    }



    // Longest Common Substring (characters should be consecutive)
    public static int longestCommonSubstring(char[] a, char[] b, int m, int n) {
        int[][] table = new int[m+1][n+1];
        int maxLength = 0;
        for(int i=0; i<=m; i++) {
            for(int j=0; j<=n; j++) {
                if(i==0 || j==0) table[i][j] = 0;
                else if(a[i-1]==b[j-1]) table[i][j] = 1 + table[i-1][j-1];
                else table[i][j] = 0;

                if(table[i][j]>maxLength) maxLength = table[i][j];
            }
        }
        printMatrix(table);
        return maxLength;
    }



    // Longest Repeating Subsequence
    // Example : a = "aabab" => 2
    public static int longestReapeatingSubsequence(char[] a, int m) {
        int[][] table = new int[m+1][m+1];
        for(int i=0; i<=m; i++) {
            for(int j=0; j<=m; j++) {
                if(i==0 || j==0) table[i][j] = 0;
                else if(a[i-1]==a[j-1] && i!=j) table[i][j] = 1 + table[i-1][j-1];
                else table[i][j] = Math.max(table[i-1][j], table[i][j-1]);
            }
        }
        return table[m][m];
    }




    // Number of char deletions/additions made to a string to form a palindrome
    public static int formPalindrome(char[] a, int n) {
        return n - longestPalindromicSubsequence(a, n);
    }



    // Longest length of Palindromic subsequence in a string
    public static int longestPalindromicSubsequence(char[] a, int n) {
        char[] rev = new char[n];
        for(int i=0; i<n; i++) rev[i] = a[n-1-i];
        return longestCS(a, rev, n, n);
    }




    // Minimum steps (Addition or Deletion) to convert string a to b
    // Steps :  A ---> LCS ---> B
    //           delete    add
    //          m - lcs   n - lcs
    public static int convertAtoB(char[] a, char[] b, int m, int n) {
        return m + n - 2*longestCS(a, b, m, n);
    }




    // Is a string subsequence of another
    //  -->     min(m,n) should be equal to lcs
    public static boolean isSubsequence(char[] a, char[] b, int m, int n) {
        return Math.min(m,n)==longestCS(a,b,m,n);
    }



    private static void printMatrix(int[][] table) {
        for (int[] array : table) {
            for (int a : array) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }

}
