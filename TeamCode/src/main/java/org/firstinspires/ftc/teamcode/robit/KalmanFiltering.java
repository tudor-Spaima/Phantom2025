package org.firstinspires.ftc.teamcode.robit;

public class KalmanFiltering {
    private double x;
    private double P;
    private final double Q;
    private double R;
    public KalmanFiltering(double initialEstimate, double initialUncertainty, double processNoise, double measurementNoise) {
        this.x = initialEstimate;
        this.P = initialUncertainty;
        this.Q = processNoise;
        this.R = measurementNoise;
    }
    public double update(double measurement, double q, double r) {
        double P_pred = P + Q;
        double K = P_pred / (P_pred + R); // Kalman Gain
        x = x + K * (measurement - x);
        P = (1 - K) * P_pred;
        return x;
    }
/**
 * Provides guidance for tuning the filter parameters to achieve desired responsiveness.
 *
 * Tuning method:
 * - Start with small values for Q and R.
 * - Increase R if the filter is too responsive to noise.
 * - Increase Q if the filter is too slow to adapt to changes.
 *
 * Notes:
 * - Proper tuning depends on the specific application and noise characteristics.
 */

}
