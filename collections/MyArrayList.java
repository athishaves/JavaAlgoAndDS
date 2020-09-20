package collections;

public class MyArrayList<T> implements MyList {

    int _length = 0;

    int _capacity;
    int _initialCapacity = 5;

    Object[] _array;

    MyArrayList() {
        _array = new Object[_initialCapacity];
        _capacity = _initialCapacity;
    }

    MyArrayList(int initialCapacity) {
        this._initialCapacity = initialCapacity;
        _array = new Object[initialCapacity];
        _capacity = _initialCapacity;
    }

    @Override
    public boolean add(int index, Object element) {
        if(isIndexInvalid(index, true)) return false;
        int curIndex = 0;
        Object[] a = {};
        if(_length==_capacity) {
            _capacity = (int) (_capacity*1.5);
            a = new Object[_capacity];
        }
        for(int i=0; i<index; i++) a[i] = _array[i];
        curIndex = index;
        _array[_length++] = element;
        _array = a;
        return true;
    }

    @Override
    public Object get(int index) {
        if(isIndexInvalid(index, true)) return null;
        return _array[index];
    }

    @Override
    public Object set(int index, Object element) {
        if(isIndexInvalid(index, true)) return null;
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
        for (Object a : _array) if(a.equals(element)) return true;
        return false;
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
    public void add(Object element) {
        if(_length==_capacity) {
            _capacity = (int) (1.5*_capacity);
            Object[] a = new Object[_capacity];
            for(int i=0; i<_length; i++) a[i] = _array[i];
            _array = a;
        }
        _array[_length] = element;
        _length++;
    }

    @Override
    public int size() {
        return _length;
    }

    @Override
    public boolean remove(Object element) {
        int index = indexOf(element);
        if(index==-1) return false;
        for(int i=index; i<_length; i++) _array[i] = _array[i+1];
        _length--;
        return true;
    }

    @Override
    public void addAll(MyCollections collections) {
        int n = collections.size();

        boolean isNewArray = false;
        while (_capacity < _length+n) {
            _capacity = (int) (_capacity*1.5);
            isNewArray = true;
        }
        if (isNewArray) {
            Object[] a = new Object[_capacity];
            for(int i=0; i<_length; i++) a[i] = _array[i];
            _array = a;
        }
        for (int i=0; i<n; i++) _array[_length+i] = collections.get(i);
    }

    @Override
    public boolean addAll(int index, MyCollections collections) {
        if(isIndexInvalid(index, true)) return false;

        int n = collections.size();

        boolean isNewArray = false;
        while (_capacity < _length+n) {
            _capacity = (int) (_capacity*1.5);
            isNewArray = true;
        }

        Object[] a = {};
        int aLength = -1;

        if(isNewArray) {
            a = new Object[_capacity];
            for (int i=0; i<index; i++) a[i] = _array[i];
            aLength = index;
        }

        for (int i=0; i<n; i++) a[i+aLength] = collections.get(i);

        for (int i=index; i<_length; i++) a[n+index+i] = _array[i];
        _array = a;
        return true;
    }

    @Override
    public void clear() {
        _array = new Object[_initialCapacity];
        _capacity = _initialCapacity;
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
    public MyArrayList subList(int fromIndex, int toIndex) {
        if(toIndex < fromIndex) return null;

        if(fromIndex < 0) fromIndex = 0;
        if(toIndex >= _length) toIndex = _length - 1;

        MyArrayList list = new MyArrayList(toIndex-fromIndex+1);
        for (int i=fromIndex; i<=toIndex; i++) list.add(_array[i]);
        return list;
    }

    private boolean isIndexInvalid(int index, boolean selectLengthElement) {
        if(selectLengthElement) return index < 0 || index > _length; // a[length] is also valid
        return index < 0 || index >= _length;
    }

}
