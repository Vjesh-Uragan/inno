import java.util.Scanner;
import java.util.Random;

public class App {
    public static void main(String[] args) {
        // Создаем несколько экземпляров класса Телевизор
        televizor tv1 = new televizor("Samsung", 55);
        televizor tv2 = new televizor("LG", 42);
        televizor tv3 = new televizor("Sony", 65);

        // Используем методы
        tv1.включить();
        tv2.включить();

        // Показываем информацию
        tv1.показатьИнформацию();
        tv2.показатьИнформацию();
        tv3.показатьИнформацию();

        // Создаем телевизор с случайными параметрами
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Введите бренд для случайного ТВ: ");
        String пользовательскийБренд = scanner.nextLine();

        int случайнаяДиагональ = 32 + random.nextInt(34); // от 32 до 65

        televizor tvСлучайный = new televizor(пользовательскийБренд, случайнаяДиагональ);
        tvСлучайный.включить();
        tvСлучайный.показатьИнформацию();
    }
}