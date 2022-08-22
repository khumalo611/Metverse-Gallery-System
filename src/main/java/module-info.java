module com.example.design_3 {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;
    requires java.desktop;

    opens com.example.design_3 to javafx.fxml;
    exports com.example.design_3;
}