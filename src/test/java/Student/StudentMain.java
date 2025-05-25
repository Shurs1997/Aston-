package Student;

import java.util.*;

public class StudentMain {

    public static void removeLowGradeStudents(Set<Student> students) {
        students.removeIf(s -> s.getAverageGrade() < 3.0);
    }

    public static void promoteStudents(Set<Student> students) {
        for (Student s : students) {
            if (s.getAverageGrade() >= 3.0) {
                s.setCourse(s.getCourse() + 1);
            }
        }
    }

    public static void printStudents(Set<Student> students, int course) {
        for (Student s : students) {
            if (s.getCourse() == course) {
                System.out.println(s.getName());
            }
        }
    }

    public static void main(String[] args) {
        Set<Student> students = new HashSet<>();

        students.add(new Student("Иванов Иван", "QA-251", 1, Arrays.asList(2, 3, 2)));
        students.add(new Student("Петров Петр", "QA-252", 2, Arrays.asList(2, 2, 3)));
        students.add(new Student("Селиванов Саша", "QA-253", 1, Arrays.asList(5, 4, 5)));

        System.out.println("До удаления:");
        students.forEach(System.out::println);

        removeLowGradeStudents(students);

        System.out.println("\nПосле удаления студентов со средним баллом < 3:");
        students.forEach(System.out::println);

        promoteStudents(students);

        System.out.println("\nПосле перевода на следующий курс:");
        students.forEach(System.out::println);

        System.out.println("\nСтуденты на 2 курсе:");
        printStudents(students, 2);
    }
}