package kingdoms.kingdoms;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

import static kingdoms.kingdoms.Main.ShowAddProvinceWindow;
import static kingdoms.kingdoms.Main.ShowAddRulerWindow;
import static kingdoms.kingdoms.Main.ShowAddDuchyWindow;
import static kingdoms.kingdoms.Main.ShowAddKingdomWindow;
import static kingdoms.kingdoms.Main.ShowAddEmpireWindow;


public class MainViewController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void addProvinceButtonClick() throws IOException {
        ShowAddProvinceWindow();
    }

    @FXML
    protected void addRulerButtonClick() throws IOException {
        ShowAddRulerWindow();
    }

    @FXML
    protected void addDuchyButtonClick() throws IOException {
        ShowAddDuchyWindow();
    }

    @FXML
    protected void addKingdomButtonClick() throws IOException {
        ShowAddKingdomWindow();
    }

    @FXML
    protected void addEmpireButtonClick() throws IOException {
        ShowAddEmpireWindow();
    }

    @FXML
    protected void displayButtonClick() throws IOException {
        Main.ShowDisplayWindow();
    }

    @FXML
    protected void deleteButtonClick() throws IOException {
        Main.ShowDeleteWindow();
    }
}