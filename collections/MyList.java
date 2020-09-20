package collections;

public interface MyList<T> extends MyCollections<T> {

    // Replaces the element at given index and returns the replaced element
    T set(int index, T element);

    // Returns true if the size of the list is 0
    boolean isEmpty();
    // Returns true if the list contains the given element
    boolean contains(T element);

    // Returns the first found index of the given element.. If not found, returns -1
    int indexOf(T element);
    // Return the last found index of the given element.. If not found returns -1
    int lastIndexOf(T element);
    // Return size of the list
    int size();

    // Return the array of the given list
    T[] toArray();

    // Return a sublist from the fromIndex to the toIndex (Inclusive)
    MyList<T> subList(int fromIndex, int toIndex);

}
