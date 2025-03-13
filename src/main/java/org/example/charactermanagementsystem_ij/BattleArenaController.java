package org.example.charactermanagementsystem_ij;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.*;

import java.io.IOException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Scene;


public class BattleArenaController
{
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
    }


    @FXML
    private Button fightButton;

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
        FXMLLoader loader = new FXMLLoader(GameManager.class.getResource("/org/example/charactermanagementsystem_ij/main-view.fxml"));
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
    public void fight(ActionEvent event) throws IOException {
        if (battleOver) {
            return;
        } else {
            int battlePlayerHp = Integer.parseInt(heroHpTxt.getText());
            int battleOpponentHp = Integer.parseInt(opponentHpTxt.getText());

            GameManager game = GameManager.getInstance();
            GameManager.BattleResult result = game.performFightRound(battlePlayer, battlePlayerHp, battlePlayerAttLev, battlePlayerDefLev,
                    battleOpponent, battleOpponentHp, battleOppAttLev, battleOppDefLev);
            heroHpTxt.setText(String.valueOf(result.playerHp));
            opponentHpTxt.setText(String.valueOf(result.opponentHp));
            heroHpBar.setProgress((double) result.playerHp / battlePlayer.getHealthPoints());
            opponentHpBar.setProgress((double) result.opponentHp / battleOpponent.getHealthPoints());
            battleInfoTxtAr.appendText(result.battleInfo);
            if (result.opponentWon) {
                playerWon();
                return;
            } else if (result.playerWon) {
                opponentWon();
                return;
            }

            System.out.println(result.playerHp);
            System.out.println(result.opponentHp);


             // Append directly to text field
        }
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



}
