package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Scanner;

public class EulerGraph extends Application {
    private static int V = 0;
    // 顶点数
    private static int adjmatrix[][];
    // 邻接矩阵
    private static int changematrix[][];
    // 改变后矩阵

    private double centerX = 200;
    private double centerY = 300;
    private double centerX1 = 600;
    private double centerY1 = 300;
    private double len = 150;
    static EulerGraph test = new EulerGraph();
    static boolean flag = true;

    private static String[] s1;
    // 顶点集合
    private static boolean[] verx;
    // 判断点是否被遍历
    private static Circle[] node;
    private static Circle[] node1;
    private ArrayList<Line> line = new ArrayList<>();
    private ArrayList<Line> line1 = new ArrayList<>();
    private ArrayList<Text> vexname = new ArrayList<>();
    // 点名集合
    private ArrayList<Text> vexname1 = new ArrayList<>();
    private String trail = "";
    // 遍历路径
    private String R;
    // trail的起点,默认为a

    @Override
    // Override the start method in the Application class
    public void start(Stage primaryStage) {

        this.input();
        this.isMatrix();

        if (flag = true) {
            BorderPane bpane = new BorderPane();
            Pane pane = new Pane();
            Pane pane1 = new Pane();
            node = new Circle[V];
            // 点集合
            for (int i = 0; i < V; i++) {
                Circle c = new Circle(0, 0, 1);
                node[i] = c;
            }
            for (int i = 0; i < V; i++) {
                // 确定点在一个圆上的位置
                node[i].setCenterX(centerX + len * Math.cos(Math.PI / 2 + i * 2 * Math.PI / V));
                node[i].setCenterY(centerY + len * Math.sin(Math.PI / 2 + i * 2 * Math.PI / V));
                node[i].setRadius(2);
            }
            for (int j = 0; j < V; j++) {
                for (int k = 0; k < V; k++) {
                    if (adjmatrix[j][k] == 1) {
                        line.add(new Line(node[j].getCenterX(), node[j].getCenterY(), node[k].getCenterX(),
                                node[k].getCenterY()));
                    }
                }
            }
            for (int j = 0; j < V; j++) {
                vexname.add(new Text(node[j].getCenterX() - 5, node[j].getCenterY() + 10, s1[j] + ""));
            }
            verx = new boolean[V];
            node1 = new Circle[V];
            for (int i = 0; i < V; i++) {
                Circle c = new Circle(0, 0, 1);
                node1[i] = c;
            }
            for (int i = 0; i < V; i++) {
                node1[i].setCenterX(centerX1 + len * Math.cos(Math.PI / 2 + i * 2 * Math.PI / V));
                node1[i].setCenterY(centerY1 + len * Math.sin(Math.PI / 2 + i * 2 * Math.PI / V));
                node1[i].setRadius(2);
            }
            for (int j = 0; j < V; j++) {
                vexname1.add(new Text(node[j].getCenterX() - 5, node[j].getCenterY() + 10, s1[j] + ""));
            }
            test.DFSTraverse(adjmatrix);
            if (test.eulerJudge() == true) {
                Scanner input = new Scanner(System.in);
                System.out.println("请输入trail的起点：");
                R = input.next();

                //trail的起点默认为a
                if (R == null) {
                    R = "a";
                }
                int r = 0;
                for (int i = 0; i < V; i++) {
                    if (R.equals(s1[i])) {
                        r = i;
                    }
                }
                changematrix = new int[V][V];
                for (int i = 0; i < V; i++) {
                    for (int j = 0; j < V; j++) {
                        changematrix[i][j] = adjmatrix[i][j];

                    }

                }
                trail = s1[r] + "  ";
                eulerTour(r, changematrix);
                System.out.println("欧拉路径：" + trail);
//

                bpane.setCenter(pane);
                bpane.setBottom(pane1);
                pane.getChildren().add(new Text(185, 100, "原图"));
                pane.getChildren().add(new Text(575, 300, "欧拉路径: " + trail));
                pane.getChildren().addAll(vexname1);
                pane.getChildren().addAll(node);
                pane.getChildren().addAll(line);
//
                Scene scene = new Scene(bpane, 800, 600);
                primaryStage.setTitle("图连通性判断示例");
                primaryStage.setScene(scene);
                primaryStage.show();
            }
        } else {
            System.out.println("此图没有欧拉回路");
        }
    }

