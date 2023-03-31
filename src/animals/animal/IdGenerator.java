package animals.animal;

public class IdGenerator {
    private static int id = 1;
    public static int getNextId() {
        return id++;
    }
}
