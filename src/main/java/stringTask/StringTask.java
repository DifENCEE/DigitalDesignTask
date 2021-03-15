package stringTask;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTask {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input your string: ");
        String str = scanner.nextLine();                // String like "2[y4[x]5[k]]ba" works perfect
        if (isValid(str)) {
            System.out.println(convert(str));
        } else {
            System.err.println("Your string was incorrect!");
        }
    }

    private static String convert(String str) {
        char[] str1 = str.toCharArray();
        int currentCharPos = 0;
        int lastCharPos = str1.length - 1;
        return wrap("", str1, currentCharPos, lastCharPos);
    }

    private static String wrap(String newString, char[] str1, int currentCharPos, int lastCharPos) {
        while (currentCharPos <= lastCharPos) {
            if (Character.isDigit(str1[currentCharPos])) {
                newString += openBrackets(str1, currentCharPos);
                currentCharPos = lastBracketCount(str1, currentCharPos) + 1;
            } else if (str1[currentCharPos] == ']' || str1[currentCharPos] == '[') {
                currentCharPos++;
            } else {
                newString += str1[currentCharPos];
                currentCharPos++;
            }
        }
        return newString;
    }


    private static String openBrackets(char[] str1, int currentCharPos) {
        int multiplicator = Character.getNumericValue(str1[currentCharPos]);
        int lastCharInBracket = lastBracketCount(str1, currentCharPos) - 1;
        currentCharPos += 2;
        String newString = "";
        newString += wrap(newString, str1, currentCharPos, lastCharInBracket);
        return multiplication(newString, multiplicator);
    }


    private static String multiplication(String substring, int multiplicator) {
        String str = "";
        for (int i = 0; i < multiplicator; i++) {
            str += substring;
        }
        return str;
    }

    private static int lastBracketCount(char[] str1, int currentCharPos) {
        int openBracketsCount = 1;
        currentCharPos += 2;
        while (openBracketsCount != 0) {
            if (str1[currentCharPos] == '[') {
                openBracketsCount++;
            } else if (str1[currentCharPos] == ']') {
                openBracketsCount--;
            }
            currentCharPos++;
        }
        return currentCharPos - 1;
    }

    public static boolean isValid(String userNameString) {
        Pattern p = Pattern.compile("^[a-z1-9\\[\\]A-Z]+");
        Matcher m = p.matcher(userNameString);
        return m.matches();
    }
}
