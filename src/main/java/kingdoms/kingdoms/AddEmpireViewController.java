package kingdoms.kingdoms;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.util.Callback;
import java.io.IOException;
import java.util.List;
import static kingdoms.kingdoms.Main.ShowAddProvinceWindow;
import static kingdoms.kingdoms.Main.database;

public class AddEmpireViewController {
    @FXML
    private TextField name;
    @FXML
    private ComboBox<Ruler> ruler;
    @FXML
    private ComboBox<Centralization> centralization;
    @FXML
    private ListView<Kingdom> kingdoms;
    @FXML
    private TextArea infos;

    @FXML
    protected void initialize()
    {
        //--------------------------------Lista rozwijana władców--------------------------------------
        List<Ruler> rulers = ruler.getItems();

        for (BaseObject obj:database)
        {
            if(obj instanceof Ruler)
                rulers.add((Ruler)obj);
        }

        Callback<ListView<Ruler>, ListCell<Ruler>> cellFactory = new Callback<ListView<Ruler>, ListCell<Ruler>>() {
            @Override public ListCell<Ruler> call(ListView<Ruler> l) {
                return new ListCell<Ruler>() {
                    @Override protected void updateItem(Ruler item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item == null || empty)
                        {
                            setGraphic(null);
                        }
                        else
                            setText(item.getName());
                    }
                };
            }
        };

        ruler.setButtonCell(cellFactory.call(null));
        ruler.setCellFactory(cellFactory);

        //---------------------------------Lista rozwijana centralizacji--------------------------------------
        List<Centralization> cents = centralization.getItems();

        cents.addAll(List.of(Centralization.values()));

        Callback<ListView<Centralization>, ListCell<Centralization>> cellFactory2 = new Callback<ListView<Centralization>, ListCell<Centralization>>() {
            @Override public ListCell<Centralization> call(ListView<Centralization> l) {
                return new ListCell<Centralization>() {
                    @Override protected void updateItem(Centralization item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item == null || empty)
                        {
                            setGraphic(null);
                        }
                        else
                            setText(item.name());
                    }
                };
            }
        };

        centralization.setButtonCell(cellFactory2.call(null));
        centralization.setCellFactory(cellFactory2);

        //---------------------------------Lista wyboru prowincji--------------------------------------

        List<Kingdom> king = kingdoms.getItems();

        for (BaseObject obj:database)
        {
            if(obj instanceof Kingdom)
            {
                king.add((Kingdom) obj);
            }
        }

        Callback<ListView<Kingdom>, ListCell<Kingdom>> cellFactory3 = new Callback<ListView<Kingdom>, ListCell<Kingdom>>() {
            @Override public ListCell<Kingdom> call(ListView<Kingdom> p) {
                return new ListCell<Kingdom>() {
                    @Override
                    public void updateItem(Kingdom item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item == null || empty)
                        {
                            setGraphic(null);
                        }
                        else
                            setText(item.getName());
                    }

                };
            }
        };

        //provinces.setButtonCell(cellFactory3.call(null));
        kingdoms.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        kingdoms.setCellFactory(cellFactory3);
    }

    @FXML
    protected void Submit() {
        if(!name.getText().isEmpty() && ruler.getValue()!=null && centralization.getValue()!=null && kingdoms.getSelectionModel().getSelectedItems()!=null)
        {
            Empire empire = new Empire(name.getText(), ruler.getValue(), kingdoms.getSelectionModel().getSelectedItems(), centralization.getValue());
            database.add(empire);
            infos.setText("Dodałeś Cesarstwo:\n" + empire.ToString());
        }
        else
            infos.setText("Uzupełnij pola");
    }
}
