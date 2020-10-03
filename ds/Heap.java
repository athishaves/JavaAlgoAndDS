package ds;

import java.util.*;

public class Heap {

    // Min Heap : PriorityQueue<Integer> q = new PriorityQueue<>();
    // Max Heap : PriorityQueue<Integer> q = new PriorityQueue<>(Collections.reverseOrder());


    // Return k-th largest element in the array
    // Time Complexity : O(N.log2K)
    // Space Complexity : O(K)

    public int kthLargestElement(int[] a, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>(k+1);
        for (int i : a) {
            q.add(i);
            if(q.size()>k) q.remove();
        }
        return q.remove();
    }




    // Return k-th smallest element in the array
    // Time Complexity : O(N.log2K)
    // Space Complexity : O(K)

    public int kthSmallestElement(int[] a, int k) {
        PriorityQueue<Integer> q = new PriorityQueue<>(k+1, Collections.reverseOrder());
        for (int i : a) {
            q.add(i);
            if(q.size()>k) q.remove();
        }
        return q.remove();
    }




    // Sort a K sorted / nearly array
    // Ascending Order : MinHeap        Descending Order : MaxHeap (reverseOrder)
    // Time Complexity : O(N.log2K)
    // Space Complexity : O(K)

    public void kSortedArray(int[] a, int n, int k) {
        int m = 0;
        PriorityQueue<Integer> q = new PriorityQueue<>(k+1);
        for (int i : a) {
            q.add(i);
            if(q.size()>k) a[m++] = q.remove();
        }
        while (!q.isEmpty()) a[m++] = q.remove();
    }




    // K closest numbers
    // Time Complexity : O(N.log2K)
    // Space Complexity : O(K)

    static class ClosestPair {
        Integer key, diff;
        ClosestPair(Integer key, Integer value) { this.key = key;     this.diff = value; }
    }

    public int[] kClosestEle(int[] a, int k, int x) {
        PriorityQueue<ClosestPair> q = new PriorityQueue<>(k + 1,
                                                    (o1, o2) -> o2.diff.compareTo(o1.diff));
        for (int i : a) {
            q.add(new ClosestPair(i, Math.abs(x-i)));
            if (q.size()>k) q.remove();
        }

        int[] res = new int[k];
        int m = 0;
        while (!q.isEmpty()) res[m++] = q.remove().key;

        return res;
    }




    // Top k frequency element
    // Time Complexity : O(N.log2K)
    // Space Complexity : O(2*K)

    static class FrequencyPair {
        Integer frequency, value;
        FrequencyPair(int frequency, int value) { this.frequency = frequency; this.value = value; }
    }

    public int[] topKFrequencyElements(int[] a, int k) {
        Map<Integer, Integer> map = new HashMap<>(k+1);
        PriorityQueue<FrequencyPair> q = new PriorityQueue<>();
        for (int i : a) {
            if(map.containsKey(i)) map.put(i, map.get(i)+1);
            else map.put(i,1);
        }
        for (Map.Entry<Integer, Integer> m : map.entrySet()) {
            q.add(new FrequencyPair(m.getValue(), m.getKey()));
            if(q.size()>k) q.remove();
        }
        int[] res = new int[k]; int m = 0;
        while (!q.isEmpty()) res[m++] = q.remove().value;
        return res;
    }




    // Frequency Sort -- Sort the array elements based on their frequency of occurance
    // Time Complexity : O(N.log2K)
    // Space Complexity : O(2*K)

    public void frequencySort(int[] a) {
        PriorityQueue<FrequencyPair> q = new PriorityQueue<>(Comparator.comparing(o -> o.frequency));
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : a) {
            if(map.containsKey(i)) map.put(i, map.get(i)+1);
            else map.put(i,1);
        }

        for (Map.Entry<Integer,Integer> m : map.entrySet()) {
            q.add(new FrequencyPair(m.getValue(), m.getKey()));
        }

        int m = 0;
        while (!q.isEmpty()) {
            FrequencyPair pair = q.remove();
            int val = pair.value;       int n = pair.frequency;
            while (n-- > 0) a[m++] = val;
        }
    }





    // k closest points to origin
    // Time Complexity : O(N.log2K)
    // Space Complexity : O(K)

    static class OriginPair {
        Integer index;
        Double distance;
        OriginPair(Double distance, Integer index) { this.distance = distance;     this.index = index; }
    }

    public int[][] kClosestPoints(int[][] a, int k) {
        PriorityQueue<OriginPair> q = new PriorityQueue<>(k+1,
                                                        (o1, o2) -> o2.distance.compareTo(o1.distance));
        for (int i=0; i<a.length; i++) {
            double d = distanceFromOrigin(a[i][0], a[i][1]);
            q.add(new OriginPair(d, i));
            if(q.size()>k) q.remove();
        }
        int[][] res = new int[k][2];
        int m = 0;
        while (!q.isEmpty()) res[m++] = a[q.remove().index];
        return res;
    }

    double distanceFromOrigin(int a, int b) {
        return Math.sqrt(a*a + b*b);
    }





    // Connect Ropes to Minimise the cost
    // Time Complexity : O(N.log2N)
    // Space Complexity : O(N)      -- Can be reduced to O(1) if heap is made by scratch

    public int connectRopes(int[] a) {
        PriorityQueue<Integer> q = new PriorityQueue<>(a.length);
        for (int i : a) q.add(i);
        while (q.size()>1) q.add(q.remove() + q.remove());
        return q.remove();
    }




    // Sum of elements between K1-th and K2-nd smallest element
    // Time Complexity : O(2.Nlog2K)
    // Space Complexity : O(K)

    public int sumOfElements(int[] a, int k1, int k2) {
        int first = kthSmallestElement(a,k1);
        int second = kthSmallestElement(a,k2);
        int sum = 0;
        for (int i : a) if(i>first && i<second) sum += i;
        return sum;
    }

}
