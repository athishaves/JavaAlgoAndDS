package algos;

public class SortTechs {


//    Algorithm         Best            Average         Worst

//    Quick Sort        Ω(n log(n))	    Θ(n log(n))     O(n^2)
//                                      S - log n       S - n
//                      Evenly Balanced Array           Sorted Array
//    -- Inplace ALgo, Not a Stable Algo
//    -- Why better than Merge Sort
//          Auxiliary Space [Inplace Algo]
//          Worst case can be avoided by choosing pivot element randomly
//          Cache friendly

//    Bubble Sort       Ω(n)	        Θ(n^2)	        O(n^2)
//                      Sorted Array
//    -- Only algo to detect if the array is already sorted or not
//    -- Stable, Inplace algo,
//       (why called bubble sort) largest element bubbles to the last index

//    Merge Sort        Ω(n log(n))	    Θ(n log(n))	    O(n log(n))

//    Insertion Sort	Ω(n)	        Θ(n^2)	        O(n^2)

//    Selection Sort	Ω(n^2)	        Θ(n^2)	        O(n^2)

//    Heap Sort	        Ω(n log(n))	    Θ(n log(n))	    O(n log(n))
//    -- In worst case, heap sort is better. In best/average case, quick sort is better

//    Radix Sort        Ω(nk)	        Θ(nk)	        O(nk)

//    Bucket Sort       Ω(n+k)	        Θ(n+k)	        O(n^2)




    // Quick Sort

    public void quickSort(int[] a, int low, int high) {
        if(low>=high) return;
        int part = partition(a, low, high);
        quickSort(a, low, part-1);
        quickSort(a, part+1, high);
    }

//    {1,4,2,5,7,3}
//    {1,4,2,5,7,3} --> 1,2
//    {1,2,4,5,7,3} --> 3
//    {1,2,3,5,7,4} --> last
    private int partition(int[] a, int low, int high) {
        int pivot = a[high];
        int i = low - 1;

        for (int j=low; j<=high-1; j++)
            if(a[j]<pivot) swap(a,++i,j);   // Increment i and then swap

        swap(a,i+1,high);

        return i+1;
    }




    // Bubble Sort

    public void bubbleSort(int[] a, int n) {
        for (int i=0; i<n-1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1; j++) {
                if (a[j] > a[j + 1]) {
                    swap(a, j, j + 1); swapped = true;
                }
            }
            if(!swapped) break;
        }
    }





    // Merge Sort

    public void mergeSort(int[] a, int low, int high) {
        if(low>=high) return;
        int mid = (low+high)>>1;
        mergeSort(a, low, mid);
        mergeSort(a, mid+1, high);
        merge(a, low, mid, high);
    }

    private void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high-low+1];
        int i = low, j = mid+1, k = 0;

        while (i<=mid && j<=high) {
            if(a[i]<=a[j]) temp[k++] = a[i++];
            else temp[k++] = a[j++];
        }

        while (i<=mid) temp[k++] = a[i++];
        while (j<=high) temp[k++] = a[j++];

        for (i=low; i<=high; i++) a[i] = temp[i-low];
    }





    // Insertion Sort

    public void insertionSort(int[] a, int n) {
        for (int i=1; i<n; i++) {
            int key = a[i];
            int j = i-1;
            while (j>=0 && a[j]>key) a[j+1] = a[j--];
            a[j+1] = key;
        }
    }





    // Selection Sort

    public void selectionSort(int[] a, int n) {
        for (int i=0; i<n; i++) {
            int minIndex = findMinIndex(a,i,n);
            swap(a,i,minIndex);
        }
    }

    int findMinIndex(int[] a, int i, int j) {
        int minIndex = i;
        for (int k=i+1; k<j; k++) if(a[k]<a[minIndex]) minIndex = k;
        return minIndex;
    }





    // Heap Sort

    public void heapSort(int[] a, int n) {
        for (int i=n>>1; i>0; i--) heapify(a,i,n);
        for (int i=n-1; i>=0; i--) {
            swap(a,0,i);
            heapify(a,0,i);
        }
    }

    void heapify(int[] a, int root, int n) {
        int left = 2*root + 1;      int right = 2*root + 2;
        int largest = root;
        if(left<n && a[left]>a[largest]) largest = left;
        if(right<n && a[right]>a[largest]) largest = right;

        if(largest!=root) {
            swap(a,root,largest);
            heapify(a,largest,n);
        }
    }





    // Swap elements in array

    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
