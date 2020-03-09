package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

import java.util.Random;

public class Boll extends Application {
    private Random random = new Random();
    private double DIAMETER = random.nextInt(150) + 50;

    private static final int BOARD_WIDTH = 800;
    private static final int BOARD_HEIGHT = 500;
    private static final int FPS = 60;

    private boolean closed;
    private GraphicsContext gc;

    private double x = 0;
    private double y = 0;
    private double dX = random.nextInt(10) + 1;
    private double dY = random.nextInt(10) + 1;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFxSample");
        Canvas canvas = new Canvas();
        canvas.setWidth(BOARD_WIDTH);
        canvas.setHeight(BOARD_HEIGHT);
        BorderPane group = new BorderPane(canvas);
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.show();

        gc = canvas.getGraphicsContext2D();
        new Thread(this::runMainGameLoopInThread).start();
    }

    @Override
    public void stop() {
        closed = true;
    }

    private void runMainGameLoopInThread() {
        while (!closed) {
            Platform.runLater(this::drawFrame);
            try {
                int pauseBetweenFramesMillis = 1000 / FPS;
                Thread.sleep(pauseBetweenFramesMillis);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void drawFrame() {
        gc.clearRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());

        gc.setFill(Color.RED);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);

        gc.fillOval(x, y, DIAMETER, DIAMETER);
        gc.strokeOval(x, y, DIAMETER, DIAMETER);

        if (x <= 0) {
            dX = Math.abs(dX);
        } else if (x + DIAMETER >= BOARD_WIDTH) {
            dX = -Math.abs(dX);
        }
        if (y <= 0) {
            dY = Math.abs(dY);
        } else if (y + DIAMETER >= BOARD_HEIGHT) {
            dY = -Math.abs(dY);
        }
        x += dX;
        y += dY;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
