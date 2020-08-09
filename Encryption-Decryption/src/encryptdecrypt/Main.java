package encryptdecrypt;

public class Main {
    public static void main(String[] args) {
        String mode = "enc";
        int key = 0;
        String data = "";
        for (int i = 0; i < args.length; i++) {
            if ("-mode".equals(args[i])) {
                mode = args[i + 1];
            }
            if ("-key".equals(args[i])) {
                key = Integer.parseInt(args[i + 1]);
            }
            if ("-data".equals(args[i])) {
                data = args[i + 1];
            }
        }
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
        System.out.println(resultLine.toString());



    }
}
