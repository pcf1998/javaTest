package application;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Weight {
    private Queue visited;
    int[] distance;

    public Weight(int len) {
        // TODO Auto-generated constructor stub
        visited = new LinkedList();
        distance = new int[len];

    }

    private int getIndex(Queue q, int[] dis) {
        int k = -1;
        int min_num = Integer.MAX_VALUE;
        for (int i = 0; i < dis.length; i++) {
            if (!q.contains(i)) {
                if (dis[i] < min_num) {
                    min_num = dis[i];
                    k = i;
                }
            }
        }
        return k;
    }

    public void dijkstra(int[][] weight, Object[] str, int v, int p) {
        HashMap path;
        path = new HashMap();
        for (int i = 0; i < str.length; i++)
            path.put(i, "");

        //初始化路径长度数组distance
        for (int i = 0; i < str.length; i++) {
            path.put(i, path.get(i) + "" + str[v]);
            if (i == v)
                distance[i] = 0;
            else if (weight[v][i] != -1) {
                distance[i] = weight[v][i];
                path.put(i, path.get(i) + "-->" + str[i]);
            } else
                distance[i] = Integer.MAX_VALUE;
        }
        visited.add(v);
        while (visited.size() < str.length) {
            int k = getIndex(visited, distance);//获取未访问点中距离源点最近的点
            visited.add(k);
            if (k != -1) {

                for (int j = 0; j < str.length; j++) {
                    if (weight[k][j] != -1)//判断k点能够直接到达的点
                    {
                        //通过遍历各点，比较是否有比当前更短的路径，有的话，则更新distance，并更新path。
                        if (distance[j] > distance[k] + weight[k][j]) {
                            distance[j] = distance[k] + weight[k][j];
                            path.put(j, path.get(k) + "-->" + str[j]);
                        }
                    }

                }
            }
        }

        System.out.printf(str[v] + "-->" + str[p] + ":" + distance[p] + " ");
        if (distance[p] == Integer.MAX_VALUE)
            System.out.print(str[v] + "-->" + str[p] + "之间没有可通行路径");
        else
            System.out.print(str[v] + "-" + str[p] + "之间有最短路径，具体路径为：" + path.get(p).toString());
        System.out.println();

        visited = new LinkedList();

    }


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        System.out.println("please input the vertex number :");
        int n = input.nextInt();

        System.out.println("please input the weight :");
        int[][] weight = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                weight[i][j] = input.nextInt();
            }
        }

        char[] str1 = new char[n];
        for (int i = 0; i < n; i++) {
            str1[i] = (char) (65 + i);
        }

        String[] str = new String[n];
        for (int i = 0; i < n; i++) {
            str[i] = String.valueOf(str1[i]);
        }

        int len = str.length;
        Weight dijkstra = new Weight(len);

        char[] charTable = new char[26];
        String[] table = new String[26];
        for (int i = 0; i < 26; i++) {
            charTable[i] = (char) (65 + i);
        }
        for (int i = 0; i < 26; i++) {
            table[i] = String.valueOf(charTable[i]);
        }

        System.out.println("please input the start :");
        String a1 = input.next();
        int a = 0;
        for (int i = 0; i < n; i++) {
            if (a1.equals(table[i])) {
                a = i;
                break;
            }
        }
        if (!a1.equals("A")) {
            if (a == 0) {
                System.out.println("invalid start point");
                System.exit(0);
            }
        }

        System.out.println("please input the end :");
        String b1 = input.next();
        int b = 0;
        for (int i = 0; i < n; i++) {
            if (b1.equals(table[i])) {
                b = i;
                break;
            }
        }

        if (!b1.equals("A")) {
            if (b == 0) {
                System.out.println("invalid end point");
                System.exit(0);
            }
        }


        dijkstra.dijkstra(weight, str, a, b);


    }

}
