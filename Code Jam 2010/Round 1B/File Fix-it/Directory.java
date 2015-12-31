import java.util.HashMap;

/**
 * @author VictorSakae
 * Problem A. File Fix-it
 * https://code.google.com/codejam/contest/635101/dashboard
 */

public class Directory {
	HashMap<String, Directory> directories = new HashMap<String, Directory>();
	Directory parent;
	private String name;

	public Directory(String name) {
		this.name = name;
	}
	
	boolean isSub(String str) {
		return directories.containsKey(str);
	}
	
	void newFolder(String newFolder) {
		directories.put(newFolder, new Directory(newFolder));
	}
	
	Directory getSub(String s) {
		return directories.get(s);
	}
}
