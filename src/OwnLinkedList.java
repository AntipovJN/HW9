public class OwnLinkedList<T> implements List<T> {

    private Node<T> first;
    private Node<T> last;
    private int length = 0;

    @Override
    public void add(T value) {
        Node<T> node = new Node<>(value);
        if (isEmpty()) {
            first = node;
            last = node;
        } else {
            last.setNext(node);
            node.setPrevious(last);
            last = node;
        }
        length++;
    }

    @Override
    public void add(T value, int index) {
        validIndex(index);
        Node<T> node = new Node<>(value);
        if (index == 0) {
            first.setPrevious(node);
            node.setNext(first);
            first = node;
        } else if (index == length) {
            add(node.value);
        } else {
            Node<T> prevNode = getNode(index - 1);
            prevNode.next.setPrevious(node);
            node.next = prevNode.next;
            node.previous = prevNode;
            prevNode.next = node;
        }
        length++;
    }

    @Override
    public void addAll(List<T> list) {
        for (int i = 0; i < list.size(); i++) {
            add(list.get(i));
        }
    }

    public void addAll(OwnLinkedList<T> list) {
        last.setNext(list.first);
        list.first.setPrevious(this.last);
        length = length + list.size();
        last = list.last;
    }

    @Override
    public T get(int index) {
        return getNode(index).value;
    }

    @Override
    public void set(T value, int index) {
        validIndex(index);
        getNode(index).value = value;
    }

    @Override
    public T remove(int index) {
        if (!isEmpty()) {
            validIndex(index);
            if (index == 0) {
                return removeFirst();
            }
            if (index == length - 1) {
                return removeLast();
            }
            Node<T> removedNode = getNode(index);
            removedNode.next.setPrevious(removedNode.previous);
            removedNode.previous.setNext(removedNode.next);
            removedNode.setPrevious(null);
            removedNode.setNext(null);
            length--;
            return removedNode.value;
        }
        return null;
    }

    @Override
    public T remove(T o) {
        Node<T> node = first;
        if (o.equals(first.value)) {
            return removeFirst();
        }
        if (o.equals(last.value)) {
            return removeLast();
        }
        while (!node.equals(last)) {
            if (node.value.equals(o)) {
                node.previous.setNext(node.next);
                node.next.setPrevious(node.previous);
                length--;
                return node.value;
            }
            node = node.next;
        }
        return null;
    }

    @Override
    public int size() {
        return length;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    public void clear() {
        first = null;
        last = null;
        length = 0;
    }

    public T[] toArray() {
        T[] array = (T[]) new Object[length - 1];
        Node<T> node = first;
        for (int i = 0; i < length - 1; i++) {
            array[i] = node.value;
            node = node.next;
        }
        return array;
    }

    private void validIndex(int index) {
        if (index < 0 || index > length - 1) {
            throw new IndexOutOfBoundsException(index + "is not valid index");
        }
    }

    private Node<T> getNode(int index) {
        validIndex(index);
        if (index > length / 2) {
            return getFromEnd(index);
        }
        return getFromStart(index);
    }

    private Node<T> getFromEnd(int index) {
        Node<T> node = last;
        for (int i = length - 1; i > index; i--) {
            node = node.previous;
        }
        return node;
    }

    private Node<T> getFromStart(int index) {
        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    private T removeFirst() {
        Node<T> removedNode = first;
        first = first.next;
        first.setPrevious(null);
        length--;
        return removedNode.value;
    }

    private T removeLast() {
        Node<T> removedNode = last;
        last = last.previous;
        last.setNext(null);
        length--;
        return removedNode.value;
    }

    @Override
    public String toString() {
        Node<T> node = first;
        StringBuilder s = new StringBuilder("OwnLinkedList{");
        for (int i = 0; i < length; i++) {
            s.append(node.value).append(" ");
            node = node.next;
        }
        return s.append("}").toString();
    }

    private class Node<T> {

        private Node<T> previous;
        private Node<T> next;
        private T value;

        public Node(T value) {
            this.value = value;
        }

        public void setPrevious(Node previous) {
            this.previous = previous;
        }

        public void setNext(Node next) {
            this.next = next;
        }

    }
}
