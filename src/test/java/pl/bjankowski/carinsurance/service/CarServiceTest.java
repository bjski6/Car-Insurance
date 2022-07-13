package pl.bjankowski.carinsurance.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.bjankowski.carinsurance.model.Car;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


class CarServiceTest {

    private CarService carService = new CarService();

    Car carToTest = Car.builder()
            .model("BMW")
            .plateNumber("XYZ 789")
            .insuranceStartDate(LocalDate.of(2021, 5, 1))
            .damageReportingDate(LocalDate.of(2021, 10, 1))
            .carValue(BigDecimal.valueOf(15000))
            .damageValuation(BigDecimal.valueOf(3000))
            .build();


    @Test
    @DisplayName("Should throw NullPointerException if car is null.")
    void testIsCarNotNull() {
        carToTest = null;
        var exception = Assertions.catchThrowable(() -> carService.calculateCompensationToPaid(carToTest));
        Assertions.assertThat(exception)
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("not be null!");
    }

    @Test
    @DisplayName("Should return 0 when car insurance is not valid.")
    void testCalculateCompensationWhenInsuranceIsNotValid() {
        carToTest.setInsuranceStartDate(LocalDate.of(2020, 1, 1));
        carToTest.setDamageReportingDate(LocalDate.of(2021, 5, 1));
        assertEquals(carService.calculateCompensationToPaid(carToTest), 0d);
    }

    @Test
    @DisplayName("Should return 70% of current car value when car is full damage.")
    void testCalculateCompensationWhenCarIsFullDamage() {
        carToTest.setDamageValuation(BigDecimal.valueOf(12000).setScale(2, RoundingMode.HALF_UP));
        BigDecimal currentCompensate = BigDecimal.valueOf(9975).setScale(2, RoundingMode.HALF_UP);
        assertEquals(carService.calculateCompensationToPaid(carToTest), currentCompensate.doubleValue());
    }

    @Test
    @DisplayName("Should return compensation equal to damage valuation.")
    void testCalculateCompensationToPaid() {
        assertEquals(carService.calculateCompensationToPaid(carToTest), BigDecimal.valueOf(3000).setScale(2,RoundingMode.HALF_UP).doubleValue());
    }

    @Test
    @DisplayName("Should calculate car value depends on insurance length.")
    void testCurrentCarValue() {
        BigDecimal result = carService.getCurrentCarValue(carToTest);
        assertEquals(BigDecimal.valueOf(14250).setScale(2, RoundingMode.HALF_UP), result);
    }

    @Test
    @DisplayName("Should return true when compensation is greater than 70% of current car value.")
    void testIsFullDamage() {
        carToTest.setDamageValuation(BigDecimal.valueOf(12000));
        assertTrue(carService.isFullDamage(carToTest));
    }

    @Test
    @DisplayName("Should return false when compensation is equal or less than 70% of current car value.")
    void testIsNotFullDamage() {
        carToTest.setDamageValuation(BigDecimal.valueOf(2000));
        assertFalse(carService.isFullDamage(carToTest));
    }

    @Test
    @DisplayName("Should return true when period between start insurance and register damage is less than 1 year.")
    void testIsInsuranceValid() {
        carToTest.setInsuranceStartDate(LocalDate.of(2021, 12, 1));
        carToTest.setDamageReportingDate(LocalDate.of(2021, 8, 1));
        assertTrue(carService.isInsuranceValid(carToTest));
    }

    @Test
    @DisplayName("Should return false when period between start insurance and register damage is greater than 1 year")
    void testIsInsuranceNotValid() {
        carToTest.setInsuranceStartDate(LocalDate.of(2020, 2, 1));
        carToTest.setDamageReportingDate(LocalDate.of(2021, 8, 1));
        assertFalse(carService.isInsuranceValid(carToTest));
    }

    @Test
    @DisplayName("Should calculate amount to compensate depends of current car value.")
    void testCompensationCalculator() {
        BigDecimal compensate = carService.compensationCalculator(carToTest).setScale(2, RoundingMode.HALF_UP);
        BigDecimal result = BigDecimal.valueOf(9975).setScale(2, RoundingMode.HALF_UP);
        assertEquals(result, compensate);
    }

}


