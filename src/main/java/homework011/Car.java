package homework011;

public class Car {
    private String number;
    private String model;
    private String color;
    private long mileage;
    private long cost;

    // Конструктор
    public Car(String number, String model, String color, long mileage, long cost) {
        this.number = number;
        this.model = model;
        this.color = color;
        this.mileage = mileage;
        this.cost = cost;
    }

    // Геттеры
    public String getNumber() { return number; }
    public String getModel() { return model; }
    public String getColor() { return color; }
    public long getMileage() { return mileage; }
    public long getCost() { return cost; }

    // Сеттеры
    public void setNumber(String number) { this.number = number; }
    public void setModel(String model) { this.model = model; }
    public void setColor(String color) { this.color = color; }
    public void setMileage(long mileage) { this.mileage = mileage; }
    public void setCost(long cost) { this.cost = cost; }

    @Override
    public String toString() {
        return String.format("%-8s %-10s %-8s %-8d %d", number, model, color, mileage, cost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return number.equals(car.number);
    }

    @Override
    public int hashCode() {
        return number.hashCode();
    }
}