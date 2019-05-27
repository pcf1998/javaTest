package algorithmDesign;

import java.util.Scanner;

public class Tournment {
    public static int MAX = 16384;
    public static int a[][] = new int[MAX][MAX];

    public static void main(String[] args) {

        System.out.println("请输入k（比赛选手人数为2^k）：");
        Scanner input = new Scanner(System.in);
        int k = input.nextInt();
        int n = 1;
        int n1 = 2;
        for (int i = 0; i < k; i++) {
            n = n * n1;
        }

        gemecal(1, n);

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.print(a[i][j] + "  ");


            }
            System.out.println();
        }
    }

    public static void gemecal(int m, int n) {
        int i, j;
        if (n == 2) {
            a[m][1] = m;
            a[m][2] = m + 1;
            a[m + 1][1] = m + 1;
            a[m + 1][2] = m;
        }
        else {
            gemecal(m, n / 2);
            gemecal(m + n / 2, n / 2);
            for (i = m; i < m + n / 2; i++) {
                for (j = n / 2 + 1; j <= n; j++) {
                    a[i][j] = a[i + n / 2][j - n / 2];
                }1
            }
            for (i = m + n / 2; i < m + n; i++) {
                for (j = n / 2 + 1; j <= n; j++) {
                    a[i][j] = a[i - n / 2][j - n / 2];
                }
            }

        }
    }

}