package stringTask;

public class StringTask {
    public static void main(String[] args) {
        String str = "2[y4[x]5[k]]ba";
        System.out.println(convert(str));
    }

    private static String convert(String str) {
        char[] str1 = str.toCharArray();
        int firstCharPos = 0;
        int lastCharPos = str1.length - 1;
        return wrap("", str1, firstCharPos, lastCharPos);
    }

    private static String wrap(String newString, char[] str1, int firstCharPos, int lastCharPos) {
        while (firstCharPos <= lastCharPos) {
            if (Character.isDigit(str1[firstCharPos])) {
                newString += openBrackets(str1, firstCharPos);
                firstCharPos = lastBracketCount(str1, firstCharPos) + 1;
            } else if (str1[firstCharPos] == ']' || str1[firstCharPos] == '[') {
                firstCharPos++;
            } else {
                newString += str1[firstCharPos];
                firstCharPos++;
            }
        }
        return newString;
    }


    private static String openBrackets(char[] str1, int firstCharPos) {
        int multiplicator = Character.getNumericValue(str1[firstCharPos]);
        int lastCharInBracket = lastBracketCount(str1, firstCharPos) - 1;
        firstCharPos += 2;
        String newString = "";
        newString += wrap(newString, str1, firstCharPos, lastCharInBracket);
        return multiplication(newString, multiplicator);
    }


    private static String multiplication(String substring, int multiplicator) {
        String str = "";
        for (int i = 0; i < multiplicator; i++) {
            str += substring;
        }
        return str;
    }

    private static int lastBracketCount(char[] str1, int firstCharPos) {
        int openBracketsCount = 1;
        firstCharPos += 2;
        while (openBracketsCount != 0) {
            if (str1[firstCharPos] == '[') {
                openBracketsCount++;
            } else if (str1[firstCharPos] == ']') {
                openBracketsCount--;
            }
            firstCharPos++;
        }
        return firstCharPos - 1;
    }
}
