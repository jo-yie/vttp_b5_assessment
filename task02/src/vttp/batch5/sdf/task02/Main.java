package vttp.batch5.sdf.task02;

import java.io.*;

public class Main {

	public static void main(String[] args) throws Exception {

		File tttFile = null;

		// accept command line argument 
		if (args.length == 0) {
			System.out.println("TTT file not provided");
			System.exit(0);

		} else if (args.length > 0) {
			tttFile = new File(args[0]);

			if (!tttFile.exists()) {
				System.out.println("TTT file does not exist");
				System.exit(0);

			} else {
				System.out.println("Processing: " + args[0] + "\n");

			}

		}

		// instantiate Board class 
		Board b = new Board(tttFile); 

		// create and return TTT board 
		b.createBoard();
		b.returnBoard();

		if (b.checkValidTurn()) {
			// read board, return all empty pos 
			b.returnEmptyPos();

			// get utility of each pos 
			b.getUtility();

			// return utility 
			b.returnUtility();
		} else {
			System.out.println("Not X's turn");
		}

	}
}
