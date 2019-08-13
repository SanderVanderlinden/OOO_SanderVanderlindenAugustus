package view;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import model.controller.Controller;
import view.panels.ArtikelOverviewPane;
import view.panels.InstellingenPane;
import view.panels.KassaMainPane;
import view.panels.KassaPane;

public class KassaView {
	Controller controller;
	private Stage stage = new Stage();

	public KassaView(){

	}


	public void start(Controller controller) {
		this.controller = controller;

		try {
			KassaPane kassaPane = new KassaPane(controller);
			ArtikelOverviewPane artikelOverviewPane = new ArtikelOverviewPane(controller);
			InstellingenPane instellingenPane = new InstellingenPane(controller);

			stage.setTitle("KASSA VIEW");
			stage.setResizable(false);
			stage.setX(20);
			stage.setY(20);
			Group root = new Group();
			Scene scene = new Scene(root, 750, 500);
			BorderPane borderPane = new KassaMainPane(artikelOverviewPane, instellingenPane, kassaPane);
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
