package algos;

// Returns index of the element in the array

public class SearchTechs {

    // Time Complexity : O(n)
    // Space Complexity : O(1)

    public int linearSearch(int[] a, int n, int target) {
        for (int i=0; i<n; i++) if(a[i]==target) return i;
        return -1;
    }



    // Time Complexity : O(log2N)
    // Space Complexity : O(1)
    // Constraint : Array should be sorted

    public int binarySearch(int[] a, int n, int target) {
        int low = 0, high = n-1, mid;
        while (low<=high) {
            mid = (low+high)>>1;
            if(a[mid]==target) return mid;
            if(a[mid]>target) high = mid - 1;
            else low = mid + 1;
        }
        return -1;
    }

}
