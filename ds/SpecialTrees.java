package ds;

public class SpecialTrees {

    // Update element : O(log2N)    so..  Creating sum array : O(N.log2N)
    // Get Sum of first K elements : O(log2K)
    // Space Complexity : O(1) sumArray can be the same array

    public static class FenwickTree {

        int _length;
        int[] _array;
        int[] _sumArray;

        public FenwickTree(int[] array) {
            this._length = array.length;
            _array = array;
            _sumArray = new int[_length + 1];

            for (int i=1; i<=_length; i++)
                setNumber(i, array[i-1]);
        }

        private void setNumber(int index, int element) {
            _sumArray[index] += element;
            int k = index + getLastBit(index);
            while (k<=_length) {
                _sumArray[k] += element;
                k += getLastBit(k);
            }
        }

        public void updateNumber(int index, int element) {
            setNumber(index+1, element - _array[index]);
            _array[index] = element;
        }

        private int getLastBit(int n) {
            return (n&(-n));
        }

        public int getSum(int k) {
            if(k<=0) return 0;
            if(k>_length) k = _length;
            int res = 0;
            while (k>0) {
                res += _sumArray[k];
                k -= getLastBit(k);
            }
            return res;
        }

    }



}
