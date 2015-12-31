import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author VictorSakae
 * Problem A. File Fix-it
 * https://code.google.com/codejam/contest/635101/dashboard
 */

public class FileFixIt {

	TestCase[] tests;
	
	class TestCase {
		String[] paths_exists;
		String[] paths_news;
		
		public TestCase(int N, int M) {
			paths_exists = new String[N];
			paths_news = new String[M];
		}
	}

	
	void readInput(String input_file) {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(input_file));
			tests = new TestCase[Integer.valueOf(br.readLine())];
			for (int i = 0; i < tests.length; i++) {
				String[] line = br.readLine().split("\\s");
				tests[i] = new TestCase(Integer.valueOf(line[0]), Integer.valueOf(line[1]));
				for (int j = 0; j < tests[i].paths_exists.length; j++) {
					tests[i].paths_exists[j] = br.readLine();
				}
				for (int k = 0; k < tests[i].paths_news.length; k++) {
					tests[i].paths_news[k] = br.readLine();
				}
				
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	void writeOutput(String output_file, String str) {
		BufferedWriter bw;
		try {
			bw = new BufferedWriter(new FileWriter(output_file));
			bw.write(str);
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
		// Read input file
		FileFixIt ffi = new FileFixIt();
		ffi.readInput(input_file);
		
		// core
		int[] output_data = new int[ffi.tests.length];
		for (int i = 0; i < ffi.tests.length; i++) {
			Directory root = new Directory("/");
			Directory current = root;
			for (int j = 0; j < ffi.tests[i].paths_exists.length; j++) {
				String[] path = ffi.tests[i].paths_exists[j].split("/");
				current = root;
				for (int k = 1; k < path.length; k++) {
					if (!current.isSub(path[k])) {
						current.newFolder(path[k]);
					}
					current = current.getSub(path[k]);
				}
			}
			
			int cont = 0;
			for (int l = 0; l < ffi.tests[i].paths_news.length; l++) {
				String[] path = ffi.tests[i].paths_news[l].split("\\/");
				current = root;
				for (int m = 1; m < path.length; m++) {
					if (!current.isSub(path[m])) {
						current.newFolder(path[m]);
						cont++;
					}
					current = current.getSub(path[m]);	
				}
			}

			output_data[i] = cont;
		}

		// Format output
		StringBuilder sb = new StringBuilder();
		for (int n = 0; n < output_data.length; n++) {
			sb.append("Case #");
			sb.append((n + 1));
			sb.append(": ");
			sb.append(output_data[n]);
			sb.append("\n");
		}
		
		// Write output
		ffi.writeOutput(output_file, sb.toString());

	}

}
