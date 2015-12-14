package MFES;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class GameTest {
    public GameTest() {
    }

    public static void main() {
        new GameTest().runAllTests();
    }

    private void runAllTests() {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
        test8();
        test9();
    }

    public static void AssertTrue(final Boolean cond) {
        return;
    }

    private void test1() {
        Game gameBot = new Game(5L, 4L, 1L);
        gameBot.makeAPlay(gameBot.getSolution());

        Boolean andResult_5 = false;

        if (gameBot.isSolutionCracked()) {
            if (gameBot.isGameOver()) {
                andResult_5 = true;
            }
        }

        AssertTrue(andResult_5);
    }

    private void test2() {
        Game gamePlayers = new Game("bbbb", "ze", "tony", 5L, 4L, 1L);
        gamePlayers.makeAPlay("bbbb");

        Boolean andResult_6 = false;

        if (gamePlayers.isSolutionCracked()) {
            if (gamePlayers.isGameOver()) {
                andResult_6 = true;
            }
        }

        AssertTrue(andResult_6);
    }

    private void test3() {
        Game gamePlayers = new Game("bbbb", "ze", "tony", 5L, 4L, 1L);
        gamePlayers.makeAPlay("bgbb");
        gamePlayers.makeAPlay("bbbb");
        gamePlayers.winGame();

        Boolean andResult_7 = false;

        if (Utils.equals(gamePlayers.getCodeMaker().getPoints(), 2L)) {
            if (gamePlayers.isGameOver()) {
                andResult_7 = true;
            }
        }

        AssertTrue(andResult_7);
    }

    private void test4() {
        Game gamePlayers = new Game("bbbb", "ze", "tony", 5L, 4L, 1L);
        gamePlayers.setMaxAttempts(2L);
        gamePlayers.makeAPlay("bgbb");
        AssertTrue(Utils.equals(gamePlayers.numberOfTriesRemaining(), 1L));
        gamePlayers.makeAPlay("bbbb");
        gamePlayers.winGame();

        Boolean andResult_8 = false;

        if (gamePlayers.isSolutionCracked()) {
            if (gamePlayers.isGameOver()) {
                andResult_8 = true;
            }
        }

        AssertTrue(andResult_8);
    }

    private void test5() {
        Game gamePlayers = new Game("bbbb", "ze", "tony", 5L, 4L, 1L);
        gamePlayers.setMaxAttempts(10L);
        gamePlayers.makeAPlay("bgbb");
        gamePlayers.makeAPlay("bgbo");
        gamePlayers.makeAPlay("bgyb");
        gamePlayers.makeAPlay("rgbb");
        gamePlayers.makeAPlay("bgbo");
        gamePlayers.makeAPlay("bgbm");
        gamePlayers.makeAPlay("bbbb");

        Boolean andResult_9 = false;

        if (gamePlayers.isSolutionCracked()) {
            if (gamePlayers.isGameOver()) {
                andResult_9 = true;
            }
        }

        AssertTrue(andResult_9);
    }

    private void test6() {
        Game gamePlayers = new Game("bbbb", "ze", "tony", 5L, 4L, 2L);
        gamePlayers.changePlayer();

        Boolean andResult_10 = false;

        if (Utils.equals(gamePlayers.getCodeBreaker().getName(), "ze")) {
            Boolean andResult_11 = false;

            if (Utils.equals(gamePlayers.getCodeMaker().getName(), "tony")) {
                if (Utils.equals(gamePlayers.getMatches(), 1L)) {
                    andResult_11 = true;
                }
            }

            if (andResult_11) {
                andResult_10 = true;
            }
        }

        AssertTrue(andResult_10);
    }

    private void test7() {
        Player p1 = new Player("ze");
        Player p2 = new Player("tony");
        Team t = new Team("FEUPinhos", p1, p2);
        Boolean andResult_12 = false;

        if (Utils.equals(t.getPlayer1().getName(), "ze")) {
            Boolean andResult_13 = false;

            if (Utils.equals(t.getPlayer2().getName(), "tony")) {
                if (Utils.equals(t.getName(), "FEUPinhos")) {
                    andResult_13 = true;
                }
            }

            if (andResult_13) {
                andResult_12 = true;
            }
        }

        AssertTrue(andResult_12);
    }

    private void test8() {
        Game tempGame = new Game("baaa", "ze", "tony", 5L, 4L, 1L);
        Boolean andResult_14 = false;

        if (Utils.equals(tempGame.calculateRightColorsInRightPlaces("baac"), 3L)) {
            if (Utils.equals(tempGame.calculateRightColorsInWrongPlaces("cccb"),
                        1L)) {
                andResult_14 = true;
            }
        }

        AssertTrue(andResult_14);
    }

    private void test9() {
        Player p1 = new Player("ze");
        Player p2 = new Player("tony");
        Team t1 = new Team("FEUPinhos", p1, p2);
        Player p3 = new Player("to");
        Player p4 = new Player("rui");
        Team t2 = new Team("FEPinhos", p1, p2);
        Tournament tor = new Tournament(MapUtil.map(
                    new Maplet(t1.getName(), t1), new Maplet(t2.getName(), t2)),
                5L, 4L);
        AssertTrue(Utils.equals(tor.getTeamNames(),
                SetUtil.set("FEUPinhos", "FEPinhos")));
        AssertTrue(Utils.equals(tor.getTeamPoints("FEUPinhos"), 0L));
        tor.startGame("bbbb", p1, p3);
        AssertTrue(Utils.equals(tor.game.isSolutionCracked(), false));
    }

    public String toString() {
        return "GameTest{}";
    }
}
