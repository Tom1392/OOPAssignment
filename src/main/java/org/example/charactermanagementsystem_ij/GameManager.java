package org.example.charactermanagementsystem_ij;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GameManager extends Application
{
    private static GameManager game;
    private ObservableList<Hero> heroes = FXCollections.observableArrayList();

    public static GameManager getInstance() {
        if (game == null) {
            game = new GameManager();
        }
        return game;
    }

    public ObservableList<Hero> getHeroes() {
        return heroes;
    }
    public void addHero(Hero hero) {
        heroes.add(hero);
    }





    public BattleResult performFightRound(Hero player, int playerHp, int playerAttackLevel, int playerDefenseLevel,
                                         Hero opponent, int opponentHp, int opponentAttackLevel, int opponentDefenseLevel) {
    BattleResult result = new BattleResult();
        result.playerHp = playerHp;
        result.opponentHp = opponentHp;

        System.out.println(playerAttackLevel+" player attack level");
        System.out.println(opponentDefenseLevel+" opponent defence level");

    int playersAttack = (int) (Math.random() * playerAttackLevel);
    int opponentsDefence = (int) (Math.random() * opponentDefenseLevel);

        System.out.println(playersAttack+" Players attack");
        System.out.println(opponentsDefence+ " opponents defence");

    result.battleInfo = player.attackDescription() + "\n";

    if (opponentsDefence > playersAttack) {
        result.battleInfo += opponent.defenceDescription() + " \n";
    } else {
        opponentHp = opponentHp - playersAttack;
        opponentHp = Math.max(0, opponentHp);

        System.out.println(opponentHp+" opponents hp");

        result.opponentHp = opponentHp;
        if (opponentHp == 0) {
            result.opponentWon = true;
            return result;
        }
    }
    int opponentsAttack = (int) (Math.random() * opponentAttackLevel);
    int playersDefence = (int) (Math.random() * playerDefenseLevel);

        System.out.println(opponentsAttack+" opponents att");
        System.out.println(playersDefence+" players def");

    result.battleInfo += opponent.attackDescription() + "\n";

    if (playersDefence > opponentsAttack) {
        result.battleInfo += player.defenceDescription() + " \n";
    } else {
        playerHp = playerHp - opponentsAttack;
        playerHp = Math.max(0, playerHp);

        System.out.println(playerHp+" players hp");

        result.playerHp = playerHp;
        if (playerHp == 0) {
            result.playerWon = true;
            return result;
        }
    }

    return result;
}


    public static class BattleResult {
        public int playerHp;
        public int opponentHp;
        public boolean playerWon;
        public boolean opponentWon;
        public String battleInfo;
    }






    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GameManager.class.getResource("/org/example/charactermanagementsystem_ij/MainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 924, 525);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args)
    {
        GameManager game = GameManager.getInstance();
        Archer Legolas = new Archer("Legolas","Archer",200,150,150);
        Warrior Aragorn = new Warrior("Aragorn","Warrior",200,150,150);
        Mage Gandalf = new Mage("Gandalf","Mage",200,150,150);
        game.addHero(Legolas);
        game.addHero(Aragorn);
        game.addHero(Gandalf);
        launch();
    }
}