public class GlobalClass {

    public static void swap(int[] a, int[] b, int i, int j) {
        int temp = a[i];
        a[i] = b[j];
        b[j] = temp;
    }

    public static void swap(int[] a, int i, int j) { swap(a,a,i,j); }

    public static void swapMatrix(int[][] a, int[][] b, int i, int j, int x, int y) {
        int temp = a[i][j];
        a[i][j] = b[x][y];
        b[x][y] = temp;
    }

    public static void swapMatrix(int[][] a, int i, int j, int x, int y) { swapMatrix(a,a,i,j,x,y); }



    public static void printArray(int[] a) {
        for (int i : a) System.out.print(i + " ");
        System.out.println();
    }

    public static void printMatrix(int[][] a) { for (int[] i : a) printArray(i); }

}
