package homework011;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        CarsRepository repository = new CarsRepositoryImpl("src//main//java//homework011//input.txt");
        List<Car> cars = repository.getAllCars();

        // Вывод в файл output.txt
        try (PrintWriter writer = new PrintWriter(new FileWriter("src//main//java//homework011//output.txt"))) {
            writer.println("Автомобили в базе:");
            writer.println("Number   Model      Color    Mileage  Cost");
            cars.forEach(car -> writer.println(car));

            // 1) Номера всех автомобилей, имеющих заданный цвет или нулевой пробег
            String colorToFind = "Black";
            long mileageToFind = 0L;
            String numbersByColorOrMileage = cars.stream()
                    .filter(car -> car.getColor().equals(colorToFind) || car.getMileage() == mileageToFind)
                    .map(Car::getNumber)
                    .collect(Collectors.joining(" "));
            writer.println("\nНомера автомобилей по цвету или пробегу: " + numbersByColorOrMileage);

            // 2) Количество уникальных моделей в ценовом диапазоне от n до m тыс.
            long n = 700_000L;
            long m = 800_000L;
            long uniqueModelsCount = cars.stream()
                    .filter(car -> car.getCost() >= n && car.getCost() <= m)
                    .map(Car::getModel)
                    .distinct()
                    .count();
            writer.println("Уникальные автомобили: " + uniqueModelsCount + " шт.");

            // 3) Цвет автомобиля с минимальной стоимостью
            String minCostColor = cars.stream()
                    .min(Comparator.comparing(Car::getCost))
                    .map(Car::getColor)
                    .orElse("N/A");
            writer.println("Цвет автомобиля с минимальной стоимостью: " + minCostColor);

            // 4) Средняя стоимость искомой модели
            String[] modelsToFind = {"Toyota", "Volvo"};
            for (String modelToFind : modelsToFind) {
                double avgCost = cars.stream()
                        .filter(car -> car.getModel().equals(modelToFind))
                        .mapToLong(Car::getCost)
                        .average()
                        .orElse(0.0);
                writer.printf("Средняя стоимость модели %s: %.2f%n", modelToFind, avgCost);
            }

        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }

        // Также выводим в консоль для удобства
        System.out.println("Результаты сохранены в output.txt");
    }
}
