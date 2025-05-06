package com.pamytniki.controllers;

import com.pamytniki.model.Order;
import com.pamytniki.db.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.Date;
import java.time.LocalDate;

public class OrderController {
    @FXML private TextField orderIdField;
    @FXML private ComboBox<String> clientComboBox;
    @FXML private DatePicker orderDatePicker;
    @FXML private ComboBox<String> modelComboBox;
    @FXML private TextField totalPriceField;
    @FXML private Button saveButton;
    @FXML private Button cancelButton;

    private ObservableList<String> clients = FXCollections.observableArrayList();
    private ObservableList<String> models = FXCollections.observableArrayList("20", "22", "25", "28");

    @FXML
    private void initialize() {
        // Заполняем ComboBox клиентами
        Database.getAllClients().forEach(client ->
                clients.add(client.getClientId() + " - " + client.getLastName()));
        clientComboBox.setItems(clients);

        // Заполняем ComboBox моделями
        modelComboBox.setItems(models);

        // Устанавливаем текущую дату
        orderDatePicker.setValue(LocalDate.now());

        // Обработчики событий
        saveButton.setOnAction(event -> saveOrder());
        cancelButton.setOnAction(event -> closeWindow());
    }

    private void saveOrder() {
        try {
            Order order = new Order(
                    orderIdField.getText(),
                    clientComboBox.getValue().split(" - ")[0], // Извлекаем ID клиента
                    Date.valueOf(orderDatePicker.getValue()),
                    modelComboBox.getValue(),
                    "Новый", // Статус по умолчанию
                    Double.parseDouble(totalPriceField.getText())
            );

            Database.addOrder(order);
            closeWindow();
        } catch (Exception e) {
            showAlert("Ошибка", "Проверьте введенные данные", e.getMessage());
        }
    }

    private void closeWindow() {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    private void showAlert(String title, String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}