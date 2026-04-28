import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Kata {
    public static String highAndLow(String numbers) {
        List<String> numbersList = Arrays.stream(numbers.split(" "))
                .sorted(Comparator.comparingInt(Integer::parseInt))
                .toList();
        return numbersList.get(numbersList.size()-1) + " " + numbersList.get(0);
    }
}