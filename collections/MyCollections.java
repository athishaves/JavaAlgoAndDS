package collections;

public interface MyCollections<T> {

    void add(T element);
    // Adds the element at the given index
    boolean add(int index, T element);
    int size();
    boolean remove(T element);
    void addAll(MyCollections<? extends T> collections);
    boolean addAll(int index, MyCollections<? extends T> collections);
    void clear();
    // Returns the element at the given index
    T get(int index);

}
