package Lab24_FullyFunctional;

import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.util.stream.Stream;

/**
 * A class to introduce Java 8 lambdas and streams.
 * @author Your name here
 */
public class Circles extends VBox {
    public static final int ROWS = 4;
    public static final int COLS = 5;
    public static final int CELL_SIZE = 100;

    private Pane canvas;
    private Button starter;

    private int row;
    private int col;

    Circles() {
        setAlignment(Pos.CENTER);
        
        canvas = new Pane();
        canvas.setPrefSize(COLS * CELL_SIZE, ROWS * CELL_SIZE);
        
        starter = new Button("Circles");

        getChildren().addAll(canvas, starter);
        
        addButtonHandler();  // You must write

        row = 0;
        col = 0;
    }

    /**
     * This method adds the handler to the button that gives
     * this application its behavior.
     */
    private void addButtonHandler() {
        starter.setOnAction(e -> {
            canvas.getChildren().clear();
            addAllRowsToCanvas(makeAllRows());
        });
    }

    private void addToCanvas(Circle circle) {
        double fromX = canvas.getWidth();
        double fromY = canvas.getHeight();
        double toX = CELL_SIZE * (col + 0.5);
        double toY = CELL_SIZE * (row + 0.5);

        circle.setCenterX(fromX);
        circle.setCenterY(fromY);
        circle.setFill(new Color(Math.random(), Math.random(), Math.random(), 1.0));

        canvas.getChildren().add(circle);

        TranslateTransition translation = new TranslateTransition();
        translation.setNode(circle);
        translation.setByX(toX - fromX);
        translation.setByY(toY - fromY);
        translation.play();

        double scaleFactor = Math.random() * 0.4 + 0.35;
        ScaleTransition scaler = new ScaleTransition(Duration.seconds(Math.random() * 0.75 + 0.25));
        scaler.setNode(circle);
        scaler.setByX(scaleFactor);
        scaler.setByY(scaleFactor);
        scaler.setCycleCount(Animation.INDEFINITE);
        scaler.setAutoReverse(true);
        scaler.play();
    }

    private Stream<Circle> makeRow() {
        return Stream.generate(() -> new Circle(CELL_SIZE / 4)).limit(COLS);
    }

    private void addRowToCanvas(Stream<Circle> circles) {
        col = 0;
        circles.forEach(c -> {
            addToCanvas(c);
            col++;
        });
    }

    private Stream<Stream<Circle>> makeAllRows() {
        return Stream.generate(this::makeRow).limit(ROWS);
    }

    private void addAllRowsToCanvas(Stream<Stream<Circle>> circles) {
        row = 0;
        circles.forEach(r -> {
            addRowToCanvas(r);
            row++;
        });
    }
}
