package Lesson5cat;

public class Cat_bowl {
    public static void main(String[] args) {
        Cat[] cats = {
                new Cat("Барсик", 5),
                new Cat("Мурзик", 3),
                new Cat("Леопольд", 7),
                new Cat("Пушок", 4)
        };

        Bowl bowl = new Bowl(15);

        System.out.println("Коты пытаются поесть:");
        for (Cat cat : cats) {
            cat.eat(bowl);
        }

        System.out.println("\nСостояние котов:");
        for (Cat cat : cats) {
            cat.info();
        }

        bowl.info();

        System.out.println("\nДобавим еды и попробуем снова:");
        bowl.addFood(10);
        for (Cat cat : cats) {
            if (!cat.isSatiety()) {
                cat.eat(bowl);
            }
        }

        System.out.println("\nФинальное состояние:");
        for (Cat cat : cats) {
            cat.info();
        }
        bowl.info();
    }
}

class Cat {
    private String name;
    private int appetite;
    private boolean satiety;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }

    public void eat(Bowl bowl) {
        if (bowl.getFood() >= appetite) {
            bowl.decreaseFood(appetite);
            satiety = true;
            System.out.println(name + " поел и теперь сыт.");
        } else {
            System.out.println(name + " не поел. В миске недостаточно еды.");
        }
    }

    public void info() {
        System.out.println(name + " — " + (satiety ? "сыт" : "голоден"));
    }

    public boolean isSatiety() {
        return satiety;
    }
}

class Bowl {
    private int food;

    public Bowl(int food) {
        this.food = food;
    }

    public int getFood() {
        return food;
    }

    public void decreaseFood(int amount) {
        if (food >= amount) {
            food -= amount;
        }
    }

    public void addFood(int amount) {
        food += amount;
        System.out.println("В миску добавлено " + amount + " еды. Теперь в миске " + food + " еды.");
    }

    public void info() {
        System.out.println("В миске осталось " + food + " еды.");
    }
}
