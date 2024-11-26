import java.util.Scanner;

public class ByteStuffing {
    private static final char FLAG = 0x7E;
    private static final char ESC = 0x7D;
    private static final char ESC_FLAG = 0x5E;
    private static final char ESC_ESC = 0x5D;

    public static String byteStuff(String data) {
        StringBuilder stuffedData = new StringBuilder();
        stuffedData.append(FLAG);

        for (int i = 0; i < data.length(); i++) {
            char byteChar = data.charAt(i);

            if (byteChar == FLAG) {
                stuffedData.append(ESC).append(ESC_FLAG);
            } else if (byteChar == ESC) {
                stuffedData.append(ESC).append(ESC_ESC);
            } else {
                stuffedData.append(byteChar);
            }
        }

        stuffedData.append(FLAG);
        return stuffedData.toString();
    }

    public static String byteUnstuff(String stuffedData) {
        StringBuilder unstuffedData = new StringBuilder();

        for (int i = 1; i < stuffedData.length() - 1; i++) {
            char byteChar = stuffedData.charAt(i);

            if (byteChar == ESC) {
                char nextChar = stuffedData.charAt(i + 1);
                if (nextChar == ESC_FLAG) {
                    unstuffedData.append(FLAG);
                } else if (nextChar == ESC_ESC) {
                    unstuffedData.append(ESC);
                }
                i++;
            } else {
                unstuffedData.append(byteChar);
            }
        }

        return unstuffedData.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the data to be stuffed (as plain text): ");
        String data = scanner.nextLine();

        String stuffedData = byteStuff(data);
        System.out.println("Stuffed Data: " + stuffedData);

        String unstuffedData = byteUnstuff(stuffedData);
        System.out.println("Unstuffed Data: " + unstuffedData);

        scanner.close();
    }
}
