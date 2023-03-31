package island;

import animals.animal.Animal;
import animals.plant.Plant;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Island {
    private volatile static Zone[][] island;
    private Random random = new Random();
    private static int aliveAnimalsOnTheIsland;

    public Island(int width, int height) {
        island = new Zone[width][height];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                this.island[i][j] = new Zone(island);
            }
        }
    }

    public static int getAliveAnimalsOnTheIsland() {
        return aliveAnimalsOnTheIsland;
    }

    public static void setAliveAnimalsOnTheIsland(int aliveAnimalsOnTheIsland) {
        Island.aliveAnimalsOnTheIsland = aliveAnimalsOnTheIsland;
    }

    public void putAnimalsInZones(Zone[][] island) throws IOException {
        for (int i = 0; i < island.length; i++) {
            for (int j = 0; j < island[i].length; j++) {
                Zone zone = island[i][j];
                zone.spawnFirstAnimals("Wolf", i, j);
                zone.spawnFirstAnimals("Snake", i, j);
                zone.spawnFirstAnimals("Fox", i, j);
                zone.spawnFirstAnimals("Bear", i, j);
                zone.spawnFirstAnimals("Eagle", i, j);
                zone.spawnFirstAnimals("Horse", i, j);
                zone.spawnFirstAnimals("Deer", i, j);
                zone.spawnFirstAnimals("Rabbit", i, j);
                zone.spawnFirstAnimals("Mouse", i, j);
                zone.spawnFirstAnimals("Goat", i, j);
                zone.spawnFirstAnimals("Sheep", i, j);
                zone.spawnFirstAnimals("Boar", i, j);
                zone.spawnFirstAnimals("Buffalo", i, j);
                zone.spawnFirstAnimals("Duck", i, j);
                zone.spawnFirstAnimals("Caterpillar", i, j);
                zone.spawnFirstAnimals("Plant", i, j);
                zone.spawnFirstAnimals("Dragon", i, j);
            }
        }
        int counter = 0;
        for (Zone[] zones : island) {
            for (Zone zone : zones) {
                counter += zone.getAnimals().size();
            }
        }
        setAliveAnimalsOnTheIsland(counter);
    }

    public void startAnimalsAndPlantsThreads() throws InterruptedException {
        int counter = 1;
        ExecutorService executorService = Executors.newCachedThreadPool();
        while (getAliveAnimalsOnTheIsland() > 0) {
            System.out.println("==========================================================================\n" + counter + "move:");
            for (Zone[] zones : island) {
                for (Zone zone : zones) {
                    for (Animal a : zone.getAnimals()) {
                        executorService.submit(a);
                    }
                    for (Plant p : zone.getPlants()) {
                        executorService.submit(p);
                    }
                }
            }
            executorService.awaitTermination(3, TimeUnit.SECONDS);
            counter++;
            countAnimalsAndPlants();
        }
        executorService.shutdown();
        System.out.println("\nTHANKS FOR PLAYING!");
    }

    public void countAnimalsAndPlants() throws InterruptedException {
        Thread.sleep(1000);
        int counterForExit = 0;
        int counter = 0;
        int wolfCounter = 0;
        int snakeCounter = 0;
        int foxCounter = 0;
        int bearCounter = 0;
        int eagleCounter = 0;
        int duckCounter = 0;
        int horseCounter = 0;
        int deerCounter = 0;
        int rabbitCounter = 0;
        int mouseCounter = 0;
        int goatCounter = 0;
        int sheepCounter = 0;
        int boarCounter = 0;
        int buffaloCounter = 0;
        int caterpillarCounter = 0;
        int plantCounter = 0;
        int dragonCounter = 0;

        for (Zone[] zones : island) {
            for (Zone zone : zones) {
                plantCounter += zone.getPlants().size();
            }
        }
        for (Zone[] zones : island) {
            for (Zone zone : zones) {
                counter += zone.getAnimals().size();
                for (Animal a : zone.getAnimals()) {
                    switch (a.getName()) {
                        case "Wolf" -> {
                            wolfCounter++;
                            counterForExit++;
                        }
                        case "Snake" -> {
                            snakeCounter++;
                            counterForExit++;
                        }
                        case "Fox" -> {
                            foxCounter++;
                            counterForExit++;
                        }
                        case "Bear" -> {
                            bearCounter++;
                            counterForExit++;
                        }
                        case "Eagle" -> {
                            eagleCounter++;
                            counterForExit++;
                        }
                        case "Duck" -> {
                            duckCounter++;
                            counterForExit++;
                        }
                        case "Horse" -> {
                            horseCounter++;
                            counterForExit++;
                        }
                        case "Deer" -> {
                            deerCounter++;
                            counterForExit++;
                        }
                        case "Rabbit" -> {
                            rabbitCounter++;
                            counterForExit++;
                        }
                        case "Mouse" -> {
                            mouseCounter++;
                            counterForExit++;
                        }
                        case "Goat" -> {
                            goatCounter++;
                            counterForExit++;
                        }
                        case "Sheep" -> {
                            sheepCounter++;
                            counterForExit++;
                        }
                        case "Boar" -> {
                            boarCounter++;
                            counterForExit++;
                        }
                        case "Buffalo" -> {
                            buffaloCounter++;
                            counterForExit++;
                        }
                        case "Caterpillar" -> caterpillarCounter++;
                        case "Dragon" -> dragonCounter++;
                    }
                }
            }
        }
        System.out.println("\nIsland has " + counter + " animals and " + plantCounter + " plants."
                + "\nWolves on island - " + wolfCounter
                + "\nSnakes on island - " + snakeCounter
                + "\nFoxes on island - " + foxCounter
                + "\nBears on island - " + bearCounter
                + "\nEagles on island - " + eagleCounter
                + "\nDucks on island - " + duckCounter
                + "\nHorses on island - " + horseCounter
                + "\nDeer on island - " + deerCounter
                + "\nRabbits on island - " + rabbitCounter
                + "\nMouse on island - " + mouseCounter
                + "\nGoats on island - " + goatCounter
                + "\nSheep on island - " + sheepCounter
                + "\nBoars on island - " + boarCounter
                + "\nBuffalo on island - " + buffaloCounter
                + "\nCaterpillar on island - " + caterpillarCounter
                + "\nSecret animals on island - " + dragonCounter);

        setAliveAnimalsOnTheIsland(counterForExit);
    }

    public static Zone[][] getIsland() {
        return island;
    }


}
