/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gameinterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import jp.co.csk.vdm.toolbox.VDM.CGException;
import mastermind.*;

/**
 *
 * @author joaoqalves
 */
public class Play {

    
static private void newGame(Game g, String teamA, String teamB) throws CGException {

        boolean error = false;
        String attempt = "";
        Scanner scanner = new Scanner (System.in);


        while(!g.getBoardPlayedByTeam(teamA).isGameOver() ||
               !g.getBoardPlayedByTeam(teamB).isGameOver()) {

            Vector attemptA = new Vector();
            Vector attemptB = new Vector();

            while(true) {
                error  = false;
                if(g.getBoardPlayedByTeam(teamA).isSolutionCracked()) break;

                System.out.println("\t" + teamA + " turn\n");
                System.out.println(g.getBoardPlayedByTeam(teamA));
                System.out.print(teamA + " attempt:");
                attempt = scanner.nextLine();

                for(int i = 0; i < attempt.length(); i++)
                    if(!Color.Colors.contains(attempt.charAt(i))) {

                        error = true;
                        System.err.println("Please choose a color from {r,g,b,y,o,m}");
                        break;

                    }

                if(!error && !attempt.isEmpty() && attempt.length() == Board.attemptLength)
                    break;
            }

            if(!g.getBoardPlayedByTeam(teamA).isSolutionCracked()) {
                for(int i = 0; i < attempt.length(); i++)
                    attemptA.add(attempt.charAt(i));

                g.getBoardPlayedByTeam(teamA).makeAPlay(attemptA);
                System.out.println(g.getBoardPlayedByTeam(teamA));
            }

            // -----------------------------------------------------------------

            while(true) {
                error  = false;
                if(g.getBoardPlayedByTeam(teamB).isSolutionCracked()) break;
                System.out.println("\t" + teamB + " turn\n");
                System.out.println(g.getBoardPlayedByTeam(teamB));
                System.out.print(teamB + " attempt:");
                attempt = scanner.nextLine();

                for(int i = 0; i < attempt.length(); i++)
                    if(!Color.Colors.contains(attempt.charAt(i))) {

                        error = true;
                        System.err.println("Please choose a colors from the list {r,g,b,y,o,m}");
                        break;

                    }

                if(!error && !attempt.isEmpty() && attempt.length() == Board.attemptLength)
                    break;
            }

            if(!g.getBoardPlayedByTeam(teamB).isSolutionCracked()) {
                for(int i = 0; i < attempt.length(); i++)
                    attemptB.add(attempt.charAt(i));

                g.getBoardPlayedByTeam(teamB).makeAPlay(attemptB);
                System.out.println(g.getBoardPlayedByTeam(teamB));
            }

        }

    }


static public void quickGame() {

        Championship championship;
        Game g;
        Scanner scanner = new Scanner (System.in);
        HashSet teams = new HashSet();

        String teamA;
        String teamB;

        do {

            while(true) {
                System.out.println("Name of Team A:");
                teamA = scanner.nextLine();
                if(!teamA.isEmpty() && !teams.contains(teamA)) break;
            }
            while(true) {
                System.out.println("Name of Team B:");
                teamB = scanner.nextLine();
                if(!teamB.isEmpty() && !teams.contains(teamB)) break;
            }

            if(teamA.compareTo(teamB) == 0)
                System.err.println("Please, choose different names for the teams!");

        }while(teamA.compareTo(teamB) == 0);


        teams.add(teamA);
        teams.add(teamB);

        try {

            championship = new Championship(teams);
            g = championship.addQuickGame(teamA, teamB);

            Play.newGame(g, teamA, teamB);

            System.out.println("Game over");
            System.out.println(teamA + " pontuation: " + championship.getScore(teamA));
            System.out.println(teamB + " pontuation: " + championship.getScore(teamB));

            if(championship.getScore(teamA) > championship.getScore(teamB))
                System.out.println("Winner: " + teamA);
            else if (championship.getScore(teamA) < championship.getScore(teamB))
                System.out.println("Winner: " + teamB);
            else
                System.out.println("It's a draw! Congratulations to both teams");



        } catch (CGException ex) {

            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);

        }

}


