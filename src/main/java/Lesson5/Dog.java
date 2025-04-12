package Lesson5;

public class Dog extends Animal{
    String name;
    static int countDod = 0;

    Dog(String name){
        super();
        countDod++;
        this.name = name;
    }

    @Override
    void run(int range) {
        if (range > 500) {
            System.out.println(name+ " не может пробежать " + range + " m");
        } else {
            System.out.println(name+ " пробежал " + range + " m");
        }

    }

    @Override
    void swim(int range) {
        if (range > 10) {
            System.out.println(name+" не может проплыть " +range+ " m");
        } else {
            System.out.println(name + " проплыл " + range + " m");
        }
    }

    static int getCountDod (){
        return countDod;
    }




}
