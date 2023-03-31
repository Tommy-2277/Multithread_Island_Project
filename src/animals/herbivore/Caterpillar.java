package animals.herbivore;

import java.io.IOException;


public class Caterpillar extends Herbivore {
    public Caterpillar(int x, int y, String name) throws IOException {
        super(x, y, name);
    }

    @Override
    public void run() {
        this.hasMoved = false;
        this.didNotFindFood = false;
        try {
            Thread.sleep(2000);
            this.generation();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

