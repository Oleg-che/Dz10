package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Boll extends Application {

    private static final int BOARD_WIDTH = 800;
    private static final int BOARD_HEIGHT = 500;
    private static final int FPS = 60;

    private boolean closed;
    private GraphicsContext gc;

    private int x = 0;
    private int y = 0;
    private double DIAMETER = 0;

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
                int pauseBetweenFramesMillis = 500 / FPS;
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

        DIAMETER = 50;
        gc.fillOval(x, y, DIAMETER, DIAMETER);
        gc.strokeOval(x, y, DIAMETER, DIAMETER);

        if (x == (BOARD_WIDTH - DIAMETER)) {
            for (int i = x; i < 0 ; i++) {
                x -= 3;
            }
        } else if (y == (BOARD_HEIGHT - DIAMETER)) {
            for (int i = y; i < 0 ; i++) {
                y -=2;
            }
        } else {
            x += 3;
            y += 2;
        }
    }
    public static void main(String[] args) {
        launch(args);
    }
}
