package algos;

import java.util.ArrayDeque;
import java.util.Deque;

public class SlidingWindowProblem {


    // Given an array of 'n' elements,
    // print 'n-k+1' elements where each element is the maximum of 'k' elements
    // a = [1,3,2,5,3,2,1] , n = 3  =>  res = [3,5,5,5,3]

    // Time Complexity : O(2*n)
    // Space Complexity : O(k)

    public static int[] slidingWindow(int[] a, int n, int k) {
        int[] res = new int[n-k+1];
        Deque<Integer> q = new ArrayDeque<>(k);
        for (int i=0; i<k; i++) {
            while ((!q.isEmpty()) && a[i]>=a[q.getLast()]) q.removeLast();
            q.addLast(i);
        }

        int l = 0;
        for (int i=k; i<n; i++) {
            res[l++] = a[q.getFirst()];
            while (!q.isEmpty() && q.getFirst()<=i-k) q.removeFirst();
            while (!q.isEmpty() && a[i]>=a[q.getLast()]) q.removeLast();
            q.addLast(i);
        }
        res[l] = a[q.getFirst()];
        return res;
    }



    // Max of sum of 'k' consecutive elements
    // Time Complexity : O(n)
    // Space Complexity : O(1)

    public static int maxSumOfConsecEle(int[] a, int n, int k) {
        int sum = 0;
        for (int i = 0; i < k; i++) sum += a[i];

        int window_sum = sum;
        for (int i = k; i < n; i++) {
            window_sum += a[i] - a[i - k];
            sum = Math.max(sum, window_sum);
        }
        return sum;
    }

}
