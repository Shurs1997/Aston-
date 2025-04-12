package Lesson5Figures;

public interface Shape {
    double calculateArea();

    default double calculatePerimeter() {
        return 0.0; // можно переопределить при необходимости
    }

    String getFillColor();
    String getBorderColor();
}
