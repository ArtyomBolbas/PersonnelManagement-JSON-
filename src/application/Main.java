package application;

import javafx.application.Application;

import javafx.stage.Stage;
import view.View;

public class Main extends Application {

	private static View view = new View();

	@Override
	public void start(Stage primaryStage) {
		view.showMainWindow(primaryStage);
	}

	public static void main(String[] args) {
		launch(args);
	}

	public static View getView() {
		return view;
	}
}
