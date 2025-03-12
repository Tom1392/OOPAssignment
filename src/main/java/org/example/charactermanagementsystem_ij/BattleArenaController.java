package org.example.charactermanagementsystem_ij;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.IOException;
import java.net.URL;


public class BattleArenaController
{
    private String opponentWeapon;
    private String playerWeapon;
    private boolean battleOver=false;
   private Hero battlePlayer;
   private Hero battleOpponent;
   private int battlePlayerDefLev;
   private int battleOppDefLev;
   private int battlePlayerAttLev;
   private int battleOppAttLev;
    Image archerIcon = new Image(getClass().getResourceAsStream("/org/example/charactermanagementsystem_ij/Screenshot2025-03-04195212.png"));
    Image warriorIcon = new Image(getClass().getResourceAsStream("/org/example/charactermanagementsystem_ij/WarriorIcon.png"));
    Image mageIcon = new Image(getClass().getResourceAsStream("/org/example/charactermanagementsystem_ij/WizardIcon.png"));

    @FXML
    private Button attackButton;

    @FXML
    private TextArea battleInfoTxtAr;

    @FXML
    private ProgressBar heroHpBar;

    @FXML
    private TextField heroHpTxt;

    @FXML
    private ImageView heroImage;

    @FXML
    private Text heroNameTxt;

    @FXML
    private ProgressBar opponentHpBar;

    @FXML
    private TextField opponentHpTxt;

    @FXML
    private ImageView opponentImage;

    @FXML
    private Text opponentNameTxt;

    @FXML
    private MenuItem quitBattleButton;

    @FXML
    private void quitBattle(ActionEvent event) throws IOException
    {
        backMainView(battlePlayer, event);
    }

    @FXML
    private void backMainView(Hero hero, ActionEvent event) throws IOException
    {
        FXMLLoader loader = new FXMLLoader(CharacterManagementSystemApplication.class.getResource("/org/example/charactermanagementsystem_ij/main-view.fxml"));
        Parent root = loader.load();
        CharacterManagementSystemController controller =loader.getController();
        controller.setHeroInfo(hero);
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();

        Stage currentStage = (Stage) ((MenuItem) event.getSource()).getParentPopup().getOwnerWindow();
        currentStage.close();
    }
    @FXML
    public void attack(ActionEvent event) throws IOException {
        if (!battleOver) {
            int battlePlayerHp = Integer.parseInt(heroHpTxt.getText());
            int battleOpponentHp = Integer.parseInt(opponentHpTxt.getText());
            int playersAttack = (int) (Math.random() * battlePlayerAttLev);
                int opponentsDefence = (int) (Math.random() * battleOppDefLev);
                if (opponentsDefence > playersAttack)
                {
                    battleInfoTxtAr.appendText(battleOpponent.getHeroName()+" blocked the attack\n");
                } else {
                    battleOpponentHp = battleOpponentHp - playersAttack;
                    battleOpponentHp = Math.max(0, battleOpponentHp);
                    opponentHpTxt.setText(String.valueOf(battleOpponentHp));
                    battleInfoTxtAr.appendText(battlePlayer.getHeroName()+" Strikes A Blow with "+playerWeapon+"\n");
                    opponentHpBar.setProgress((double) battleOpponentHp / battleOpponent.getHealthPoints());
                    if (battleOpponentHp == 0)
                    {
                        playerWon();
                        return;
                    }
                }
                int opponentsAttack= (int)(Math.random()*battleOppAttLev);
                int playersDefence= (int)(Math.random()*battlePlayerDefLev);
                if(playersDefence>opponentsAttack)
                {
                    battleInfoTxtAr.appendText(battlePlayer.getHeroName()+" blocked the attack\n");
                }
                else
                {
                    battlePlayerHp=battlePlayerHp-opponentsAttack;
                    battlePlayerHp = Math.max(0, battlePlayerHp);
                    heroHpTxt.setText(String.valueOf(battlePlayerHp));
                    battleInfoTxtAr.appendText(battleOpponent.getHeroName()+" Stikes A blow with "+opponentWeapon+"\n");
                    heroHpBar.setProgress((double) battlePlayerHp/ battlePlayer.getHealthPoints());
                    if (battlePlayerHp == 0)
                    {
                        opponentWon();
                        return;
                    }
                }
                }
            }



    public void setPlayersInfo(Hero hero, Hero opponent)
    {
        heroNameTxt.setText(hero.getHeroName());
        opponentNameTxt.setText(opponent.getHeroName());
        heroHpTxt.setText(String.valueOf(hero.getHealthPoints()));
        opponentHpTxt.setText(String.valueOf(opponent.getHealthPoints()));
        setPlayerImageView(hero,opponent);
        battlePlayer=hero;
        battleOpponent=opponent;
        battlePlayerDefLev=hero.getDefenceLevel();
        battleOppDefLev=opponent.getDefenceLevel();
        battlePlayerAttLev=hero.getAttackPower();
        battleOppAttLev=opponent.getAttackPower();
        playerWeapon=setWeapon(hero);
        opponentWeapon=setWeapon(opponent);
    }

    public void setPlayerImageView(Hero player, Hero opponent)
    {

        switch (player.getHeroClass())
        {
            case "Archer":
                heroImage.setImage(archerIcon);
                break;
            case "Warrior":
                heroImage.setImage(warriorIcon);
                break;
            case "Mage":
                heroImage.setImage(mageIcon);
                break;
        }
        setOpponentImageView(opponent);
    }
    public void setOpponentImageView(Hero opponent)
    {
        switch (opponent.getHeroClass())
        {
            case "Archer":
                opponentImage.setImage(archerIcon);
                break;
            case "Warrior":
                opponentImage.setImage(warriorIcon);
                break;
            case "Mage":
                opponentImage.setImage(mageIcon);
                break;
        }
    }

public void playerWon()
{
    battleOver=true;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle("Congratulations!");
    alert.setContentText("You Have Won the Battle");
    alert.showAndWait();
    int currentXP =battlePlayer.getExperiencePoints();
    currentXP+=20;
    battlePlayer.setExperiencePoints(currentXP);
}
public void opponentWon()
    {
        battleOver=true;
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("You Lost!");
        alert.setContentText("You Have Been Defeated");
        alert.showAndWait();
        int currentXP =battlePlayer.getExperiencePoints();
        currentXP+=5;
        battlePlayer.setExperiencePoints(currentXP);
    }

    public String setWeapon(Hero hero)
    {
        if (hero instanceof Archer)
        {
            return ((Archer) hero).getWeapon();
        }
        else if (hero instanceof Warrior)
        {
            return ((Warrior) hero).getWeapon();
        }
        else if (hero instanceof Mage)
        {
            return ((Mage) hero).getWeapon();
        }
        return "Error";
    }

}
