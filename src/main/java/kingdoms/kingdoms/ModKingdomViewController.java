package kingdoms.kingdoms;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.util.List;

import static kingdoms.kingdoms.Main.database;
import static kingdoms.kingdoms.Main.modID;

public class ModKingdomViewController {
    @FXML
    private TextField name;
    @FXML
    private ComboBox<Ruler> ruler;
    @FXML
    private ComboBox<Centralization> centralization;
    @FXML
    private TextField legitimacy;
    @FXML
    private ListView<Province> provinces;
    @FXML
    private TextArea infos;

    private Kingdom kingdom;

    @FXML
    protected void initialize()
    {
        String originalName = null;

        for (var obj:database)
        {
            if(obj.getId() == modID)
            {
                originalName = obj.getName();
                kingdom = (Kingdom) obj;
            }
        }

        infos.setText("Modyfikujesz królestwo o ID: "+modID);

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

        List<Province> prov = provinces.getItems();

        for (BaseObject obj:database)
        {
            if(obj instanceof Province)
            {
                if (obj instanceof Kingdom)
                    continue;
                else
                    prov.add((Province) obj);
            }
        }

        Callback<ListView<Province>, ListCell<Province>> cellFactory3 = new Callback<ListView<Province>, ListCell<Province>>() {
            @Override public ListCell<Province> call(ListView<Province> p) {
                return new ListCell<Province>() {
                    @Override
                    public void updateItem(Province item, boolean empty) {
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
        provinces.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        provinces.setCellFactory(cellFactory3);
    }

    @FXML
    protected void Submit() {
        if(!name.getText().isEmpty() && ruler.getValue()!=null && centralization.getValue()!=null && !legitimacy.getText().isEmpty() && provinces.getSelectionModel().getSelectedItems()!=null)
        {
            try {
                int l = Integer.parseInt(legitimacy.getText());
                kingdom.setName(name.getText());
                kingdom.setRuler(ruler.getValue());
                kingdom.setProvinces(provinces.getSelectionModel().getSelectedItems());
                kingdom.setCentralization(centralization.getValue());
                kingdom.setLegitimacy(l);
                infos.setText("Zmodyfikowane królestwo:\n" + kingdom.ToString());
            }
            catch(Throwable exception)
            {
                infos.setText("\"Prawowitość\" musi być liczbą całkowitą");
            }
        }
        else
            infos.setText("Uzupełnij pola");
    }
}
