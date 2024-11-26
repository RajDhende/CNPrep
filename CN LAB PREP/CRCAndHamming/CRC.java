import java.util.Scanner;

public class CRC {
    public static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 1; i < b.length(); i++) {
            result.append(a.charAt(i) == b.charAt(i) ? '0' : '1');
        }
        return result.toString();
    }

    public static String mod2div(String dividend, String divisor) {
        int pick = divisor.length();
        String tmp = dividend.substring(0, pick);

        while (pick < dividend.length()) {
            if (tmp.charAt(0) == '1') {
                tmp = xor(divisor, tmp) + dividend.charAt(pick);
            } else {
                tmp = xor("0".repeat(pick), tmp) + dividend.charAt(pick);
            }
            pick += 1;
        }

        if (tmp.charAt(0) == '1') {
            tmp = xor(divisor, tmp);
        } else {
            tmp = xor("0".repeat(pick), tmp);
        }

        return tmp;
    }

    public static String encodeData(String data, String divisor) {
        int l_key = divisor.length();
        String appendedData = data + "0".repeat(l_key - 1);
        String remainder = mod2div(appendedData, divisor);
        return data + remainder;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the data to be transmitted: ");
        String data = sc.nextLine();
        System.out.println("Enter the generator polynomial: ");
        String divisor = sc.nextLine();
        
        String codeword = encodeData(data, divisor);
        System.out.println("Transmitted Codeword: " + codeword);
    }
}
