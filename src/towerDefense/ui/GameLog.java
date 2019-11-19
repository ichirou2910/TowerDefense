package towerDefense.ui;

import javafx.geometry.VPos;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import towerDefense.Config;

import java.util.ArrayList;

public class GameLog {

    private ArrayList<String> messages = new ArrayList<>(Config.LOG_SIZE);
    private Text displayMsg;

    public GameLog(Pane layer) {
        displayMsg = new Text(30, 750, "");
        displayMsg.setTextOrigin(VPos.TOP);
        displayMsg.setStroke(Color.GRAY);
        displayMsg.setFill(Color.GRAY);
        displayMsg.setFont(Config.UI_FONT);
        layer.getChildren().add(displayMsg);
        messages.add("> Welcome to Starsector");
    }

    public void update() {
        String msg = "";
        for (int i = 0; i < messages.size(); i++) {
            msg = msg + messages.get(i) + "\n";
        }
        displayMsg.setText(msg);
    }

    public void addMessage(String msg) {
        messages.add(msg);
    }
}
