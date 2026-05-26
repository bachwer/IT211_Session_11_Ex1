package org.example.ex1.Test.logistics;


import org.example.ex1.logistics.ShippingFeeCalculator;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class ShippingFeeCalculatorTest {

    private final ShippingFeeCalculator calculator =
            new ShippingFeeCalculator();

    @Test
    public void shouldCalculateFeeForWeightLessThanOrEqualTo1KgAndDistanceLessThan10() {

        double fee = calculator.calculateFee(1, 5);

        assertThat(fee).isEqualTo(50000);
    }

    @Test
    public void shouldCalculateFeeForIntegerWeightAndDistanceBetween10And50() {

        double fee = calculator.calculateFee(3, 20);

        // 50k + 2*10k + 20*5k
        assertThat(fee).isEqualTo(170000);
    }

    @Test
    public void shouldCalculateFeeForDecimalWeightAndDistanceGreaterThan50() {

        double fee = calculator.calculateFee(1.5, 60);

        // 50k + ceil(0.5)*10k + 60*4k
        assertThat(fee).isEqualTo(300000);
    }

    @Test
    public void shouldCalculateCorrectlyAtDistanceBoundary10Km() {

        double fee = calculator.calculateFee(1, 10);

        // 50k + 10*5k
        assertThat(fee).isEqualTo(100000);
    }

    @Test
    public void shouldCalculateCorrectlyAtDistanceBoundary50Km() {

        double fee = calculator.calculateFee(1, 50);

        // 50k + 50*4k
        assertThat(fee).isEqualTo(250000);
    }

    @Test
    public void shouldThrowExceptionWhenWeightIsInvalid() {

        assertThatThrownBy(() ->
                calculator.calculateFee(0, 10))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Weight and distance must be positive");
    }

    @Test
    void shouldThrowExceptionWhenDistanceIsInvalid() {

        assertThatThrownBy(() ->
                calculator.calculateFee(1, -5))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Weight and distance must be positive");
    }

    @Test
    public void shouldCalculateCorrectlyFor2Point3Kg() {

        double fee = calculator.calculateFee(2.3, 55);

        // 50k + ceil(1.3)*10k + 55*4k
        assertThat(fee).isEqualTo(290000);
    }
}