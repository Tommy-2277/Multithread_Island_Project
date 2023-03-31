package animals.herbivore;

import animals.animal.Animal;
import animals.plant.Plant;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;

public class Mouse extends Herbivore {
    private static Properties properties = new Properties();

    public Mouse(int x, int y, String name) throws IOException {
        super(x, y, name);
    }

    {
        try {
            properties.load(new FileInputStream("src\\config\\mouse.properties"));
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
                CopyOnWriteArrayList<Animal> listAnimalsInZone = getIsland()[zoneCoordinates[0]][zoneCoordinates[1]].getAnimals();
                while (this.getHungryLevel() < Double.parseDouble(getProperties().getProperty("satiety")) && !this.didNotFindFood) {
                    int chooseAnimalToEat = random.nextInt(1, 3);
                    switch (chooseAnimalToEat) {
                        case 1 -> {
                            eatCaterpillar(listAnimalsInZone, zoneCoordinates);
                        }
                        case 2 -> {
                            eatPlant(listPlantsInZone, zoneCoordinates);
                        }
                    }
                }
            }
        }
        this.setHungryLevel(this.getHungryLevel() - 0.0015);
    }
}

