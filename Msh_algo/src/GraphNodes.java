/* Mshabab Alrizah
 * This pace of code is ADjacency linked that take the graph as text file 
 * which contains lines represent a link associate each pair.
 * 
 *  it outputs a list of nodes. The key represent the node where the the value that map 
 *  to the key is the nighbors of that node  
 *  
 * you can chose if the graph direct or indirect when you enter the name of text file.
 * I made it easy to choose which one by entering number 1 directed graph and 2 for undirected 
 *  graph 
 * 
 * you are free to use this code for any purpose since it prepared as node with there adages.  
 * 
 * I add google guava to the project, please make sure to add it your library. 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

public class GraphNodes {

	// we use most of time static class because we just have only one class for
	// small amount of
	// mehtods and data.

	static String fileName = "null";

	static Map<String, List<String>> map = new HashMap<String, List<String>>();
	static List<String> nodos = new ArrayList<String>();

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		Scanner gType = new Scanner(System.in);

		System.out.print("Enter input graph file name: ");
		String fileName = sc.nextLine();
		System.out
				.print("Enter the type of graph, 1 directed and 2 for undirected : ");
		String graphtype = gType.nextLine();

		System.out.println(" ************************************ ");

		if (graphtype.equals("1")) {
			System.out
					.println(" The undirected graph has thw following nodes: ");
		} else
			System.out.println(" The directed graph has thw following nodes: ");

		int numberoflines = numberofline(fileName);
		String[][] dataArray = new String[numberoflines][2];

		loadData(dataArray, fileName);

		SetMultimap<String, String> multiMap = HashMultimap.create();

		for (int i = 0; i < dataArray.length; i++) {

			multiMap.put(dataArray[i][0], dataArray[i][1]);

			if (graphtype.equals("1")) {
				if (dataArray[i][1] != null)
					multiMap.put(dataArray[i][1], dataArray[i][0]);
			}

		}

		// retrieve and display values

		// get all the set of keys
		Set<String> keys = multiMap.keySet();

		for (String key : keys) {
			if (multiMap.keys().count(key) > 1) {

				multiMap.remove(key, null);

			}
			System.out.print("node = " + key);
			System.out.println("\tnighbor = " + multiMap.get(key));
		}

	}

	// map the neighbors to the node.

	// this method load the data in array
	static void loadData(String[][] dataArray, String fileName) {
		String line = null;
		int i = 0;

		try {
			FileReader fileReader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(fileReader);

			while ((line = bufferedReader.readLine()) != null) {

				String[] nodeNames = line.split(" ");

				if (nodeNames.length > 1) {
					dataArray[i][0] = nodeNames[0];
					dataArray[i][1] = nodeNames[1];
				} else
					dataArray[i][0] = nodeNames[0];
				i++;
			}
			bufferedReader.close();
		} catch (FileNotFoundException ex) {
			System.out.println("Unable to open file '" + fileName + "'");
		} catch (IOException ex) {
			System.out.println("Error reading file '" + fileName + "'");

		}
	}

	static int numberofline(String name) throws IOException {

		LineNumberReader lineNum = new LineNumberReader(new FileReader(
				new File(name)));
		lineNum.skip(Long.MAX_VALUE);
		// System.out.println(1 + lnr.getLineNumber());
		lineNum.close();

		return 1 + lineNum.getLineNumber();

	}

}
