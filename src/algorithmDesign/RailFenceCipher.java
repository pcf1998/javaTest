package algorithmDesign;

import java.util.Scanner;

public class RailFenceCipher {

    public static String quitBlank(String normalStr) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < normalStr.length(); i++) {
            if (normalStr.charAt(i) != ' ')
                sBuffer.append(normalStr.charAt(i));
        }
        return sBuffer.toString();
        //原文去空格
    }

    public static String En(int n, String str) {
        StringBuffer result = new StringBuffer();

        int step = (int) ((double) str.length() / n + 0.5);
        //操作步数凑整

        String[] splitStr = new String[step];
        int temp = 0;
        for (int i = 0; i < step; i++) {
            if (temp + n < str.length()) {
                splitStr[i] = str.substring(temp, temp + n);
            } else {
                splitStr[i] = str.substring(temp, str.length());
            }

            temp += n;
        }
        //原文分组

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < step; j++) {
                if (i < splitStr[j].length()) {
                    result.append(splitStr[j].charAt(i));
                }
            }
        }
        return result.toString();
        //原文加密
    }

    public static String De(int n, String str) {
        StringBuffer result = new StringBuffer();

        int step = (int) ((double) str.length() / n + 0.5);
        //操作步数凑整

        String[] splitStr = new String[n];
        int temp = 0;
        for (int i = 0; i < n; i++) {
            if (temp + step < str.length()) {
                splitStr[i] = str.substring(temp, temp + step);
            } else {
                splitStr[i] = str.substring(temp, str.length());
            }

            temp += step;
        }
        //密文分组

        for (int i = 0; i < step; i++) {
            for (int j = 0; j < n; j++) {
                if (i < splitStr[j].length()) {
                    result.append(splitStr[j].charAt(i));
                }
            }
        }
        return result.toString();
        //密文还原，生成明文
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("第一行输入明文字符\n" + "第二行输入分栏数n");
        String normalStr = scan.nextLine();
        //获取原文
        int n = scan.nextInt();
        //获取分栏数
        String str = quitBlank(normalStr);
        //原文去空
        String EnStr = En(n, str);
        System.out.println("密文如下：\n" + EnStr);
        //加密
        String DeStr = De(n, EnStr);
        System.out.println("解密后明文如下：\n" + DeStr);
        //解密
    }
}
