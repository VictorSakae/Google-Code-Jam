import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author VictorSakae
 * Problem A. Rotate
 * https://code.google.com/codejam/contest/544101/dashboard
 */

public class Rotate {

	TestCase[] tests;
	
	class TestCase {
		int pieces;
		char[][] board;
		
		public TestCase(int k, int l) {
			board = new char[k][k];
			pieces = l;			
		}
	
		public void rotate() {
			char temp;
			for (int h = 0; h < board.length; h++) {
				for (int i = 0; i < board.length; i++) {
					for (int j = (board.length - 1); j > 0; j--) {
						if (board[i][j] == '.') {
							temp = board[i][j];
							board[i][j] = board[i][j - 1];
							board[i][j - 1] = temp;	
						}
					}
				}	
			}
		}
	
		public String winners(int k) {
			String result = "Neither";
			for (int i = 0; i < board.length; i++) {
				for (int j = 0; j < board[i].length; j++) {
					if (board[i][j] != '.') {
						if (checkHorizontal(i, j) || checkVertical(i, j) || checkDiagonalAsc(i, j) || checkDiagonalDesc(i, j)) {
							String player = (board[i][j] == 'R') ? "Red" : "Blue";
							result = (result.equals("Neither") || result.equals(player)) ? player : "Both";
						
							if (result.equals("Both")) {
								return result;
							}	
						}	
					}
				}
			}
			
			return result;
		}
		
		boolean checkHorizontal(int i, int j) {
			boolean result = false;
			if ((j + pieces - 1) < board[i].length) {
				for (int n = 0; n < pieces; n++) {
					if (board[i][j] != board[i][j + n]) {
						return false;
					}
				}
				result = true;
			}
			return result;
		}
		
		boolean checkVertical(int i, int j) {
			boolean result = false;
			if ((i + pieces - 1) < board.length) {
				for (int n = 0; n < pieces; n++) {
					if (board[i][j] != board[i + n][j]) {
						return false;
					}
				}
				result = true;
			}
			return result;
		}
		
		boolean checkDiagonalAsc(int i, int j) {
			boolean result = false;
			if (((i - pieces + 1) >=  0) &&	((j + pieces - 1) < board[i].length)){
				for (int n = 0; n < pieces; n++) {
					if (board[i][j] != board[i - n][j + n]) {
						return false;
					}
				}
				result = true;
			}
			return result;
		}
		
		boolean checkDiagonalDesc(int i, int j) {
			boolean result = false;
			if (((i + pieces - 1) <  board.length) && ((j + pieces - 1) < board[i].length)){
				for (int n = 0; n < pieces; n++) {
					if (board[i][j] != board[i + n][j + n]) {
						return false;
					}
				}
				result = true;
			}
			return result;
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input_file = "A-small-practice.in";
		String output_file = "A-small-practice.out";
//		String input_file = "A-large-practice.in";
//		String output_file = "A-large-practice.out";
		
		try {
			// Read input file
			BufferedReader br = new BufferedReader(new FileReader(input_file));
			Rotate rt = new Rotate();
			rt.tests = new TestCase[Integer.valueOf(br.readLine())];
			for (int i = 0; i < rt.tests.length; i++) {
				String[] line_args = br.readLine().split(" ");
				TestCase tc = rt.new TestCase(Integer.valueOf(line_args[0]), Integer.valueOf(line_args[1]));
				for (int j = 0; j < tc.board.length; j++) {
					char[] row = br.readLine().toCharArray();
					for (int k = 0; k < tc.board[j].length; k++) {
						tc.board[j][k] = row[k];
					}
				}
				rt.tests[i] = tc;
			}
			br.close();
			
			// core
			String[] output_data = new String[rt.tests.length];
			for (int l = 0; l < rt.tests.length; l++) {
				rt.tests[l].rotate();				
				String result = rt.tests[l].winners(rt.tests[l].pieces);
				output_data[l] = result;
			}
			
			
			// Format output
			StringBuilder sb = new StringBuilder();
			for (int n = 0; n < output_data.length; n++) {
				sb.append("Case #");
				sb.append(n + 1);
				sb.append(": ");
				sb.append(output_data[n]);
				sb.append("\n");
			}
			
			// Write output file
			BufferedWriter bw = new BufferedWriter(new FileWriter(output_file));
			bw.write(sb.toString());
			bw.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
