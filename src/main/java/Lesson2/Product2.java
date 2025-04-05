package Lesson2;

public class Product2 {
    private String name;
    private String manufactureDate;
    private String manufacturer;
    private String countryOfOrigin;
    private double price;
    private boolean isReserved;


    public Product2(String name, String manufactureDate, String manufacturer,
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
        System.out.println("----------------------");
    }

    public static void main(String[] args) {

        Product[] productArray = new Product[5];


        productArray[0] = new Product("Samsung S25Ultra", "01.02.2025", "Samsung Corporation", "Korea", 55999.99, true);
        productArray[1] = new Product("Xiaomi MiBand 8", "15.01.2025", "Xiaomi", "China", 2999.99, false);
        productArray[2] = new Product("Sony PlayStation 5", "20.03.2024", "Sony", "Japan", 49999.99, true);
        productArray[3] = new Product("HP Pavilion Laptop", "10.12.2023", "HP", "USA", 69999.00, false);
        productArray[4] = new Product("Apple iPhone 15", "05.10.2024", "Apple Inc.", "USA", 109999.00, true);


        for (Product product : productArray) {
            product.printInfo();
        }
    }
}
