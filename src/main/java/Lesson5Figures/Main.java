package Lesson5Figures;

public class Main {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Circle(5, "Красный", "Черный"),
                new Rectangle(4, 6, "Синий", "Белый"),
                new Triangle(3, 4, 5, "Зеленый", "Серый")
        };

        for (Shape shape : shapes) {
            System.out.println("Фигура: " + shape.getClass().getSimpleName());
            System.out.println("Цвет заливки: " + shape.getFillColor());
            System.out.println("Цвет границы: " + shape.getBorderColor());
            System.out.printf("Площадь: %.2f\n", shape.calculateArea());
            System.out.printf("Периметр: %.2f\n", shape.calculatePerimeter());
            System.out.println("------------");
        }
    }
}
