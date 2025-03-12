package org.example.charactermanagementsystem_ij;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.Scene;

public class CharacterManagementSystemController implements Initializable
{
    private String initialValue="";
    private int heroExperiencePts;
    private Hero currentHero;
    Image archerIcon = new Image(getClass().getResourceAsStream("/org/example/charactermanagementsystem_ij/Screenshot2025-03-04195212.png"));
    Image warriorIcon = new Image(getClass().getResourceAsStream("/org/example/charactermanagementsystem_ij/WarriorIcon.png"));
    Image mageIcon = new Image(getClass().getResourceAsStream("/org/example/charactermanagementsystem_ij/WizardIcon.png"));

    @FXML
    private ImageView heroImageView;

    @FXML
    private TextField classTxt;

    @FXML
    private TextField defTxt;

    @FXML
    private TextField eXTxt;

    @FXML
    private TextField hPTxt;

    @FXML
    private TextField nameTxt;

    @FXML
    private TextField attTxt;

    @FXML
    private Button battleButton;

    @FXML
    private MenuItem backToMenuButton;

    @FXML
    private Button levelUpButton;

    @FXML
    private void getReadyForBattle(ActionEvent event) throws IOException {
        if(CharacterManagementSystemApplication.heroes.size()==1)
        {
            noOpponentAlert();
            return;
        }
        else
        {
            int oppSelector;
            do {
                oppSelector=(int)(Math.random()*CharacterManagementSystemApplication.heroes.size());
                System.out.println("Do LOOP");
            }
            while(CharacterManagementSystemApplication.heroes.get(oppSelector).equals(currentHero));
            Hero opponent=CharacterManagementSystemApplication.heroes.get(oppSelector);
            System.out.println("get ready for battle()");
            goToBattle(currentHero,opponent, event);
        }
    }

    @FXML
    private void goToBattle(Hero player, Hero opponent, ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(CharacterManagementSystemApplication.class.getResource("/org/example/charactermanagementsystem_ij/BattleArena.fxml"));
        Parent root = loader.load();
        BattleArenaController controller =loader.getController();
        controller.setPlayersInfo(player,opponent);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
        System.out.println("goToBattle");
        Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        currentStage.close();
    }
    @FXML
    private void BackToMainMenu(ActionEvent event) throws IOException
    {
        URL fxmlLocation = getClass().getResource("/org/example/charactermanagementsystem_ij/MainMenu.fxml");
        FXMLLoader loader = new FXMLLoader(fxmlLocation);
        Parent root = loader.load();
        Stage stage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
    @FXML
    public void levelUp(ActionEvent event) throws IOException
    {
        if (heroExperiencePts<0)
        {
            eXPtsAlert();
            return;
        }
        else
        {
            currentHero.setHealthPoints(Integer.parseInt(hPTxt.getText()));
            currentHero.setDefenceLevel(Integer.parseInt(defTxt.getText()));
            currentHero.setAttackPower(Integer.parseInt(attTxt.getText()));
            currentHero.setExperiencePoints(Integer.parseInt(eXTxt.getText()));
        }
    }

public void setHeroInfo(Hero hero)
{
    nameTxt.setText(hero.getHeroName());
    classTxt.setText(hero.getHeroClass());
    hPTxt.setText(String.valueOf(hero.getHealthPoints()));
    defTxt.setText((String.valueOf(hero.getDefenceLevel())));
    attTxt.setText(String.valueOf(hero.getAttackPower()));
    eXTxt.setText(String.valueOf(hero.getExperiencePoints()));
    setHeroImageView(hero);
    heroExperiencePts=hero.getExperiencePoints();
    currentHero=hero;
}
public void setHeroImageView(Hero hero)
{
    switch (hero.getHeroClass())
    {
        case "Archer":
            heroImageView.setImage(archerIcon);
            break;
        case "Warrior":
            heroImageView.setImage(warriorIcon);
            break;
        case "Mage":
            heroImageView.setImage(mageIcon);
            break;
    }
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
                            if (difference > heroExperiencePts) {
                                textField.setText(String.valueOf(intOldValue + heroExperiencePts));
                                heroExperiencePts -=difference;
                                eXPtsAlert();
                            } else {
                                //reduce creationPts by amount used
                                heroExperiencePts += (intOldValue - intNewValue);
                            }
                        } else {
                            //if the new value is less than old, top up creation points with the difference.
                            heroExperiencePts += (intOldValue - intNewValue);
                        }
                        //update creation points to display correct amount
                        eXTxt.setText(String.valueOf(heroExperiencePts));
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

    public void eXPtsAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Insufficient Experience Points\nPlease amend your hero's stats");
        alert.showAndWait();
    }

    public void noOpponentAlert()
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("No Opponent Available\nPlease create a another hero to fight with");
        alert.showAndWait();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        setupTextField(hPTxt);
        setupTextField(defTxt);
        setupTextField(attTxt);
    }
}
