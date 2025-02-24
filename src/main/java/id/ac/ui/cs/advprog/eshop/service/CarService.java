package id.ac.ui.cs.advprog.eshop.service;
import id.ac.ui.cs.advprog.eshop.model.Car;
import java.util.List;

public interface CarService {

    public Car createCar(Car car);

    public List<Car> findAll();

    Car findById(String carId);

    public void update(String carId, Car car);

    public void deleteCarById(String carId);
}
