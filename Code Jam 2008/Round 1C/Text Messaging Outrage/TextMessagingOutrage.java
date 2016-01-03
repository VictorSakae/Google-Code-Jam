import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author VictorSakae
 * Problem A. Text Messaging Outrage
 * https://code.google.com/codejam/contest/32015/dashboard
 */

public class TextMessagingOutrage {

	TestCase[] tests;
	
	class TestCase {
		int[][] keypad;
		int[] frequency;
		
		public TestCase(int p, int k, int l) {
			keypad = new int[p][k];
			frequency = new int[l];
		}
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
			TextMessagingOutrage tmo = new TextMessagingOutrage();
			tmo.tests = new TestCase[Integer.valueOf(br.readLine())];
			for (int i = 0; i < tmo.tests.length; i++) {
				String[] line_args = br.readLine().split(" ");
				TestCase testCase = tmo.new TestCase(Integer.valueOf(line_args[0]), Integer.valueOf(line_args[1]), Integer.valueOf(line_args[2]));
				line_args = br.readLine().split(" ");
				for (int j = 0; j < line_args.length; j++) {
					testCase.frequency[j] = Integer.valueOf(line_args[j]);
				}
				tmo.tests[i] = testCase;
			}
			br.close();
			
			// core
			long[] output_data = new long[tmo.tests.length];
			for (int k = 0; k < tmo.tests.length; k++) {
				Arrays.sort(tmo.tests[k].frequency);
				int pos = tmo.tests[k].frequency.length - 1;
				long sum = 0;
				for (int a = 0; a < tmo.tests[k].keypad.length; a++) {
					for (int b = 0; b < tmo.tests[k].keypad[a].length; b++) {
//						tmo.tests[k].keypad[a][b] = tmo.tests[k].frequency[pos];
						if (pos >= 0) {
							sum = sum + ((a + 1) * tmo.tests[k].frequency[pos]);
							pos--;	
						}
					}
				}
				
				output_data[k] = sum;
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
