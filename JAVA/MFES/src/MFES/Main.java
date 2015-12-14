package MFES;

import java.io.IOException;
import java.util.Scanner;

import org.overture.codegen.runtime.VDMMap;

public class Main {
	Game game;
	Tournament torneio;
	Scanner in = new Scanner(System.in);
	String s;
	String team1, team2;

	public static void main(String[] args) {
		System.out.println("##################");
		System.out.println("###   Master   ###");
		System.out.println("###    Mind    ###");
		System.out.println("##################");
		new Main();

	}

	Main() {
		s = "";
		Menu();
	}

	public void Menu() {
		while (!s.equals("4")) {
			System.out.println("1-Bots");
			System.out.println("2-Players");
			System.out.println("3-Tournament");
			System.out.println("4-Exit");
			s = in.nextLine();
			/*
			 * (final String correctPlay, final String m, final String b, final
			 * Number trys, final Number size, final Number partidas)
			 */
			if (s.equals("1")) {
				int trys;
				int size;
				int matches;

				System.out.println("Tentativas:");
				trys = Integer.parseInt(in.nextLine());
				System.out.println("NºPeças:");
				size = Integer.parseInt(in.nextLine());
				System.out.println("Partidas:");
				matches = Integer.parseInt(in.nextLine());
				game = new Game(trys, size, matches);
				playGameBot();
			} else if (s.equals("2")) {
				String code;
				String p1;
				String p2;
				int trys;
				int size;
				int matches;

				System.out.println("Code:");
				code = in.nextLine();
				System.out.println("Code Maker Name:");
				p1 = in.nextLine();
				System.out.println("Code Breaker Name:");
				p2 = in.nextLine();
				System.out.println("Tentativas:");
				trys = Integer.parseInt(in.nextLine());
				System.out.println("NºPeças:");
				size = Integer.parseInt(in.nextLine());
				System.out.println("Partidas:");
				matches = Integer.parseInt(in.nextLine());
				game = new Game(code, p1, p2, trys, size, matches);
				playGamePlayer();
			} else if (s.equals("3")) {
				Team t1, t2;
				String p1, p3;
				String p2, p4;
				int trys;
				int size;
				int matches;

				System.out.println("Nome team1:");
				team1 = in.nextLine();
				System.out.println("Player1:team1 Name:");
				p1 = in.nextLine();
				System.out.println("Player2:team1 Name:");
				p2 = in.nextLine();
				t1 = new Team(team1, new Player(p1), new Player(p2));

				System.out.println("Nome team2:");
				team2 = in.nextLine();
				System.out.println("Player1:team2 Name:");
				p3 = in.nextLine();
				System.out.println("Player2:team2 Name:");
				p4 = in.nextLine();
				t2 = new Team(team2, new Player(p3), new Player(p4));
				VDMMap map = new VDMMap();
				map.putIfAbsent(t1.getName(), t1);
				map.putIfAbsent(t2.getName(), t2);

				System.out.println("Tentativas:");
				trys = Integer.parseInt(in.nextLine());
				System.out.println("Partidas:");
				matches = Integer.parseInt(in.nextLine());

				torneio = new Tournament(map, trys, matches);
				playTournament();
			}
		}
	}

	public void playGameBot() {
		while (!s.equals("exit")) {
			System.out.println("Solution: " + game.getSolution());
			System.out.println("Nova tentativa: ");
			s = in.nextLine();
			game.makeAPlay(s);
			if (game.isSolutionCracked()) {
				System.out.println("Ganhou!!");
				return;
			}

			if (game.isGameOver()) {
				System.out.println("Perdeu :(");
				return;
			} else {
				System.out.println("Tem + " + game.numberOfTriesRemaining() + " tentativas");
				System.out.println("Acertou em " + game.calculateRightColorsInRightPlaces(s) + " no lugar.");
				System.out.println("Acertou em " + game.calculateRightColorsInWrongPlaces(s) + " fora do lugar.");

			}

		}
	}

