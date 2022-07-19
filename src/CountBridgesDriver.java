
import util.DisjointSet;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CountBridgesDriver {

    static DisjointSet<String> set;

    public static boolean possibleIslandTraversal(String[] names, HashMap<String, List<String>> bridges, String src, String dst) {
        String parent1 = set.find(src);
        String parent2 = set.find(dst);
        if(parent1.equals(parent2)) {
            if(!bridges.containsKey(src))
                bridges.put(src, new LinkedList<>());
            bridges.get(src).add(dst);
        }
        return bridges.containsKey(src);
    }

    public static void main(String[] args) {
        String[] names = { "island1", "island2", "island3", "island4", "island5", "island6" };
        HashMap<String, List<String>> bridges = new HashMap<>();
        set = new DisjointSet<>(names);
        set.union(names[0], names[2]);
        set.union(names[3], names[2]);
        System.out.println(possibleIslandTraversal(names, bridges, names[0], names[3])); // expect true
        System.out.println(possibleIslandTraversal(names, bridges, names[5], names[4])); // expect false
        System.out.println(bridges); // expect island1: island4 -> null
    }
}
