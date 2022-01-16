package kingdoms.kingdoms;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;

import static kingdoms.kingdoms.Main.*;
import static kingdoms.kingdoms.Main.ShowModProvinceWindow;

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
                long i = Long.parseLong(id.getText());
                boolean found = false;

                for (BaseObject obj:database)
                {
                    if(obj.getId() == i)
                    {
                        database.remove(obj);
                        infos.setText("Usunięto obiekt o ID "+i);
                        found = true;
                        break;
                    }
                }

                if(!found)
                    infos.setText("Nie znaleziono obiektu o ID "+i);
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
