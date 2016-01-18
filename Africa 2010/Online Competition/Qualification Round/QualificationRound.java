import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author VictorSakae
 * Problem C. Qualification Round
 * https://code.google.com/codejam/contest/438101/dashboard#s=p2&a=2
 */

public class QualificationRound {

	TestCase[] tests;
	
	class TestCase {
		long[] problems;
		int contestants;
		
		public TestCase (int P, int C) {
			problems = new long[P];
			contestants = C;
		}				
	}
	
	static void QuickSort(long[] array, int lo, int hi) {
		if (lo < hi) {
			//System.out.println("hi: " + hi);
			int q = partition(array, lo, hi);
			QuickSort(array, lo, (q - 1));
			QuickSort(array, (q + 1), hi);	
		}
	}
	
	static int partition (long[] array, int lo, int hi) {
		int i, j;
		long x, temp;
		i = lo;
		j = hi - 1;
		x = array[hi];
		while (i <= j) {
			if (array[i] <= x) {
				i++;
			} else if (array[j] > x) {
				j--;
			} else {
				temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				i++; j--;
			}
		}
		array[hi] = array[i];
		array[i] = x;
		return i;
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String input_file = "C-small-practice.in";
		String output_file = "C-small-practice.out";
//		String input_file = "C-large-practice.in";
//		String output_file = "C-large-practice.out";
		
		try {
			// Read input file
			BufferedReader br = new BufferedReader(new FileReader(input_file));
			QualificationRound qr = new QualificationRound();
			qr.tests = new TestCase[Integer.valueOf(br.readLine())];
			for (int i = 0; i < qr.tests.length; i++) {
				String[] data = br.readLine().split("\\s");
				 TestCase tc = qr.new TestCase(Integer.valueOf(data[0]), Integer.valueOf(data[1]));
				for (int j = 0; j < tc.problems.length; j++) {
					tc.problems[j] = Long.valueOf(data[j + 2]);
				}
				qr.tests[i] = tc;
			}
			br.close();
			
			// core
			long[] output_data = new long[qr.tests.length];
			for (int k = 0; k < qr.tests.length; k++) {
				long result = 0;
				TestCase tc = qr.tests[k];
				
				QuickSort(tc.problems, 0, (tc.problems.length - 1));
				while (tc.problems[tc.problems.length - tc.contestants] > 0) {					
					for (int l = tc.problems.length - tc.contestants; l < tc.problems.length; l++) {
						tc.problems[l]--;
					}
					result++;
					QuickSort(tc.problems, 0, (tc.problems.length - 1));
				}
				output_data[k] = result;
			}
			
			// Format output
			StringBuilder sb = new StringBuilder();
			for (int z = 0; z < output_data.length; z++) {
				sb.append("Case #");
				sb.append(z + 1);
				sb.append(": ");
				sb.append(output_data[z]);
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
