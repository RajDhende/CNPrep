import java.util.Scanner;

public class BitStuffing {
    public static String bitStuff(String data) {
        StringBuilder stuffedData = new StringBuilder();
        int consecutiveOnes = 0;

        for (int i = 0; i < data.length(); i++) {
            char bit = data.charAt(i);
            stuffedData.append(bit);

            if (bit == '1') {
                consecutiveOnes++;
                if (consecutiveOnes == 5) {
                    stuffedData.append('0');
                    consecutiveOnes = 0;
                }
            } else {
                consecutiveOnes = 0;
            }
        }

        return stuffedData.toString();
    }

    public static String unStuff(String data) {
        StringBuilder unstuffedData = new StringBuilder();
        int consecutiveOnes = 0;

        for (int i = 0; i < data.length(); i++) {
            char bit = data.charAt(i);
            unstuffedData.append(bit);

            if (bit == '1') {
                consecutiveOnes++;
                if (consecutiveOnes == 5 && (i + 1) < data.length() && data.charAt(i + 1) == '0') {
                    i++;
                    consecutiveOnes = 0;
                }
            } else {
                consecutiveOnes = 0;
            }
        }

        return unstuffedData.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter binary data for bit stuffing: ");
        String data = scanner.next();

        String stuffedData = bitStuff(data);
        System.out.println("Stuffed Data: " + stuffedData);

        String unstuffedData = unStuff(stuffedData);
        System.out.println("Unstuffed Data: " + unstuffedData);

        scanner.close();
    }
}
