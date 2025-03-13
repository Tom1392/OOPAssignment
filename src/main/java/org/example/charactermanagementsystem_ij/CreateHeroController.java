package org.example.charactermanagementsystem_ij;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class CreateHeroController implements Initializable
{
    Image archerIcon = new Image(getClass().getResourceAsStream("/org/example/charactermanagementsystem_ij/Screenshot2025-03-04195212.png"));
    Image warriorIcon = new Image(getClass().getResourceAsStream("/org/example/charactermanagementsystem_ij/WarriorIcon.png"));
    Image mageIcon = new Image(getClass().getResourceAsStream("/org/example/charactermanagementsystem_ij/WizardIcon.png"));
    String initialValue="";
    @FXML
    private ImageView classImage;
    @FXML
    private int creationPt=500;
    @FXML
    private TextField creationPtsTxt;
    @FXML
    private MenuItem backToMenuButton;
    @FXML
    private Button createHeroButton;
    @FXML
    private TextField attackPowerTxt;
    @FXML
    private TextField defenceLevelTxt;
    @FXML
    private TextField hPTxt;
    @FXML
    private TextField heroClassTxt;
    @FXML
    private TextField heroNameTxt;
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
    @FXML
    private void setClassTxtArcher(ActionEvent event)
    {
        heroClassTxt.setText("Archer");
        classImage.setImage(archerIcon);
    }
    @FXML
    private void setClassTxtWarrior(ActionEvent event)
    {
        heroClassTxt.setText("Warrior");
        classImage.setImage(warriorIcon);
    }
    @FXML
    private void setClassTxtMage(ActionEvent event)
    {
        heroClassTxt.setText("Mage");
        classImage.setImage(mageIcon);
    }

    public void setupTextField(TextField textField)
    {
        textField.focusedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                //focus gained
                if (newValue)
                {
                    //store the existing value
                    initialValue = textField.getText();
                }
                    //focus lost
                    else
                    {
                        String enteredValue = textField.getText();
                        //valid input
                        if (enteredValue.isEmpty()) {
                            //reset to initial value
                            textField.setText(initialValue);
                            return;
                        }
                        try {
                            //New and old values as interger
                            int intOldValue = initialValue.isEmpty() ? 0 : Integer.parseInt(initialValue);
                            int intNewValue = Integer.parseInt(enteredValue);
                            //if the new value is greater than the old value, calculate the difference
                            if (intNewValue > intOldValue) {
                                int difference = intNewValue - intOldValue;

                                //is the difference greater than creationPts. if so allocate availible creation points
                                //then alert and set canCreate to false
                                if (difference > creationPt) {
                                    textField.setText(String.valueOf(intOldValue + creationPt));
                                    creationPt -=difference;
                                    creationPtsAlert();
                                } else {
                                    //reduce creationPts by amount used
                                    creationPt += (intOldValue - intNewValue);
                                }
                            } else {
                            //if the new value is less than old, top up creation points with the difference.
                                creationPt += (intOldValue - intNewValue);
                            }
                            //update creation points to display correct amount
                                creationPtsTxt.setText(String.valueOf(creationPt));
                            //update textfield with correct amount
                                textField.setText(String.valueOf(intNewValue));

                        }
                        catch (NumberFormatException e)
                        {
                            textField.setText(initialValue);
                        }
                    }
            }
        });
    }

    @FXML
    private void createHero(ActionEvent event) {
        GameManager game = GameManager.getInstance();
        ObservableList<Hero> heroes = game.getHeroes();
        String heroName = heroNameTxt.getText();
        for(Hero hero : heroes)
        {
            if(Objects.equals(heroName, hero.getHeroName()))
            {
                nameTakenAlert();
                return;
            }
        }
        String heroClass = heroClassTxt.getText();
        int healthPoints = Integer.parseInt(hPTxt.getText());
        int defenceLevel = Integer.parseInt(defenceLevelTxt.getText());
        int attackPower = Integer.parseInt(attackPowerTxt.getText());
        int creationPtUsed = healthPoints + defenceLevel + attackPower;
        if (creationPtUsed > 500)
        {
            creationPtsAlert();
            return;
        } else if (healthPoints<=0||defenceLevel<=0||attackPower<=0)
        {
            statZeroAlert();
            return;
        }
        else
        {
            Hero newHero;
            switch (heroClass) {
                case "Archer":
                    Archer archer = new Archer(heroName, heroClass, healthPoints, defenceLevel, attackPower);
                    game.addHero(archer);
                    break;
                case "Warrior":
                    Warrior warrior = new Warrior(heroName, heroClass, healthPoints, defenceLevel, attackPower);
                    game.addHero(warrior);
                    break;
                case "Mage":
                    Mage mage = new Mage(heroName, heroClass, healthPoints, defenceLevel, attackPower);
                    game.addHero(mage);
                    break;
            }
        }
    }
    public void creationPtsAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Insufficient Creation Points\nPlease amend your hero's stats");
        alert.showAndWait();
    }
    public void statZeroAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("No stats can be zero");
        alert.showAndWait();
    }
    public void nameTakenAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Hero Name already exists");
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        creationPtsTxt.setText(String.valueOf(creationPt));
        setupTextField(hPTxt);
        setupTextField(defenceLevelTxt);
        setupTextField(attackPowerTxt);
    }
}


