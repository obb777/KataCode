import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class ClosestWeight {
    public static int[][] closest(String strng) {

        List<String> numStrngs = Arrays.asList(strng.split("\\s+"));

        List<int[]> data = new ArrayList();
        try {
            for (int i = 0; i < numStrngs.size(); i++) {
                int number = getNumber(numStrngs.get(i));
                int weight = getWeight(numStrngs.get(i));
                int[] item = new int[]{weight, i, number};
                data.add(item);
            }
        }
        catch (NumberFormatException ex) {
            //System.out.println("Incorrect input provided");
            return new int[0][0];
        }

        if (data.size() < 2) {
            return new int [0][0];
        }

        Comparator<int[]> comparator = (int[] a, int[] b) -> {
            int comp1 = Integer.compare(a[0],b[0]);
            return comp1 == 0 ? Integer.compare(a[1],b[1]) : comp1;
        };
        data.sort(comparator);

        int min = data.get(1)[0]-data.get(0)[0];
        int minIdx = 0;
        for (int i=0; i<data.size()-1; i++){
            int diff = data.get(i+1)[0] - data.get(i)[0];
            if (diff < min) {
                min = diff;
                minIdx = i;
            }
            if (diff == 0) {
                break;
            }
        }
        int[][] out = new int[][] {data.get(minIdx), data.get(minIdx+1)};
        return out;
    }

    protected static int getNumber(String s) {
        return Integer.parseUnsignedInt(s);
    }

    protected static int getWeight(String s) {
        int weight = 0;
        for (char c : s.toCharArray()) {
            weight += Integer.parseUnsignedInt(String.valueOf(c));
        }
        return weight;
    }
}