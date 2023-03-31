package animals.animal;

import island.Island;
import island.Zone;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


public abstract class Animal implements Runnable{
    private int id;
    private String name;
    private int[] coordinates;
    private double hungryLevel;
    private volatile static Zone[][] island;
    private final Properties properties = new Properties();
    public boolean hasMoved = false;
    public boolean didNotFindFood = false;

    public Animal(int x, int y, String name) throws IOException {
        id = IdGenerator.getNextId();
        coordinates = new int[] {x, y};
        this.name = name;
        properties.load(new FileInputStream("src\\config\\"+name + ".properties"));
        hungryLevel = Double.parseDouble(properties.getProperty("satiety"));
        island = Island.getIsland();
    }

    public Properties getProperties() {
        return properties;
    }

    public static Zone[][] getIsland() {
        return island;
    }

    public int getId() {
        return id;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public synchronized void setCoordinates(int[] coordinates) {
        this.coordinates = coordinates;
    }

    public String getName() {
        return name;
    }

    public double getHungryLevel() {
        return hungryLevel;
    }

    public void setHungryLevel(double hungryLevel) {
        this.hungryLevel = hungryLevel;
    }

    public void move() throws IOException {
    }
    public void eat() throws IOException {}
    public void generation() throws IOException {}

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
