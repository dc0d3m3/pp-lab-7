module zad1.zad2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens zad1.zad2 to javafx.fxml;
    exports zad1.zad2;
}