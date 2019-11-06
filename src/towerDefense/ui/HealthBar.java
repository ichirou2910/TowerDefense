package towerDefense.ui;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * HeathBar
 */
public class HealthBar {

    Rectangle current = new Rectangle();
    Rectangle lost = new Rectangle();
    
    public HealthBar() {}

    public HealthBar(Pane layer)
    {
        double height = 10;
        double width = 40;

        double x = 0.0;
        double y = 0.0;

        current = new Rectangle(x, y, width, height);
        current.setFill(Color.GREEN);
        lost = new Rectangle(x, y, width, height);

        layer.getChildren().add(current);
    }
}