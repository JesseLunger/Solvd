package functionalinterfaces;

@FunctionalInterface
public interface IFiveParameters<T> {

    T concatenate(T para1, T para2, T para3, T para4, T para5);
}
