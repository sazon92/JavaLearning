interface HasWheels {
    int getWheelCount();
}
interface HasRotor {
    int getRotorCount();
}
interface HasWings {
    double getWingspan();
}

interface CarryCargo {
    void loadCargo();
}

abstract class Vehicle {
    protected String model;

    public Vehicle(String model) {
        this.model = model;
    }

    abstract void move();
}

class Airplane extends Vehicle implements HasWheels,HasRotor,HasWings,CarryCargo  {

    public Airplane(String model) {
        super(model);
    }

    @Override
    public void loadCargo() {
        System.out.println("Загрузка груза на самолёт");
    }

    @Override
    public int getRotorCount() {
        return 1;
    }

    @Override
    public int getWheelCount() {
        return 3;
    }

    @Override
    public double getWingspan() {
        return 30;
    }

    @Override
    void move() {
        System.out.println("Летит");
    }
}
class Helicopter extends Vehicle implements HasWheels, HasRotor, CarryCargo {
    public Helicopter(String model) {
        super(model);
    }

    @Override
    void move() {
        System.out.println("Летит");
    }

    @Override
    public void loadCargo() {
        System.out.println("Загрузка груза на вертолёт");
    }

    @Override
    public int getRotorCount() {
        return 2;
    }

    @Override
    public int getWheelCount() {
        return 3;
    }
}
class Boat extends Vehicle implements HasRotor,CarryCargo  {
    public Boat(String model) {
        super(model);
    }

    @Override
    void move() {
        System.out.println("Идёт");
    }

    @Override
    public void loadCargo() {
        System.out.println("Загрузка груза на катер");
    }

    @Override
    public int getRotorCount() {
        return 6;
    }
}
class Tanker extends Vehicle implements HasRotor,CarryCargo  {
    public Tanker(String model) {
        super(model);
    }

    @Override
    void move() {
        System.out.println("Идёт");
    }

    @Override
    public void loadCargo() {
        System.out.println("Загрузка груза на танкер");
    }

    @Override
    public int getRotorCount() {
        return 2;
    }
}
class Truck extends Vehicle implements HasWheels,CarryCargo  {
    public Truck(String model) {
        super(model);
    }

    @Override
    void move() {
        System.out.println("Едет");
    }

    @Override
    public void loadCargo() {
        System.out.println("Загрузка груза на грузовик");
    }

    @Override
    public int getWheelCount() {
        return 6;
    }
}
class Taxi extends Vehicle implements HasWheels  {
    public Taxi(String model) {
        super(model);
    }

    @Override
    void move() {
        System.out.println("Едет");
    }

    @Override
    public int getWheelCount() {
        return 4;
    }
}
