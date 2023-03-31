package animals.secretAnimal;

import animals.animal.Animal;
import island.Island;

import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Dragon extends Animal {
    Random random = new Random();

    public Dragon(int x, int y, String name) throws IOException {
        super(x, y, name);
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int[] zoneCoordinates = new int[2];
        if(random.nextInt(1, 201) == 111) {
        int x = Island.getIsland().length;
        int y = Island.getIsland()[0].length;
        zoneCoordinates[0] = random.nextInt(x);
        zoneCoordinates[1] = random.nextInt(y);
        System.out.println("Dragon has destroyed all life at zone " + Arrays.toString(zoneCoordinates));
        Island.getIsland()[zoneCoordinates[0]][zoneCoordinates[1]].getAnimals().clear();
        Island.getIsland()[zoneCoordinates[0]][zoneCoordinates[1]].getPlants().clear();
        }
    }
}
