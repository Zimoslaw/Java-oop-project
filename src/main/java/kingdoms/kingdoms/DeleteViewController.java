package kingdoms.kingdoms;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import static kingdoms.kingdoms.Main.ShowAddProvinceWindow;
import static kingdoms.kingdoms.Main.database;

public class DeleteViewController {
    @FXML
    private TextField id;
    @FXML
    private Label infos;

    @FXML
    protected void Submit() {
        if(!id.getText().isEmpty())
        {
            try
            {
                int i = Integer.parseInt(id.getText());
                BaseObject obj = database.get(i);
                obj = null;
                database.remove(i);
                infos.setText("Usunięto obiekt od ID "+i);
            }
            catch(Throwable exception)
            {
                infos.setText("\"ID\" musi być liczbą całkowitą");
            }
        }
        else
            infos.setText("Uzupełnij pole \"ID\"");
    }
}
