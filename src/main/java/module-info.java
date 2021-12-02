module kingdoms.kingdoms {
    requires javafx.controls;
    requires javafx.fxml;


    opens kingdoms.kingdoms to javafx.fxml;
    exports kingdoms.kingdoms;
}