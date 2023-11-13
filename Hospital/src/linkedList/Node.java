package linkedList;

public class Node<T> {
    private Node<T> next;
    private Node<T> previous;
    private T object;

    public Node(T object){
        this.object = object;
    }

    public T getObject(){
        return this.object;
    }
    public void setObject(T object){
        this.object = object;
    }

    public Node<T> getNext(){
        return this.next;
    }

    public void setNext(Node<T> next){
        this.next = next;
    }


    public Node<T> getPrevious(){
        return this.previous;
    }

    public void setPrevious(Node<T> previous){
        this.previous = previous;
    }

}