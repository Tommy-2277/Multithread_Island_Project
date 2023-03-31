package View;

import java.util.Arrays;
import java.util.Scanner;

public class View {
    Scanner scanner = new Scanner(System.in);
    public int[] inputIslandSize() {
        System.out.println("Введите размер острова двумя числами через пробел. Например: 10 10.");
        String stringIslandSize = scanner.nextLine();
        int[] islandSizeValues = Arrays.stream(stringIslandSize.split(" ")).mapToInt(Integer::parseInt).toArray();
        return islandSizeValues;
    }
}
