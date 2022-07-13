package pl.bjankowski.carinsurance.service;

import org.springframework.stereotype.Service;
import pl.bjankowski.carinsurance.utils.Utils;
import pl.bjankowski.carinsurance.model.Car;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Period;

@Service
public class CarService {

    private static final Period INSURANCE_PERIOD = Period.ofYears(1);
    private static final BigDecimal FULL_DAMAGE_PERCENTAGE = BigDecimal.valueOf(70);

    public double calculateCompensationToPaid(Car car) {
        if (car == null) throw new NullPointerException("car must not be null!");
        if (!isInsuranceValid(car)) return BigDecimal.valueOf(0).setScale(2).doubleValue();
        if (isFullDamage(car)) return compensationCalculator(car).setScale(2, RoundingMode.HALF_UP).doubleValue();
        return car.getDamageValuation().setScale(2,RoundingMode.HALF_UP).doubleValue();
    }

    public BigDecimal getCurrentCarValue(Car car) {
        BigDecimal carValue = car.getCarValue().setScale(2,RoundingMode.HALF_UP);
        BigDecimal insuranceLengthInMonths = BigDecimal.valueOf(Period.between(car.getInsuranceStartDate(), car.getDamageReportingDate()).getMonths());
        BigDecimal currentCarValue = carValue.subtract(Utils.percentage(carValue, insuranceLengthInMonths)).setScale(2,RoundingMode.HALF_UP);
        return currentCarValue;
    }

    public boolean isFullDamage(Car car) {
        double compensation = car.getDamageValuation().setScale(2,RoundingMode.HALF_UP).doubleValue();
        double carValueAfterPercentageCalculating = compensationCalculator(car).doubleValue();
        if (compensation > carValueAfterPercentageCalculating) return true;
        return false;
    }

    public boolean isInsuranceValid(Car car) {
        if (INSURANCE_PERIOD.toTotalMonths() > Period.between(car.getInsuranceStartDate(), car.getDamageReportingDate()).toTotalMonths()) return true;
        return false;
    }

    public BigDecimal compensationCalculator(Car car) {
        return Utils.percentage(getCurrentCarValue(car), FULL_DAMAGE_PERCENTAGE).setScale(2,RoundingMode.HALF_UP);
    }


}
