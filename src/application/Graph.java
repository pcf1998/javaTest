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

public class Graph extends Application {

    private double centerX = 150;
    private double centerY = 150;
    private double centerX1 = 450;
    private double centerY1 = 150;
    private double len = 100;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        int vertexNumber = 6;
        int[][] adjmatrix = {
                {0, 1, 1, 1, 0, 0},
                {1, 0, 1, 0, 1, 0},
                {1, 1, 0, 1, 1, 1},
                {1, 0, 1, 0, 0, 1},
                {0, 1, 1, 0, 0, 1},
                {0, 0, 1, 1, 1, 0}
        };


        int[][] adjmatrix1 = {
                {0, 1, 0, 1, 0, 0, 0},
                {1, 0, 1, 1, 1, 0, 0},
                {0, 1, 0, 0, 1, 0, 0},
                {1, 1, 0, 0, 1, 1, 0},
                {0, 1, 1, 1, 0, 1, 1},
                {0, 0, 0, 1, 1, 0, 1},
                {0, 0, 0, 0, 1, 1, 0}
        };

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
            vexname.add(new Text(node[j].getCenterX(), node[j].getCenterY(), (char) (65 + j) + ""));
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
            vexname1.add(new Text(node1[j].getCenterX(), node1[j].getCenterY(), (char) (65 + j) + ""));
        }

        bpane.setCenter(p);
        p.getChildren().addAll(vexname);
        p.getChildren().addAll(node);
        p.getChildren().addAll(l);
        p.getChildren().addAll(vexname1);
        p.getChildren().addAll(node1);
        p.getChildren().addAll(l1);
        Scene scene = new Scene(bpane, 600, 300);
        primaryStage.setTitle("Draw a graph!");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
