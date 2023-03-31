package animals.plant;

import animals.animal.Animal;
import animals.animal.IdGenerator;
import animals.herbivore.Horse;
import island.Island;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;


public class Plant implements Runnable {

    Random random = new Random();
    private int[] coordinates;
    private String name;
    private int id;
    private final Properties properties = new Properties();

    public Plant(int x, int y, String name) throws IOException {
        coordinates = new int[]{x, y};
        this.name = name;
        id = IdGenerator.getNextId();
        properties.load(new FileInputStream("src\\config\\plant.properties"));
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public Properties getProperties() {
        return properties;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            this.generation();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void generation() throws IOException {
        if (random.nextInt(1, 101) <= 70) {
            System.out.println("New " + this.getName() + " was raised.");
            int[] coordinates = this.getCoordinates();
            if (Island.getIsland()[coordinates[0]][coordinates[1]].getPlants().size() < Integer.parseInt(this.getProperties().getProperty("maximumAtZone"))) {
                Island.getIsland()[coordinates[0]][coordinates[1]].addPlant(new Plant(coordinates[0], coordinates[1], "Plant"));
            }
        }
    }
}
