package encryptdecrypt;

public class UnicodeAlgorithm extends Algorithm {

    StringBuilder resultLine = new StringBuilder();

    public UnicodeAlgorithm(String line, int key) {
        super(line, key);
    }

    @Override
    public String getEncrypted() {
        for (int i = 0; i < line.length(); i++) {
            resultLine.append((char) (line.charAt(i) + key));
        }
        return resultLine.toString();
    }

    @Override
    public String getDecrypted() {
        for (int i = 0; i < line.length(); i++) {
            resultLine.append((char) (line.charAt(i) - key));
        }
        return resultLine.toString();
    }
}
