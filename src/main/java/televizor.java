import java.util.Random;

public class televizor {
    // Приватные поля
    private String бренд;
    private int диагональ; // в дюймах
    private boolean включен;

    // Конструктор с параметрами
    public televizor(String бренд, int диагональ) {
        this.бренд = бренд;
        this.диагональ = диагональ;
        this.включен = false; // по умолчанию выключен
    }

    // Метод для включения телевизора
    public void включить() {
        включен = true;
        System.out.println(бренд + " включен.");
    }

    // Метод для выключения телевизора
    public void выключить() {
        включен = false;
        System.out.println(бренд + " выключен.");
    }

    // Метод для вывода информации о телевизоре
    public void показатьИнформацию() {
        System.out.println("Бренд: " + бренд);
        System.out.println("Диагональ экрана: " + диагональ + " дюймов");
        System.out.println("Состояние: " + (включен ? "Включен" : "Выключен"));
        System.out.println("---------------------------");
    }

    // Геттеры и сеттеры
    public String getБренд() {
        return бренд;
    }

    public void setБренд(String бренд) {
        this.бренд = бренд;
    }

    public int getДиагональ() {
        return диагональ;
    }

    public void setДиагональ(int диагональ) {
        this.диагональ = диагональ;
    }

    public boolean isВключен() {
        return включен;
    }
}