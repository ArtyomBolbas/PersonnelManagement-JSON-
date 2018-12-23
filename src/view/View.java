package view;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class View {

	private static final String NAME_APP = " - HR Personnel management";
	private static final String MAIN_WINDOW = "Main window";
	private static final String EDIT_WINDOW = "Edit window";

	private static final String PATH_MAIN_WINDOW = "../view/mainWindow.fxml";
	private static final String PATH_EDIT_WINDOW = "../view/editWindow.fxml";

	private FXMLLoader loaderMainWindow;
	private FXMLLoader loaderEditWindow;

	Image image = new Image("/image/employees.jpg");

	public void showMainWindow(Stage primaryStage) {
		FXMLLoader loaderMainWindow = new FXMLLoader();
		this.loaderMainWindow = loaderMainWindow;
		loaderMainWindow.setLocation(getClass().getResource(PATH_MAIN_WINDOW));
		try {
			loaderMainWindow.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Parent root = loaderMainWindow.getRoot();
		primaryStage.setScene(new Scene(root));
		primaryStage.setResizable(false);
		primaryStage.setTitle(MAIN_WINDOW + NAME_APP);
		primaryStage.getIcons().add(image);
		primaryStage.show();
	}

	public void showEditWindow() {
		FXMLLoader loaderEditWindow = new FXMLLoader();
		this.loaderEditWindow = loaderEditWindow;
		Stage primaryStage = new Stage();
		loaderEditWindow.setLocation(getClass().getResource(PATH_EDIT_WINDOW));
		try {
			loaderEditWindow.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		Parent root = loaderEditWindow.getRoot();
		primaryStage.setScene(new Scene(root));
		primaryStage.initModality(Modality.APPLICATION_MODAL);
		primaryStage.setResizable(false);
		primaryStage.setTitle(EDIT_WINDOW + NAME_APP);
		primaryStage.getIcons().add(image);
		primaryStage.show();
	}

	public FXMLLoader getLoaderMainWindow() {
		return loaderMainWindow;
	}

	public FXMLLoader getLoaderEditWindow() {
		return loaderEditWindow;
	}

}
