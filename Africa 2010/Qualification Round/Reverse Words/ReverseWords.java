import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author VictorSakae
 * Problem B. Reverse Words
 * https://code.google.com/codejam/contest/351101/dashboard#s=p1
 */

public class ReverseWords {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		String input_file = "B-small-practice.in";
//		String output_file = "B-small-practice.out";
		String input_file = "B-large-practice.in";
		String output_file = "B-large-practice.out";
		

		try {
			// Read input file
			BufferedReader br = new BufferedReader(new FileReader(input_file));
			String[] input_data = new String[Integer.valueOf(br.readLine())];
			for (int i = 0; i < input_data.length; i++) {
				input_data[i] = br.readLine();
			}
			br.close();
			
			// core
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < input_data.length; j++) {
				String[] words = input_data[j].split("\\s");
				String temp;
				for (int k = 0; k < (words.length / 2); k++) {
					temp = words[(words.length - 1 - k)];
					words[(words.length - 1 - k)] = words[k];
					words[k] = temp;
				}
				
				// Format output
				sb.append("Case #" + (j + 1) + ": ");
				for (int w = 0; w < words.length; w++) {
					sb.append(words[w] + " ");
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
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