static public void championship() throws CGException {

    Championship championship = new Championship();
    Scanner scanner = new Scanner (System.in);
    Scanner scanner4 = new Scanner (System.in);
    int nTeams = 0;
    ArrayList<HashMap> games = new ArrayList<HashMap>();

    do {

        try {
        System.out.print("No. of Teams (even, >= 2): ");
        nTeams = scanner.nextInt();
        }
        catch(InputMismatchException e) {

            System.err.println("Please, the no. of teams should be even and >=2.");
            continue;

        }

        if(nTeams < 2 || nTeams%2 != 0)
            System.err.println("Please, the no. of teams should be even and >=2.");

    }while(nTeams < 2 || nTeams%2 != 0);

    HashSet teams = new HashSet();
    String _team;

    for(int i = 1; i <= nTeams; i++) {

        do {
            System.out.print("Team " + i +": ");
            _team = scanner4.nextLine();

            if(teams.contains(_team))
                System.err.println(_team + " already exists!");
        }
        while(_team.isEmpty() || teams.contains(_team));

        teams.add(_team);


    }

    championship.addTeams(teams);

    int selection = -1;

       do {

            System.out.println("----- CHAMPIONSHIP -----");

            teams = championship.getTeams();
            int maxPont = 0;
            String champion = "";
            boolean manyChampions = false;

            for(Object team: teams) {

                   if(championship.getScore(team.toString()) > maxPont) {

                       champion = team.toString();
                       maxPont = championship.getScore(team.toString());

                   }
                   else if(championship.getScore(team.toString()) == maxPont && maxPont != 0) {

                       manyChampions = true;

                   }

            }

            if(!champion.isEmpty()) {

                if(!manyChampions)
                    System.out.println("The actual champion is the team " + champion + " with " + maxPont + " points");
                else
                    System.out.println("Sorry, there are 2 or more teams equalised with " + maxPont + " points");
           }
            else
                System.out.println("There's no champion, right now");

            System.out.println("[1] New Game");
            System.out.println("[0] Exit");

            System.out.print("Selection: ");

            try {

                selection = scanner.nextInt();

            }
            catch(InputMismatchException e) {

                System.err.println("Please enter a valid option");
                continue;

            }

            switch(selection) {

                case 1:
                    boolean gameExist = false;
                    do {
                        String teamA;
                        String teamB;
                        String codefromAtoB;
                        String codefromBtoA;
                        boolean error = false;
                        HashMap game = new HashMap();
                        Scanner scanner2 = new Scanner(System.in);
                        Scanner scanner3 = new Scanner(System.in);

                        do {
                            while(true) {
                                System.out.println("Name of Team A:");
                                teamA = scanner2.nextLine();
                                if(!teamA.isEmpty() && teams.contains(teamA)) break;
                                else System.err.println("Please insert a team which is on the championship");
                            }
                            while(true) {
                                System.out.println("Name of Team B:");
                                teamB = scanner2.nextLine();
                                if(!teamB.isEmpty() && teams.contains(teamB)) break;
                                else System.err.println("Please insert a team which is on the championship");
                            }

                            if(teamA.compareTo(teamB) == 0)
                                System.err.println("Please, insert TWO DIFFERENT teams which are on the championship");

                        }while(teamA.compareTo(teamB) == 0);

                        do {
                            error = false;
                            System.out.println(teamA +  ", pick a code to the team " + teamB);
                            codefromAtoB = scanner3.nextLine();

                            for(int i = 0; i < codefromAtoB.length(); i++)
                                if(!Color.Colors.contains(codefromAtoB.charAt(i))) {

                                    System.err.println("Please choose a color from {r,g,b,y,o,m}");
                                    error = true;
                                    break;

                                }
                        }while(error);

                        do {
                            error = false;
                            System.out.println(teamB +  ", pick a code to the team " + teamA);
                            codefromBtoA = scanner3.nextLine();

                            for(int i = 0; i < codefromBtoA.length(); i++)
                                if(!Color.Colors.contains(codefromBtoA.charAt(i))) {

                                    System.err.println("Please choose a color from {r,g,b,y,o,m}");
                                    error = true;
                                    break;

                                }
                        }while(error);

                        game.put(teamA, codefromAtoB);
                        game.put(teamB, codefromBtoA);

                        for(HashMap g : games) {

                            if(g.containsKey(teamA) && g.containsKey(teamB))
                                gameExist = true;

                        }

                        if(gameExist) {
                            System.err.println("That game was already played");
                            gameExist = false;
                            break;
                        }

                        Game g = championship.addGame(game);
                        newGame(g, teamA, teamB);
                        System.out.println("Game over");
                        break;
                    }while(!gameExist);

                    
                    break;
                case 0:
                    break;
                default:
                    System.err.println("Please enter a valid option");
                    break;

            }

       }while(selection != 0);
    

}


}
