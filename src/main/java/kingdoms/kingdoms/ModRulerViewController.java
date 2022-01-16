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

import static kingdoms.kingdoms.Main.*;

public class ModRulerViewController {
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

    private Ruler ruler;

    @FXML
    protected void initialize()
    {
        String originalName = null;

        for (var obj:database)
        {
            if(obj.getId() == modID)
            {
                originalName = obj.getName();
                ruler = (Ruler)obj;
            }
        }

        infos.setText("Modyfikujesz władcę o ID: "+modID);

        name.setText(originalName);

        //---------------------------------------------------
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
                ruler.setName(name.getText());
                ruler.setAge(a);
                ruler.setPrestige(p);
                ruler.setClaim(claim.getValue());
                ruler.updateStrength();
                infos.setText("Zmodyfikowany władca:\n"+ruler.ToString());
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
