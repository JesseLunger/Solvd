package functionalinterfaces;

@FunctionalInterface
public interface Compare<T> {

    int myApply(T para1, T para2);

}
