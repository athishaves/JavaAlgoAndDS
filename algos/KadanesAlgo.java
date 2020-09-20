package algos;

public class KadanesAlgo {

    public static int maxSumSubArray(int[] a, int n) {
        int maxSum, curSum;
        maxSum = curSum = a[0];
        for (int i=1; i<n; i++) {
            curSum = Math.max(a[i] + curSum, a[i]);
            maxSum = Math.max(curSum, maxSum);
        }
        return maxSum;
    }

}
