package collections;

import java.util.Collection;

public interface MyCollections<T> {

    // Add element at last
    void add(T element);
    // Add element at given index
    // Return false if the index is invalid
    boolean add(int index, T element);

    // Add the collection at end of list
    void addAll(MyCollections<? extends T> collections);
    // Add the collection at given index
    // Return false if the index is invalid
    boolean addAll(int index, MyCollections<? extends T> collections);

    // Remove element
    // Return false if array doesnt contain the element
    boolean remove(T element);
    // Remove element at last occuring index
    // Return false if array doesnt contain the element
    boolean removeLast(T element);
    // Remove element at given index
    // Return false if the index is invalid
    boolean removeIndex(int index);

    // True if collection is empty
    boolean isEmpty();
    // True if collection contains the given element
    boolean contains(T element);

    // Returns size of the collection
    int size();

    // Clears/Resets the collection
    void clear();

    // Return element at given index
    T get(int index);
    // Converts the collection to an array of object
    T[] toArray();

}
