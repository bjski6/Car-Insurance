package pl.bjankowski.carinsurance.utils;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilsTest {

    @Test
    @DisplayName("should calculate percentage of base value")
    void percentageOfValue(){
        BigDecimal base= BigDecimal.valueOf(500).setScale(2,RoundingMode.HALF_UP);
        BigDecimal pct= BigDecimal.valueOf(70).setScale(2,RoundingMode.HALF_UP);
        BigDecimal result=BigDecimal.valueOf(350).setScale(2,RoundingMode.HALF_UP);
        assertEquals(Utils.percentage(base,pct), result);
    }

    @Test
    @DisplayName("should throw IllegalArgumentException when percent is not between 1 and 100")
    void exceptionThrows(){
        BigDecimal base= BigDecimal.valueOf(500).setScale(2,RoundingMode.HALF_UP);

        BigDecimal pct1= BigDecimal.valueOf(120).setScale(2,RoundingMode.HALF_UP);
        BigDecimal pct2= BigDecimal.valueOf(0).setScale(2,RoundingMode.HALF_UP);
        BigDecimal pct3= BigDecimal.valueOf(-20).setScale(2,RoundingMode.HALF_UP);

        var exception1 = Assertions.catchThrowable(()->Utils.percentage(base,pct1));
        var exception2 = Assertions.catchThrowable(()->Utils.percentage(base,pct2));
        var exception3 = Assertions.catchThrowable(()->Utils.percentage(base,pct3));

        Assertions.assertThat(exception1)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("between 1 and 100");

        Assertions.assertThat(exception2)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("between 1 and 100");

        Assertions.assertThat(exception3)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("between 1 and 100");
    }

}