package collections;

import java.util.Collection;

public interface MyCollections<T> {

    void add(T element);
    boolean add(int index, T element);
    void addAll(MyCollections<? extends T> collections);
    boolean addAll(int index, MyCollections<? extends T> collections);

    boolean remove(T element);
    boolean removeLast(T element);
    boolean removeIndex(int index);
    boolean isEmpty();
    boolean contains(T element);

    int size();

    void clear();

    T get(int index);
    T[] toArray();
}
