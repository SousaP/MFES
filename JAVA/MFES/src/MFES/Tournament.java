package MFES;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Tournament {
    public VDMMap teams;
    public Game game;
    public Number trys;
    public Number size;

    public Tournament(final VDMMap t, final Number ts, final Number s) {
        cg_init_Tournament_1(Utils.copy(t), ts, s);
    }

    public Tournament() {
    }

    public void cg_init_Tournament_1(final VDMMap t, final Number ts,
        final Number s) {
        teams = Utils.copy(t);
        trys = ts;
        size = s;
    }

    public void startGame(final String code, final Player p1, final Player p2) {
        game = new Game(code, p1.getName(), p2.getName(), trys, size, 3L);
    }

    public VDMSet getTeamNames() {
        return MapUtil.dom(Utils.copy(teams));
    }

    public Number getTeamPoints(final String n) {
        return ((Team) Utils.get(teams, n)).getPontuacao();
    }

    public String dumpTournamentshipToString() {
        return "new Tournament(" + teamsToString() + ", " + gamesToString() +
        ")";
    }

    private String teamsToString() {
        String string = "TeamName";

        for (Iterator iterator_7 = getTeamNames().iterator();
                iterator_7.hasNext();) {
            String n = (String) iterator_7.next();
            string = string + ";" + n;
        }

        return string;
    }

    private String gamesToString() {
        String string = "Games";

        for (Iterator iterator_8 = getTeamNames().iterator();
                iterator_8.hasNext();) {
            String n = (String) iterator_8.next();
            string = string + ";" + n;
        }

        return string;
    }

    public String toString() {
        return "Tournament{" + "teams := " + Utils.toString(teams) +
        ", game := " + Utils.toString(game) + ", trys := " +
        Utils.toString(trys) + ", size := " + Utils.toString(size) + "}";
    }
}
