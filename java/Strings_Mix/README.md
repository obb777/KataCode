import java.util.*;

class Mixing {

    static Map<Character, Integer> countFrequencies(String s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (!Character.isLowerCase(c))
                continue;
            Integer cnt = freq.getOrDefault(c, 0);
            freq.put(c, cnt+1);
        }
        for (Iterator<Map.Entry<Character, Integer>> it = freq.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Character, Integer> entry = it.next();
            if (entry.getValue() < 2) {
                it.remove();
            }
        }
        return freq;
    }

    public static String mix(String s1, String s2) {
        Map<Character, Integer> s1Freq = countFrequencies(s1);
        System.out.println(s1Freq);

        Map<Character, Integer> s2Freq = countFrequencies(s2);
        System.out.println(s2Freq);

        Set<Character> resKeySet = new TreeSet<> (s1Freq.keySet());
        resKeySet.addAll(s2Freq.keySet());

        List<String> res = new ArrayList<>(resKeySet.size());

        for (Character c : resKeySet) {
            StringBuilder item = new StringBuilder();
            Integer f1 = s1Freq.getOrDefault(c, 0);
            Integer f2 = s2Freq.getOrDefault(c, 0);

            if (f1.compareTo(f2) == 0) {
                char[] chars = new char[f1];
                Arrays.fill(chars, c);
                item.append("=:").append(chars);
            }
            else if (f1.compareTo(f2) > 0) {
                char[] chars = new char[f1];
                Arrays.fill(chars, c);
                item.append("1:").append(chars);
            }
            else {
                char[] chars = new char[f2];
                Arrays.fill(chars, c);
                item.append("2:").append(chars);
            }
            res.add(item.toString());
        }

        res.sort((String str1, String str2)->{
            if(str1.length() != str2.length())
                return str2.length() - str1.length();
            else
                return str1.compareTo(str2);
        });

        return String.join("/", res);
    }
}