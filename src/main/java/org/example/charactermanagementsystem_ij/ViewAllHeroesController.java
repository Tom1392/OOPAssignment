package org.example.charactermanagementsystem_ij;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class ViewAllHeroesController implements Initializable
{


    @FXML
    private MenuItem backToMenuButton;

    @FXML
    private TableView<Hero> allHeroesTable;

    @FXML
    private TableColumn<Hero, String> nameCol;

    @FXML
    private TableColumn<Hero, String> classCol;

    @FXML
    private TableColumn<Hero, Integer> defCol;

    @FXML
    private TableColumn<Hero, Integer> attackCol;

    @FXML
    private TableColumn<Hero, Integer> hPCol;

    @FXML
    private TableColumn<Hero, Integer> xPCol;


    @FXML
    private void backToMenu(ActionEvent event) throws IOException
    {
        URL fxmlLocation = getClass().getResource("/org/example/charactermanagementsystem_ij/MainMenu.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
 @Override
    public void initialize(URL url, ResourceBundle rb)
 {
    nameCol.setCellValueFactory(new PropertyValueFactory<>("heroName"));
    classCol.setCellValueFactory(new PropertyValueFactory<>("heroClass"));
    hPCol.setCellValueFactory(new PropertyValueFactory<>("healthPoints"));
    defCol.setCellValueFactory(new PropertyValueFactory<>("defenceLevel"));
    attackCol.setCellValueFactory(new PropertyValueFactory<>("attackPower"));
    xPCol.setCellValueFactory(new PropertyValueFactory<>("experiencePoints"));

    allHeroesTable.setItems(CharacterManagementSystemApplication.heroes);
 }

    @FXML
   private void selectHero(MouseEvent event) throws IOException
    {
    Hero selectedHero = allHeroesTable.getSelectionModel().getSelectedItem();
    heroMainView(selectedHero, event);
    }
    @FXML
    private void heroMainView(Hero hero, MouseEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(CharacterManagementSystemApplication.class.getResource("/org/example/charactermanagementsystem_ij/main-view.fxml"));
        Parent root = loader.load();
        CharacterManagementSystemController controller =loader.getController();
        controller.setHeroInfo(hero);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        Stage currentStage = (Stage) ((TableView) event.getSource()).getScene().getWindow();
        currentStage.close();
    }

}
