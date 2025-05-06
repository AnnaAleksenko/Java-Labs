module com.pamytniki {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.pamytniki.controllers to javafx.fxml;
    opens com.pamytniki.model to javafx.base;
    exports com.pamytniki;
    exports com.pamytniki.controllers;
    exports com.pamytniki.db;
}