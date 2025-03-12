package org.example.charactermanagementsystem_ij;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.io.IOException;
import java.util.HashMap;

public class CharacterManagementSystemApplication extends Application
{
    static ObservableList<Hero> heroes = FXCollections.observableArrayList();



    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CharacterManagementSystemApplication.class.getResource("/org/example/charactermanagementsystem_ij/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 924, 525);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}