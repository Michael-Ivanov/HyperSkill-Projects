package encryptdecrypt;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        String mode = "enc";
        int key = 0;
        String data = "";
        String output = "";
        String input = "";
        boolean inputDefined = false;
        for (int i = 0; i < args.length; i++) {
            switch (args[i]) {
                case "-mode":
                    mode = args[i + 1];
                    break;
                case "-key":
                    key = Integer.parseInt(args[i + 1]);
                    break;
                case "-data":
                    if (inputDefined) {
                        throw new IllegalArgumentException("Too many input parameters");
                    } else {
                        data = args[i + 1];
                        inputDefined = true;
                    }
                    break;
                case "-in":
                    if (inputDefined) {
                        throw new IllegalArgumentException("Too many input parameters");
                    } else {
                        input = args[i + 1];
                        File file = new File(input);
                        try (Scanner scanner = new Scanner(file)) {
                            data = scanner.nextLine();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "-out":
                    output = args[i + 1];
                    break;
            }
        }
        if (output.equals("")) {
            System.out.println(lineModification(mode, data, key));
        } else {
            try (FileWriter fileWriter = new FileWriter(output)) {
                fileWriter.write(lineModification(mode, data, key));
            } catch (IOException e) {
                System.out.println("File not found: " + output);
                e.printStackTrace();
            }
        }
    }

    private static String lineModification(String mode, String data, int key) {
        StringBuilder resultLine = new StringBuilder();
        if ("enc".equals(mode)) {
            for (int i = 0; i < data.length(); i++) {
                resultLine.append((char) (data.charAt(i) + key));
            }
        } else if ("dec".equals(mode)) {
            for (int i = 0; i < data.length(); i++) {
                resultLine.append((char) (data.charAt(i) - key));
            }
        } else {
            throw new IllegalArgumentException("Incorrect target operation. Should be \"enc\" or \"dec\".");
        }
        return resultLine.toString();
    }
}
