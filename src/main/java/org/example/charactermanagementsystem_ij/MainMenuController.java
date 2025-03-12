package org.example.charactermanagementsystem_ij;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import java.io.IOException;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import java.net.URL;

public class MainMenuController
{

    @FXML
    private Button createNewHeroButton;

    @FXML
    private MenuItem quitGame;

    @FXML
    private Button veiwAllHeroesButton;

    @FXML
    private void startCreateNewHero(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) createNewHeroButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/charactermanagementsystem_ij/CreateHero.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    private void startViewAllHeroes(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) veiwAllHeroesButton.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/org/example/charactermanagementsystem_ij/viewAllHeroes.fxml"));
        stage.setScene(new Scene(root));
        stage.show();
    }
}
