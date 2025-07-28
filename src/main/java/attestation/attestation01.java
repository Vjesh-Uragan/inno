package attestation;

import java.util.*;
import java.util.ArrayList;
import java.util.List;

// Класс Продукт
class Product {
    private String name;
    private double price;

    // Конструктор
    public Product(String name, double price) {
        setName(name);
        setPrice(price);
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым");
        }
        this.name = name.trim();
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price < 0) {
            throw new IllegalArgumentException("Стоимость продукта не может быть отрицательной");
        }
        this.price = price;
    }

    // Переопределение toString
    @Override
    public String toString() {
        return "Product{name='" + name + "', price=" + price + "}";
    }

    // Переопределение equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 && Objects.equals(name, product.name);
    }

    // Переопределение hashCode
    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}

// Класс Покупатель
class Person {
    private String name;
    private double money;
    private List<Product> cart; // Пакет с продуктами

    // Конструктор
    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.cart = new ArrayList<>();
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
        }
        if (name.trim().length() < 3) {
            throw new IllegalArgumentException("Имя не может быть короче 3 символов");
        }
        this.name = name.trim();
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        if (money < 0) {
            throw new IllegalArgumentException("Деньги не могут быть отрицательными");
        }
        this.money = money;
    }

    public List<Product> getCart() {
        return new ArrayList<>(cart); // Возвращаем копию для инкапсуляции
    }

    // Метод для покупки продукта
    public boolean buyProduct(Product product) {
        if (money >= product.getPrice()) {
            cart.add(product);
            money -= product.getPrice();
            return true;
        } else {
            System.out.println(this.name + " не может позволить себе " + product.getName());
            return false;
        }
    }

    // Переопределение toString
    @Override
    public String toString() {
        return "Person{name='" + name + "', money=" + money + ", cart=" + cart + "}";
    }

    // Переопределение equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Double.compare(person.money, money) == 0 &&
                Objects.equals(name, person.name) &&
                Objects.equals(cart, person.cart);
    }

    // Переопределение hashCode
    @Override
    public int hashCode() {
        return Objects.hash(name, money, cart);
    }
}

// Основной класс с переименованным названием
public class attestation01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Person> people = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        // Ввод покупателей
        System.out.println("Введите список покупателей (введите 'END' для завершения ввода):");
        while (true) {
            System.out.print("Имя покупателя: ");
            String name = scanner.nextLine();
            if ("END".equalsIgnoreCase(name.trim())) {
                break;
            }

            System.out.print("Деньги: ");
            String moneyInput = scanner.nextLine();
            try {
                double money = Double.parseDouble(moneyInput);
                try {
                    Person person = new Person(name, money);
                    people.add(person);
                    System.out.println("Покупатель добавлен: " + person.getName());
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод суммы денег.");
            }
        }

        // Ввод продуктов
        System.out.println("\nВведите список продуктов (введите 'END' для завершения ввода):");
        while (true) {
            System.out.print("Название продукта: ");
            String productName = scanner.nextLine();
            if ("END".equalsIgnoreCase(productName.trim())) {
                break;
            }

            System.out.print("Стоимость: ");
            String priceInput = scanner.nextLine();
            try {
                double price = Double.parseDouble(priceInput);
                try {
                    Product product = new Product(productName, price);
                    products.add(product);
                    System.out.println("Продукт добавлен: " + product.getName());
                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            } catch (NumberFormatException e) {
                System.out.println("Некорректный ввод стоимости.");
            }
        }

        // Покупка продуктов по очереди
        System.out.println("\n=== Начало процесса покупки ===");
        System.out.println("Введите название продукта для покупки. Введите 'END', чтобы завершить.");

        while (true) {
            System.out.print("Введите название продукта: ");
            String input = scanner.nextLine().trim();
            if ("END".equalsIgnoreCase(input)) {
                break;
            }

            // Поиск продукта
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

            // Каждый покупатель пытается купить продукт по очереди
            boolean someoneBought = false;
            for (Person person : people) {
                if (person.getMoney() >= selectedProduct.getPrice()) {
                    person.buyProduct(selectedProduct);
                    someoneBought = true;
                    break; // Только один покупатель может купить продукт
                }
            }

            if (!someoneBought) {
                System.out.println("Ни один из покупателей не может позволить себе " + selectedProduct.getName());
            }
        }

        // Вывод результатов
        System.out.println("\n=== Результаты покупок ===");
        for (Person person : people) {
            if (person.getCart().isEmpty()) {
                System.out.println(person.getName() + " — Ничего не куплено");
            } else {
                System.out.println(person.getName() + " купил: " + person.getCart());
            }
        }

        scanner.close();
    }
}