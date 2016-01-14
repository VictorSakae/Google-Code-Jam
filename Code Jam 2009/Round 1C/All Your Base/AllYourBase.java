import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 * @author VictorSakae
 * Problem A. All Your Base
 * https://code.google.com/codejam/contest/189252/dashboard
 */

public class AllYourBase {

	/**
	 * @param args
	 */
	
	static long exp (int base, int ele) {
		long result = 1;
		for (int i = 0; i < ele; i++) {
			result = result * base;
		}
		return result;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input_file = "A-small-practice.in";
		String output_file = "A-small-practice.out";
//		String input_file = "A-large-practice.in";
//		String output_file = "A-large-practice.out";
		
		try {
			// Read input file
			BufferedReader br = new BufferedReader(new FileReader(input_file));
			String[] input_data = new String[Integer.valueOf(br.readLine())];
			for (int i = 0; i < input_data.length; i++) {
				input_data[i] = br.readLine();
			}			
			br.close();

			// core
			long[] output_data = new long[input_data.length];
			
			for (int j = 0; j < input_data.length; j++) {
				String msg = input_data[j];
				int[] msgTrans = new int[msg.length()];
				int[] mapSymbols = new int[36];
				Arrays.fill(mapSymbols, -1);
				int digit = 0;
				// map symbols
				for (int k = 0; k < msg.length(); k++) {
					int pos = ((msg.charAt(k) - 'a') < 0) ? Character.getNumericValue(msg.charAt(k)) : ((msg.charAt(k) - 'a') + 10);
					if (mapSymbols[pos] < 0) {
						// cant start with a zero
						if (k == 0) {
							mapSymbols[pos] = 1;
						} else {
							mapSymbols[pos] = (digit == 1) ? 0 : digit; // switch 1 to 0
																			// digit 1 (one) is used for the first symbol/char
						}
						digit++;							
					}
					msgTrans[k] = mapSymbols[pos];
				}
				
				// convert to decimal
				long result = 0;
				int base = (digit < 2) ? 2 : digit; // base 1 is not allowed
				for (int l = 0; l < msgTrans.length; l++) {
					long exp = msgTrans[l] * exp(base, msgTrans.length - 1 - l);
					result = result + exp;
				}
				output_data[j] = result;
			}
			
			// Format output 
			StringBuilder sb = new StringBuilder();
			for (int m = 0; m < output_data.length; m++) {
				sb.append("Case #");
				sb.append(m + 1);
				sb.append(": ");
				sb.append(output_data[m]);
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