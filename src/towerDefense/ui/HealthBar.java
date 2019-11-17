package towerDefense.ui;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * HeathBar
 */
public class HealthBar {

    // 2 bar for the lost part and the current part
    private Rectangle current;
    private Rectangle lost;

    public HealthBar(Pane layer, double posX, double posY)
    {
        double height = 10;
        double width = 40;

        current = new Rectangle(posX, posY, width, height);
        current.setFill(Color.GREEN);
        lost = new Rectangle(posX, posY, width, height);
        lost.setFill(Color.RED);

        // add the rectangles to Pane for displaying
        layer.getChildren().addAll(lost, current);
    }

    public void updateCurrent(double posX, double posY)
    {
        current.setX(posX);
        current.setY(posY);
    }

    public void updateLost(double posX, double posY)
    {
        lost.setX(posX);
        lost.setY(posY);
    }

    public void update(double curHP, double maxHP, double posX, double posY)
    {
        // move the bar
        double percentage = curHP / maxHP;
        current.setWidth(40 * percentage);
        updateCurrent(posX - 20, posY - 20);
        updateLost(posX - 20, posY - 20);
    }

    public void destroy(Pane layer)
    {
        layer.getChildren().removeAll(lost, current);
    }
}