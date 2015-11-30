package MFES;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Player {
    public String name;
    private Number myAttempts = 1L;
    private Number points = 0L;

    public Player(final String n) {
        cg_init_Player_1(n);
    }

    public Player() {
    }

    public void cg_init_Player_1(final String n) {
        name = n;

        return;
    }

    public void addAttempts() {
        myAttempts = myAttempts.longValue() + 1L;
    }

    public void addPoints(final Number inc) {
        points = points.longValue() + inc.longValue();
    }

    public Number getPoints() {
        return points;
    }

    public Number getAttempts() {
        return myAttempts;
    }

    public String toString() {
        return "Player{" + "name := " + Utils.toString(name) +
        ", myAttempts := " + Utils.toString(myAttempts) + ", points := " +
        Utils.toString(points) + "}";
    }
}
