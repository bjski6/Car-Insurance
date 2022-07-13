package pl.bjankowski.carinsurance.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import pl.bjankowski.carinsurance.model.Car;
import pl.bjankowski.carinsurance.service.CarGenerator;
import pl.bjankowski.carinsurance.service.CarService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/")
public class InsuranceController {


    private final CarGenerator carGenerator;
    private final CarService carService;

    public InsuranceController(CarGenerator carGenerator, CarService carService) {
        this.carGenerator = carGenerator;
        this.carService = carService;
    }

    @GetMapping("compensationInfo")
    @ResponseBody
    public Map<String, Double> getCompensationInfo() {

        Map<String,Double> compensationInfo = new HashMap<>();
        List<Car> carList = carGenerator.generateCars();
            carList.stream()
                    .forEach(car -> compensationInfo.put(car.getPlateNumber(),carService.calculateCompensationToPaid(car)));

            return compensationInfo;
    }
}
