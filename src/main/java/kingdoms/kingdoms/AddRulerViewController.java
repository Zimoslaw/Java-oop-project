package kingdoms.kingdoms;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static kingdoms.kingdoms.Main.ShowAddRulerWindow;
import static kingdoms.kingdoms.Main.database;

public class AddRulerViewController {
    @FXML
    private TextField name;
    @FXML
    private TextField age;
    @FXML
    private TextField prestige;
    @FXML
    private ComboBox<Province> claim;
    @FXML
    private TextArea infos;

    @FXML
    protected void initialize()
    {
        List<Province> options = claim.getItems();

        for (BaseObject obj:database)
        {
            if(obj instanceof Province)
                options.add((Province)obj);
        }

        Callback<ListView<Province>, ListCell<Province>> cellFactory = new Callback<ListView<Province>, ListCell<Province>>() {
            @Override public ListCell<Province> call(ListView<Province> l) {
                return new ListCell<Province>() {
                    @Override protected void updateItem(Province item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item == null || empty)
                        {
                            setGraphic(null);
                        }
                        else
                            setText(item.ToString());
                    }
                };
            }
        };

        claim.setButtonCell(cellFactory.call(null));
        claim.setCellFactory(cellFactory);
    }

    @FXML
    protected void Submit() {
        if(!name.getText().isEmpty()  && !age.getText().isEmpty() && !prestige.getText().isEmpty() && claim.getValue()!=null)
        {
            try
            {
                short a = Short.parseShort(age.getText());
                int p = Integer.parseInt(prestige.getText());
                Ruler ruler = new Ruler(name.getText(), a, p, claim.getValue());
                database.add(ruler);
                infos.setText("Dodałeś władcę:\n"+ruler.ToString());
            }
            catch(Throwable exception)
            {
                infos.setText("\"Wiek\" i \"Renoma\" muszą być liczbą całkowitą");
            }
        }
        else
            infos.setText("Uzupełnij pola");
    }
}
