package pl.bjankowski.carinsurance.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {


    public static BigDecimal percentage(BigDecimal base, BigDecimal percentages) {
        int value= percentages.intValue();
        if(value>100 || value<=0) throw new IllegalArgumentException("percentages value must be between 1 and 100");
        return base.multiply(percentages).divide(BigDecimal.valueOf(100)).setScale(2, RoundingMode.HALF_UP);
    }

}
