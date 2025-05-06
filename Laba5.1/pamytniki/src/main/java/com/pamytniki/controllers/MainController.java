package com.pamytniki.controllers;

import com.pamytniki.model.Order;
import com.pamytniki.db.Database;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class MainController {
    @FXML private TableView<Order> ordersTable;
    @FXML private TableColumn<Order, String> idColumn;
    @FXML private TableColumn<Order, String> clientColumn;
    @FXML private TableColumn<Order, String> dateColumn;
    @FXML private TableColumn<Order, String> statusColumn;
    @FXML private TableColumn<Order, Double> priceColumn;

    @FXML private TextField searchField;
    @FXML private Button addButton;
    @FXML private Button refreshButton;

    private ObservableList<Order> ordersData = FXCollections.observableArrayList();

    @FXML
    private void initialize() {
        // Настройка таблицы
        idColumn.setCellValueFactory(new PropertyValueFactory<>("orderId"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("clientId"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("orderDate"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("totalPrice"));

        // Загрузка данных
        refreshData();

        // Обработчики событий
        addButton.setOnAction(event -> openNewOrderWindow());
        refreshButton.setOnAction(event -> refreshData());

        // Обработчик поиска (срабатывает при нажатии Enter)
        searchField.setOnAction(event -> handleSearch());

        // ИЛИ поиск при изменении текста (реагирует на каждый ввод)
        searchField.textProperty().addListener((observable, oldValue, newValue) -> handleSearch());
    }

    private void refreshData() {
        ordersData.clear();
        ordersData.addAll(Database.getAllOrders());
        ordersTable.setItems(ordersData);
    }

    private void openNewOrderWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/pamytniki/views/order.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Новый заказ");
            stage.setScene(new Scene(root));
            stage.showAndWait();

            refreshData(); // Обновляем данные после закрытия окна
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void handleSearch() {
        String searchText = searchField.getText().trim().toLowerCase();

        if (searchText.isEmpty()) {
            refreshData(); // Если поле пустое, показываем все данные
            return;
        }

        // Фильтрация данных
        ObservableList<Order> filteredData = ordersData.filtered(order ->
                order.getOrderId().toLowerCase().contains(searchText) ||
                        order.getClientId().toLowerCase().contains(searchText) ||
                        order.getStatus().toLowerCase().contains(searchText)
        );

        ordersTable.setItems(filteredData);
    }
}