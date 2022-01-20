package kingdoms.kingdoms;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

import java.io.IOException;

import static kingdoms.kingdoms.Main.ShowAddProvinceWindow;
import static kingdoms.kingdoms.Main.ShowAddRulerWindow;
import static kingdoms.kingdoms.Main.ShowAddDuchyWindow;
import static kingdoms.kingdoms.Main.ShowAddKingdomWindow;
import static kingdoms.kingdoms.Main.ShowAddEmpireWindow;

/**
 * Controller of the main window (start window)
 */
public class MainViewController {

    /**
     * Opens a window for adding new province
     * @throws IOException
     */
    @FXML
    protected void addProvinceButtonClick() throws IOException {
        ShowAddProvinceWindow();
    }

    /**
     * Opens a window for adding new ruler
     * @throws IOException
     */
    @FXML
    protected void addRulerButtonClick() throws IOException {
        ShowAddRulerWindow();
    }

    /**
     * Opens a window for adding new duchy
     * @throws IOException
     */
    @FXML
    protected void addDuchyButtonClick() throws IOException {
        ShowAddDuchyWindow();
    }

    /**
     * Opens a window for adding new kingdom
     * @throws IOException
     */
    @FXML
    protected void addKingdomButtonClick() throws IOException {
        ShowAddKingdomWindow();
    }

    /**
     * Opens a window for adding new empire
     * @throws IOException
     */
    @FXML
    protected void addEmpireButtonClick() throws IOException {
        ShowAddEmpireWindow();
    }

    /**
     * Opens the object browser window
     * @throws IOException
     */
    @FXML
    protected void displayButtonClick() throws IOException {
        Main.ShowDisplayWindow();
    }

    /**
     * Opens the window for deleting object
     * @throws IOException
     */
    @FXML
    protected void deleteButtonClick() throws IOException {
        Main.ShowDeleteWindow();
    }
}