    //先找一条边 判断是否为割边 是找其他 如果都是割边 走第一条 然后新图删除这条边
    public int eulerTour(int vex, int[][] euler) {
        int[][] et = new int[euler.length][euler.length];
        for (int k = 0; k < et.length; k++) {
            //输出更改后的矩阵
            for (int j = 0; j < et.length; j++) {
                et[k][j] = euler[k][j];
            }
        }
        int counter = 0;
        ;
        if (et.length == 0) {
            return 0;
        }

        for (int j = 0; j < et.length; j++) {
            //有几条边
            if (et[vex][j] == 1) {
                counter++;
            }
        }
        boolean[] vexcut = new boolean[counter];
        int ctr = 0;
        boolean flag = false;
        //有没有边不是割边
        int nocutnum = 0;
        int cutnum1 = 0;
        for (int j = 0; j < et.length; j++) {
            //这几条是不是割边
            if (et[vex][j] == 1) {
                vexcut[ctr] = cutedge(vex, j, et);
                if (vexcut[ctr] == false) {
                    flag = true;
                    //存在一条边不是割边
                    nocutnum = j;
                } else {
                    cutnum1 = j;
                }
                ctr++;
            }
        }
        if (flag == true) {
            trail += vexname.get(nocutnum).getText() + "  ";
            et[vex][nocutnum] = 0;
            et[nocutnum][vex] = 0;

            int[] degree = new int[et.length];
            //去除孤立点
            for (int k = 0; k < et.length; k++) {
                for (int k1 = 0; k1 < et.length; k1++) {
                    degree[k] += et[k][k1];
                }
            }
            for (int k = 0; k < degree.length; k++) {
                if (degree[k] == 0) {
                    if (k < vexname.size()) {
                        vexname.remove(vexname.get(k));
                    }

                    ArrayList<Integer> temp = new ArrayList<>();
                    //临时存储表
                    for (int k1 = 0; k1 < et.length; k1++) {
                        for (int k2 = 0; k2 < et.length; k2++) {
                            if (k1 != k && k2 != k) {
                                temp.add(et[k1][k2]);
                            }
                        }
                    }
                    et = new int[et.length - 1][et.length - 1];
                    if (et.length != 0) {
                        int count = 0;
                        for (int k1 = 0; k1 < et.length; k1++) {
                            for (int k2 = 0; k2 < et.length; k2++) {
                                et[k1][k2] = temp.get(count);
                                count++;
                            }
                        }
                    }

                    if (nocutnum > k) {
                        nocutnum = nocutnum - 1;
                    }
                }
            }
            eulerTour(nocutnum, et);
        }
        else {
            if (euler[vex][cutnum1] == 1) {
                trail += vexname.get(cutnum1).getText() + "  ";
                et[vex][cutnum1] = 0;
                et[cutnum1][vex] = 0;
                int[] degree = new int[et.length];
                //去除孤立点
                for (int k = 0; k < et.length; k++) {
                    for (int k1 = 0; k1 < et.length; k1++) {
                        degree[k] += et[k][k1];
                    }
                }
                for (int k = 0; k < degree.length; k++) {
                    if (degree[k] == 0) {
                        if (k < vexname.size()) {
                            vexname.remove(vexname.get(k));
                        }
                        ArrayList<Integer> temp = new ArrayList<>();
                        //临时存储表
                        for (int k1 = 0; k1 < et.length; k1++) {
                            for (int k2 = 0; k2 < et.length; k2++) {
                                if (k1 != k && k2 != k) {
                                    temp.add(et[k1][k2]);
                                }
                            }
                        }
                        et = new int[et.length - 1][et.length - 1];
                        if (et.length != 0) {
                            int count = 0;
                            for (int k1 = 0; k1 < et.length; k1++) {
                                for (int k2 = 0; k2 < et.length; k2++) {
                                    et[k1][k2] = temp.get(count);
                                    count++;
                                }
                            }
                        }

                        if (cutnum1 > k) {
                            cutnum1 = cutnum1 - 1;
                        }
                    }
                }
                eulerTour(cutnum1, et);
            }
        }

        return 1;
    }

    private void DFSTraverse(int[][] dtest) {
        for (int k = 0; k < dtest.length; k++) {
            verx[k] = false;
        }
        for (int i = 0; i < dtest.length; i++) {
            if (verx[i] == false) {
                DFS(i, dtest);
            }
        }
    }

