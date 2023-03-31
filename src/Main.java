import View.View;
import island.Island;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {
        View view = new View();
        int[] islandSize = view.inputIslandSize();
        Island island = new Island(islandSize[0], islandSize[1]);
        island.putAnimalsInZones(Island.getIsland());
        island.startAnimalsAndPlantsThreads();
    }
}