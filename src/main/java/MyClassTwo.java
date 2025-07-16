import java.util.Random;

public class MyClassTwo {
    public static void main(String[] args) {

        Random random = new Random();

        int vasyaChoice = random.nextInt(3);
        int petyaChoice = random.nextInt(3);

        // Выводим выбор игроков
        System.out.println("Вася показал: " + moveToString(vasyaChoice));
        System.out.println("Петя показал: " + moveToString(petyaChoice));

        String result = determineWinner(vasyaChoice, petyaChoice);
        System.out.println("Результат: " + result);
    }

    public static String moveToString(int move) {
        switch (move) {
            case 0: return "Камень";
            case 1: return "Ножницы";
            case 2: return "Бумага";
            default: return "Неизвестный ход";
        }
    }


    public static String determineWinner(int vasya, int petya) {
        if (vasya == petya) {
            return "Ничья!";
        }


        if ((vasya == 0 && petya == 1) ||
                (vasya == 1 && petya == 2) ||
                (vasya == 2 && petya == 0)) {
            return "Вася победил!";
        } else {
            return "Петя победил!";
        }
    }
}