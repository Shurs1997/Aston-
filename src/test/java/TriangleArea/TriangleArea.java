package TriangleArea;

public class TriangleArea {
    public double calculate(double base, double height) {
        if (base < 0 || height < 0) throw new IllegalArgumentException("Negative value");
        return 0.5 * base * height;
    }
}
