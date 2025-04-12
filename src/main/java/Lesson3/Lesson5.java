package Lesson3;

public class Lesson5 {
    public static void main(String[] args) {
        compareNumbers(16, 5);

    }

    public static void compareNumbers(int a, int b) {
        int sum = a + b;
        if (sum >= 10 && sum <= 20) {
            System.out.println(true);
        } else {
            System.out.println(false);
        }
    }
}
