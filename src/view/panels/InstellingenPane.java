package view.panels;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.controller.Controller;

import javax.swing.*;

public class InstellingenPane extends GridPane {

    private final ComboBox<Object> dataSourceField;
    private final Button btnOK;
    private Controller controller;

    public InstellingenPane(Controller controller) {
        setController(controller);

        this.add(new Label("Data source:"), 0, 3, 1, 1);
        dataSourceField = new ComboBox<>(FXCollections.observableArrayList(controller.getDataSources()));
//        dataSourceField.getSelectionModel().select(controller.getDataSource());
        this.add(dataSourceField, 1, 3, 1, 1);

        btnOK = new Button("Save");
        btnOK.isDefaultButton();
        btnOK.setOnAction(new InstellingenPane.ButtonHandler());
        this.add(btnOK, 1, 6, 1, 1);
    }

    private void setController(Controller controller) {
        if (controller == null) throw new IllegalArgumentException("Controller cannot be empty");
        this.controller = controller;
    }

    private class ButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource().equals(btnOK)) {
                try {
                    controller.setDataSource(dataSourceField.getSelectionModel().getSelectedItem());
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
        }
    }
}
