import java.util.ArrayList;
import java.util.List;

public class ListFunctions {

    // member
    public static <T> boolean member(T atm, List<T> list) {
        for (T item : list) {
            if (item.equals(atm)) {
                return true;
            }
        }
        return false;
    }

    // append
    public static <T> List<T> append(List<T> list1, List<T> list2) {
    List<T> result = new ArrayList<>();

    for (T item : list1) {
        result.add(item);
    }
    for (T item : list2) {
        result.add(item);
    }

    return result;
    }

    // map
    public static <T, R> List<R> map(Function<T, R> fun, List<T> list) {
    List<R> result = new ArrayList<>();

    for (T element : list) {
        result.add(fun.apply(element));
    }

    return result;
    }

    // same
    public static <T> boolean same(List<T> list1, List<T> list2) {
    if (list1.size() != list2.size()) {
        return false;
    }

    for (int i = 0; i < list1.size(); i++) {
        if (!list1.get(i).equals(list2.get(i))) {
            return false;
        }
    }

    return true;
    }

    // intersect
    public static <T> List<T> intersect(List<T> list1, List<T> list2) {
    List<T> result = new ArrayList<>();

    for (T item : list1) {
        if (member(item, list2)) {
            result.add(item);
        }
    }

    return result;
    }

}