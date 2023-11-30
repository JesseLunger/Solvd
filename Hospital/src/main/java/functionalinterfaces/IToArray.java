package functionalinterfaces;

import java.util.ArrayList;

@FunctionalInterface
public interface IToArray<T> {

    ArrayList myApply(T para1);

}
