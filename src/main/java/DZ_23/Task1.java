package DZ_23;

import java.util.*;

public class Task1 {
    public static <T> Set<T> getUniqueElements(ArrayList<T> list) {
        return new HashSet<>(list);
    }

    // Тестовый метод
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>(Arrays.asList("a", "b", "a", "c", "b"));
        Set<String> unique = getUniqueElements(list);
        System.out.println(unique); // [a, b, c] (порядок может отличаться)
    }
}