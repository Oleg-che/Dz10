package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello JavaFx");
        Canvas canvas = new Canvas();
        canvas.setWidth(800);
        canvas.setHeight(500);
        BorderPane group = new BorderPane(canvas);
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.show();

        GraphicsContext gc = canvas.getGraphicsContext2D();
        test(gc);
    }
    private void test(GraphicsContext gc) {
        double[] arr = {280, 570, 425};
        double[] arr2 = {280, 280, 180};
        gc.setFill(Color.GRAY);
        gc.setStroke(Color.BEIGE);
        gc.fillRoundRect(300, 280, 250, 150, 10, 10);
        gc.setFill(Color.BROWN);
        gc.fillPolygon(arr, arr2, 3);
        gc.setFill(Color.BEIGE);
        gc.fillOval(325, 305, 70, 70);
        gc.setFill(Color.BLUE);
        gc.fillOval(330, 310, 60, 60);
        gc.strokeLine(360, 310, 360,370);
        gc.strokeLine(330, 340, 390,340);
        gc.setFill(Color.GREEN);
        gc.fillRoundRect(450, 330, 60, 100, 10, 10);
        gc.setFill(Color.SILVER);
        gc.fillOval(490, 375, 8, 15);
        gc.setStroke(Color.ORANGE);
        gc.setLineWidth(2);
        gc.strokeText("W E L C O M E !!!", 380, 450);
        gc.setFill(Color.YELLOW);
        gc.fillOval(600, 50 , 150, 150);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
