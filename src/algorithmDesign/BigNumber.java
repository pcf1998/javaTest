package algorithmDesign;

import java.util.Scanner;

public class BigNumber {

    public int MULT(String num1, String num2, int n) {
        int s = SIGN(num1) * SIGN(num2);
        num1 = ABS(num1);
        num2 = ABS(num2);
        if (n == 1) {
            if (Integer.parseInt(num1) == 1 && Integer.parseInt(num2) == 1) {
                return (s);
            } else {
                return s * (Integer.parseInt(num1) * Integer.parseInt(num2));
            }
        } else {
            String A = num1.substring(0, n / 2);
            String B = num1.substring(n / 2);
            String C = num2.substring(0, n / 2);
            String D = num2.substring(n / 2);

            int m1 = MULT(A, C, n / 2);
            int m2 = MULT(String.valueOf(Integer.parseInt(A) - Integer.parseInt(B)), String.valueOf(Integer.parseInt(D) - Integer.parseInt(C)), n / 2);
            int m3 = MULT(B, D, n / 2);
            s = (int) (s * (m1 * Math.pow(10, n) + (m1 + m2 + m3) * Math.pow(10, n / 2) + m3));

        }
        return s;
    }

    public int SIGN(String num) {
        if (num.charAt(0) == '-') {
            return -1;
        } else {
            return 1;
        }
    }

    public String ABS(String num) {
        if (num.charAt(0) == '-') {
            num = num.substring(1);
        }
        return num;
    }

    public static void main(String[] args) {
        BigNumber test = new BigNumber();
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter the bit number:");
        String n = scanner.nextLine();
        System.out.println("enter the big number 1：");
        String bigNumber1 = scanner.nextLine();
        System.out.println("enter the big number 2：");
        String bigNumber2 = scanner.nextLine();

        System.out.println("result:  " + bigNumber1 + " * " + bigNumber2 + " = " + test.MULT(bigNumber1, bigNumber2, Integer.parseInt(n)));
    }

}