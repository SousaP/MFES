package MFES;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Team {
    private String name;
    private Player p1;
    private Player p2;

    public Team(final String n, final Player p_1, final Player p_2) {
        cg_init_Team_1(n, p_1, p_2);
    }

    public Team() {
    }

    public void cg_init_Team_1(final String n, final Player p_1,
        final Player p_2) {
        p1 = p_1;
        p2 = p_2;
        name = n;
    }

    public Number getPontuacao() {
        return p1.getPoints().longValue() + p2.getPoints().longValue();
    }

    public Player getPlayer1() {
        return p1;
    }

    public Player getPlayer2() {
        return p2;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Team{" + "name := " + Utils.toString(name) + ", p1 := " +
        Utils.toString(p1) + ", p2 := " + Utils.toString(p2) + "}";
    }
}
