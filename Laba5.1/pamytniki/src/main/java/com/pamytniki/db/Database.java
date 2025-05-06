package com.pamytniki.db;

import com.pamytniki.model.Client;
import com.pamytniki.model.Order;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private static final String URL = "jdbc:postgresql://localhost:5432/Памятники";
    private static final String USER = "postgres";
    private static final String PASSWORD = "admin";
    private static final String SCHEMA = "Памятники";

    public static Connection connect() throws SQLException {
          Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
          conn.setSchema(SCHEMA);
          return conn;
    }

    // Получение всех клиентов
    public static List<Client> getAllClients() {
        List<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM \"Клиенты\"";

        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                clients.add(new Client(
                        rs.getString("код_клиента"),
                        rs.getString("фамилия_клиента"),
                        rs.getString("имя_клиента"),
                        rs.getString("отчество_клиента"),
                        rs.getString("почта_клиента"),
                        rs.getLong("номер_телефона_клиента")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return clients;
    }

    // Получение всех заказов
    public static List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM \"Заказы\"";

        try (Connection conn = connect()) {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                orders.add(new Order(
                        rs.getString("код_заказа"),
                        rs.getString("код_клиента"),
                        rs.getDate("дата_заказа"),
                        rs.getString("код_модели"),
                        rs.getString("статус"),
                        rs.getDouble("итоговая_стоимость")
                ));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }

    // Добавление нового заказа
    public static void addOrder(Order order) throws SQLException {
        String sql = "INSERT INTO \"Заказы\" (код_заказа, код_клиента, дата_заказа, код_модели, статус, итоговая_стоимость) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, order.getOrderId());
            pstmt.setString(2, order.getClientId());
            pstmt.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            pstmt.setString(4, order.getModelId());
            pstmt.setString(5, order.getStatus());
            pstmt.setDouble(6, order.getTotalPrice());

            pstmt.executeUpdate();
        }
    }
}