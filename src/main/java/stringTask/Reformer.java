package stringTask;


import java.util.regex.Matcher;
import java.util.regex.Pattern;


class Reformer {


    public String convert(String str) {
        char[] str1 = str.toCharArray();
        int currentCharPos = 0;
        int lastCharPos = str1.length - 1;
        return wrap("", str1, currentCharPos, lastCharPos);
    }

    private String wrap(String newString, char[] str1, int currentCharPos, int lastCharPos) {
        while (currentCharPos <= lastCharPos) {
            if (Character.isDigit(str1[currentCharPos])) {
                int multiplicator;
                if (str1[currentCharPos + 1] == '[') {
                    multiplicator = Character.getNumericValue(str1[currentCharPos]);
                } else {
                    multiplicator = increaseNumber(str1, currentCharPos);
                    currentCharPos = getLastNumberPos(str1, currentCharPos);
                }
                newString += openBrackets(str1, currentCharPos, multiplicator);
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

    private int increaseNumber(char[] str1, int currentCharPos) {
        int counter = getLastNumberPos(str1, currentCharPos);
        int fullNumber = 0;
        int exponent = 0;
        while (counter >= currentCharPos) {
            fullNumber += Character.getNumericValue(str1[counter]) * (int)Math.pow(10, exponent);
            counter--;
            exponent++;
        }
        return fullNumber;
    }

    private int getLastNumberPos(char[] str1, int currentCharPos) {
        while (Character.isDigit(str1[currentCharPos + 1])) {
            currentCharPos++;
        }
        return currentCharPos;
    }


    private String openBrackets(char[] str1, int currentCharPos, int multiplicator) {
        int lastCharInBracket = lastBracketCount(str1, currentCharPos) - 1;
        currentCharPos += 2;
        String newString = "";
        newString += wrap(newString, str1, currentCharPos, lastCharInBracket);
        return multiplication(newString, multiplicator);
    }


    private String multiplication(String substring, int multiplicator) {
        String str = "";
        for (int i = 0; i < multiplicator; i++) {
            str += substring;
        }
        return str;
    }

    private int lastBracketCount(char[] str1, int currentCharPos) {
        int openBracketsCount = 1;
        currentCharPos += 2;
        try {
            while (openBracketsCount != 0) {
                if (str1[currentCharPos] == '[') {
                    openBracketsCount++;
                } else if (str1[currentCharPos] == ']') {
                    openBracketsCount--;
                }
                currentCharPos++;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Missing brackets in front of number!\n" + e);
        }
        return currentCharPos - 1;
    }

    public boolean isValid(String str) {
        if (str.equals("")) {
            System.err.println("Your string is empty!");
            return false;
        }
        if (!isEqualBrackets(str)) {
            System.err.println("The number of opening and closing brackets does not match!");
            return false;
        }
        if (!isRegExValid(str)) {
            System.err.println("Found invalid characters in string!");
            return false;
        }
        return true;
    }

    private boolean isEqualBrackets(String str) {
        char[] str1 = str.toCharArray();
        int openBracketsCount = 0;
        int closeBracketsCount = 0;
        for (char c : str1) {
            if (c == '[') {
                openBracketsCount++;
            }
            if (c == ']') {
                closeBracketsCount++;
            }
        }
        return openBracketsCount == closeBracketsCount;
    }

    private boolean isRegExValid(String str) {
        Pattern p = Pattern.compile("^[a-z0-9\\[\\]A-Z]+");
        Matcher m = p.matcher(str);
        return m.matches();
    }
}
