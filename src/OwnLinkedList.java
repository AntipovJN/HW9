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
        Node<T> prevNode = first;
        Node<T> lastNode;
        if (index == 0) {
            first.setPrevious(node);
            node.setNext(first);
            first = node;
            length++;
            return;
        }
        if (index == length) {
            last.setNext(node);
            node.setPrevious(last);
            last = node;
            length++;
            return;
        }
        for (int i = 0; i <= index; i++) {
            if (i == index) {
                lastNode = prevNode;
                prevNode = lastNode.previous;
                lastNode.setPrevious(node);
                prevNode.setNext(node);
                node.setNext(lastNode);
                node.setPrevious(prevNode);
            }
            prevNode = prevNode.next;
        }
        length++;
    }

    @Override
    public void addAll(List<T> list) {
        if (!list.isEmpty()) {
            Node<T> node;
            for (int i = 0; i < list.size(); i++) {
                node = new Node<>(list.get(i));
                node.setPrevious(last);
                last.setNext(node);
                last = last.next;
                length++;
            }
        }
    }

    @Override
    public T get(int index) {
        validIndex(index);
        Node<T> node = first;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node.value;
    }

    @Override
    public void set(T value, int index) {
        validIndex(index);
        Node<T> node = first;
        if (index == 0) {
            first.value = value;
            return;
        }
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        node.value = value;
    }

    @Override
    public T remove(int index) {
        validIndex(index);
        Node<T> removedNode = first;
        if (index == 0) {
            return removeFirst();
        }
        if (index == length - 1) {
            return removeLast();
        }
        for (int i = 0; i < index; i++) {
            removedNode = removedNode.next;
        }
        removedNode.next.setPrevious(removedNode.previous);
        removedNode.previous.setNext(removedNode.next);
        length--;
        return removedNode.value;
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
        while (node != last) {
            if (node.value == o) {
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

    private void validIndex(int index) {
        if (index < 0 || index > length-1) {
            throw new IndexOutOfBoundsException(index + "is not valid index");
        }
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
        String s = "OwnLinkedList{";
        for (int i = 0; i < length; i++) {
            s += node.value + " ";
            node = node.next;
        }
        return s + "}";
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
