package encryptdecrypt;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String line = scanner.nextLine();
        int shift = scanner.nextInt();
        StringBuilder encryptedLine = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (Character.isUpperCase(line.charAt(i))) {
                encryptedLine.append((char) ((line.charAt(i) + shift - 65) % 26 + 65));
            } else if (Character.isLowerCase(line.charAt(i))) {
                encryptedLine.append((char) ((line.charAt(i) + shift - 97) % 26 + 97));
            } else {
                encryptedLine.append(line.charAt(i));
            }
        }
        System.out.println(encryptedLine.toString());
    }
}
