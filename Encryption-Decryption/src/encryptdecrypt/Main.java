package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String direction = scanner.nextLine();
        String line = scanner.nextLine();
        int shift = scanner.nextInt();
        StringBuilder resultLine = new StringBuilder();
        if ("enc".equals(direction)) {
            for (int i = 0; i < line.length(); i++) {
                resultLine.append((char) (line.charAt(i) + shift));
            }
        } else if ("dec".equals(direction)) {
            for (int i = 0; i < line.length(); i++) {
                resultLine.append((char) (line.charAt(i) - shift));
            }
        } else {
            throw new IllegalArgumentException("Incorrect target operation. Should be \"enc\" or \"dec\".");
        }
        System.out.println(resultLine.toString());
    }
}
