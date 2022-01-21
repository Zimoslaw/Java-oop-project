package kingdoms.kingdoms;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import static kingdoms.kingdoms.Main.ShowAddProvinceWindow;
import static kingdoms.kingdoms.Main.database;

public class AddProvinceViewController {
    @FXML
    private TextField name;
    @FXML
    private TextField area;
    @FXML
    private Label infos;

    @FXML
    protected void Submit() {
        if(!name.getText().isEmpty() && !area.getText().isEmpty())
        {
            try
            {
                int a = Integer.parseInt(area.getText());
                Province province = new Province(name.getText(), a);
                database.add(province);
                infos.setText("Dodałeś prowincje:\n"+province.ToString());
            }
            catch(NumberFormatException exception)
            {
                infos.setText("\"Powierzchnia\" musi być liczbą całkowitą");
            }
        }
        else
            infos.setText("Uzupełnij pola");
    }
}
