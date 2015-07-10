/**
 * 
 */
package haloGames;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 * @author david_000
 *
 */
public class HI_LOMOD {

	public static void main(String[] args) throws IOException {
		{
			//-- Var declarations start -- 
			Scanner input = new Scanner(System.in);
			String[] strHighscore = new String[4];
			int Score = 0;
			int Choice = 0;
			boolean valid = false;
			boolean end = false;
			//-- Var declarations end -- 

			try {
				strHighscore = ScoreReader(strHighscore);
			} catch (FileNotFoundException e) {
				// call method to create new save file when
				// file not found exception is triggered
				RNGesus(strHighscore);
				System.out.println("all praise RNGesus!");
			}

			// main flow of program
			do {
				Menu();
				do {
					try {
						Choice = input.nextInt();
						valid = true;
					} catch (Exception ex) {
						input.nextLine();
						System.out.println("Please enter 1, 2 or 3");
					}
				} while (valid != true);

				//descision system
				switch (choice(Choice)) {
				case 1:
					//gamplay flow
					Score = Playgame(input, Score);
					strHighscore = addScore(input, strHighscore, Score);
					ScoreKeeper(strHighscore);
					Score = 0;
					System.out.println("");
					break;
				case 2:
					//score display flow
					ScoreDisplay(strHighscore);
					System.out.println("");
					break;
				case 3:
					//game end
					System.exit(0);
					break;
				default:
					// error catcher
				}
			} while (end == false);
			input.close();
		}
	}

	public static void Menu() {
		System.out.println("Welcome to \"HI-LO HIGHROLLERS\" ");
		System.out.println("Would you like to:");
		System.out.println("________________________");
		System.out.println("|Play the game enter |(1|");
		System.out.println("|See the scores enter|(2|");
		System.out.println("|Quit the game enter |(3|");
		System.out.println("|-----------------------|");
		System.out.print("-->");
	}

	public static int choice(int men) {
		// menu selection switch
		switch (men) {
		case 1:
			return 1;
		case 2:
			return 2;
		case 3:
			return 3;
		default:
			return 4;
		}
	}

	public static int randomNumGen() {
		// Generates a random number between 0 and 12
		int num = (int) (Math.random() * (12 - 1)) + 1;

		// returns the random number
		return num;
	}

	public static int Playgame(Scanner input, int Score) throws IOException {
		int conRndNum = 0, initalNum = randomNumGen();
		String reply = null;
		System.out
				.println("Type 'H' for higher, 'L' for lower, 'quit' to quit and /help for help");

		System.out.println("_________________");
		System.out.println("|first number|" + initalNum + "|");
		System.out.println("-----------------");

		// While loop to check for input = "quit"
		while (reply != "quit") {
			conRndNum = randomNumGen();
			System.out
					.println("is the next number going to be higher or lower?");

			reply = input.next();
			// Get first character of user input
			char ans = reply.charAt(0);

			if (reply.equalsIgnoreCase("quit")) {
				break;
				// On Screen help system
			} else if (reply.equalsIgnoreCase("/help")) {
				System.out
						.println("If you think the next number is higher enter 'h'");

				System.out
						.println("If you think the next number is lower enter 'l' ");
			}

			// Code to check if users guess was right or wrong
			if (ans == 'H' || ans == 'h') {
				// checks the new number is higher than the previous number
				if (conRndNum >= initalNum) {
					Score++;
					System.out.println("correct it was " + conRndNum);
					initalNum = conRndNum;
				} else {
					System.out.println("wrong it was " + conRndNum);
					System.out.println("Thanks for playing");
					break;
				}
			} else if (ans == 'L' || ans == 'l') {// User Choses lower

				if (conRndNum <= initalNum) {
					Score++;
					System.out.println("correct it was " + conRndNum);
					initalNum = conRndNum;
				} else {
					System.out.println("wrong it was " + conRndNum);
					System.out.println("Thanks for playing");
					break;
				}

			}
		}
		System.out.println("You Scored:" + Score);
		// adds score to array
		return Score;
	}

	public static String[] addScore(Scanner input, String[] strHighscore,
			int Score) {
		int i = 0;

		String Sname, FScoreC;
		// Transfers ScoreArray scores into Sarn Array
		int[] Sarn = new int[4];
		// code below strips only int score values from score array
		Sarn[0] = Integer.parseInt(strHighscore[0].substring(4, 6));

		Sarn[1] = Integer.parseInt(strHighscore[1].substring(4, 6));

		Sarn[2] = Integer.parseInt(strHighscore[2].substring(4, 6));

		Sarn[3] = Integer.parseInt(strHighscore[3].substring(4, 6));
		// end of stripping code
		while (i < 4) {
			if (Score > Sarn[i]) {
				System.out.println("please enter a 3 letter name");
				Sname = input.next();
				// IF Score greater than 10 is to do with Score file formatting
				// it looks better with a 2 digit number under another 2 digit
				// number
				if (Score > 10) {
					FScoreC = (Sname + "," + Score);
					strHighscore[i] = FScoreC;
					break;
				} else {
					FScoreC = (Sname + "," + "0" + Score);
					strHighscore[i] = FScoreC;
					break;
				}
			} else {
				i++;

				if (i == 3 && Score < Sarn[i]) {
					System.out
							.println("Sorry you didn't get a high score :) Try harder next time");
				}
			}
		}
		return strHighscore;

	}

	public static void ScoreDisplay(String[] strHighscore) {
		int i = 0;
		int o = 0;
		String score;

		System.out.println("|Nam|SC|");

		while (i < 4) {
			strHighscore[i].length();

			while (strHighscore[i].charAt(o) != ',') {
				o++;
			}

			score = strHighscore[i].substring(0, o)
					+ "|"
					+ strHighscore[i]
							.substring(o + 1, strHighscore[i].length());

			i++;
			o = 0;

			System.out.println("|" + score + "|");
		}
	}

	public static String[] ScoreReader(String[] strHighscore)
			throws FileNotFoundException {
		// reads from score file
		FileInputStream in = new FileInputStream("score.hlsk");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));

		try {
			for (int j = 0; j < strHighscore.length; j++) {
				// Adds score to high score array
				strHighscore[j] = br.readLine();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			in.close(); // stops memory leaks
		} catch (IOException e) {
			e.printStackTrace();
		}

		return strHighscore;
	}

	public static void ScoreKeeper(String[] strHighscore) throws IOException {
		FileWriter fw = null;
		try {
			fw = new FileWriter("score.hlsk");// Makes or finds Score file
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 0; i < strHighscore.length; i++) {
			// Write to the Score file the current highscores in the array
			fw.write(strHighscore[i] + "\n");
		}
		fw.close();
	}

	public static void RNGesus(String[] strHighscore) throws IOException {
		int rGS;
		for (int i = 0; i < 4; i++) {
			char[] nam = new char[3]; // Random name holder array
			int j = 0;

			// generates random names
			while (j < 3) {
				nam[j] = (char) ((int) (Math.random() * ('z' - 'a')) + 'a');
				j++;
			}

			String name = new String(nam);
			rGS = randomNumGen();

			// formats socre in array (makes it look nice)
			if (rGS > 10) {
				strHighscore[i] = name + "," + rGS;
			} else {
				strHighscore[i] = name + "," + "0" + rGS;
			}
		}
		ScoreKeeper(strHighscore);
	}

}
