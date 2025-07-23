package DZ_10;

import java.util.Scanner;

public class key {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Строка, представляющая клавиатуру (без учета shift, только буквы)
        String keyboard = "qwertyuiopasdfghjklzxcvbnm";

        // Предложение ввести букву
        System.out.print("Введите с клавиатуры буквы английского алфавита: ");

        // Читаем входной символ
        char inputChar = scanner.next().charAt(0);

        // Находим позицию символа в строке клавиатуры
        int position = keyboard.indexOf(inputChar);

        // Если символ найден
        if (position != -1) {
            // Вычисляем позицию левой соседней буквы (с учетом замкнутости)
            int leftPosition = (position - 1 + keyboard.length()) % keyboard.length();

            // Выводим левую соседнюю букву
            System.out.println(keyboard.charAt(leftPosition));
        } else {
            System.out.println("Некорректный ввод");
        }

        scanner.close();
    }
}
