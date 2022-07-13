package pl.bjankowski.carinsurance.model;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@Builder
@Getter@Setter
public class Car {


    private String model;
    private String plateNumber;
    private LocalDate insuranceStartDate;
    private LocalDate damageReportingDate;
    private BigDecimal carValue;
    private BigDecimal damageValuation;




}
