package collections;

public class MyLinkedList<T> implements MyList {

    static class Node {
        Object data;
        Node next, prev;
        Node(Object data) { this.data = data; }
    }

    int _length;
    Node head, tail;


    public MyLinkedList() {
        _length = 0;
    }

    public MyLinkedList(MyCollections<T> collections) {
        addAll(collections);
    }



    @Override
    public void add(Object element) {
        Node newNode = new Node(element);
        if(head==null) {
            head = tail = newNode;
            _length++;
            return;
        }
        tail.next = newNode;
        newNode.prev = tail;
        tail = newNode;
        _length++;
    }


    @Override
    public boolean add(int index, Object element) {
        if(isIndexInvalid(index, true)) return false;
        if(index==0) {
            addFirst(element);
            return true;
        }
        if(index==_length) {
            add(element);
            return true;
        }

        Node newNode = new Node(element);
        Node temp = head;
        for (int i=0; i<index; i++) temp = temp.next;
        Node prevTemp = temp.prev;

        prevTemp.next = newNode;    newNode.next = temp;
        newNode.prev = prevTemp;    temp.prev = newNode;

        _length++;
        return true;
    }


    public void addFirst(Object element) {
        Node newNode = new Node(element);
        if(head==null) {
            head = tail = newNode;
            _length++;
            return;
        }
        newNode.next = head;
        head.prev = newNode;
        head = newNode;
        _length++;
    }



    public void addFirst(MyCollections collections) {
        if(collections.size()==0) return;
        Object[] array = collections.toArray();
        for (Object o : array) addFirst(o);
    }


    @Override
    public void addAll(MyCollections collections) {
        if(collections.size()==0) return;
        Object[] array = collections.toArray();
        for (Object o : array) add(o);
    }


    @Override
    public boolean addAll(int index, MyCollections collections) {
        if(collections==null) return false;
        if(isIndexInvalid(index, true)) return false;
        if(index==0) {
            addFirst(collections);
            return true;
        }
        if(index==_length) {
            add(collections);
            return true;
        }
        Node temp = head;
        for(int i=0; i<index; i++) temp = temp.next;
        Node prevTemp = temp.prev;

        Object[] array = collections.toArray();
        for (int i=0; i<collections.size(); i++) {
            Node newNode = new Node(array[i]);
            prevTemp.next = newNode;    newNode.next = temp;
            temp.prev = newNode;    newNode.prev = prevTemp;
            prevTemp = newNode;
        }
        _length += collections.size();
        return true;
    }



    @Override
    public Object get(int index) {
        if(isIndexInvalid(index, false)) return null;
        Node temp = head;
        for(int i=0; i<index; i++) temp = temp.next;
        return temp.data;
    }


    @Override
    public Object set(int index, Object element) {
        if(isIndexInvalid(index, false)) return null;
        Node temp = head;
        for (int i=0; i<index; i++) temp = temp.next;
        Object cur = temp.data;
        temp.data = element;
        return cur;
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
        Node temp = head;
        for (int i=0; i<_length; i++) {
            if(temp.data==element) return i;
            temp = temp.next;
        }
        return -1;
    }


    @Override
    public int lastIndexOf(Object element) {
        Node temp = head;
        int index = -1;
        for (int i=0; i<_length; i++) {
            if(temp.data==element) index = i;
            temp = temp.next;
        }
        return index;
    }



    @Override
    public int size() {
        return _length;
    }



    @Override
    public boolean remove(Object element) {
        return removeIndex(indexOf(element));
    }


    @Override
    public boolean removeLast(Object element) {
        return removeIndex(lastIndexOf(element));
    }


    @Override
    public boolean removeIndex(int index) {
        if(isIndexInvalid(index, false)) return false;
        if(index==0) {
            removeFirst();
            return true;
        }
        if(index==_length-1) {
            removeLast();
            return true;
        }
        Node temp = head;
        for(int i=0; i<index; i++) temp = temp.next;
        Node prevTemp = temp.prev;

        prevTemp.next = temp.next;  (temp.next).prev = prevTemp;
        temp.next = temp.prev = null;

        _length--;

        return true;
    }


    public void removeFirst() {
        if(_length==1) clear();
        Node temp = head.next;
        head.next = null;
        temp.prev = null;
        head = temp;
        _length--;
    }

    public void removeLast() {
        if(_length==1) clear();
        Node temp = tail.prev;
        temp.next = null;
        tail.prev = null;
        tail = temp;
        _length--;
    }



    @Override
    public void clear() {
        _length = 0;
        head = tail = null;
    }



    @Override
    public Object[] toArray() {
        Object[] res = new Object[_length];
        Node temp = head;
        for (int i=0; i<_length; i++) {
            res[i] = temp.data;
            temp = temp.next;
        }
        return res;
    }



    @Override
    public MyLinkedList<T> subList(int fromIndex, int toIndex) {
        if(fromIndex>toIndex) return null;

        if(fromIndex<0) fromIndex = 0;
        if(toIndex>=_length) toIndex = _length - 1;

        MyLinkedList<T> sublist = new MyLinkedList<>();
        Node temp = head;
        for (int i=0; i<fromIndex; i++) temp = temp.next;
        for (int i=fromIndex; i<=toIndex; i++) {
            sublist.add(temp.data);
            temp = temp.next;
        }
        return sublist;
    }


    @Override
    public MyLinkedList<T> subList(int fromIndex) {
        return subList(fromIndex, _length-1);
    }



    private boolean isIndexInvalid(int index, boolean lengthElementConsidered) {
        if(lengthElementConsidered) return index<0 || index>_length;
        return index<0 || index>=_length;
    }

}
