package PhoneBook;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PhoneBook {
    private Map<String, List<String>> phoneBook = new HashMap<>();

    public void add(String lastName, String phoneNumber) {
        phoneBook.computeIfAbsent(lastName, k -> new ArrayList<>()).add(phoneNumber);
    }

    public List<String> get(String lastName) {
        return phoneBook.getOrDefault(lastName, new ArrayList<>());
    }

    public static void main(String[] args) {
        PhoneBook book = new PhoneBook();
        book.add("Иванов", "123-456");
        book.add("Петров", "234-567");
        book.add("Селиванов", "245-555");
        book.add("Иванов", "345-678");

        System.out.println("Телефоны Иванов: " + book.get("Иванов"));
        System.out.println("Телефоны Петров: " + book.get("Петров"));
        System.out.println("Телефоны Селиванов: " + book.get("Селиванов"));
    }
}
