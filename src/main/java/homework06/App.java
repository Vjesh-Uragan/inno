package homework06;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        try {
            // Ввод количества покупателей с обработкой ошибок
            int numPeople = 0;
            boolean validNumInput = false;
            while (!validNumInput) {
                try {
                    System.out.print("Сколько покупателей? ");
                    String input = scanner.nextLine();
                    numPeople = Integer.parseInt(input);
                    if (numPeople < 0) {
                        System.out.println("Количество покупателей не может быть отрицательным.");
                        continue;
                    }
                    validNumInput = true;
                } catch (NumberFormatException e) {
                    System.out.println("Ошибка: Введите целое число.");
                }
            }

            // Ввод покупателей
            for (int i = 0; i < numPeople; i++) {
                System.out.print("Имя покупателя: ");
                String name = scanner.nextLine();
                double money = 0;
                boolean validMoney = false;

                while (!validMoney) {
                    try {
                        System.out.print("Сумма денег: ");
                        money = Double.parseDouble(scanner.nextLine());
                        Person person = new Person(name, money);
                        people.add(person);
                        validMoney = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: Введите корректное число.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                }
            }

            // Ввод продуктов
            while (true) {
                System.out.print("Введите название продукта (или 'END' для завершения ввода продуктов): ");
                String productName = scanner.nextLine();
                if ("END".equalsIgnoreCase(productName.trim())) {
                    break;
                }

                double price = 0;
                boolean validPrice = false;
                while (!validPrice) {
                    try {
                        System.out.print("Цена продукта: ");
                        price = Double.parseDouble(scanner.nextLine());
                        Product product = new Product(productName, price);
                        products.add(product);
                        validPrice = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: Введите корректное число.");
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                }
            }

            // Покупка продуктов по очереди
            if (!products.isEmpty()) {
                int personIndex = 0;
                while (true) {
                    System.out.print("Что покупаем? (название продукта или 'END'): ");
                    String input = scanner.nextLine();
                    if ("END".equalsIgnoreCase(input.trim())) {
                        break;
                    }

                    // Поиск продукта по названию (без учёта регистра)
                    Product selectedProduct = null;
                    for (Product p : products) {
                        if (p.getName().equalsIgnoreCase(input)) {
                            selectedProduct = p;
                            break;
                        }
                    }

                    if (selectedProduct == null) {
                        System.out.println("Продукт '" + input + "' не найден.");
                        continue;
                    }

                    // Покупатель пытается купить
                    Person currentPerson = people.get(personIndex);
                    currentPerson.buyProduct(selectedProduct);

                    // Переход к следующему покупателю
                    personIndex = (personIndex + 1) % people.size();
                }
            }

            // Вывод результатов
            System.out.println("\n=== Результаты ===");
            for (Person person : people) {
                if (person.getBasket().isEmpty()) {
                    System.out.println(person.getName() + " — ничего не куплено");
                } else {
                    System.out.println(person);
                }
            }

        } catch (Exception e) {
            System.out.println("Неожиданная ошибка: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}