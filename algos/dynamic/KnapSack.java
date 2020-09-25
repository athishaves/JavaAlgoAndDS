package algos.dynamic;

public class KnapSack {

    // Knapsack --> Given list of item (weights and profits) and weight constraint..,
    // What is the maximum profit we get
    public int knapsackRecursion(int[] w, int[] p, int n, int maxW) {
        if(n==0 || maxW==0) return 0;
        if(w[n-1]>maxW) return knapsackRecursion(w, p, n-1, maxW);
        return Math.max(knapsackRecursion(w, p, n-1, maxW), // Choosing item
                        p[n-1] + knapsackRecursion(w, p, n-1, maxW - w[n-1]));   // Not choosing item
    }

    public int knapsackTable(int[] w, int[] p, int n, int maxW) {
        int[][] table = new int[n+1][maxW+1];
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=maxW; j++) {
                if(i==0 || j==0) table[i][j] = 0;
                else if(w[i-1]>j) table[i][j] = table[i-1][j];
                else table[i][j] = Math.max(table[i-1][j], p[i-1] + table[i-1][j-w[i-1]]);
            }
        }
        return table[n][maxW];
    }



    // Is there a subset in an array whose sum is the given Sum
    // This method is also applicable for "Equal Sum Partition"
    //          --> (arraySum%2==0) && callFun(maxSum = arraySum / 2)

    public boolean subset(int[] a, int n, int maxSum) {
        boolean[][] table = new boolean[n+1][maxSum+1];
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=maxSum; j++) {
                if(j==0) table[i][j] = true;
                else if(i==0) table[i][j] = false;
                else if(a[i-1]>j) table[i][j] = table[i-1][j];
                else table[i][j] = table[i-1][j] || table[i-1][j-a[i-1]];
            }
        }
        return table[n][maxSum];
    }



    // Count number of subsets in an array having the required sum
    public int numberOfSubsets(int[] a, int n, int maxSum) {
        int[][] table = new int[n+1][maxSum+1];
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=maxSum; j++) {
                if(j==0) table[i][j] = 1;
                else if(i==0) table[i][j] = 0;
                else if(a[i-1]>j) table[i][j] = table[i-1][j];
                else table[i][j] = table[i-1][j] + table[i-1][j-a[i-1]];
            }
        }
        return table[n][maxSum];
    }



    // What is the minimum difference you can get from subarrays
    //   s1 + s2 = arraySum
    // - s1 + s2 = diff
    // 2s1 = arraySum - diff
    // diff = arraySum - 2s1
    //          --> arraySum == sum of the elements in the array

    public int minimumSubarrayDifference(int[] a, int n, int arraySum) {
        boolean[][] table = new boolean[n+1][arraySum+1];
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=arraySum; j++) {
                if(j==0) table[i][j] = true;
                else if(i==0) table[i][j] = false;
                else if(a[i-1]>j) table[i][j] = table[i-1][j];
                else table[i][j] = table[i-1][j] || table[i-1][j-a[i-1]];
            }
        }
        int minDiff = Integer.MAX_VALUE;
        for(int i=1; i<=arraySum/2; i++) {
            if(table[n][i]) minDiff = Math.min(arraySum, arraySum - 2*i);
        }
        return minDiff;
    }



    // Also target sum problem
    // Assign + or - to the elements of array and count the number of subarrays whose sum is givenSum
    // s1 + s2 = arraySum
    // s1 - s2 = givenDiff
    // s1 = (arraySum + givenDiff) / 2      -->     Note that s1 cannot be odd
    //          --> callFunc(maxSum = (arraySum+givenDiff)/2)

    public int countSubarraysWithGivenDiff(int[] a, int n, int maxSum) {
        if(maxSum%2==1) return 0;
        maxSum >>= 1;   // Divide it by 2
        System.out.println(maxSum);
        int[][] table = new int[n+1][maxSum+1];
        for(int i=0; i<=n; i++) {
            for(int j=0; j<=maxSum; j++) {
                if(j==0) table[i][j] = 1;
                else if(i==0) table[i][j] = 0;
                else if(a[i-1]>j) table[i][j] = table[i-1][j];
                else table[i][j] = table[i-1][j] + table[i-1][j-a[i-1]];
            }
        }
        // Will get the number of subsets which add upto maxSum/2
        // So dividing the result by 2 to get no of subArrays
        return table[n][maxSum]>>1;
    }

}
