package Lesson2;

public class Product {
    private String name;
    private String manufactureDate;
    private String manufacturer;
    private String countryOfOrigin;
    private double price;
    private boolean isReserved;

    public Product(String name, String manufactureDate, String manufacturer,
                   String countryOfOrigin, double price, boolean isReserved) {
        this.name = name;
        this.manufactureDate = manufactureDate;
        this.manufacturer = manufacturer;
        this.countryOfOrigin = countryOfOrigin;
        this.price = price;
        this.isReserved = isReserved;
    }

    public void printInfo() {
        System.out.println("Название: " + name);
        System.out.println("Дата производства: " + manufactureDate);
        System.out.println("Производитель: " + manufacturer);
        System.out.println("Страна происхождения: " + countryOfOrigin);
        System.out.println("Цена: " + price + " руб.");
        System.out.println("Забронировано: " + (isReserved ? "Да" : "Нет"));
    }

    // Пример использования
    public static void main(String[] args) {
        Product product = new Product("Smartphone", "05.04.2025", "Samsung", "China", 20000, false);
        product.printInfo();
    }
}
