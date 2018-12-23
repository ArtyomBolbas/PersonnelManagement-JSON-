package controller;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Message {

	// private static final Logger LOG = Logger.getLogger(Dialogs.class);

	public static void showErrorDialog(String errorText, String ImagePath) {
		// LOG.debug("Запущен метод - showErrorDialog(); (), в классе - Dialogs");
		Alert alert = new Alert(AlertType.ERROR);
		if (ImagePath != null) {
			Image imageError = new Image(ImagePath);
			ImageView imageViewError = new ImageView(imageError);
			alert.setGraphic(imageViewError);
		}
		alert.setTitle("Ошибка");
		alert.setHeaderText(null);
		alert.setContentText(errorText);
		alert.showAndWait();

	}

	public static void showWarningDialog(String warningText, String ImagePath) {
		// LOG.debug("Запущен метод - showWarningDialog(); (), в классе - Dialogs");
		Alert alert = new Alert(AlertType.WARNING);
		if (ImagePath != null) {
			Image imageWarning = new Image(ImagePath);
			ImageView imageViewWarning = new ImageView(imageWarning);
			alert.setGraphic(imageViewWarning);
		}
		alert.setTitle("Предупреждение");
		alert.setHeaderText(null);
		alert.setContentText(warningText);
		alert.showAndWait();
	}

	public static void showInformationDialog(String infoText, String ImagePath) {
		// LOG.debug("Запущен метод - showInformationDialog(); (), в классе - Dialogs");
		Alert alert = new Alert(AlertType.INFORMATION);
		if (ImagePath != null) {
			Image imageInfo = new Image(ImagePath);
			ImageView imageViewInfo = new ImageView(imageInfo);
			alert.setGraphic(imageViewInfo);
		}
		alert.setTitle("Информация");
		alert.setHeaderText(null);
		alert.setContentText(infoText);
		alert.showAndWait();
	}

}