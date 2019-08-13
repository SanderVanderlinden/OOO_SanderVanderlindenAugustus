package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.controller.Controller;
import view.panels.ArtikelOverviewPane;
import view.panels.KassaMainPane;
import view.panels.KlantMainPane;
import view.panels.KlantPane;

public class KlantView {
	Controller controller;
	private Stage stage = new Stage();

	public KlantView(){

	}

	public void start(Controller controller) {
		this.controller = controller;

		try {
			KlantPane klantPane = new KlantPane(controller, controller.getRekeningData());

			stage.setTitle("KLANT VIEW");
			stage.setResizable(false);
			stage.setX(775);
			stage.setY(20);
			Group root = new Group();
			Scene scene = new Scene(root, 500, 500);
			BorderPane borderPane = new KlantMainPane(klantPane);
			borderPane.prefHeightProperty().bind(scene.heightProperty());
			borderPane.prefWidthProperty().bind(scene.widthProperty());
			root.getChildren().add(borderPane);
			stage.setScene(scene);
			stage.sizeToScene();
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
