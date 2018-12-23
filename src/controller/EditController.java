package controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Employee;

public class EditController {

	private ObservableList<Employee> employeeListEditCont;

	private MainWinController mainWinController = Main.getView().getLoaderMainWindow().getController();

	private static String INCORRECTLY_FILLED_FIELD = "Неккоректно заполненно поле! ИМЯ и ФАМИЛИЯ (ТОЛЬКО БУКВЕННЫЕ СИМВОЛЫ (НЕ БОЛЕЕ 20) "
			+ "EMAIL (необходимо писать ВАЛИДНЫЙ АДРЕС ПОЧТЫ!)";

	private boolean isNewEmployee;

	@FXML
	private TextField surnameField;

	@FXML
	private TextField nameField;

	@FXML
	private TextField emailField;

	@FXML
	private Button saveButton;

	@FXML
	private Button cancelButton;

	@FXML
	void cancelButton(ActionEvent event) {
		cancelButton.getScene().getWindow().hide();
	}

	@FXML
	void saveButton(ActionEvent event) {
		if (validationCheck()) {
			if (isNewEmployee) {
				employeeListEditCont
						.add(new Employee(nameField.getText(), surnameField.getText(), emailField.getText()));
				isNewEmployee = false;
			} else {
				// строчки в низу меня как-то смущают, думаю можно и по интерессней написать, но
				// у меня чего-то другого - работоспособного не получилось((
				Employee employee = employeeListEditCont.get(mainWinController.getSelectIndex());
				employeeListEditCont.remove(mainWinController.getSelectIndex().intValue());
				employee.setName(nameField.getText());
				employee.setSurname(surnameField.getText());
				employee.setEmail(emailField.getText());
				employeeListEditCont.add(mainWinController.getSelectIndex(), employee);
				//
			}
			saveButton.getScene().getWindow().hide();
			mainWinController.updateTable();
			mainWinController.getTableView().refresh();
			clearField();
		} else {
			Message.showErrorDialog(INCORRECTLY_FILLED_FIELD, null);
		}
	}

	@FXML
	void initialize() {
		if (mainWinController.getEmployeeListMainCont() == null) {
			employeeListEditCont = FXCollections.observableArrayList();
		} else {
			employeeListEditCont = mainWinController.getEmployeeListMainCont();
		}
	}

	/**
	 * проверяем на валидность введеные пользователем данные
	 */
	private boolean validationCheck() {
		if (checkNameAndSurname(nameField.getText()) && checkNameAndSurname(surnameField.getText())
				&& checkEmail(emailField.getText())) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param (String args)
	 * @return возвращает true в случае если пользователь ввел валидные данные,
	 *         иначе false Проверяет на корректность ввода именя и фамилии.
	 *         Валидация успешна, в случае если пользователь ввел только буквенные
	 *         символы и символов не более 20
	 */
	public static boolean checkNameAndSurname(String args) {
		Pattern p = Pattern.compile("^[A-Za-zА-Яа-я]{1,20}$");
		Matcher m = p.matcher(args);
		return m.matches();
	}

	/**
	 * @param (String args)
	 * @return возвращает true в случае если пользователь ввел валидные данные,
	 *         иначе false Проверяет на корректность email. Валидация успешна, в
	 *         случае если пользователь ввел корректный почтовый адрес
	 */
	public static boolean checkEmail(String args) {
		Pattern p = Pattern.compile("^[A-Za-zА-Яа-я]{1,30}\\@.{1,10}\\..{1,10}$");
		Matcher m = p.matcher(args);
		return m.matches();
	}

	/**
	 * в методе saveButton(ActionEvent event) укащывает, что будем добавлять нового
	 */
	public void getNewEmployee() {
		isNewEmployee = true;
	}

	/**
	 * получаем сущность из списка (employeeListEditCont) и запоняем свойствами этой
	 * сущности поля
	 */
	public void getSelectedValue() {
		nameField.setText(employeeListEditCont.get(mainWinController.getSelectIndex()).getName());
		surnameField.setText(employeeListEditCont.get(mainWinController.getSelectIndex()).getSurname());
		emailField.setText(employeeListEditCont.get(mainWinController.getSelectIndex()).getEmail());
	}

	/**
	 * Метод очищает поля
	 */
	private void clearField() {
		nameField.clear();
		surnameField.clear();
		emailField.clear();
	}

	public ObservableList<Employee> getEmployeeListEditCont() {
		return employeeListEditCont;
	}

	public void setSurnameField(TextField surnameField) {
		this.surnameField = surnameField;
	}

	public void setNameField(TextField nameField) {
		this.nameField = nameField;
	}

	public void setEmailField(TextField emailField) {
		this.emailField = emailField;
	}

}
