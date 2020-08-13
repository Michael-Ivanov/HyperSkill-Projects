package encryptdecrypt;

public abstract class Algorithm {

    protected String line;
    protected int key;

    public Algorithm(String line, int key) {
        this.line = line;
        this.key = key;
    }

    public abstract String getEncrypted();

    public abstract String getDecrypted();
}
