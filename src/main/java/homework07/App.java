package homework07;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        try {
            // Ввод количества покупателей
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

            // Ввод продуктов (обычных и скидочных)
            while (true) {
                System.out.print("Введите название продукта (или 'END' для завершения): ");
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
                        validPrice = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Ошибка: Введите корректное число.");
                    }
                }

                // Спрашиваем, есть ли скидка
                System.out.print("Есть скидка? (да/нет): ");
                String hasDiscount = scanner.nextLine().trim().toLowerCase();

                if (hasDiscount.equals("да") || hasDiscount.equals("yes")) {
                    double discount = 0;
                    boolean validDiscount = false;
                    while (!validDiscount) {
                        try {
                            System.out.print("Размер скидки (%): ");
                            discount = Double.parseDouble(scanner.nextLine());
                            if (discount < 0 || discount > 100) {
                                System.out.println("Скидка должна быть от 0 до 100%");
                                continue;
                            }
                            validDiscount = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Ошибка: Введите число.");
                        }
                    }

                    LocalDate validUntil = null;
                    boolean validDate = false;
                    while (!validDate) {
                        try {
                            System.out.print("Срок действия скидки (ГГГГ-ММ-ДД): ");
                            String dateStr = scanner.nextLine();
                            validUntil = LocalDate.parse(dateStr);
                            if (validUntil.isBefore(LocalDate.now())) {
                                System.out.println("Срок действия не может быть в прошлом.");
                                continue;
                            }
                            validDate = true;
                        } catch (DateTimeParseException e) {
                            System.out.println("Ошибка: Неверный формат даты. Используйте ГГГГ-ММ-ДД.");
                        }
                    }

                    try {
                        Product product = new DiscountProduct(productName, price, discount, validUntil);
                        products.add(product);
                        System.out.println("Добавлен скидочный продукт: " + product);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка при создании скидочного продукта: " + e.getMessage());
                    }

                } else {
                    try {
                        Product product = new Product(productName, price);
                        products.add(product);
                        System.out.println("Добавлен обычный продукт: " + product);
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

                    Person currentPerson = people.get(personIndex);
                    currentPerson.buyProduct(selectedProduct);
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