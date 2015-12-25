import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author VictorSakae
 * Problem A. Store Credit
 * https://code.google.com/codejam/contest/351101/dashboard
 */
public class StoreCredit {
	
	class TestCase {
		int amount;
		int items;
		int[] prices;
		
	}
	
	TestCase[] tests;
	
	/**
	 * @param Array to be sorted
	 * @return original indexes
	 */
	static int[] sort(int[] array) {
		int[] indexes = new int[array.length];
		for (int i = 0; i < indexes.length; i++) {
			indexes[i] = i;
		}
		QuickSort(array, indexes, 0, (array.length - 1));
		return indexes;
	}
	
	static void QuickSort(int[] array, int[] indexes, int lo, int hi) {
		if (lo < hi) {
			int q = partition (array, indexes, lo, hi);
			QuickSort(array, indexes, lo, (q - 1));
			QuickSort(array, indexes, (q + 1), hi);
		}
	}
	
	static int partition(int[] array, int[] indexes, int lo, int hi) {
		int i, j, temp;
		int x = array[hi];
		int y = indexes[hi];
		i = lo;
		j = hi - 1;
		while (i <= j) {
			if(array[i] <= x) {
				i++;
			} else if (array[j] > x) {
				j--;
			} else { // trocar A[i] e A[j]
				temp = array[i];
				array[i] = array[j];
				array[j] = temp;
				temp = indexes[i];
				indexes[i] = indexes[j];
				indexes[j] = temp;
			}
		}
		array[hi] = array[i]; // reposicionar o pivô
		array[i] = x;
		indexes[hi] = indexes[i];
		indexes[i] = y;
		return i;
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
			
			StoreCredit sc = new StoreCredit();
			sc.tests = new TestCase[Integer.valueOf(br.readLine())];
			int[] results = new int[sc.tests.length * 2];
			
			for (int i = 0; i < sc.tests.length; i++) {
				TestCase testCase = sc.new TestCase();
				testCase.amount = Integer.valueOf(br.readLine());
				testCase.items = Integer.valueOf(br.readLine());
				testCase.prices = new int[testCase.items];
				int aux = 0;
				for (String s : br.readLine().split("\\s")) {
					testCase.prices[aux] = Integer.valueOf(s);
					aux++;
				}
				sc.tests[i] = testCase;
			}
			br.close();

			// core 
			for (int j = 0; j < sc.tests.length; j++) {
				 int[] index = sort(sc.tests[j].prices);
				 int a = 0;
				 int b = sc.tests[j].prices.length - 1;
				 while (sc.tests[j].amount - sc.tests[j].prices[a] != sc.tests[j].prices[b]) {
					 if (sc.tests[j].amount - sc.tests[j].prices[a] > sc.tests[j].prices[b]) {
						 a++;
					 } else {
						 b--;
					 }
				 } 
				 results[j * 2] = index[a];
				 results[(j * 2) + 1] = index[b];
			}
			
			// Format output
			StringBuilder output = new StringBuilder();
			for (int k = 0; k < sc.tests.length; k++) {
				output.append("Case #" + (k + 1) + ": ");
				if (results[k * 2 + 1] < results[k * 2]) {
					output.append((results[k * 2 + 1] + 1) + " " + (results[k * 2] + 1) + "\n");
				} else {
					output.append((results[k * 2] + 1) + " " + (results[k * 2 + 1] + 1) + "\n");
				}
			}
			
			// Write output file
			BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(output_file));
			bufferedWriter.write(output.toString());
			bufferedWriter.close();
			
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
