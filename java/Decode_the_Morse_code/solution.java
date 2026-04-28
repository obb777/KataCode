import java.util.ArrayList;
import java.util.List;

public class MorseCodeDecoder {

    public static String decode(String morseCode) {
        List<String> codedWords = getTokens(morseCode.trim(), "   ");
        List<String> words = new ArrayList<>();
        for (String codedWord : codedWords) {
            StringBuffer word = new StringBuffer();
            List<String> charCodes = getTokens(codedWord, " ");
            for (String charCode : charCodes) {
                word.append(MorseCode.get(charCode));
            }
            words.add(word.toString());
        }

        return String.join(" ", words);
    }

    protected static List<String> getTokens(final String morseCode, final String separator) {
        ArrayList<String> tokens = new ArrayList<>();
        int sepPosition = -1;
        int startPosition = 0;
        while ((sepPosition = morseCode.indexOf(separator, startPosition)) != -1) {
            tokens.add(morseCode.substring(startPosition, sepPosition));
            startPosition = sepPosition + separator.length();
        }
        tokens.add(morseCode.substring(startPosition));
        return tokens;
    }

}