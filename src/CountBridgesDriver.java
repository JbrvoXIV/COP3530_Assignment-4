
import util.DisjointSet;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class CountBridgesDriver {

    public static boolean possibleIslandTraversal(String[] names, HashMap<String, List<String>> bridges, String src, String dst) {
        DisjointSet<String> ds = new DisjointSet<>(names);
        for(String from: bridges.keySet()) {
            for(String to: bridges.get(from))
                ds.union(from, to);
        }
        return ds.find(src).equals(ds.find(dst));
    }

    public static void main(String[] args) {
        String[]names = new String[]{ "Alex", "Jacob", "Marc", "Bob" };
        HashMap<String, List<String>> bridges = new HashMap<String, List<String>>();

        bridges.put(names[0], new ArrayList<String>());
        bridges.get(names[0]).add(names[1]); // bridge 1
        bridges.put(names[2], new ArrayList<String>());
        bridges.get(names[2]).add(names[3]);//bridge 2

        System.out.println(possibleIslandTraversal(names, bridges, names[0], names[1])); // Jacob <-> Alex (true)
        System.out.println(possibleIslandTraversal(names, bridges, names[1], names[0]));  // Jacob <-> Alex (true)
        System.out.println(possibleIslandTraversal(names, bridges, names[2], names[3])); // Marc <-> Bob (true)
        System.out.println(possibleIslandTraversal(names, bridges, names[2], names[1])); // Marc <-> Bob (false)

        System.out.println(possibleIslandTraversal(names, bridges, names[0], names[3])); // Jacob <-> Alex (false)
        bridges.get(names[0]).add(names[2]);// bridge 3
        System.out.println(possibleIslandTraversal(names, bridges, names[0], names[3])); // Jacob <-> Alex <-> Marc <-> Bob (true)
    }
}
