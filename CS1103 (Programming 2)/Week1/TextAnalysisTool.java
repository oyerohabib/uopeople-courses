import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class TextAnalysisTool {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a paragraph or text: ");
        String inputText = scanner.nextLine();

        int charCount = inputText.length();
        System.out.println("Character Count: " + charCount);

        String[] words = inputText.split("\\s+|\\p{Punct}");
        words = Arrays.stream(words)
                .filter(word -> !word.isEmpty())
                .toArray(String[]::new);

        int wordCount = words.length;
        System.out.println("Word Count: " + wordCount);

        char mostCommonChar = findMostCommonCharacter(inputText);
        System.out.println("Most Common Character: " + mostCommonChar);

        System.out.print("Enter a character: ");
        char userChar = scanner.next().toLowerCase().charAt(0);

        long charFrequency = inputText.chars().filter(ch -> Character.toLowerCase(ch) == userChar).count();
        System.out.println("Frequency of '" + userChar + "': " + charFrequency);

        System.out.print("Enter a word: ");
        String userWord = scanner.next().toLowerCase();

        long wordFrequency = countWordFrequency(words, userWord);
        System.out.println("Frequency of '" + userWord + "': " + wordFrequency);

        Set<String> uniqueWords = new HashSet<>(Arrays.asList(words));
        int uniqueWordCount = uniqueWords.size();
        System.out.println("Unique Words Count: " + uniqueWordCount);
    }

    private static char findMostCommonCharacter(String inputText) {
        Map<Character, Integer> charFrequencyMap = new HashMap<>();

        for (char ch : inputText.toCharArray()) {
            charFrequencyMap.put(ch, charFrequencyMap.getOrDefault(ch, 0) + 1);
        }

        char mostCommonChar = ' ';
        int maxFrequency = 0;

        for (Map.Entry<Character, Integer> entry : charFrequencyMap.entrySet()) {
            if (entry.getValue() > maxFrequency) {
                mostCommonChar = entry.getKey();
                maxFrequency = entry.getValue();
            }
        }

        return mostCommonChar;
    }

    private static long countWordFrequency(String[] words, String userWord) {
        return Arrays.stream(words).filter(word -> word.equalsIgnoreCase(userWord)).count();
    }
}
