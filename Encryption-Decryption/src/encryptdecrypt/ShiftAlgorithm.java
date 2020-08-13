package encryptdecrypt;

public class ShiftAlgorithm extends Algorithm{

    StringBuilder resultLine = new StringBuilder();
    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();

    public ShiftAlgorithm(String line, int key) {
        super(line, key);
    }

    @Override
    public String getEncrypted() {
        for (int i = 0; i < line.length(); i++) {
            if (Character.isUpperCase(line.charAt(i))) {
                int alphNumber = line.charAt(i) - 65;
                int resNum = (alphNumber + key) % 26;
                resultLine.append(Character.toUpperCase(alphabet[resNum]));
            } else if (Character.isLowerCase(line.charAt(i))) {
                int alphNumber = line.charAt(i) - 97;
                int resNum = (alphNumber + key) % 26;
                resultLine.append(alphabet[resNum]);
            } else {
                resultLine.append(line.charAt(i));
            }
        }
        return resultLine.toString();
    }

    @Override
    public String getDecrypted() {
        for (int i = 0; i < line.length(); i++) {
            if (Character.isUpperCase(line.charAt(i))) {
                int alphNumber = line.charAt(i) - 65;
                int resNum = (alphNumber - key + 26) % 26;
                resultLine.append(Character.toUpperCase(alphabet[resNum]));
            } else if (Character.isLowerCase(line.charAt(i))) {
                int alphNumber = line.charAt(i) - 97;
                int resNum = (alphNumber - key + 26) % 26;
                resultLine.append(alphabet[resNum]);
            } else {
                resultLine.append(line.charAt(i));
            }
        }
        return resultLine.toString();
    }
}
