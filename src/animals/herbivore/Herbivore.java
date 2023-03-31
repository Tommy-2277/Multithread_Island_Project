package animals.herbivore;

import animals.animal.Animal;
import animals.plant.Plant;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Herbivore extends Animal {
    Random random = new Random();

    public Herbivore(int x, int y, String name) throws IOException {
        super(x, y, name);
    }

    @Override
    public void run() {

    }

    public void move() throws IOException {
        if (getIsland().length != 1 && getIsland()[0].length != 1) {
            if (!this.hasMoved) {
                int horizontal = 0;
                int vertical = 0;
                int steps = random.nextInt(1, Integer.parseInt(getProperties().getProperty("speed")) + 1);
                while (steps > 0) {
                    switch (random.nextInt(1, 5)) {
                        case 1 -> {
                            horizontal -= 1;
                            steps--;
                        }
                        case 2 -> {
                            vertical += 1;
                            steps--;
                        }
                        case 3 -> {
                            horizontal += 1;
                            steps--;
                        }
                        case 4 -> {
                            vertical -= 1;
                            steps--;
                        }
                    }
                }

                int[] coordinates = this.getCoordinates();
                int yCoordinate = vertical + coordinates[0];
                int xCoordinate = horizontal + coordinates[1];

                if (yCoordinate < 0) {
                    yCoordinate = 0;
                }
                if (yCoordinate >= getIsland()[0].length) {
                    yCoordinate = getIsland()[0].length - 1;
                }
                if (xCoordinate < 0) {
                    xCoordinate = 0;
                }
                if (xCoordinate >= getIsland().length) {
                    xCoordinate = getIsland().length - 1;
                }


                if (coordinates[0] == yCoordinate && coordinates[1] == xCoordinate) {
                    System.out.println(this.getName() + "[id" + this.getId() + "] has moved from " + Arrays.toString(coordinates) + " to " + "[" + yCoordinate + ", " + xCoordinate + "].");
                } else {
                    System.out.println(this.getName() + "[id" + this.getId() + "] has moved from " + Arrays.toString(coordinates) + " to " + "[" + yCoordinate + ", " + xCoordinate + "].");
                    getIsland()[coordinates[0] + yCoordinate][coordinates[1] + xCoordinate].addAnimal(this);
                    getIsland()[coordinates[0]][coordinates[1]].removeAnimal(this);
                    coordinates[0] = coordinates[0] + yCoordinate;
                    coordinates[1] = coordinates[1] + xCoordinate;
                    this.setCoordinates(coordinates);
                }
                this.hasMoved = true;

            }
        }
    }

    @Override
    public void generation() throws IOException {
        if (random.nextInt(1, 101) <= 10) {
            System.out.println("New " + this.getName() + " was born.");
            int[] coordinates = this.getCoordinates();
            int counter = 0;
            switch (this.getName()) {
                case "Horse" -> {
                    for (Animal a : getIsland()[coordinates[0]][coordinates[1]].getAnimals()) {
                        if (a instanceof Horse) {
                            counter++;
                        }
                    }
                    if (counter < Integer.parseInt(this.getProperties().getProperty("maximumAtZone"))) {
                        getIsland()[coordinates[0]][coordinates[1]].addAnimal(new Horse(coordinates[0], coordinates[1], "Horse"));
                    }
                }
                case "Deer" -> {
                    for (Animal a : getIsland()[coordinates[0]][coordinates[1]].getAnimals()) {
                        if (a instanceof Deer) {
                            counter++;
                        }
                    }
                    if (counter < Integer.parseInt(this.getProperties().getProperty("maximumAtZone"))) {
                        getIsland()[coordinates[0]][coordinates[1]].addAnimal(new Deer(coordinates[0], coordinates[1], "Deer"));
                    }
                }
                case "Rabbit" -> {
                    for (Animal a : getIsland()[coordinates[0]][coordinates[1]].getAnimals()) {
                        if (a instanceof Rabbit) {
                            counter++;
                        }
                    }
                    if (counter < Integer.parseInt(this.getProperties().getProperty("maximumAtZone"))) {
                        getIsland()[coordinates[0]][coordinates[1]].addAnimal(new Rabbit(coordinates[0], coordinates[1], "Rabbit"));
                    }
                }
                case "Mouse" -> {
                    for (Animal a : getIsland()[coordinates[0]][coordinates[1]].getAnimals()) {
                        if (a instanceof Mouse) {
                            counter++;
                        }
                    }
                    if (counter < Integer.parseInt(this.getProperties().getProperty("maximumAtZone"))) {
                        getIsland()[coordinates[0]][coordinates[1]].addAnimal(new Mouse(coordinates[0], coordinates[1], "Mouse"));
                    }
                }
                case "Goat" -> {
                    for (Animal a : getIsland()[coordinates[0]][coordinates[1]].getAnimals()) {
                        if (a instanceof Goat) {
                            counter++;
                        }
                    }
                    if (counter < Integer.parseInt(this.getProperties().getProperty("maximumAtZone"))) {
                        getIsland()[coordinates[0]][coordinates[1]].addAnimal(new Goat(coordinates[0], coordinates[1], "Goat"));
                    }
                }
                case "Sheep" -> {
                    for (Animal a : getIsland()[coordinates[0]][coordinates[1]].getAnimals()) {
                        if (a instanceof Sheep) {
                            counter++;
                        }
                    }
                    if (counter < Integer.parseInt(this.getProperties().getProperty("maximumAtZone"))) {
                        getIsland()[coordinates[0]][coordinates[1]].addAnimal(new Sheep(coordinates[0], coordinates[1], "Sheep"));
                    }
                }
                case "Boar" -> {
                    for (Animal a : getIsland()[coordinates[0]][coordinates[1]].getAnimals()) {
                        if (a instanceof Boar) {
                            counter++;
                        }
                    }
                    if (counter < Integer.parseInt(this.getProperties().getProperty("maximumAtZone"))) {
                        getIsland()[coordinates[0]][coordinates[1]].addAnimal(new Boar(coordinates[0], coordinates[1], "Boar"));
                    }
                }
                case "Buffalo" -> {
                    for (Animal a : getIsland()[coordinates[0]][coordinates[1]].getAnimals()) {
                        if (a instanceof Buffalo) {
                            counter++;
                        }
                    }
                    if (counter < Integer.parseInt(this.getProperties().getProperty("maximumAtZone"))) {
                        getIsland()[coordinates[0]][coordinates[1]].addAnimal(new Buffalo(coordinates[0], coordinates[1], "Buffalo"));
                    }
                }
                case "Duck" -> {
                    for (Animal a : getIsland()[coordinates[0]][coordinates[1]].getAnimals()) {
                        if (a instanceof Duck) {
                            counter++;
                        }
                    }
                    if (counter < Integer.parseInt(this.getProperties().getProperty("maximumAtZone"))) {
                        getIsland()[coordinates[0]][coordinates[1]].addAnimal(new Duck(coordinates[0], coordinates[1], "Duck"));
                    }
                }
                case "Caterpillar" -> {
                    for (Animal a : getIsland()[coordinates[0]][coordinates[1]].getAnimals()) {
                        if (a instanceof Caterpillar) {
                            counter++;
                        }
                    }
                    if (counter < Integer.parseInt(this.getProperties().getProperty("maximumAtZone"))) {
                        getIsland()[coordinates[0]][coordinates[1]].addAnimal(new Caterpillar(coordinates[0], coordinates[1], "Caterpillar"));
                    }
                }
            }
        }
    }

    public void hungryDeath() {
        System.out.println(this.getName() + "[id" + this.getId() + "] died of hunger.");
        int[] tmp = this.getCoordinates();
        getIsland()[tmp[0]][tmp[1]].removeAnimal(this);
    }

    public void eatMouse(CopyOnWriteArrayList<Animal> animalsInZone, int[] zoneCoordinates) {
        int counter = 0;
        for (Animal a : animalsInZone) {
            if (a instanceof Mouse) {
                counter++;
                if (random.nextInt(1, 101) <= Integer.parseInt(this.getProperties().getProperty("chanceEatMouse"))) {
                    System.out.println(this.getName() + "[id" + this.getId() + "] has eat Mouse[id" + a.getId() + "].");
                    this.setHungryLevel(this.getHungryLevel() + Double.parseDouble(a.getProperties().getProperty("weight")));
                    if (this.getHungryLevel() > Double.parseDouble(this.getProperties().getProperty("satiety"))) {
                        this.setHungryLevel(Double.parseDouble(this.getProperties().getProperty("satiety")));
                    }
                    getIsland()[zoneCoordinates[0]][zoneCoordinates[1]].removeAnimal(a);
                    break;
                } else {
                    System.out.println(this.getName() + "[id" + this.getId() + "] has failed hunt.");
                }
                break;
            }
        }
        if (counter == 0) {
            System.out.println(this.getName() + "[id" + this.getId() + "] did not find desired food.");
            this.didNotFindFood = true;
        }
    }

    public void eatCaterpillar(CopyOnWriteArrayList<Animal> animalsInZone, int[] zoneCoordinates) {
        int counter = 0;
        for (Animal a : animalsInZone) {
            if (a instanceof Caterpillar) {
                counter++;
                if (random.nextInt(1, 101) <= Integer.parseInt(this.getProperties().getProperty("chanceEatCaterpillar"))) {
                    System.out.println(this.getName() + "[id" + this.getId() + "] has eat Caterpillar[id" + a.getId() + "].");
                    this.setHungryLevel(this.getHungryLevel() + Double.parseDouble(a.getProperties().getProperty("weight")));
                    if (this.getHungryLevel() > Double.parseDouble(this.getProperties().getProperty("satiety"))) {
                        this.setHungryLevel(Double.parseDouble(this.getProperties().getProperty("satiety")));
                    }
                    getIsland()[zoneCoordinates[0]][zoneCoordinates[1]].removeAnimal(a);
                    break;
                } else {
                    System.out.println(this.getName() + "[id" + this.getId() + "] has failed hunt.");
                }
                break;
            }
        }
        if (counter == 0) {
            System.out.println(this.getName() + "[id" + this.getId() + "] did not find desired food.");
            this.didNotFindFood = true;
        }
    }

    public void eatPlant(CopyOnWriteArrayList<Plant> plantsInZone, int[] zoneCoordinates) {
        int counter = 0;
        for (Plant p : plantsInZone) {
            counter++;
            if (random.nextInt(1, 101) <= Integer.parseInt(this.getProperties().getProperty("chanceEatPlant"))) {
                System.out.println(this.getName() + "[id" + this.getId() + "] has eat Plant[id" + p.getId() + "].");
                this.setHungryLevel(this.getHungryLevel() + Double.parseDouble(p.getProperties().getProperty("weight")));
                if (this.getHungryLevel() > Double.parseDouble(this.getProperties().getProperty("satiety"))) {
                    this.setHungryLevel(Double.parseDouble(this.getProperties().getProperty("satiety")));
                }
                getIsland()[zoneCoordinates[0]][zoneCoordinates[1]].removePlant(p);
                break;
            } else {
                System.out.println(this.getName() + "[id" + this.getId() + "] has failed hunt.");
            }
            break;
        }
        if (counter == 0) {
            System.out.println(this.getName() + "[id" + this.getId() + "] did not find desired food.");
            this.didNotFindFood = true;
        }
    }

}
