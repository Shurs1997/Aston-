package Lesson1;

public class Lesson2 {
    public static void main(String[] args) {
        checkSumSign();
    }

    public static void checkSumSign() {
        int a = 10;
        int b = -20;
        int sum = a + b;

        if (sum >= 0) {
            System.out.println("сумма положительна");
        } else {
            System.out.println("сумма отрицательна");
        }
    }
}

