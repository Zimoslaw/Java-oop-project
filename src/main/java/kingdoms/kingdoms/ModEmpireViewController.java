package kingdoms.kingdoms;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.util.List;

import static kingdoms.kingdoms.Main.database;
import static kingdoms.kingdoms.Main.modID;

public class ModEmpireViewController {
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

    private Empire empire;

    @FXML
    protected void initialize()
    {
        String originalName = null;

        for (var obj:database)
        {
            if(obj.getId() == modID)
            {
                originalName = obj.getName();
                empire = (Empire) obj;
            }
        }

        infos.setText("Modyfikujesz cesarstwo o ID: "+modID);

        name.setText(originalName);

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
            empire.setName(name.getText());
            empire.setRuler(ruler.getValue());
            empire.setKingdoms(kingdoms.getSelectionModel().getSelectedItems());
            empire.setCentralization(centralization.getValue());
            empire.updateLegitimacy();
            empire.updateStability();
            empire.updateInfluence();
            empire.getRuler().updateStrength(empire.getEmpireArea());
            infos.setText("Zmodyfikowane cesarstwo:\n" + empire.ToString());
        }
        else
            infos.setText("Uzupełnij pola");
    }
}
