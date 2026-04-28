package Decode_the_Morse_code;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class MorseCodeDecoder {

    private static HashMap<String, String> MorseCode = buildMorseCodeMap();

    public static void main(String[] args) {
        System.out.println(decode(".... . -.--   .--- ..- -.. ."));
    }

    private static HashMap<String, String> buildMorseCodeMap() {
        HashMap<String, String> codes = new HashMap<>();
        // Letters A–Z
        codes.put(".-",   "A");
        codes.put("-...", "B");
        codes.put("-.-.", "C");
        codes.put("-..",  "D");
        codes.put(".",    "E");
        codes.put("..-.", "F");
        codes.put("--.",  "G");
        codes.put("....", "H");
        codes.put("..",   "I");
        codes.put(".---", "J");
        codes.put("-.-",  "K");
        codes.put(".-..", "L");
        codes.put("--",   "M");
        codes.put("-.",   "N");
        codes.put("---",  "O");
        codes.put(".--.", "P");
        codes.put("--.-", "Q");
        codes.put(".-.",  "R");
        codes.put("...",  "S");
        codes.put("-",    "T");
        codes.put("..-",  "U");
        codes.put("...-", "V");
        codes.put(".--",  "W");
        codes.put("-..-", "X");
        codes.put("-.--", "Y");
        codes.put("--..", "Z");

        // Digits 0–9
        codes.put("-----", "0");
        codes.put(".----", "1");
        codes.put("..---", "2");
        codes.put("...--", "3");
        codes.put("....-", "4");
        codes.put(".....", "5");
        codes.put("-....", "6");
        codes.put("--...", "7");
        codes.put("---..", "8");
        codes.put("----.", "9");
        return codes;
    }



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