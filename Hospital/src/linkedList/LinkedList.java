package linkedList;

import java.util.ArrayList;

public class LinkedList<T> {

    private Node<T> head;
    private int size;
    public Node<T> tail;

    public LinkedList() {}


    public boolean addItem(T object){
        Node<T> newNode = new Node<>(object);
        if (head == null){
            head = newNode;
            tail = newNode;
        } else {
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
        return true;
    }

    public boolean insertItem(int index, T object){
        Node<T> targetNode = findNodeIndex(index);
        if (targetNode == null){
            return false;
        }
        targetNode.setObject(object);
        return true;
    }

    public boolean addAtIndex(int index, T object) {
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



    private boolean removeNode(Node<T> node){
        if (node == null){
            return false;
        }
        Node<T> previous = node.getPrevious();
        Node<T> next = node.getNext();
        if (previous != null){
            previous.setNext(next);
        }
        if (next != null){
            next.setPrevious(previous);
        }
        node.setPrevious(null);
        node.setNext(null);
        size--;
        return true;
    }

    public boolean removeItem(Object object){
        if (head == null){
            return false;
        }
        Node<T> current = head;
        while (current.getNext() != null){
            if (current.getObject().equals(object)){
                return removeNode(current);
            }
        }
        return false;
    }
    private Node<T> findNodeIndex(int index){
        if (head == null){
            return null;
        }
        Node<T> current = head;
        while(current.getNext() != null && index > 0){
            current = current.getNext();
            index--;
        }
        if (index == 0){
            return current;
        }
        return null;
    }

    public boolean removeIndex(int index){
        return removeNode(findNodeIndex(index));
    }

    public ArrayList<T> toArray(){
        ArrayList<T> newArray = new ArrayList<>();
        if (head == null){
            return newArray;
        }
        Node<T> current = head;
        while (current != null){
            newArray.add(current.getObject());
            current = current.getNext();
        }
        return newArray;
    }

    public  int getSize(){
        return this.size;
    }


}