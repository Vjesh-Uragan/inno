package homework07;

import java.time.LocalDate;

public class DiscountProduct extends Product {
    private double discount; // в процентах: 0.0 до 100.0
    private LocalDate validUntil;

    public DiscountProduct(String name, double price, double discount, LocalDate validUntil) {
        super(name, price);
        setDiscount(discount);
        setValidUntil(validUntil);
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        if (discount < 0 || discount > 100) {
            throw new IllegalArgumentException("Скидка должна быть от 0 до 100%");
        }
        this.discount = discount;
    }

    public LocalDate getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(LocalDate validUntil) {
        if (validUntil == null) {
            throw new IllegalArgumentException("Срок действия скидки не может быть null");
        }
        if (validUntil.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Срок действия скидки не может быть в прошлом");
        }
        this.validUntil = validUntil;
    }

    // Проверяем, действует ли скидка
    public boolean isDiscountValid() {
        return !LocalDate.now().isAfter(validUntil);
    }

    // Возвращает цену со скидкой, если скидка действует
    @Override
    public double getPrice() {
        if (isDiscountValid()) {
            return super.getPrice() * (1 - discount / 100);
        }
        return super.getPrice(); // без скидки, если срок истек
    }

    @Override
    public String toString() {
        String base = super.toString();
        String discountInfo = String.format(", скидка=%.1f%% до %s", discount, validUntil);
        if (!isDiscountValid()) {
            discountInfo += " (истекла)";
        }
        return base.substring(0, base.length() - 1) + discountInfo + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) return false;
        DiscountProduct that = (DiscountProduct) o;
        return Double.compare(that.discount, discount) == 0 &&
                validUntil.equals(that.validUntil);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(super.hashCode(), discount, validUntil);
    }
}