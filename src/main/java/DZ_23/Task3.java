package DZ_23;

import java.util.*;

public class Task3 {

    // Пересечение двух множеств
    public <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.retainAll(set2);
        return result;
    }

    // Объединение двух множеств
    public <T> Set<T> union(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.addAll(set2);
        return result;
    }

    // Относительное дополнение: set1 \ set2
    public <T> Set<T> relativeComplement(Set<T> set1, Set<T> set2) {
        Set<T> result = new HashSet<>(set1);
        result.removeAll(set2);
        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task3 ps = new Task3();

        System.out.println("Выберите тип элементов:");
        System.out.println("1 - Целые числа (Integer)");
        System.out.println("2 - Строки (String)");
        System.out.print("Ваш выбор: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // съедаем \n после nextInt()

        if (choice == 1) {
            // Работаем с Integer
            System.out.println("Введите элементы первого множества (целые числа, через пробел):");
            Set<Integer> set1 = parseIntegerSet(scanner.nextLine());

            System.out.println("Введите элементы второго множества (целые числа, через пробел):");
            Set<Integer> set2 = parseIntegerSet(scanner.nextLine());

            System.out.println("\n--- Результаты для чисел ---");
            System.out.println("Пересечение: " + ps.intersection(set1, set2));
            System.out.println("Объединение: " + ps.union(set1, set2));
            System.out.println("Дополнение (set1 \\ set2): " + ps.relativeComplement(set1, set2));

        } else if (choice == 2) {
            // Работаем с String
            System.out.println("Введите элементы первого множества (слова, через пробел):");
            Set<String> set1 = parseStringSet(scanner.nextLine());

            System.out.println("Введите элементы второго множества (слова, через пробел):");
            Set<String> set2 = parseStringSet(scanner.nextLine());

            System.out.println("\n--- Результаты для строк ---");
            System.out.println("Пересечение: " + ps.intersection(set1, set2));
            System.out.println("Объединение: " + ps.union(set1, set2));
            System.out.println("Дополнение (set1 \\ set2): " + ps.relativeComplement(set1, set2));

        } else {
            System.out.println("Неверный выбор. Завершение программы.");
        }

        scanner.close();
    }

    // Парсинг строки в Set<Integer>
    private static Set<Integer> parseIntegerSet(String input) {
        Set<Integer> set = new HashSet<>();
        String[] parts = input.trim().split("\\s+");
        for (String part : parts) {
            if (!part.isEmpty()) {
                try {
                    set.add(Integer.parseInt(part));
                } catch (NumberFormatException e) {
                    System.out.println("Предупреждение: '" + part + "' не является целым числом и будет пропущено.");
                }
            }
        }
        return set;
    }

    // Парсинг строки в Set<String>
    private static Set<String> parseStringSet(String input) {
        Set<String> set = new HashSet<>();
        String[] parts = input.trim().split("\\s+");
        for (String part : parts) {
            if (!part.isEmpty()) {
                set.add(part);
            }
        }
        return set;
    }
}