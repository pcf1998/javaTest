package algorithmDesign;
import java.util.ArrayList;

public class Matrix {

    public int[][] A(int[][] a, int row, int column) {
        int[][] ans = new int[a.length - 1][a.length - 1];
        // ans用于储存返回的最终结果
        int[] temp = new int[(a.length - 1) * (a.length - 1)];
        // 临时一维数组temp用于按顺序储存剔除相应行和列元素后的数组
        int k = 0;
        // 剔除行和列并按顺序储存到temp内
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                if (i == row - 1) {
                    continue;
                }
                else if (j == column - 1) {
                    continue;
                }
                temp[k++] = a[i][j];
            }
        }
        // 按顺序从temp中读取数据并储存到ans内
        k = 0;
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[i].length; j++) {
                ans[i][j] = temp[k++];
            }
        }
        return ans;
    }

    // det用于求行列式
    public int det(int[][] a) {
        if (a.length == 1) {
            return a[0][0];
        }
        else {
            int ans = 0;
            for (int i = 0; i < a.length; i++) {
                ans += a[i][0] * (int) Math.pow(-1, i) * det(A(a, i + 1, 1));
            }
            return ans;
        }
    }

    public int[][]  identityMatrix(int a){
        int[][] c=new int[a][a];
        for(int i=0;i<a;i++){
            for(int j=0;j<a;j++){
                if(i==j){
                    c[i][j]=1;
                }
                else{
                    c[i][j]=0;
                }
            }
        }

        return c;

    }

    public static int[] ranNum(int n){
        ArrayList result1 = new ArrayList();
        int[] result=new int[n];

        while(result1.size() != n){
            int num = (int)(Math.random()*10+1);
            if(!result1.contains(num)){
                result1.add(num);
            }
        }

        for (int i = 0; i < n; i++) {
            result[i] = (int) result1.get(i);
        }

        return result;
    }
    //生成n个不一样的1-100的随机数

    public static int[][] invertibleMatrix(int a){
        int[][] matrix1=new int[a][a];
        int[][] ranNumber=new int[a+1][a];

        for(int i=0;i<a+1;i++){
                ranNumber[i]=ranNum(a);
        }

        for(int i=0;i<a;i++){
            for(int j=0;j<a;j++){
                if(i==j){
                    matrix1[i][j]=ranNumber[0][i];
                }
                else{
                    matrix1[i][j]=0;
                }
            }
        }
        //生成单位矩阵de变形

        for(int i=0;i<a;i++){
            for(int j=0;j<a;j++){
                for(int k=0;k<a;k++){
                matrix1[i][j]=ranNumber[i+1][0] * matrix1[0][j] + ranNumber[i+1][1]*matrix1[1][j] + ranNumber[i+1][2]*matrix1[2][j] + ranNumber[i+1][3]*matrix1[3][j];
                }
            }
        }

        return matrix1;
    }


    public static void main(String[] args){
        int n=5;
        int[][] a=invertibleMatrix(n);

        for(int i=0;i<n;i++){
            System.out.println(a[i][0]+ " "+a[i][1]+ " "+a[i][2]+ " "+a[i][3]);
        }

    }



}
