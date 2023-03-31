package animals.herbivore;

import animals.plant.Plant;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

public class Rabbit extends Herbivore {
    private static Properties properties = new Properties();

    public Rabbit(int x, int y, String name) throws IOException {
        super(x, y, name);
    }

    {
        try {
            properties.load(new FileInputStream("src\\config\\rabbit.properties"));
            this.setHungryLevel(Double.parseDouble(properties.getProperty("satiety")) / 2);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void run() {
        this.hasMoved = false;
        this.didNotFindFood = false;
        if (this.getHungryLevel() <= 0) {
            hungryDeath();
        } else {
            try {
                this.eat();
                Thread.sleep(1000);
                this.move();
                Thread.sleep(1000);
                this.generation();
                Thread.sleep(1000);
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void eat() throws IOException {
        int[] zoneCoordinates = this.getCoordinates();

        synchronized (getIsland()[zoneCoordinates[0]][zoneCoordinates[1]].getAnimals()) {
            if (this.getHungryLevel() < Double.parseDouble(getProperties().getProperty("satiety")) / 2) {
                CopyOnWriteArrayList<Plant> listPlantsInZone = getIsland()[zoneCoordinates[0]][zoneCoordinates[1]].getPlants();

                while (this.getHungryLevel() < Double.parseDouble(getProperties().getProperty("satiety")) && !this.didNotFindFood) {
                    eatPlant(listPlantsInZone, zoneCoordinates);
                }
            }
        }
        this.setHungryLevel(this.getHungryLevel() - 0.06);
    }
}

