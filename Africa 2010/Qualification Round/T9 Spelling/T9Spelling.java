import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author VictorSakae
 * Problem C. T9 Spelling
 * https://code.google.com/codejam/contest/351101/dashboard#s=p2
 */

public class T9Spelling {

	static final String[] keypad = new String[26];

	static void mapLetters () {
		String result = "";
		int digit = 0;
		// A - R
		for (int i = 0; i <= 17; i++) { 
			digit = (i / 3) + 2;
			result = String.valueOf(digit);
			for (int j = 0; j < (i % 3); j++) {
				result = result + digit;
			};
			keypad[i] = result;
		}

		// S - Z
		for (int i = 18; i < keypad.length; i++) {
			if (i == 18) { // S
				result = "7777";
			} else if (i < 22) { // T - V
				digit = ((i - 1) / 3) + 2;
				result = String.valueOf(digit);
				for (int j = 0; j < ((i -1) % 3); j++) {
					result = result + digit;
				};
			} else { // W - Z
				digit = (((i + 6) / 4) + 2);
				result = String.valueOf(digit); 
				for (int j = 0; j < ((i + 6) % 4); j++) {
					result = result + digit;
				}
			}
			keypad[i] = result;
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//		String input_file = "C-small-practice.in";
		//		String output_file = "C-small-practice.out";
		String input_file = "C-large-practice.in";
		String output_file = "C-large-practice.out";

		try {
			// Read input file
			BufferedReader br = new BufferedReader(new FileReader(input_file));
			String[] input_data = new String[Integer.valueOf(br.readLine())];
			for (int i = 0; i < input_data.length; i++) {
				input_data[i] = br.readLine();
			}
			br.close();

			// core
			mapLetters();
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < input_data.length; j++) {
				String last = "";

				sb.append("Case #" + (j + 1) + ": ");
				for (char c : input_data[j].toCharArray()) {
					String s = (c == ' ') ? "0" : keypad[(c - 'a')];  
					if(!last.equals("")) {
						if ((Integer.valueOf(s) % 10) == (Integer.valueOf(last) % 10)) {
							sb.append(" ");
						}	
					}
					sb.append(s);
					last = s;
				}
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
