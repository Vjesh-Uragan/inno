package homework07;

import java.util.Objects;

public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        setName(name);
        setPrice(price);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название продукта не может быть пустым");
        }

        String trimmed = name.trim();

        if (trimmed.length() < 3) {
            throw new IllegalArgumentException("Название продукта должно быть не менее 3 символов");
        }

        if (trimmed.chars().allMatch(Character::isDigit)) {
            throw new IllegalArgumentException("Название продукта не может состоять только из цифр");
        }

        this.name = trimmed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Стоимость продукта должна быть положительной");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return "Продукт{" +
                "название='" + name + '\'' +
                ", цена=" + String.format("%.2f", price) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Double.compare(product.price, price) == 0 &&
                Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, price);
    }
}