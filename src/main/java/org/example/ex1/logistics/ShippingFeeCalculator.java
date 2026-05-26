package org.example.ex1.logistics;

public class ShippingFeeCalculator {

    public double calculateFee(double weightKg, double distanceKm) {
        if (weightKg <= 0 || distanceKm <= 0) {
            throw new IllegalArgumentException("weight and distance must be positive !");

        }
            double weightFee;

            if (weightKg <= 1) {
                weightFee = 50000;
            } else {
                weightFee = 50000 + (Math.ceil(weightKg - 1) * 10000);
            }


            double distanceFee;


            if (distanceKm < 10) {
                distanceFee = 0;
            } else if (distanceKm < 50) {
                distanceFee = distanceKm * 5000;
            } else {
                distanceFee = distanceKm * 4000;
            }



        return weightFee + distanceFee;
    }
}
