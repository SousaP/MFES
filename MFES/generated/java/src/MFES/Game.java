package MFES;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Game {
    private static String code = new String(new char[] { 'a' });
    private Boolean gameOver = false;
    private Boolean win = false;
    private Number maxAttempts = 8L;
    private Player codeMaker = new Player();
    private Player codeBreaker = new Player();

    public Game() {
        cg_init_Game_1();
    }

    public void cg_init_Game_1() {
        code = Piece.getRandomSequence(4L);

        return;
    }

    public Boolean isGameOver() {
        return gameOver;
    }

    public Boolean won() {
        return win;
    }

    public String toString() {
        return "Game{" + "gameOver := " + Utils.toString(gameOver) +
        ", win := " + Utils.toString(win) + ", maxAttempts := " +
        Utils.toString(maxAttempts) + ", codeMaker := " +
        Utils.toString(codeMaker) + ", codeBreaker := " +
        Utils.toString(codeBreaker) + ", code := " + Utils.toString(code) +
        "}";
    }
}
