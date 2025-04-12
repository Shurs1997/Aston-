package Lesson3;

public class Lesson9 {
    public static void main(String[] args) {
        System.out.println(isLeapYear(2001));

    }

    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}
