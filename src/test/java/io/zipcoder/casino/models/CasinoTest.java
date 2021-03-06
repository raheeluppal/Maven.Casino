package io.zipcoder.casino.models;


import io.zipcoder.casino.App;
import io.zipcoder.casino.games.Game;
import io.zipcoder.casino.games.specific.BlackJack;
import io.zipcoder.casino.games.specific.Craps;
import io.zipcoder.casino.games.specific.GoFish;
import io.zipcoder.casino.games.specific.LoopyDice;
import io.zipcoder.casino.players.*;
import io.zipcoder.casino.utilities.io.AbstractConsole;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class CasinoTest {
    @Test
    public void getGames() {
        App app = new App();
        app.runCasino();
        Casino casino = app.getCasino();
        Map<AbstractConsole.Command, Game> expected = new HashMap<>();
        Map<AbstractConsole.Command, Game> actual = casino.getGames();
        expected.put(AbstractConsole.Command.BLACKJACK, new BlackJack());
        expected.put(AbstractConsole.Command.GOFISH, new GoFish());
        expected.put(AbstractConsole.Command.LOOPY_DICE, new LoopyDice());
        expected.put(AbstractConsole.Command.CRAPS, new Craps());
        for (Map.Entry<AbstractConsole.Command, Game> entry : expected.entrySet()) {
            Assert.assertTrue(actual.containsKey(entry.getKey()));
        }
    }

    @Test
    public void getCurrentPlayer() {
        App app = new App();
        app.runCasino();
        Casino casino = app.getCasino();
        Player expected = new Player("Adam");
        casino.setCurrentPlayer(expected);
        Player actual = casino.getCurrentPlayer();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void isGame() {
        App app = new App();
        app.runCasino();
        Casino casino = app.getCasino();
        Assert.assertTrue(casino.isGame(AbstractConsole.Command.BLACKJACK));
        Assert.assertTrue(casino.isGame(AbstractConsole.Command.GOFISH));
        Assert.assertTrue(casino.isGame(AbstractConsole.Command.LOOPY_DICE));
        Assert.assertTrue(casino.isGame(AbstractConsole.Command.CRAPS));
    }

    @Test
    public void updateCurrentPlayer() {
        App app = new App();
        app.runCasino();
        Casino casino = app.getCasino();
        casino.setCurrentPlayer(new Player("Adam"));
        Assert.assertFalse(casino.getCurrentPlayer() instanceof BlackJackPlayer);
        casino.updateCurrentPlayer(new BlackJack());
        Assert.assertTrue(casino.getCurrentPlayer() instanceof BlackJackPlayer);
    }

    @Test
    public void updateCurrentPlayerB() {
        App app = new App();
        app.runCasino();
        Casino casino = app.getCasino();
        casino.setCurrentPlayer(new Player("Adam"));
        Assert.assertFalse(casino.getCurrentPlayer() instanceof GoFishPlayer);
        casino.updateCurrentPlayer(new GoFish());
        Assert.assertTrue(casino.getCurrentPlayer() instanceof GoFishPlayer);
    }

    @Test
    public void updateCurrentPlayerC() {
        App app = new App();
        app.runCasino();
        Casino casino = app.getCasino();
        casino.setCurrentPlayer(new Player("Adam"));
        Assert.assertFalse(casino.getCurrentPlayer() instanceof DicePlayer);
        casino.updateCurrentPlayer(new Craps());
        Assert.assertTrue(casino.getCurrentPlayer() instanceof DicePlayer);
    }

    @Test
    public void updateCurrentPlayerD() {
        App app = new App();
        app.runCasino();
        Casino casino = app.getCasino();
        casino.setCurrentPlayer(new Player("Adam"));
        Assert.assertFalse(casino.getCurrentPlayer() instanceof LoopyDicePlayer);
        casino.updateCurrentPlayer(new LoopyDice());
        Assert.assertTrue(casino.getCurrentPlayer() instanceof LoopyDicePlayer);
    }
}
