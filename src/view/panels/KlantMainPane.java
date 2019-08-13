package view.panels;

import javafx.scene.layout.BorderPane;

import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class KlantMainPane extends BorderPane {
    public KlantMainPane(KlantPane klantPane) {
        TabPane tabPane = new TabPane();
        Tab klantTab = new Tab("Rekening",klantPane);
        tabPane.getTabs().add(klantTab);
        this.setCenter(tabPane);
    }
}