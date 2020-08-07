package encryptdecrypt;

public class Main {
    public static void main(String[] args) {
        String line = "we found a treasure!";
        StringBuilder reverseLine = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) > 96 && line.charAt(i) < 123) {
                reverseLine.append((char) (122 - (line.charAt(i) - 97)));
            } else {
                reverseLine.append(line.charAt(i));
            }
        }
        System.out.println(reverseLine.toString());
    }
}
