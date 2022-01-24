module TorqueScout {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.texastorque to javafx.fxml;
    exports org.texastorque;
}