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

public class DrawAGraph extends Application {
    int[][] vertices;
    static int[][] adjmatrix;
    static int vertexNumber = 0;
    private double centerX = 150;
    private double centerY = 150;
    private double len = 100;
    static DrawAGraph graph = new DrawAGraph();
    static boolean flag = false;

    @Override
    public void start(Stage primaryStage) {
        Scanner input = new Scanner(System.in);
        System.out.println("The number of vertex:");
        vertexNumber = input.nextInt();
        graph.initial(vertexNumber);
        System.out.println("The adjmatrix of your graph:");
        for (int i = 0; i < vertexNumber; i++) {
            if (i >= 1) {
                System.out.println();
            }
            for (int j = 0; j < vertexNumber; j++) {
                adjmatrix[i][j] = input.nextInt();
            }
        }
        //enter the matrix

        graph.judge();
        if (flag = true) {
            BorderPane bpane = new BorderPane();
            Pane p = new Pane();
            Pane p1 = new Pane();
            Circle[] node = new Circle[vertexNumber];
            for (int i = 0; i < vertexNumber; i++) {
                Circle c = new Circle(0, 0, 1);
                node[i] = c;
            }
            for (int i = 0; i < vertexNumber; i++) {
                node[i].setCenterX(centerX + len * Math.cos(Math.PI / 2 + 2 * i * Math.PI / vertexNumber));
                node[i].setCenterY(centerX + len * Math.sin(Math.PI / 2 + 2 * i * Math.PI / vertexNumber));
                node[i].setRadius(2);
            }
            ArrayList<Line> l = new ArrayList<>();
            for (int j = 0; j < vertexNumber; j++) {
                for (int k = 0; k < vertexNumber; k++) {
                    if (true) {
                        l.add(new Line(node[j].getCenterX(), node[j].getCenterY(), node[k].getCenterX(), node[k].getCenterY()));
                    }
                }
            }
            Text t = new Text(50, 50, "Degree:");
            ArrayList<Text> degreetext = new ArrayList<>();
            for (int i = 0; i < vertexNumber; i++) {
                int sumdegree = 0;
                for (int j = 0; j < vertexNumber; j++) {
                    sumdegree += adjmatrix[i][j];
                }
                degreetext.add(new Text(200, t.getLayoutY() + (i + 1) * 50, (char) (97 + i) + " 's degree is ：" + sumdegree));
            }
            ArrayList<Text> vexname = new ArrayList<>();
            for (int j = 0; j < vertexNumber; j++) {
                vexname.add(new Text(node[j].getCenterX(), node[j].getCenterY(), (char) (97 + j) + ""));
            }
            p1.getChildren().add(t);
            p1.getChildren().addAll(degreetext);
            bpane.setCenter(p);
            bpane.setTop(p1);
            p.getChildren().addAll(vexname);
            p.getChildren().addAll(node);
            p.getChildren().addAll(l);
            Scene scene = new Scene(bpane, 500, 500);
            primaryStage.setTitle("Draw a graph!");
            primaryStage.setScene(scene);
            primaryStage.show();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void initial(int vexnumber1) {
        adjmatrix = new int[vexnumber1][vexnumber1];
        for (int i = 0; i < vexnumber1; i++) {
            for (int j = 0; j < vexnumber1; j++) {
                adjmatrix[i][j] = 0;
            }
        }
    }
    //create the new matrix with entered numbers

    public void Graph() {
        Scanner input = new Scanner(System.in);
        System.out.println("The number of vertex:");
        vertexNumber = input.nextInt();
        graph.initial(vertexNumber);
        System.out.println("The adjmatrix of your graph:");
        for (int i = 0; i < vertexNumber; i++) {
            if (i >= 1) {
                System.out.println();
            }
            for (int j = 0; j < vertexNumber; j++) {
                adjmatrix[i][j] = input.nextInt();
            }
        }
        graph.judge();
    }
    //enter a new matrix

    public void judge() {
        if (vertexNumber <= 0) {
            System.out.println("The number of vertex is wrong!");
            graph.Graph();
            return;
        }

        for (int i = 0; i < adjmatrix[0].length; i++) {
            for (int j = 1; j < adjmatrix[0].length; j++) {
                if (adjmatrix[i][j] < 0) {
                    System.out.println("The matrix can not have negative integer!");
                    graph.Graph();
                }
            }
        }

        for (int r = 0; r < adjmatrix[0].length; r++) {
            for (int c = 0; c < adjmatrix[0].length; c++) {
                if (adjmatrix[r][c] == adjmatrix[c][r]) {
                    continue;
                } else {
                    System.out.println("The matrix is wrong!");
                    graph.Graph();
                }
            }
        }
        //tell whether the matrix has negative integer or not

        boolean tellFlag = false;
        for (int i = 0; i < adjmatrix[0].length; i++) {
            for (int j = 0; j < adjmatrix[0].length; j++) {
                if (adjmatrix[i][j] >= 2) {
                    tellFlag = true;
                }
                if (i == j) {
                    if (adjmatrix[i][j] != 0) {
                        tellFlag = true;
                        System.out.println("It's not a regular simple graph because the diagonal of the input adjacency matrix is not 0 !");
                        graph.Graph();
                    }
                }
            }
            if (tellFlag) {
                System.out.println("It's not a simple graph!");
                break;
            }
        }

        if (!tellFlag) {
            System.out.println("It's a simple graph!");
        }
        //tell whether the graph is a simple or not

        int edgesDisplayNum = (vertexNumber * (vertexNumber - 1)) / 2;
        int[] edges;
        edges = new int[edgesDisplayNum];
        int count = 0;
        for (int i = 1; i < vertexNumber; i++) {
            for (int j = 0; j < i; j++) {
                edges[count] = adjmatrix[i][j];
                count++;
            }
        }
        int count1 = 0;
        for (int i = 1; i < vertexNumber; i++) {
            for (int j = 0; j < i; j++) {
                System.out.println((char) (97 + i) + " - " + (char) (97 + j) + " has " + edges[count1] + " edges.");
                count1++;
            }
        }
        //display the edges' number

        System.out.println("The graph will be shown!");
        flag = true;

    }
}

