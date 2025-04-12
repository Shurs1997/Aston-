package Lesson3;

public class Lesson8 {
    public static void main(String[] args) {
        printStringNTimes("Hellow, java!", 5);
    }

    public static void printStringNTimes(String text, int count) {
        for (int i = 0; i < count; i++) {
            System.out.println(text);
        }
    }
}
