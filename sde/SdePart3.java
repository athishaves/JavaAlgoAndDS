package sde;

import java.util.ArrayList;

public class SdePart3 {

    // Search in a matrix
    // Given each individual rows and columns are sorted
    // Time Complexity : O(M+N)
    // Space Complexity : O(1)

    public boolean searchMatrix(int[][] a, int target) {
        int m = a.length, n = a[0].length;
        int i = 0, j = n-1;
        while (i<m && j>=0) {
            if(a[i][j]==target) return true;
            else if(a[i][j]>target) j--;
            else i++;
        }
        return false;
    }


    // Search in a matrix
    // Here.. the first element of each row is greater than
    // the last element of previous element
    // Time Complexity : O(log2(M*N))
    // Space Complexity : O(1)

    public boolean searchMatrixTwo(int[][] a, int target) {
        int m = a.length, n = a[0].length;
        int low = 0, high = m*n - 1;
        while (low<=high) {
            int mid = (low+high)>>1;
            if(a[mid/n][mid%n]==target) return true;
            else if (a[mid/n][mid%n]>target) high = mid-1;
            else low = mid+1;
        }
        return false;
    }




    // Return a power b
    // Time Complexity : O(log2B)
    // Space Complexity : O(1)

    public double power(double a, int b) {
        if(b==0) return 1;

        double result = 1;
        while (b>0) {
            if((b&1)==1) {
                result *= a;
                b--;
                continue;
            }
            a *= a;
            b >>= 1;
        }
        return result;
    }



    // Moore's Voting Algorithm
    // Given an element find its majority element
    // (Element which appears more than [n/2] times)
    // Constraint : It is given that there will be an element present

    public int majorityElement(int[] a, int n) {
        int ele = -1, count = 0;
        for (int i : a) {
            if(count==0) { ele = i; count = 1; }
            else if(i==ele) count++;
            else count--;
        }
        return ele;
    }


    // Boyer Moore Voting Algorithm
    // Given an element find its majority element
    // (Element which appears more than [n/3] times)
    // Time Complexity : O(2*N)
    // Space Complexity : O(1)

    public ArrayList<Integer> majorityElementThree(int[] a, int n) {
        int num1 = -1, num2 = -1, count1 = 0, count2 = 0;
        for (int i : a) {
            if(i==num1) count1++;
            else if(i==num2) count2++;
            else if(count1==0) {
                num1 = i;   count1 = 1;
            } else if(count2==0) {
                num2 = i;   count2 = 1;
            } else {
                count1--;   count2--;
            }
        }

        count1 = count2 = 0;
        ArrayList<Integer> result = new ArrayList<>(2);
        for (int i : a) {
            if(i==num1) count1++;
            else if(i==num2) count2++;
        }
        if(count1>(n/3)) result.add(num1);
        if(count2>(n/3)) result.add(num2);

        return result;
    }



    // NUmber of unique paths to reach from (0,0) to (M-1,N-1) in a matrix of M*N
    // Time Complexity : O(M-1) or O(N-1)
    // Space Complexity : O(1)

    public int uniquePaths(int m, int n) {
        int N = n + m - 2;
        int r = Math.min(m-1, n-1);
        int res = 1;
        for (int i=1; i<=r; i++) res = res * ((N - r + i) / i);
        return res;
    }

    public int uniquePathsRecursion(int m, int n) {
        if(m==1 || n==1) return 1;
        return uniquePathsRecursion(m-1, n) + uniquePathsRecursion(m, n-1);
    }



    // Reverse Pairs
    // Pairs of elements for which i < j and a[i] > 2*a[j]
    // Time Complexity : O()
    // Space Complexity : O()

}
