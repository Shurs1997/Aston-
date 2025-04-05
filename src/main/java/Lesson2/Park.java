package Lesson2;

public class Park {

    public class Attraction {
        private String name;
        private String workingHours;
        private double price;

        public Attraction(String name, String workingHours, double price) {
            this.name = name;
            this.workingHours = workingHours;
            this.price = price;
        }

        public void printAttractionInfo() {
            System.out.println("Аттракцион: " + name);
            System.out.println("Время работы: " + workingHours);
            System.out.println("Стоимость: " + price + " руб.");
        }
    }

    public static void main(String[] args) {
        Park park = new Park();

        Park.Attraction rollerCoaster = park.new Attraction("Американские горки", "10:00 - 22:00", 500);
        Park.Attraction ferrisWheel = park.new Attraction("Колесо обозрения", "09:00 - 21:00", 300);
        Park.Attraction hauntedHouse = park.new Attraction("Дом с привидениями", "12:00 - 20:00", 400);

        rollerCoaster.printAttractionInfo();
        ferrisWheel.printAttractionInfo();
        hauntedHouse.printAttractionInfo();
    }
}