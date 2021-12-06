package kingdoms.kingdoms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 300);
        stage.setTitle("Kingdoms");
        stage.getIcons().add(new Image(String.valueOf(Main.class.getResource("kingdoms.png"))));
        stage.setScene(scene);
        stage.show();
    }

    public static void ShowAddProvinceWindow() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("add-province-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 200);
        stage.setTitle("Dodaj nową prowincję");
        stage.setScene(scene);
        stage.show();
    }

    public static void ShowAddRulerWindow() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("add-ruler-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Dodaj nowego władcę");
        stage.setScene(scene);
        stage.show();
    }

    public static void ShowAddDuchyWindow() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("add-duchy-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 500);
        stage.setTitle("Dodaj nowe księstwo");
        stage.setScene(scene);
        stage.show();
    }

    public static void ShowAddKingdomWindow() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("add-kingdom-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Dodaj nowe królestwo");
        stage.setScene(scene);
        stage.show();
    }

    public static void ShowAddEmpireWindow() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("add-empire-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Dodaj nowe cesarstwo");
        stage.setScene(scene);
        stage.show();
    }

    public static void ShowDisplayWindow() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("display-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 500);
        stage.setTitle("Przeglądarka");
        stage.setScene(scene);
        stage.show();
    }

    public static void ShowDeleteWindow() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("delete-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 200, 150);
        stage.setTitle("Usuń obiekt z bazy danych");
        stage.setScene(scene);
        stage.show();
    }

    static List<BaseObject> database = new ArrayList<BaseObject>();

    public static void main(String[] args) {
        launch();
    }
}