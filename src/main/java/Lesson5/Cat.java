package Lesson5;

public class Cat extends Animal  {

    String name;
    static int countCat = 0;

    Cat (String name){
        super();
        countCat++;
        this.name = name;
    }

    @Override
    void run(int range) {
        if (range > 200) {
            System.out.println(name+ " не может пробежать " + range + " m");
        } else {
            System.out.println(name + " пробежал " + range + " m");
        }
    }

    @Override
    void swim(int range) {
        System.out.println(name+ " не умеет плавать");
    }

    static int getCountCat(){
        return countCat;
    }

}