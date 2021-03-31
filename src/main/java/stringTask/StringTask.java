package stringTask;

import java.util.Scanner;

public class StringTask {
    public static void main(String[] args) {
        String str = inputConsole();                // String like "2[y4[x]5[k]]ba" works perfect
        Reformer reformer = new Reformer();
        if (reformer.isValid(str)) {
            System.out.println(reformer.convert(str));
        } else {
            System.err.println("Your string was incorrect!");
        }
    }

    private static String inputConsole() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please input your string: ");
        return scanner.nextLine();
    }
}
