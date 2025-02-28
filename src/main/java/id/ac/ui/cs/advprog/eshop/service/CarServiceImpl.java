package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.repository.CarRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarRepositoryInterface carRepository;

    @Override
    public Car create(Car car) {
        return carRepository.create(car);
    }

    @Override
    public List<Car> findAll() {
        Iterator<Car> carIterator = carRepository.findAll();
        List<Car> allCars = new ArrayList<>();
        carIterator.forEachRemaining(allCars::add);
        return allCars;
    }

    @Override
    public Optional<Car> findById(String carId) {
        return carRepository.findById(carId);
    }

    @Override
    public Car update(String carId, Car car) {
        return carRepository.update(carId, car);
    }

    @Override
    public boolean delete(String carId) {
        return carRepository.delete(carId);
    }
}
