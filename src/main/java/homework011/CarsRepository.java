package homework011;

import java.util.List;

public interface CarsRepository {
    List<Car> getAllCars();
    void saveAll(List<Car> cars);
}
