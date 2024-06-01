module zad3.demo {
    requires javafx.controls;
    requires javafx.fxml;


    opens zad3.demo to javafx.fxml;
    exports zad3.demo;
}