    private void DFS(int i, int[][] test) {
        verx[i] = true;
        for (int j = 0; j < test.length; j++) {
            if (test[i][j] == 1 && verx[j] == false) {
                line1.add(new Line(20, 10, 30, 40));
                DFS(j, test);
            }
        }
    }

    private boolean eulerJudge() {
        boolean euler = true;


        if (line1.size() != V - 1) {
            euler = false;
        }
        int[] degree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                degree[i] += adjmatrix[i][j];
            }
        }
        for (int i = 0; i < V; i++) {
            if (degree[i] % 2 != 0) {
                euler = false;
            }

        }

        return euler;
    }

    private void initial(int v) {
        adjmatrix = new int[v][v];
        for (int i = 0; i < v; i++) {
            for (int j = 0; j < v; j++) {
                adjmatrix[i][j] = 0;
            }
        }
    }

    void isMatrix() {
        boolean flag1 = true;

        if (this.V <= 0) {
            flag1 = false;
            System.out.print("顶点数必须为正整数");
            this.input();
        }
        //顶点数违法

        for (int i = 0; i < adjmatrix[0].length; i++) {
            for (int j = 1; j < adjmatrix[0].length; j++) {
                if (adjmatrix[i][j] < 0) {
                    System.out.println("矩阵中不可以存在负整数！");
                    flag1 = false;
                    this.input();
                }
            }
        }
        //矩阵违法

        for (int r = 0; r < adjmatrix[0].length; r++) {
            for (int c = 0; c < adjmatrix[0].length; c++) {
                if (adjmatrix[r][c] == adjmatrix[c][r]) {
                    continue;
                } else {
                    System.out.println("输入的矩阵非邻接矩阵");
                    flag1 = false;
                    this.input();
                }
            }
        }
        //矩阵违法

        boolean tellFlag = false;
        for (int i = 0; i < adjmatrix[0].length; i++) {
            for (int j = 0; j < adjmatrix[0].length; j++) {
                if (adjmatrix[i][j] >= 2) {
                    tellFlag = true;
                }
                if (i == j) {
                    if (adjmatrix[i][j] != 0) {
                        tellFlag = true;
                        System.out.println("非普通简单图，因为邻接矩阵的对角线非0");
                        flag1 = false;
                        this.input();
                    }
                }
            }
            if (tellFlag) {
                System.out.println("不是简单图");
                break;
            }
        }

        if (!tellFlag) {
            System.out.println("是简单图");
        }
        //判断是否为简单图

        if(!flag1){
            flag=false;
        }

        if (flag1) {
            int edgesDisplayNum = (V * (V - 1)) / 2;
            int[] edges;
            edges = new int[edgesDisplayNum];
            int count = 0;
            for (int i = 1; i < V; i++) {
                for (int j = 0; j < i; j++) {
                    edges[count] = adjmatrix[i][j];
                    count++;
                }
            }
            int count1 = 0;
            for (int i = 1; i < V; i++) {
                for (int j = 0; j < i; j++) {
                    System.out.println((char) (97 + i) + " - " + (char) (97 + j) + " has " + edges[count1] + " edges.");
                    count1++;
                }
            }
            //输出每条边的边数
        }

    }

    void input() {
        Scanner input = new Scanner(System.in);
        System.out.print("请输入顶点数:");
        V = input.nextInt();
        System.out.println("请输入顶点:(以逗号为间隔)");
        s1 = (new Scanner(System.in)).nextLine().split(",");
        test.initial(V);
        System.out.println("请输入邻接矩阵:");
        for (int i = 0; i < V; i++) {
            if (i >= 1) {
                System.out.print("");
            }
            for (int j = 0; j < V; j++) {
                adjmatrix[i][j] = input.nextInt();
            }
        }
    }

    public boolean cutedge(int i, int j, int[][] cutmatrix) {
        boolean flag = true;
        int[][] copy = new int[cutmatrix.length][cutmatrix.length];
        for (int k = 0; k < cutmatrix.length; k++) {
            for (int k1 = 0; k1 < cutmatrix.length; k1++) {
                copy[k][k1] = cutmatrix[k][k1];
            }
        }
        copy[i][j] = 0;
        copy[j][i] = 0;
        line1.removeAll(line1);
        //清除线集合
        DFSTraverse(copy);
        if (line1.size() == copy.length - 1) {
            flag = false;
        }
        return flag;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
