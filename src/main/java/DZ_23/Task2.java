package DZ_23;

import java.util.*;

public class Task2 {
    public static boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) return false;

        Map<Character, Integer> charCount = new HashMap<>();

        // Считаем частоту символов в первой строке
        for (char c : s.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        // Уменьшаем частоту по символам второй строки
        for (char c : t.toCharArray()) {
            if (!charCount.containsKey(c)) return false;
            charCount.put(c, charCount.get(c) - 1);
            if (charCount.get(c) == 0) {
                charCount.remove(c);
            }
        }

        return charCount.isEmpty();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите первую строку:");
        String s = scanner.nextLine().toLowerCase().replaceAll("\\s+", "");

        System.out.println("Введите вторую строку:");
        String t = scanner.nextLine().toLowerCase().replaceAll("\\s+", "");

        System.out.println(isAnagram(s, t));
    }
}
