import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author VictorSakae
 * Problem A. Alien Language
 * https://code.google.com/codejam/contest/90101/dashboard#s=p0
 */

public class AlienLanguage {

	static int core (String str, int l, String[] words) {
		// Build the bitmap
		int[][] bitmap = new int[l][26];
		int pos = 0;
		boolean isGroup = false;
		for (int c = 0; c < str.length(); c++) {
			if (isGroup) {
				if (str.charAt(c) == ')') {
					 isGroup = false;
					 pos++;
				} else {
					bitmap[pos][str.charAt(c) - 'a'] = 1;
				}
			} else {
				if (str.charAt(c) == '(') {
					 isGroup = true;
				} else {
					bitmap[pos][str.charAt(c) - 'a'] = 1;
					pos++;
				}
			}
		}

		// Count how many words match
		int cont = 0;
		boolean flag = false;
		for (int i = 0; i < words.length; i++) {
			flag = true;
			pos = 0;
			while (flag && pos < l) {
				if (bitmap[pos][words[i].charAt(pos) - 'a'] != 1) {
					flag = false;
				}
				pos++;
			}
			if (flag) {
				cont++;
			}
		}
		
		return cont;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String input_file = "A-small-practice.in";
//		String output_file = "A-small-practice.out";
		String input_file = "A-large-practice.in";
		String output_file = "A-large-practice.out";
		
		try {
			// Read input file
			BufferedReader br = new BufferedReader(new FileReader(input_file));
			String[] first_line = br.readLine().split("\\s");
			int length = Integer.parseInt(first_line[0]);
			String[] words = new String[Integer.parseInt(first_line[1])];
			String[] testCases = new String[Integer.parseInt(first_line[2])];
			for (int i = 0; i < words.length; i++) {
				words[i] = br.readLine();
			}
			for (int i = 0; i < testCases.length; i++) {
				testCases[i] = br.readLine();
			}			
			br.close();
			
			// core
			int[] output_data = new int[testCases.length];
			Arrays.sort(words);
			for (int j = 0; j < testCases.length; j++) {
				output_data[j] = core(testCases[j], length, words);
			}
			
			// Format output
			StringBuilder sb = new StringBuilder();
			for (int l = 0; l < output_data.length; l++) {
				sb.append("Case #");
				sb.append(l + 1);
				sb.append(": ");
				sb.append(output_data[l]);
				sb.append("\n");
			}
			
			// Write output
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
