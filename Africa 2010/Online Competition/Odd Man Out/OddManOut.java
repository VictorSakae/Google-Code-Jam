import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author VictorSakae
 * Problem A. Odd Man Out
 * https://code.google.com/codejam/contest/438101/dashboard
 */

public class OddManOut {

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
			int testCases = Integer.valueOf(br.readLine());
			List<Integer[]> input_data = new ArrayList<Integer[]>();
			for(int i = 0; i < testCases; i++) {
				Integer[] iv = new Integer[Integer.parseInt(br.readLine())];
				String[] gst = br.readLine().split(" ");
				for (int j = 0; j < gst.length; j++) {
					iv[j] = Integer.parseInt(gst[j]);
				}
				input_data.add(iv);
			}
			br.close();
			
			// core
			int[] output_data = new int[testCases];
			for (int k = 0; k < testCases; k++) {
				Map<Integer, Integer> map = new HashMap();
				for (int i = 0; i < input_data.get(k).length; i++) {
					if (map.containsKey(input_data.get(k)[i])) {
						map.remove(input_data.get(k)[i]);
					} else {
						map.put(input_data.get(k)[i], i);
					}
				}
				output_data[k] = (Integer)map.keySet().toArray()[0];
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
