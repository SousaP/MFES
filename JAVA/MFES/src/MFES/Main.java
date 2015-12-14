package MFES;

import java.util.Scanner;

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
		while(true) {
			System.out.println("1-Bots");
			System.out.println("2-Players");
			System.out.println("3-Tournament");
			System.out.println("4-Exit");
			 s = in.nextLine();
			 /*
			  *  (final String correctPlay, final String m, final String b,
					        final Number trys, final Number size, final Number partidas)
			  */
			 if(s.equals("1"))
			 {
				 int trys;
				 int size;
				 int matches;	 
				 
				 System.out.println("Tentativas:");				
				 trys = Integer.parseInt(in.nextLine());
				 System.out.println("NºPeças:");				
				 size = Integer.parseInt(in.nextLine());
				 System.out.println("Partidas:");				
				 matches = Integer.parseInt(in.nextLine());
				 game = new Game(trys,size,matches);
				 playGame();
			 }
			 else if(s.equals("2"))
			 {
				 String code;
				 String p1;
				 String p2;
				 int trys;
				 int size;
				 int matches;
				 
				 System.out.println("Code:");				
				 code = in.nextLine();
				 System.out.println("Player 1 Name:");				
				 p1 = in.nextLine();	
				 System.out.println("Player 2 Name:");				
				 p2 = in.nextLine();	
				 System.out.println("Tentativas:");				
				 trys = Integer.parseInt(in.nextLine());
				 System.out.println("NºPeças:");				
				 size = Integer.parseInt(in.nextLine());
				 System.out.println("Partidas:");				
				 matches = Integer.parseInt(in.nextLine());
				 game = new Game(code,p1,p2,trys,size,matches);
			 }
			 else if(s.equals("3"))
			 {
				 String team1, team2;
				 String p1;
				 String p2;
				 int trys;
				 int size;
				 int matches;
				 
				 System.out.println("Nome team1:");				
				 team1 = in.nextLine();
				 System.out.println("Player 1 Name:");				
				 p1 = in.nextLine();	
				 System.out.println("Player 2 Name:");				
				 p2 = in.nextLine();	
				 System.out.println("Tentativas:");				
				 trys = Integer.parseInt(in.nextLine());;
				 System.out.println("Partidas:");				
				 matches = Integer.parseInt(in.nextLine());
				 //Team(final String n, final Player p_1, final Player p_2);
				 System.out.println("Nome da equipa:");
				 size = Integer.parseInt(in.nextLine());
			 }
			 else if(s.equals("4"))
			 {
				 return;
			 }
		}
	}

	public void playGame(){
		while(!s.equals("exit"))
		{
			System.out.println("Nova tentativa: ");
			
		}
	}

}
