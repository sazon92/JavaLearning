interface HasSpine { // имеет позвоночник
    int getVertebraeCount(); // количество позвонков
    default void showSpineInfo() {
        System.out.println("Это животное имеет позвоночник");
    }
}

interface HasFur { // есть шерсть
    String getFurColor(); // цвет шерсти
    double getFurLength(); // длина шерсти в см
    default void showFurInfo() {
        System.out.println("у животного есть шерсть");
    }
}

interface LiveInWater {
    int getWaterDepth(); // глубина обитания в метрах
    boolean canLiveInFreshWater(); // может ли жить в пресной воде
    default void showWaterInfo() {
        System.out.println("Это животное плавает");
    }
}

abstract class Animal{
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract void makeSound();

    public void eat() {
        System.out.println(name + " ест");
    }
}

abstract class Mammal extends Animal implements HasSpine {

    public Mammal(String name, int age) {
        super(name, age);
    }
}

class Cat extends Mammal implements HasFur {

    public Cat(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println("мяу");
    }

    @Override
    public String getFurColor() {
        return "Черный";
    }

    @Override
    public double getFurLength() {
        return 3;
    }

    @Override
    public int getVertebraeCount() {
        return 52;
    }
}
class Bear extends Mammal implements HasFur {
    public Bear(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println("ррр");
    }

    @Override
    public String getFurColor() {
        return "Бурый";
    }

    @Override
    public double getFurLength() {
        return 4;
    }

    @Override
    public int getVertebraeCount() {
        return 56;
    }
}
class Whale extends Mammal implements LiveInWater {
    public Whale(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println("Муу");
    }

    @Override
    public int getVertebraeCount() {
        return 98;
    }

    @Override
    public int getWaterDepth() {
        return 1000;
    }

    @Override
    public boolean canLiveInFreshWater() {
        return false;
    }
}
class Fish extends Animal implements LiveInWater {
    public Fish(String name, int age) {
        super(name, age);
    }

    @Override
    public void makeSound() {
        System.out.println("буль");
    }

    @Override
    public int getWaterDepth() {
        return 15;
    }

    @Override
    public boolean canLiveInFreshWater() {
        return true;
    }
}