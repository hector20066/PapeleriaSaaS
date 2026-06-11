package com.hector.papeleriasaas.view.chart;

/**
 *
 * @author Ing. Hector Acevedo
 */
public class NiceScale {

    private double min;
    private double max;
    private int maxTicks = 10;
    private double tickSpacing;
    private double range;
    private double niceMin;
    private double niceMax;

    public NiceScale(final double min, final double max) {
        this.min = min;
        this.max = max;
        calculate();
    }

    private void calculate() {
        range = niceNum(max - min, false);
        tickSpacing = niceNum(range / (maxTicks - 1), true);
        niceMin = Math.floor(min / tickSpacing) * tickSpacing;
        niceMax = Math.ceil(max / tickSpacing) * tickSpacing;
    }

    private double niceNum(final double range, final boolean round) {
        double exponent;
        double fraction;
        double niceFraction;

        exponent = Math.floor(Math.log10(range));
        fraction = range / Math.pow(10, exponent);

        if (round) {
            if (fraction < 1.5) {
                niceFraction = 1;
            } else {
                if (fraction < 3) {
                    niceFraction = 2;
                } else {
                    if (fraction < 7) {
                        niceFraction = 5;
                    } else {
                        niceFraction = 10;
                    }
                }
            }
        } else {
            if (fraction <= 1) {
                niceFraction = 1;
            } else {
                if (fraction <= 2) {
                    niceFraction = 2;
                } else {
                    if (fraction <= 5) {
                        niceFraction = 5;
                    } else {
                        niceFraction = 10;
                    }
                }
            }

        }
        return niceFraction * Math.pow(10, exponent);
    }

    public void setMinMax(final double min, final double max) {
        this.min = min;
        this.max = max;
        calculate();
    }

    public double getTickSpacing() {
        return tickSpacing;
    }

    public double getNiceMin() {
        return niceMin;
    }

    public double getNiceMax() {
        return niceMax;
    }

    public double getMaxTicks() {
        return maxTicks;
    }

    public void setMaxTicks(final int maxTicks) {
        this.maxTicks = maxTicks;
        calculate();
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
        calculate();
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
        calculate();
    }

}
