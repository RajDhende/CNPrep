import java.util.Scanner;

public class HammingCode {
    public static int[] calculateParityBits(int[] dataBits, int parityBitsCount) {
        int[] hammingCode = new int[dataBits.length + parityBitsCount];

        for (int i = 0; i < hammingCode.length; i++) {
            if (Math.pow(2, Math.ceil(Math.log(i + 1) / Math.log(2))) == (i + 1)) {
                hammingCode[i] = 0; 
            } else {
                hammingCode[i] = dataBits[i - Integer.bitCount(i + 1)];
            }
        }

        for (int i = 0; i < parityBitsCount; i++) {
            int parityPosition = (int) Math.pow(2, i);
            int parity = 0;

            for (int j = parityPosition - 1; j < hammingCode.length; j += (2 * parityPosition)) {
                for (int k = j; k < j + parityPosition && k < hammingCode.length; k++) {
                    parity ^= hammingCode[k];
                }
            }

            hammingCode[parityPosition - 1] = parity;
        }

        return hammingCode;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the number of data bits: ");
        int dataBitsCount = sc.nextInt();
        int[] dataBits = new int[dataBitsCount];

        System.out.println("Enter the data bits: ");
        for (int i = 0; i < dataBitsCount; i++) {
            dataBits[i] = sc.nextInt();
        }

        int parityBitsCount = 0;
        while (Math.pow(2, parityBitsCount) < dataBitsCount + parityBitsCount + 1) {
            parityBitsCount++;
        }

        int[] hammingCode = calculateParityBits(dataBits, parityBitsCount);

        System.out.println("The encoded Hamming Code is: ");
        for (int bit : hammingCode) {
            System.out.print(bit + " ");
        }
    }
}
