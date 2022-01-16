package kingdoms.kingdoms;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.io.IOException;
import java.util.List;

import static kingdoms.kingdoms.Main.*;

public class DisplayViewController {
    @FXML
    private TextField name;
    @FXML
    private ComboBox<Classes> type;
    @FXML
    private ListView<String> list;
    @FXML
    private Button modButton;

    @FXML
    protected void initialize()
    {
        //--------------------------------Lista rozwijana klas--------------------------------------
        List<Classes> types = type.getItems();

        types.addAll(List.of(Classes.values()));

        Callback<ListView<Classes>, ListCell<Classes>> cellFactory = new Callback<ListView<Classes>, ListCell<Classes>>() {
            @Override public ListCell<Classes> call(ListView<Classes> l) {
                return new ListCell<Classes>() {
                    @Override protected void updateItem(Classes item, boolean empty) {
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

        type.setButtonCell(cellFactory.call(null));
        type.setCellFactory(cellFactory);

    }

    @FXML
    protected void Submit() {

        //--------------------------------Wyświetlanie obiektów z bazy danych--------------------------------------
        List<String> objects = list.getItems();
        objects.removeAll(objects);

        String searchedWord = name.getText();

        if(type.getValue() == null)
        {
            for (var obj:database)
            {
                if(obj.getName().contains(searchedWord))
                {
                    objects.add(obj.ToString());
                }
            }
        }
        else
        {
            switch (type.getValue())
            {
                case Cesarstwo:
                    for (var obj:database)
                    {
                        if(obj.getName().contains(searchedWord))
                        {
                            if (obj instanceof Empire)
                                objects.add(obj.ToString());
                        }
                    }
                    break;
                case Królestwo:
                    for (var obj:database)
                    {
                        if(obj.getName().contains(searchedWord))
                        {
                            if(obj instanceof Kingdom && obj instanceof Empire == false)
                                objects.add(obj.ToString());
                        }
                    }
                    break;
                case Księstwo:
                    for (var obj:database)
                    {
                        if(obj.getName().contains(searchedWord))
                        {
                            if (obj instanceof Duchy && obj instanceof Kingdom == false)
                                objects.add(obj.ToString());
                        }
                    }
                    break;
                case Prowincja:
                    for (var obj:database)
                    {
                        if(obj.getName().contains(searchedWord))
                        {
                            if (obj instanceof Province && obj instanceof Duchy == false)
                                objects.add(obj.ToString());
                        }
                    }
                    break;
                case Władca:
                    for (var obj:database)
                    {
                        if(obj.getName().contains(searchedWord))
                        {
                            if (obj instanceof Ruler)
                                objects.add(obj.ToString());
                        }
                    }
                    break;
                default:
                    for (var obj:database)
                    {
                        if (obj.getName().contains(searchedWord))
                        {
                            objects.add(obj.ToString());
                        }
                    }
                    break;
            }
        }

        Callback<ListView<String>, ListCell<String>> cellFactory2 = new Callback<>() {
            @Override public ListCell<String> call(ListView<String> l) {
                return new ListCell<>() {
                    @Override protected void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if(item == null || empty)
                        {
                            setGraphic(null);
                        }
                        else
                            //setText("\""+item.getName()+"\" (ID: "+item.getId()+"), typ: "+item.getClass().toString());
                            setText(item);
                    }
                };
            }
        };

        list.setCellFactory(cellFactory2);
    }

    @FXML
    protected void EnableModButton() throws IOException {
        if(list.getSelectionModel().getSelectedItem() != null)
            modButton.setDisable(false);
    }

    @FXML
    protected void UpdateSelectedObject() throws IOException {
        String objectData = list.getSelectionModel().getSelectedItem();

        long id = Long.parseLong(objectData.substring(objectData.indexOf("ID: ")+4,objectData.indexOf(")")));

        for (var obj:database)
        {
            if(obj.getId() == id)
            {
                modID = id;
                if(obj instanceof Ruler)
                {
                    ShowModRulerWindow();
                    break;
                }
                if(obj instanceof Empire)
                {
                    ShowModEmpireWindow();
                    break;
                }
                if(obj instanceof Kingdom)
                {
                    ShowModKingdomWindow();
                    break;
                }
                if(obj instanceof Duchy)
                {
                    ShowModDuchyWindow();
                    break;
                }
                if(obj instanceof Province)
                {
                    ShowModProvinceWindow();
                    break;
                }
            }
        }
    }
}
