package controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import com.google.gson.Gson;

import application.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Employee;
import model.ListEmployyeForJSON;

public class MainWinController {

	private ObservableList<Employee> employeeListMainCont = FXCollections.observableArrayList();

	private static String NOT_SELECTED = "Не выбранна строка в таблице или таблица пуста!";
	private static String ERROR_TO_WRITE_JSON = "Ошибка во время записи в json файл";

	private EditController editController;

	private Gson json = new Gson();
	private File file = new File("info.json");

	@FXML
	private TableView<Employee> tableView;

	@FXML
	private TableColumn<Employee, String> empNameCol;

	@FXML
	private TableColumn<Employee, String> empSurnameCol;

	@FXML
	private TableColumn<Employee, String> empEMailCol;

	/**
	 * номер выбранного индекса в таблице, если ни какой индекс не выбран, то
	 * значение null
	 */
	private Integer selectIndex;

	/**
	 * условно - индекс, который отсутствует в таблице. Если в таблице что-то
	 * выбрали, то это что-то априоре больше, чем -1 (по этому индексу узнаем -
	 * выбрали что-то в таблце или нет)
	 */
	private int missingInd = -1;

	@FXML
	void add(ActionEvent event) {
		Main.getView().showEditWindow();
		editController = Main.getView().getLoaderEditWindow().getController();
		editController.getNewEmployee();
	}

	@FXML
	void edit(ActionEvent event) {
		selectIndex = getTableSelect();
		if (selectIndex != null) {
			Main.getView().showEditWindow();
			editController = Main.getView().getLoaderEditWindow().getController();
			editController.getSelectedValue();
		} else {
			Message.showErrorDialog(NOT_SELECTED, null);
		}
	}

	@FXML
	void remove(ActionEvent event) {
		employeeListMainCont.remove(tableView.getSelectionModel().getSelectedItem());
		saveToJson();
	}

	@FXML
	void initialize() {
		empNameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
		empSurnameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("surname"));
		empEMailCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("email"));
		tableView.setItems(employeeListMainCont);
	}

	/**
	 * обновление таблицы
	 */
	public void updateTable() {
		employeeListMainCont = editController.getEmployeeListEditCont();
		tableView.setItems(employeeListMainCont);
		saveToJson();
	}

	/**
	 * сохраняем иформацию в файл json
	 */
	private void saveToJson() {
		String jsonStr = json.toJson(new ListEmployyeForJSON(employeeListMainCont));
		try (PrintWriter printWriter = new PrintWriter(new FileWriter(file))) {
			printWriter.println(jsonStr);
		} catch (IOException e) {
			Message.showErrorDialog(ERROR_TO_WRITE_JSON, null);
			e.printStackTrace();
		}

	}

	/**
	 * @return номер выбранной строки, либо null
	 */
	public Integer getTableSelect() {
		int indexRow = tableView.getSelectionModel().getSelectedIndex();
		return (indexRow > missingInd) ? indexRow : null;
	}

	public ObservableList<Employee> getEmployeeListMainCont() {
		return employeeListMainCont;
	}

	public void setEmployeeListMainCont(ObservableList<Employee> employeeListMainCont) {
		this.employeeListMainCont = employeeListMainCont;
	}

	public Integer getSelectIndex() {
		return selectIndex;
	}

	public TableView<Employee> getTableView() {
		return tableView;
	}

}
