import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author VictorSakae
 * Problem A. Minimum Scalar Product
 * https://code.google.com/codejam/contest/32016/dashboard
 */

public class MinimumScalarProduct {

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
			Scanner sc = new Scanner(new FileReader(input_file));
			int testCases = sc.nextInt();
			long[] output_data = new long[testCases];

			for (int i = 0; i < testCases; i++) {
				int size = sc.nextInt();
				
				int[] vector_one = new int[size];
				int[] vector_two = new int[size];
				
				for (int j = 0; j < vector_one.length; j++) {
					vector_one[j] = sc.nextInt();
				}
				for (int j = 0; j < vector_two.length; j++) {
					vector_two[j] = sc.nextInt();
				}

				// core
				Arrays.sort(vector_one);
				Arrays.sort(vector_two);
				
				long minimum = 0;
				
				for (int k = 0; k< size; k++) {
					long v1 = vector_one[k];
					long v2 = vector_two[(size - 1) - k];
					minimum = minimum + (v1 * v2);
				}
				output_data[i] = minimum;	
			}
			sc.close();
			
			// Format output
			StringBuilder sb = new StringBuilder();
			for (int n = 0; n < output_data.length; n++) {
				sb.append("Case #");
				sb.append(n + 1);
				sb.append(": ");
				sb.append(output_data[n]);
				sb.append("\n");
			}
			
			// Write output
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
