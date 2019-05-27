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

public class MinSpanTree extends Application {

    private double centerX = 150;
    private double centerY = 150;
    private double centerX1 = 450;
    private double centerY1 = 150;
    private double len = 100;
    static int[][] result;
    static int[][] weightResult;

    public static void PRIM(int[][] graph, int start, int n) {
        result = new int[n][n];
        weightResult=new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                result[i][j] = 0;
            }
        }

        int[][] mins = new int[n][2];
        for (int i = 0; i < n; i++) {

            if (i == start) {
                mins[i][0] = -1;
                mins[i][1] = 0;
            } else if (graph[start][i] != -1) {
                mins[i][0] = start;
                mins[i][1] = graph[start][i];
            } else {
                mins[i][0] = -1;
                mins[i][1] = Integer.MAX_VALUE;
            }
        }
        for (int i = 0; i < n - 1; i++) {
            int minV = -1, minW = Integer.MAX_VALUE;
            for (int j = 0; j < n; j++) {

                if (mins[j][1] != 0 && minW > mins[j][1]) {
                    minW = mins[j][1];
                    minV = j;
                }
            }
            mins[minV][1] = 0;
            System.out.println("最小生成树的第" + (i + 1) + "条最小边=<" + (mins[minV][0] + 1) + "," + (minV + 1) + ">，权重=" + minW);
            result[mins[minV][0]][minV] = 1;
            weightResult[mins[minV][0]][minV] = minW;

            for (int j = 0; j < n; j++) {
                if (mins[j][1] != 0) {
                    if (graph[minV][j] != -1 && graph[minV][j] < mins[j][1]) {
                        mins[j][0] = minV;
                        mins[j][1] = graph[minV][j];
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (result[i][j] == 1) {
                    result[j][i] = 1;
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (weightResult[i][j] !=0) {
                    weightResult[j][i] = weightResult[i][j];
                }
            }
        }

        System.out.println("********************************");
        System.out.println("Weight matrix of the tree is: ");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(weightResult[i][j]+" ");
            }
            System.out.println();
        }

    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Scanner input = new Scanner(System.in);
        System.out.println("The number of vertex :");
        int vertexNumber = input.nextInt();
        int[][] adjmatrix = new int[vertexNumber][vertexNumber];
        System.out.println("The weighted adjmatrix is(0 represent no edge) :");
        for (int i = 0; i < vertexNumber; i++) {
            if (i >= 1) {
                System.out.println();
            }
            for (int j = 0; j < vertexNumber; j++) {
                adjmatrix[i][j] = input.nextInt();
            }
        }

        int[][] tree = new int[vertexNumber][vertexNumber];
        for (int i = 0; i < vertexNumber; i++) {
            for (int j = 0; j < vertexNumber; j++) {
                tree[i][j] = adjmatrix[i][j];
            }
        }
        for (int i = 0; i < vertexNumber; i++) {
            for (int j = 0; j < vertexNumber; j++) {
                if (tree[i][j] == 0) {
                    tree[i][j] = -1;
                }
            }
        }

        MinSpanTree.PRIM(tree, vertexNumber - vertexNumber, vertexNumber);

        int[][] adjmatrix1 = MinSpanTree.result;

        BorderPane bpane = new BorderPane();
        Pane p = new Pane();

        Circle[] node = new Circle[vertexNumber];

        for (int i = 0; i < vertexNumber; i++) {
            Circle c = new Circle(0, 0, 1);
            node[i] = c;
        }
        for (int i = 0; i < vertexNumber; i++) {
            node[i].setCenterX(centerX + len * Math.cos(Math.PI / 2 + 2 * i * Math.PI / vertexNumber));
            node[i].setCenterY(centerY + len * Math.sin(Math.PI / 2 + 2 * i * Math.PI / vertexNumber));
            node[i].setRadius(2);
        }

        ArrayList<Line> l = new ArrayList<>();

        for (int j = 0; j < vertexNumber; j++) {
            for (int k = 0; k < vertexNumber; k++) {
                if (adjmatrix[j][k] != 0) {
                    l.add(new Line(node[j].getCenterX(), node[j].getCenterY(), node[k].getCenterX(), node[k].getCenterY()));
                }
            }
        }

        ArrayList<Text> vexname = new ArrayList<>();

        for (int j = 0; j < vertexNumber; j++) {
            vexname.add(new Text(node[j].getCenterX(), node[j].getCenterY(), (j + 1) + ""));
        }
        //------ 2 --------
        Circle[] node1 = new Circle[vertexNumber];

        for (int i = 0; i < vertexNumber; i++) {
            Circle c1 = new Circle(0, 0, 1);
            node1[i] = c1;
        }
        for (int i = 0; i < vertexNumber; i++) {
            node1[i].setCenterX(centerX1 + len * Math.cos(Math.PI / 2 + 2 * i * Math.PI / vertexNumber));
            node1[i].setCenterY(centerY1 + len * Math.sin(Math.PI / 2 + 2 * i * Math.PI / vertexNumber));
            node1[i].setRadius(2);
        }

        ArrayList<Line> l1 = new ArrayList<>();

        for (int j = 0; j < vertexNumber; j++) {
            for (int k = 0; k < vertexNumber; k++) {
                if (adjmatrix1[j][k] != 0) {
                    l1.add(new Line(node1[j].getCenterX(), node1[j].getCenterY(), node1[k].getCenterX(), node1[k].getCenterY()));
                }
            }
        }

        ArrayList<Text> vexname1 = new ArrayList<>();

        for (int j = 0; j < vertexNumber; j++) {
            vexname1.add(new Text(node1[j].getCenterX(), node1[j].getCenterY(), (j + 1) + ""));
        }

        bpane.setCenter(p);
        p.getChildren().addAll(vexname);
        p.getChildren().addAll(node);
        p.getChildren().addAll(l);
        p.getChildren().addAll(vexname1);
        p.getChildren().addAll(node1);
        p.getChildren().addAll(l1);
        Scene scene = new Scene(bpane, 600, 300);
        primaryStage.setTitle("MinSpanTree");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
