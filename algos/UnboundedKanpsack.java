package algos;

public class UnboundedKanpsack {

    // Knapsack --> Given list of item (weights and profits) and weight constraint.. (repitition is allowed).
    // What is the maximum profit we get
    public int knapsackRecursion(int[] w, int[] p, int n, int maxW) {
        if(n==0 || maxW==0) return 0;
        if(w[n-1]>maxW) return knapsackRecursion(w, p, n-1, maxW);
        return Math.max(knapsackRecursion(w, p, n-1, maxW), // Choosing item
                p[n-1] + knapsackRecursion(w, p, n, maxW - w[n-1]));   // Not choosing item
    }

    public int knapsackTable(int[] w, int[] p, int n, int maxW) {
        int[][] table = new int[n+1][maxW+1];
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=maxW; j++) {
                if(i==0 || j==0) table[i][j] = 0;
                else if(w[i-1]>j) table[i][j] = table[i-1][j];
                else table[i][j] = Math.max(table[i-1][j], p[i-1] + table[i][j-w[i-1]]);
            }
        }
        return table[n][maxW];
    }



    // Rod cutting problem
    // Sarfaraz : Same unbounded knapsack.. no change
    // But dealing with rods.. That's it
    // array [1..rodLength]
    // n == rodLength

    public int rodCutting(int[] p, int n, int rodLength) {
        int[][] table = new int[n+1][rodLength+1];
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=rodLength; j++) {
                if(i==0 || j==0) table[i][j] = 0;
                else if(i>j) table[i][j] = table[i-1][j];
                else table[i][j] = Math.max(table[i-1][j], p[i-1] + table[i][j-i]);
            }
        }
        return table[n][rodLength];
    }



    // minimum coins required for exchanging coin for a given sum
    public int coinExchange(int[] c, int n, int maxCoin) {
        int[][] table = new int[n+1][maxCoin+1];
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=maxCoin; j++) {
                if(j==0) table[i][j] = 0;
                else if(i==0) table[i][j] = Integer.MAX_VALUE;
                else if(c[i-1]>j) table[i][j] = table[i-1][j];
                else table[i][j] = Math.min(table[i-1][j], 1 + table[i][j-c[i-1]]);
            }
        }
        return table[n][maxCoin];
    }



    // number of a coin can be exchanged
    public int numberOfCoinExchangeWays(int[] c, int n, int maxCoin) {
        int[][] table = new int[n+1][maxCoin+1];
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=maxCoin; j++) {
                if(j==0) table[i][j] = 1;
                else if(i==0) table[i][j] = 0;
                else if(c[i-1]>j) table[i][j] = table[i-1][j];
                else table[i][j] = table[i-1][j] + table[i][j-c[i-1]];
            }
        }
        return table[n][maxCoin];
    }

}
