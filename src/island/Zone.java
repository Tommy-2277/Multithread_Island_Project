package island;

import animals.animal.Animal;
import animals.herbivore.*;
import animals.plant.Plant;
import animals.predator.*;
import animals.secretAnimal.Dragon;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Zone {
    private volatile CopyOnWriteArrayList<Animal> animals;
    private CopyOnWriteArrayList<Plant> plants;
    private Random random = new Random();
    private volatile static Zone[][] island;
    public Zone(Zone[][] island) {
        this.animals = new CopyOnWriteArrayList<>();
        this.plants = new CopyOnWriteArrayList<>();
        this.island = island;
    }

    public synchronized void spawnFirstAnimals(String animal, int x, int y) throws IOException {
        int count;
        switch (animal) {
            case "Wolf" -> {
                count = random.nextInt(1, 11);
                for (int i = 0; i < count; i++) {
                    this.animals.add(new Wolf(x, y, "Wolf"));
                }
            }
            case "Snake" -> {
                count = random.nextInt(1, 11);
                for (int i = 0; i < count; i++) {
                    this.animals.add(new Snake(x, y, "Snake"));
                }
            }
            case "Fox" -> {
                count = random.nextInt(1, 11);
                for (int i = 0; i < count; i++) {
                    this.animals.add(new Fox(x, y, "Fox"));
                }
            }
            case "Bear" -> {
                count = random.nextInt(1, 3);
                for (int i = 0; i < count; i++) {
                    this.animals.add(new Bear(x, y, "Bear"));
                }
            }
            case "Eagle" -> {
                count = random.nextInt(1, 8);
                for (int i = 0; i < count; i++) {
                    this.animals.add(new Eagle(x, y, "Eagle"));
                }
            }
            case "Horse" -> {
                count = random.nextInt(1, 8);
                for (int i = 0; i < count; i++) {
                    this.animals.add(new Horse(x, y, "Horse"));
                }
            }
            case "Deer" -> {
                count = random.nextInt(1, 8);
                for (int i = 0; i < count; i++) {
                    this.animals.add(new Deer(x, y, "Deer"));
                }
            }
            case "Rabbit" -> {
                count = random.nextInt(1, 50);
                for (int i = 0; i < count; i++) {
                    this.animals.add(new Rabbit(x, y, "Rabbit"));
                }
            }
            case "Mouse" -> {
                count = random.nextInt(1, 170);
                for (int i = 0; i < count; i++) {
                    this.animals.add(new Mouse(x, y, "Mouse"));
                }
            }
            case "Goat" -> {
                count = random.nextInt(1, 50);
                for (int i = 0; i < count; i++) {
                    this.animals.add(new Goat(x, y, "Goat"));
                }
            }
            case "Sheep" -> {
                count = random.nextInt(1, 50);
                for (int i = 0; i < count; i++) {
                    this.animals.add(new Sheep(x, y, "Sheep"));
                }
            }
            case "Boar" -> {
                count = random.nextInt(1, 17);
                for (int i = 0; i < count; i++) {
                    this.animals.add(new Boar(x, y, "Boar"));
                }
            }
            case "Buffalo" -> {
                count = random.nextInt(1, 3);
                for (int i = 0; i < count; i++) {
                    this.animals.add(new Buffalo(x, y, "Buffalo"));
                }
            }
            case "Duck" -> {
                count = random.nextInt(1, 66);
                for (int i = 0; i < count; i++) {
                    this.animals.add(new Duck(x, y, "Duck"));
                }
            }
            case "Caterpillar" -> {
                count = random.nextInt(1, 333);
                for (int i = 0; i < count; i++) {
                    this.animals.add(new Caterpillar(x, y, "Caterpillar"));
                }
            }
            case "Plant" -> {
                count = random.nextInt(1, 333);
                for (int i = 0; i < count; i++) {
                    this.plants.add(new Plant(x, y, "Plant"));
                }
            }
            case "Dragon" -> {
                    this.animals.add(new Dragon(x, y, "Dragon"));
            }

        }
    }
    public synchronized void addAnimal(Animal animal) {
        this.animals.add(animal);
    }
    public synchronized void removeAnimal(Animal animal) {
        this.animals.remove(animal);
    }
    public synchronized void addPlant(Plant plant) {
        this.plants.add(plant);
    }
    public synchronized void removePlant(Plant plant) {
        this.plants.remove(plant);
    }
    public CopyOnWriteArrayList<Animal> getAnimals() {
        return animals;
    }
    public CopyOnWriteArrayList<Plant> getPlants() {
        return plants;
    }
    public Zone[][] getIsland() {
        return island;
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
