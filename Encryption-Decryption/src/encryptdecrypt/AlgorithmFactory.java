package encryptdecrypt;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class AlgorithmFactory {

    private String mode = "enc";
    private int key = 0;
    private String line = "";
    private String output = "";
    private String input = "";
    private boolean inputDefined = false;
    private String algType = "shift";

    private String[] args;

    public AlgorithmFactory(String[] args) {
        this.args = args;
    }

    public void process() {
        parseArgs();
        Algorithm algorithm = getAlgorithm(algType, line, key);
        String result = "";
        if (mode.equalsIgnoreCase("enc")) {
            result = algorithm.getEncrypted();
        } else if (mode.equalsIgnoreCase("dec")) {
            result = algorithm.getDecrypted();
        } else {
            result = "";
        }

        if (output.equals("")) {
            System.out.println(result);
        } else {
            try (FileWriter fileWriter = new FileWriter(output)) {
                fileWriter.write(result);
            } catch (IOException e) {
                System.out.println("File not found: " + output);
                e.printStackTrace();
            }
        }
    }

    public Algorithm getAlgorithm(String type, String line, int key) {
        if ("shift".equalsIgnoreCase(type)) {
            return new ShiftAlgorithm(line, key);
        } else if ("unicode".equalsIgnoreCase(type)) {
            return new UnicodeAlgorithm(line, key);
        } else {
            return null;
        }
    }

    private void parseArgs() {
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
                        line = args[i + 1];
                        inputDefined = true;
                    }
                    break;
                case "-in":
                    if (inputDefined) {
                        throw new IllegalArgumentException("Too many input parameters");
                    } else {
                        input = args[i + 1];
                        File file = new File(input);
                        inputDefined = true;
                        try (Scanner scanner = new Scanner(file)) {
                            line = scanner.nextLine();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case "-out":
                    output = args[i + 1];
                    break;
                case "-alg":
                    algType = args[i + 1];
                    break;
            }
        }
    }
}
