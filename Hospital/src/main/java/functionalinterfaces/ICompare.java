package functionalinterfaces;

@FunctionalInterface
public interface ICompare<T> {

    int compare(T para1, T para2);
}
