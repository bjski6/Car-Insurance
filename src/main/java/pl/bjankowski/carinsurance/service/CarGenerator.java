package pl.bjankowski.carinsurance.service;

import org.springframework.stereotype.Component;
import pl.bjankowski.carinsurance.model.Car;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class CarGenerator {

    public List<Car> generateCars() {

        Car car1 = Car.builder()
                .model("Audi A6")
                .plateNumber("ABC 1234")
                .insuranceStartDate(LocalDate.of(2021, 3, 1))
                .damageReportingDate(LocalDate.of(2021, 6, 1))
                .carValue(BigDecimal.valueOf(40000))
                .damageValuation(BigDecimal.valueOf(4500))
                .build();

        Car car2 = Car.builder()
                .model("VW Passat")
                .plateNumber("DEF 567")
                .insuranceStartDate(LocalDate.of(2021, 1, 1))
                .damageReportingDate(LocalDate.of(2021, 9, 1))
                .carValue(BigDecimal.valueOf(12000))
                .damageValuation(BigDecimal.valueOf(7730))
                .build();

        Car car3 = Car.builder()
                .model("Skoda Octavia")
                .plateNumber("GHI 8910")
                .insuranceStartDate(LocalDate.of(2020, 1, 1))
                .damageReportingDate(LocalDate.of(2021, 5, 1))
                .carValue(BigDecimal.valueOf(25000))
                .damageValuation(BigDecimal.valueOf(7000))
                .build();

        List<Car> carList = Arrays.asList(car1, car2, car3);

        return carList;
    }

}

