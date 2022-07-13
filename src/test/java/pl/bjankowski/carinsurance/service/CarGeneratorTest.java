package pl.bjankowski.carinsurance.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.bjankowski.carinsurance.model.Car;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CarGeneratorTest {

    private CarGenerator carGenerator = new CarGenerator();


    @Test
    @DisplayName("Should return list of cars.")
    void testGenerateCars() {
        Car car1 = Car.builder().build();
        Car car2 = Car.builder().build();
        Car car3 = Car.builder().build();

        List<Car> carList= Arrays.asList(car1,car2,car3);
        List<Car> generatedCarList = carGenerator.generateCars();

        assertEquals(generatedCarList.size(),carList.size());

    }

}