package kingdoms.kingdoms;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import static kingdoms.kingdoms.Main.*;

public class ModProvinceViewController {
    @FXML
    private TextField name;
    @FXML
    private TextField area;
    @FXML
    private Label infos;

    private Province province;

    @FXML
    protected void initialize()
    {
        String originalName = null;

        for (var obj:database)
        {
            if(obj.getId() == modID)
            {
                originalName = obj.getName();
                province = (Province) obj;
            }
        }

        infos.setText("Modyfikujesz objekt o ID: "+modID);

        name.setText(originalName);
    }

    @FXML
    protected void Submit() {
        if(!name.getText().isEmpty() && !area.getText().isEmpty())
        {
            try
            {
                int a = Integer.parseInt(area.getText());
                province.setName(name.getText());
                province.setArea(a);
                infos.setText("Zmodyfikowana prowincja:\n"+province.ToString());
            }
            catch(Throwable exception)
            {
                infos.setText("\"Powierzchnia\" musi być liczbą całkowitą");
            }
        }
        else
            infos.setText("Uzupełnij pola");
    }
}
