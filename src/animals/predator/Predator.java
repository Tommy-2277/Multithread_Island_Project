package animals.predator;

import animals.animal.Animal;
import animals.herbivore.*;
import island.Island;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Predator extends Animal {
    Random random = new Random();

    public Predator(int x, int y, String name) throws IOException {
        super(x, y, name);
    }

    public void eat() throws IOException {
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
                case "Bear" -> {
                    for (Animal a : getIsland()[coordinates[0]][coordinates[1]].getAnimals()) {
                        if (a instanceof Bear) {
                            counter++;
                        }
                    }
                    if (counter < Integer.parseInt(this.getProperties().getProperty("maximumAtZone"))) {
                        getIsland()[coordinates[0]][coordinates[1]].addAnimal(new Bear(coordinates[0], coordinates[1], "Bear"));
                    }
                }
                case "Eagle" -> {
                    for (Animal a : getIsland()[coordinates[0]][coordinates[1]].getAnimals()) {
                        if (a instanceof Eagle) {
                            counter++;
                        }
                    }
                    if (counter < Integer.parseInt(this.getProperties().getProperty("maximumAtZone"))) {
                        getIsland()[coordinates[0]][coordinates[1]].addAnimal(new Eagle(coordinates[0], coordinates[1], "Eagle"));
                    }
                }
                case "Fox" -> {
                    for (Animal a : getIsland()[coordinates[0]][coordinates[1]].getAnimals()) {
                        if (a instanceof Fox) {
                            counter++;
                        }
                    }
                    if (counter < Integer.parseInt(this.getProperties().getProperty("maximumAtZone"))) {
                        getIsland()[coordinates[0]][coordinates[1]].addAnimal(new Fox(coordinates[0], coordinates[1], "Fox"));
                    }
                }
                case "Snake" -> {
                    for (Animal a : getIsland()[coordinates[0]][coordinates[1]].getAnimals()) {
                        if (a instanceof Snake) {
                            counter++;
                        }
                    }
                    if (counter < Integer.parseInt(this.getProperties().getProperty("maximumAtZone"))) {
                        getIsland()[coordinates[0]][coordinates[1]].addAnimal(new Snake(coordinates[0], coordinates[1], "Snake"));
                    }
                }
                case "Wolf" -> {
                    for (Animal a : getIsland()[coordinates[0]][coordinates[1]].getAnimals()) {
                        if (a instanceof Wolf) {
                            counter++;
                        }
                    }
                    if (counter < Integer.parseInt(this.getProperties().getProperty("maximumAtZone"))) {
                        getIsland()[coordinates[0]][coordinates[1]].addAnimal(new Wolf(coordinates[0], coordinates[1], "Wolf"));
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

    public void eatSnake(CopyOnWriteArrayList<Animal> animalsInZone, int[] zoneCoordinates) {
        int counter = 0;
        for (Animal a : animalsInZone) {
            if (a instanceof Snake) {
                counter++;
                if (random.nextInt(1, 101) <= Integer.parseInt(this.getProperties().getProperty("chanceEatSnake"))) {
                    System.out.println(this.getName() + "[id" + this.getId() + "] has eat Snake[id" + a.getId() + "].");
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

    public void eatFox(CopyOnWriteArrayList<Animal> animalsInZone, int[] zoneCoordinates) {
        int counter = 0;
        for (Animal a : animalsInZone) {
            if (a instanceof Fox) {
                counter++;
                if (random.nextInt(1, 101) <= Integer.parseInt(this.getProperties().getProperty("chanceEatFox"))) {
                    System.out.println(this.getName() + "[id" + this.getId() + "] has eat Fox[id" + a.getId() + "].");
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

    public void eatHorse(CopyOnWriteArrayList<Animal> animalsInZone, int[] zoneCoordinates) {
        int counter = 0;
        for (Animal a : animalsInZone) {
            if (a instanceof Horse) {
                counter++;
                if (random.nextInt(1, 101) <= Integer.parseInt(this.getProperties().getProperty("chanceEatHorse"))) {
                    System.out.println(this.getName() + "[id" + this.getId() + "] has eat Horse[id" + a.getId() + "].");
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

    public void eatDeer(CopyOnWriteArrayList<Animal> animalsInZone, int[] zoneCoordinates) {
        int counter = 0;
        for (Animal a : animalsInZone) {
            if (a instanceof Deer) {
                counter++;
                if (random.nextInt(1, 101) <= Integer.parseInt(this.getProperties().getProperty("chanceEatDeer"))) {
                    System.out.println(this.getName() + "[id" + this.getId() + "] has eat Deer[id" + a.getId() + "].");
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

    public void eatRabbit(CopyOnWriteArrayList<Animal> animalsInZone, int[] zoneCoordinates) {
        int counter = 0;
        for (Animal a : animalsInZone) {
            if (a instanceof Rabbit) {
                counter++;
                if (random.nextInt(1, 101) <= Integer.parseInt(this.getProperties().getProperty("chanceEatRabbit"))) {
                    System.out.println(this.getName() + "[id" + this.getId() + "] has eat Rabbit[id" + a.getId() + "].");

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

    public void eatGoat(CopyOnWriteArrayList<Animal> animalsInZone, int[] zoneCoordinates) {
        int counter = 0;
        for (Animal a : animalsInZone) {
            if (a instanceof Goat) {
                counter++;
                if (random.nextInt(1, 101) <= Integer.parseInt(this.getProperties().getProperty("chanceEatGoat"))) {
                    System.out.println(this.getName() + "[id" + this.getId() + "] has eat Goat[id" + a.getId() + "].");

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

    public void eatSheep(CopyOnWriteArrayList<Animal> animalsInZone, int[] zoneCoordinates) {
        int counter = 0;
        for (Animal a : animalsInZone) {
            if (a instanceof Sheep) {
                counter++;
                if (random.nextInt(1, 101) <= Integer.parseInt(this.getProperties().getProperty("chanceEatSheep"))) {
                    System.out.println(this.getName() + "[id" + this.getId() + "] has eat Sheep[id" + a.getId() + "].");

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

    public void eatBoar(CopyOnWriteArrayList<Animal> animalsInZone, int[] zoneCoordinates) {
        int counter = 0;
        for (Animal a : animalsInZone) {
            if (a instanceof Boar) {
                counter++;
                if (random.nextInt(1, 101) <= Integer.parseInt(this.getProperties().getProperty("chanceEatBoar"))) {
                    System.out.println(this.getName() + "[id" + this.getId() + "] has eat Boar[id" + a.getId() + "].");

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

    public void eatBuffalo(CopyOnWriteArrayList<Animal> animalsInZone, int[] zoneCoordinates) {
        int counter = 0;
        for (Animal a : animalsInZone) {
            if (a instanceof Buffalo) {
                counter++;
                if (random.nextInt(1, 101) <= Integer.parseInt(this.getProperties().getProperty("chanceEatBuffalo"))) {
                    System.out.println(this.getName() + "[id" + this.getId() + "] has eat Buffalo[id" + a.getId() + "].");

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

    public void eatDuck(CopyOnWriteArrayList<Animal> animalsInZone, int[] zoneCoordinates) {
        int counter = 0;
        for (Animal a : animalsInZone) {
            if (a instanceof Duck) {
                counter++;
                if (random.nextInt(101) <= Integer.parseInt(this.getProperties().getProperty("chanceEatDuck"))) {
                    System.out.println(this.getName() + "[id" + this.getId() + "] has eat Duck[id" + a.getId() + "].");
                    this.setHungryLevel(this.getHungryLevel() + Double.parseDouble(a.getProperties().getProperty("weight")));
                    if (this.getHungryLevel() > Double.parseDouble(this.getProperties().getProperty("satiety"))) {
                        this.setHungryLevel(Double.parseDouble(this.getProperties().getProperty("satiety")));
                    }
                    getIsland()[zoneCoordinates[0]][zoneCoordinates[1]].removeAnimal(a);
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


}
