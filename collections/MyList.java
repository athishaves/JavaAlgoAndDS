package collections;

public interface MyList<T> extends MyCollections<T> {

    // Replaces the element at given index and returns the replaced element
    T set(int index, T element);

    // Returns the first found index of the given element.. If not found, returns -1
    int indexOf(T element);
    // Return the last found index of the given element.. If not found returns -1
    int lastIndexOf(T element);

    // Return a sublist from the fromIndex to the toIndex (Inclusive)
    MyList<T> subList(int fromIndex, int toIndex);
    // Return a sublist from the fromIndex to the end of the list
    MyList<T> subList(int fromIndex);

}
