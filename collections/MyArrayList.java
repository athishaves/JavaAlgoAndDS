package collections;

public class MyArrayList<T> implements MyList {

    // Time Complexity
    // Inserting an element =>  O(n)
    // Deleting an element =>  O(n)
    // Accessing an element =>  O(1)

    static final float increaseBy = 1.5f;

    int _length = 0;

    int _capacity;
    int _initialCapacity = 5;

    Object[] _array;


    public MyArrayList() {
        _array = new Object[_initialCapacity];
        _capacity = _initialCapacity;
    }

    public MyArrayList(int initialCapacity) {
        if(initialCapacity<=0) {
            System.out.println("Capacity of the array cannot be non-positive");
            System.out.println("Initial Capacity is set to 1");
            initialCapacity = 1;
        }
        this._initialCapacity = initialCapacity;
        _array = new Object[_initialCapacity];
        _capacity = _initialCapacity;
    }

    public MyArrayList(MyCollections<T> collections) {
        this._capacity = collections.size();
        _array = new Object[_capacity];
        addAll(collections);
    }



    @Override
    public void add(Object element) {
        if(_length==_capacity) {
            _capacity = increasedCapacity(_capacity);
            Object[] a = new Object[_capacity];
            for(int i=0; i<_length; i++) a[i] = _array[i];
            _array = a;
        }
        _array[_length++] = element;
    }

    @Override
    public boolean add(int index, Object element) {
        if(isIndexInvalid(index, true)) return false;

        if(_length==_capacity) {
            _capacity = increasedCapacity(_capacity);  // +1 for case when _capacity will be equal to 1
            Object[] a = new Object[_capacity];
            for(int i=0; i<index; i++) a[i] = _array[i];    // Left part of index
            a[index] = element;    // Element
            for(int i=index; i<_length; i++) a[i+1] = _array[i];    // Right part of index
            _array = a;
            _length++;

        } else {
            for(int i=_length-1; i>=index; i--) _array[i+1] = _array[i];    // Right part of index
            _array[index] = element;    // Element
            _length++;
        }
        return true;
    }



    @Override
    public void addAll(MyCollections collections) {
        int n = collections.size();

        boolean isNewArray = false;
        while (_capacity < _length+n) {
            _capacity = increasedCapacity(_capacity);
            isNewArray = true;
        }
        if (isNewArray) {
            Object[] a = new Object[_capacity];
            for(int i=0; i<_length; i++) a[i] = _array[i];
            _array = a;
        }
        for (int i=0; i<n; i++) _array[_length+i] = collections.get(i);
        _length += n;
    }

    @Override
    public boolean addAll(int index, MyCollections collections) {
        if(collections.size()==0) return true;
        if(isIndexInvalid(index, true)) return false;

        int n = collections.size();

        boolean isNewArray = false;
        while (_capacity < _length+n) {
            _capacity = increasedCapacity(_capacity);
            isNewArray = true;
        }

        if(isNewArray) {
            Object[] a = new Object[_capacity];
            for (int i=0; i<index; i++) a[i] = _array[i];   // Index left part
            for (int i=0; i<n; i++) a[i+index] = collections.get(i);    // Collections part
            for (int i=index; i<_length; i++) a[i+n] = _array[i];    // Index right part
            _array = a;
            _length += n;
            return true;
        }

        // Index left part is present in the original array
        for (int i=_length-1; i>=index; i++) _array[i+n] = _array[i];    // Index right part
        for (int i=0; i<n; i++) _array[i+index] = collections.get(i);    // Collections part
        _length += n;
        return true;
    }



    @Override
    public Object get(int index) {
        if(isIndexInvalid(index, false)) return null;
        return _array[index];
    }

    @Override
    public Object set(int index, Object element) {
        if(isIndexInvalid(index, false)) return null;
        Object curElement = _array[index];
        _array[index] = element;
        return curElement;
    }



    @Override
    public boolean isEmpty() {
        return _length==0;
    }

    @Override
    public boolean contains(Object element) {
        return indexOf(element)!=-1;
    }



    @Override
    public int indexOf(Object element) {
        for(int i=0; i<_length; i++) if(_array[i].equals(element)) return i;
        return -1;
    }

    @Override
    public int lastIndexOf(Object element) {
        int index = -1;
        for(int i=0; i<_length; i++) if(_array[i].equals(element)) index = i;
        return index;
    }



    @Override
    public int size() {
        return _length;
    }



    @Override
    public boolean remove(Object element) {
        int index = indexOf(element);
        return removeIndex(index);
    }

    @Override
    public boolean removeLast(Object element) {
        int index = lastIndexOf(element);
        return removeIndex(index);
    }

    @Override
    public boolean removeIndex(int index) {
        if(isIndexInvalid(index, false)) return false;
        for(int i=index; i<_length-1; i++) _array[i] = _array[i+1];
        _length--;
        return true;
    }



    @Override
    public void clear() {
        _capacity = _initialCapacity;
        _array = new Object[_initialCapacity];
        _length = 0;
    }



    @Override
    public Object[] toArray() {
        if(_length==_capacity) return _array;
        Object[] a = new Object[_length];
        for(int i=0; i<_length; i++) a[i] = _array[i];
        return a;
    }



    @Override
    public MyArrayList<T> subList(int fromIndex, int toIndex) {
        if(toIndex < fromIndex) return null;

        if(fromIndex < 0) fromIndex = 0;
        if(toIndex >= _length) toIndex = _length - 1;

        MyArrayList<T> list = new MyArrayList<>(toIndex-fromIndex+1);
        for (int i=fromIndex; i<=toIndex; i++) list.add(_array[i]);
        return list;
    }


    @Override
    public MyArrayList<T> subList(int fromIndex) {
        return subList(fromIndex, _length-1);
    }


    private boolean isIndexInvalid(int index, boolean selectLengthElement) {
        if(selectLengthElement) return index < 0 || index > _length; // a[length] is also valid
        return index < 0 || index >= _length;
    }

    private int increasedCapacity(int capacity) { return ((int) (capacity*increaseBy) + 1); }

}
