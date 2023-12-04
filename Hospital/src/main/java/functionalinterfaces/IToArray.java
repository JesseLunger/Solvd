package functionalinterfaces;

import java.util.ArrayList;

@FunctionalInterface
public interface IToArray<T> {

    ArrayList toArray(T para1);
}
