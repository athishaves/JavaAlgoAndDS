package sde;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class SdePart1 {


    // Find the duplicate in an array of n+1 integers
    // Elements range from 0 to n
    // Time Complexity : O(n)
    // Space Complexity : O(1)

    static int findDuplicate(int[] a, int n) {
        int fast, slow;
        fast = slow = a[0];
        do {
            slow = a[slow];
            fast = a[a[fast]];
        } while (fast!=slow);
        fast = a[0];
        while (slow!=fast) {
            slow = a[slow];
            fast = a[fast];
        }
        return slow;
    }



    // Dutch Flag or Something Problem
    // Given an array of 0,1 and 2's.. Sort them
    // Time Complexity : O(n)
    // Space Complexity : O(1)

    static int[] sortZOT(int[] a, int n) {
        int low, mid, high;
        low = mid = 0;
        high = n-1;
        while (mid<=high) {
            if(a[mid]==0) swap(a, low++, mid++);
            else if(a[mid]==1) mid++;
            else swap(a, high--, mid);
        }
        return a;
    }

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }



    // GAP Problem/Algorithm
    // Merge two sorted arrays
    // First array = [0...m-1]
    // Second array = [m...n-1]
    // Input array => {1,4,7,8,10,2,3,9};   firstArray upto 5.. secondArray from 6

    // Time Complexity : O(n.log2n)
    // Space Complexity : O(1)

    static int[] mergeArrays(int[] a, int n) {
        int gap = n;
        if(gap%2==1) gap++;
        gap >>= 1;
        int first, second;
        while (gap!=1) {
            first = 0;  second = gap;
            while (second<n) {
                if(a[first]>a[second]) {
                    // Swap a[first] and a[second]
                    int temp = a[first];
                    a[first] = a[second];
                    a[second] = temp;
                }
                first++;    second++;
            }
            if(gap%2==1) gap++;
            gap >>= 1;
        }
        first = 0;  second = gap;
        while (second<n) {
            if(a[first]>a[second]) {
                int temp = a[first];
                a[first] = a[second];
                a[second] = temp;
            }
            first++;    second++;
        }
        return a;
    }


    // Time Complexity : O(m.n)
    // Space Complexity : O(1)

    static void mergeSortedArraysMethodTwo(int[] a, int[] b, int m, int n) {
        int first = 0, temp;
        while(first<m) {
            if(a[first]>b[0]) {
                // swap a[first] and b[0]
                temp = a[first];
                a[first] = b[0];
                b[0] = temp;
                int per = b[0], i;
                for (i=1; i<n && b[i]<per; i++) b[i-1]=b[i];
                // replace b[i-1] and per
                b[i-1] = per;
            }
            first++;
        }
        for (int z : a) System.out.print(z + " ");
        for (int z : b) System.out.print(z + " ");
    }



    // Merge given intervals
    // Time Complexity : O(NlogN + N)
    // Space Complexity : O(1)

    public static int[][] mergeIntervals(int[][] intervals) {
        List<int[]> res = new ArrayList<>();

        if(intervals==null || intervals.length==0) return res.toArray(new int[0][]);

        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));

        int start = intervals[0][0];    int end = intervals[0][1];
        for (int[] i : intervals) {
            if(i[0]<=end) end = Math.max(end, i[1]);
            else {
                res.add(new int[]{start, end});
                start = i[0];   end = i[1];
            }
        }
        res.add(new int[]{start, end});
        return res.toArray(new int[0][]);
    }

}
