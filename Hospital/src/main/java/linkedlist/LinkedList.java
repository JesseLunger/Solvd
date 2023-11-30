package linkedlist;

import java.util.ArrayList;
import java.util.function.Function;

public class LinkedList<T> {

    public Node<T> tail;
    private Node<T> head;
    private int size;

    public boolean addItem(T object, Function<T, Boolean> condition) {
        if (condition.apply(object)) {
            return false;
        }
        Node<T> newNode = new Node<>(object);
        if (head == null) {
            head = newNode;
        } else {
            tail.setNext(newNode);
            newNode.setPrevious(tail);
        }
        tail = newNode;
        size++;
        return true;
    }

    public boolean insertItem(int index, T object) {
        Node<T> targetNode = findNodeIndex(index);
        if (targetNode == null) {
            return false;
        }
        targetNode.setObject(object);
        return true;
    }

    public boolean addAtIndex(int index, T object, Function<T, Boolean> condition) {
        if (condition.apply(object)) {
            return false;
        }
        Node<T> newNode = new Node<>(object);
        Node<T> targetNode = findNodeIndex(index);
        if (targetNode == null) {
            return false;
        }
        newNode.setNext(targetNode);
        newNode.setPrevious(targetNode.getPrevious());
        targetNode.getPrevious().setNext(newNode);
        targetNode.setPrevious(newNode);
        size++;
        return true;
    }

    private boolean removeNode(Node<T> node) {
        if (node == null) {
            return false;
        }
        if (tail != null && tail.equals(node)) {
            tail = node.getPrevious();
        }
        if (head != null && head.equals(node)) {
            head = node.getNext();
        }
        Node<T> previous = node.getPrevious();
        Node<T> next = node.getNext();
        if (previous != null) {
            previous.setNext(next);
        }
        if (next != null) {
            next.setPrevious(previous);
        }
        node.setPrevious(null);
        node.setNext(null);
        size--;
        return true;
    }

    public boolean removeItem(Object object) {
        if (head == null) {
            return false;
        }
        Node<T> current = head;
        while (current.getNext() != null) {
            if (current.getObject().equals(object)) {
                return removeNode(current);
            }
        }
        return false;
    }

    private Node<T> findObject(T object) {
        Node<T> current = head;
        while (current != null) {
            if (current.getObject().equals(object)) {
                return current;
            }
            current = current.getNext();
        }
        return null;
    }

    public boolean contains(T object) {
        return (findObject(object) != null);
    }

    private Node<T> findNodeIndex(int index) {
        if (head == null) {
            return null;
        }
        Node<T> current = head;
        while (current.getNext() != null && index > 0) {
            current = current.getNext();
            index--;
        }
        if (index == 0) {
            return current;
        }
        return null;
    }

    public T getItemAtIndex(int index) {
        return findNodeIndex(index).getObject();
    }

    public T getItem(T object) {
        return findObject(object).getObject();
    }

    public boolean removeIndex(int index) {
        return removeNode(findNodeIndex(index));
    }

    public ArrayList<T> toArray() {
        ArrayList<T> newArray = new ArrayList<>();
        if (head == null) {
            return newArray;
        }
        Node<T> current = head;
        while (current != null) {
            newArray.add(current.getObject());
            current = current.getNext();
        }
        return newArray;
    }

    public int getSize() {
        return this.size;
    }

    public T getLast() {
        return tail.getObject();
    }
}