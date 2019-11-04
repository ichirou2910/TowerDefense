module TowerDefense {
    requires javafx.fxml;
    requires javafx.controls;

    exports towerDefense;
    opens towerDefense to javafx.graphics;
}