package MFES;

import java.io.IOException;
import java.util.Scanner;

import org.overture.codegen.runtime.VDMMap;

public class Main {
	Game game;
	Tournament torneio;
	Scanner in = new Scanner(System.in);
	String s;

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
		while (true) {
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
				String team1, team2;
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
			} else if (s.equals("4")) {
				return;
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
			
			if(game.isGameOver())
			{
				System.out.println("Perdeu :(");
				return;
			}
			else{
				System.out.println("Tem + " +game.numberOfTriesRemaining()+ " tentativas");
				System.out.println("Acertou em " +game.calculateRightColorsInRightPlaces(s)+ " no lugar.");
				System.out.println("Acertou em " +game.calculateRightColorsInWrongPlaces(s)+ " fora do lugar.");
				
			}

		}
	}
	
	public void playGamePlayer(){
		
		while (!s.equals("exit")) {
			try {
				String[] cls = new String[] {"cmd.exe", "/c", "cls"};
				Runtime.getRuntime().exec(cls); 
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Code Breaker: " + game.getCodeBreaker().getName());
			System.out.println("Solution: " + game.getSolution());
			System.out.println("Nova tentativa: ");
			s = in.nextLine();
			game.makeAPlay(s);
			if (game.isSolutionCracked() && game.getMatches().intValue() < 1) {
				System.out.println("Ganhou!!");
				return;
			}
			
			if(game.isGameOver())
			{
				game.changePlayer();
				game.winGame();
				System.out.println("New Code:");
				s = in.nextLine();
				game.setSolution(s);
			}
			else{
				System.out.println("Tem + " +game.numberOfTriesRemaining()+ " tentativas");
				System.out.println("Acertou em " +game.calculateRightColorsInRightPlaces(s)+ " no lugar.");
				System.out.println("Acertou em " +game.calculateRightColorsInWrongPlaces(s)+ " fora do lugar.");
				
			}

		}
	}

	public void playTournament(){
		
	}
}
