package homework06;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Person {
    private String name;
    private double money;
    private List<Product> basket;

    public Person(String name, double money) {
        setName(name);
        setMoney(money);
        this.basket = new ArrayList<>();
    }

    // Геттеры и сеттеры
    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя не может быть пустым");
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

    public List<Product> getBasket() {
        return new ArrayList<>(basket); // возвращаем копию для инкапсуляции
    }

    // Метод для добавления продукта
    public boolean buyProduct(Product product) {
        if (product == null) {
            return false;
        }
        if (money >= product.getPrice()) {
            basket.add(product);
            money -= product.getPrice();
            return true;
        } else {
            System.out.println(this.name + " не может позволить себе " + product.getName());
            return false;
        }
    }

    // toString
    @Override
    public String toString() {
        return "Покупатель{" +
                "имя='" + name + '\'' +
                ", деньги=" + String.format("%.2f", money) +
                ", пакет=" + basket +
                '}';
    }

    // equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Double.compare(person.money, money) == 0 &&
                Objects.equals(name, person.name) &&
                Objects.equals(basket, person.basket);
    }

    // hashCode
    @Override
    public int hashCode() {
        return Objects.hash(name, money, basket);
    }
}