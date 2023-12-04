package functionalinterfaces;

import java.util.ArrayList;

@FunctionalInterface
public interface ToArray<T> {

    ArrayList myApply(T para1);

}