	public void playGamePlayer() {

		while (!s.equals("exit")) {
			System.out.println("\n\n\n\n\n\n");

			System.out.println("Code Breaker: " + game.getCodeBreaker().getName());
			System.out.println("Solution: " + game.getSolution());
			System.out.println("Nova tentativa: ");
			s = in.nextLine();
			game.makeAPlay(s);
			if (game.isSolutionCracked() && game.getMatches().intValue() < 1) {
				System.out.println("Ganhou!!");
				return;
			}

			if (game.isGameOver()) {
				game.changePlayer();
				game.winGame();
				System.out.println("New Code:");
				s = in.nextLine();
				game.setSolution(s);
			} else {
				System.out.println("Tem + " + game.numberOfTriesRemaining() + " tentativas");
				System.out.println("Acertou em " + game.calculateRightColorsInRightPlaces(s) + " no lugar.");
				System.out.println("Acertou em " + game.calculateRightColorsInWrongPlaces(s) + " fora do lugar.");

			}

		}
	}

	public void playTournament() {
		String teamPlaying = team1;
		String notPlaying = team2;
		Player Player_team1;
		Player Player_team2;
		while (!s.equals("exit")) {
			System.out.println("\n\n\n\n\n\n");

			System.out.println("Code Maker? Team " + teamPlaying);
			System.out
					.println("Jogadores disponiveis: " + ((Team) torneio.teams.get(teamPlaying)).getPlayer1().getName()
							+ " " + ((Team) torneio.teams.get(teamPlaying)).getPlayer2().getName());

			s = in.nextLine();

			if (s.equals(((Team) torneio.teams.get(teamPlaying)).getPlayer1().getName()))
				Player_team1 = ((Team) torneio.teams.get(teamPlaying)).getPlayer1();
			else
				Player_team1 = ((Team) torneio.teams.get(teamPlaying)).getPlayer2();

			System.out.println("Novo Code: ");
			String code = in.nextLine();

			System.out.println("Code Breaker? Team " + notPlaying);
			System.out.println("Jogadores disponiveis: " + ((Team) torneio.teams.get(notPlaying)).getPlayer1().getName()
					+ " " + ((Team) torneio.teams.get(notPlaying)).getPlayer2().getName());

			s = in.nextLine();

			if (s.equals(((Team) torneio.teams.get(notPlaying)).getPlayer1().getName()))
				Player_team2 = ((Team) torneio.teams.get(notPlaying)).getPlayer1();
			else
				Player_team2 = ((Team) torneio.teams.get(notPlaying)).getPlayer2();

			torneio.startGame(code, Player_team1, Player_team2);
			game = torneio.game;
			while (!game.isGameOver()) {
				System.out.println("Nova tentativa: ");
				s = in.nextLine();
				game.makeAPlay(s);
				if (game.isSolutionCracked() && game.getMatches().intValue() < 1) {
					System.out.println("Ganhou!!");
					System.out.println("Pontos equipa 1: " + "");
					return;
				} else {
					System.out.println("Tem + " + game.numberOfTriesRemaining() + " tentativas");
					System.out.println("Acertou em " + game.calculateRightColorsInRightPlaces(s) + " no lugar.");
					System.out.println("Acertou em " + game.calculateRightColorsInWrongPlaces(s) + " fora do lugar.");

				}
			}

			if (game.isGameOver()) {
				if (teamPlaying.equals(team1)) {
					teamPlaying = team2;
					notPlaying = team1;
				} else {
					teamPlaying = team1;
					notPlaying = team2;

				}
				
				torneio.game.winGame();
				
				if(teamPlaying.equals(team1))
					Player_team1 = torneio.game.getCodeMaker();
				else
					Player_team2 = torneio.game.getCodeMaker();
				
				System.out.println(Player_team1.getName() + ": " + Player_team1.getPoints());
				System.out.println(Player_team2.getName() + ": " + Player_team2.getPoints());
				System.err.println("Pontuaçoes: ");
				System.err.println(teamPlaying + ": " + ((Team) torneio.teams.get(teamPlaying)).getPontuacao());
				System.err.println(notPlaying + ": " + ((Team) torneio.teams.get(notPlaying)).getPontuacao());

			}
		}
	}
}
