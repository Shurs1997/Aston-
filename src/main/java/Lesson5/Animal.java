package Lesson5;

public class Animal {
    static int countAnimal = 0;
    Animal(){
        countAnimal++;
    }


    void run (int range) {
        System.out.println("Животные пробежали " + range + " m");
    }

    void swim (int range){
        System.out.println("животные проплыли " + range + " m");
    }

    public static int getCountAnimal(){
        return countAnimal;
    }
}
