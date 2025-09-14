package homework011;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class CarsRepositoryImpl implements CarsRepository {
    private final String filename;

    public CarsRepositoryImpl(String filename) {
        this.filename = filename;
    }

    @Override
    public List<Car> getAllCars() {
        List<Car> cars = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    String number = parts[0];
                    String model = parts[1];
                    String color = parts[2];
                    long mileage = Long.parseLong(parts[3]);
                    long cost = Long.parseLong(parts[4]);
                    cars.add(new Car(number, model, color, mileage, cost));
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
        return cars;
    }

    @Override
    public void saveAll(List<Car> cars) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Car car : cars) {
                bw.write(String.format("%s|%s|%s|%d|%d",
                        car.getNumber(), car.getModel(), car.getColor(),
                        car.getMileage(), car.getCost()));
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}
