package algorithmDesign;

import java.util.ArrayList;
import java.util.Scanner;

public class PolyalphabeticCipher {

    PolyalphabeticCipher() {
    }

    ;
    String normalStr;
    int n;
    int[] midStr;
    String cipherStr;

    public static String quitBlank(String normalStr) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < normalStr.length(); i++) {
            if (normalStr.charAt(i) != ' ')
                sBuffer.append(normalStr.charAt(i));
        }
        return sBuffer.toString();
        //原文去空格
    }

    public int[] str2num(String str) {
        //原文加密
        int numOfStr = str.length();
        char[] strArray = str.toCharArray();
        ArrayList numArray = new ArrayList();

        char[] cipherTable = new char[26];
        String[] cipherTable1 = new String[26];
        for (int i = 0; i < 26; i++) {
            cipherTable[i] = (char) (97 + i);
        }
        for (int i = 0; i < 26; i++) {
            cipherTable1[i] = String.valueOf(cipherTable[i]);
        }

        for (int i = 0; i < numOfStr; i++) {
            for (int j = 0; j < 26; j++) {
                if ((int) cipherTable[j] == (int) strArray[i]) {
                    numArray.add(j+1);
                }
            }
        }

        int[] numberArray = new int[numArray.size()];
        for (int i = 0; i < numArray.size(); i++) {
            numberArray[i] = (int) numArray.get(i);
        }

        return numberArray;


    }

    public int[] En(int n,int[] plainNum){
        int numOfPlain=plainNum.length;
        int[] result=new int[n];
        //int yu=
        return result;
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
        PolyalphabeticCipher test = new PolyalphabeticCipher();
        Scanner scan = new Scanner(System.in);
        System.out.println("第一行输入明文字符\n" + "第二行输入加密矩阵的阶层数n");
        test.normalStr = scan.nextLine();
        //获取原文
        test.n = scan.nextInt();
        //获取矩阵阶层数
        String str = quitBlank(test.normalStr);
        //原文去空
        test.midStr = test.str2num(str);
        for (int i = 0; i < test.midStr.length; i++) {
            System.out.println(test.midStr[i]);
        }


        //System.out.println("密文如下：\n" + EnStr);
        //加密
        //String DeStr = De(EnStr);
        //System.out.println("解密后明文如下：\n" + DeStr);
        //解密
    }
}
