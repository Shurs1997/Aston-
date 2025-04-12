package Lesson5;

public class Main {
    public static void main(String[] args) {

        Cat barsik = new Cat("Барсик");
        Dog sharik = new Dog("Шарик");
        Cat volodya = new Cat("Володя");
        Dog tuzik = new Dog("Тузик");
        barsik.run(150);
        volodya.run(201);
        barsik.swim(0);
        volodya.swim(0);

        sharik.run(500);
        tuzik.run(501);
        sharik.swim(10);
        tuzik.swim(12);
        System.out.println("Общее количество животных: " +Animal.getCountAnimal());
        System.out.println("Количество котов: "+Cat.getCountCat());
        System.out.println("Количество собак: "+Dog.getCountDod());



    }
}