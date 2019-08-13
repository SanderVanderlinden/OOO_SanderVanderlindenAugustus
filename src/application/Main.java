package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import model.controller.Controller;
import view.KassaView;
import view.KlantView;

public class Main extends Application {
	private Controller controller;

	@Override
	public void start(Stage primaryStage) {
		controller = new Controller();
		controller.start(controller);

		//KassaView kassaView = new KassaView(controller);
		//KlantView klantView = new KlantView();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
