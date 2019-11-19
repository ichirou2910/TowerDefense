package towerDefense.ui;

import javafx.geometry.VPos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import towerDefense.Config;

import java.util.LinkedList;
import java.util.Queue;

public class GameLog {

    private Queue<String> messages = new LinkedList<>();
    private int size = 1;
    private Text displayMsg;

    public GameLog(Pane layer) {
        displayMsg = new Text(30, 750, "");
        displayMsg.setTextOrigin(VPos.TOP);
        displayMsg.setStroke(Config.LOG_COLOR);
        displayMsg.setFill(Config.LOG_COLOR);
        displayMsg.setFont(Config.LOG_FONT);
        layer.getChildren().add(displayMsg);
        messages.add("> Welcome to Starsector");
    }

    public void update() {
        String msg = "";
        for (String s : messages) {
            msg = msg + s + "\n";
        }
        displayMsg.setText(msg);
    }

    public void addMessage(String msg) {

        if (size >= Config.LOG_SIZE) {
            messages.poll();
            size--;
        }
        messages.add(msg);
        size++;
    }
}
