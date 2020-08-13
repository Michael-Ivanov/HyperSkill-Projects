package encryptdecrypt;

public class AlgorithmFactory {

    public static Algorithm getAlgorithm(String type, String line, int key) {
        if ("shift".equalsIgnoreCase(type)) {
            return new ShiftAlgorithm(line, key);
        } else if ("unicode".equalsIgnoreCase(type)) {
            return new UnicodeAlgorithm(line, key);
        } else {
            return null;
        }
    }
}
