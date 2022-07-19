import util.Vertex;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.*;

public class ClassTopologicalSortDriver {

    static String out = System.getProperty("user.dir");

    private static void addEdge(Vertex from, Vertex to, HashMap<Vertex, Set<Vertex>> graph) {
        if (!graph.containsKey(from))
            graph.put(from, new HashSet<Vertex>());
        graph.get(from).add(to);
        to.indegree++;
    }

    public static void topSortUsingDFS(HashMap<Vertex, Set<Vertex>> graph, Vertex[] vertices)
    {
        LinkedList<Vertex> starters = new LinkedList<Vertex>();
        for (Vertex v : vertices)
            if (v.indegree == 0)
                starters.add(v);
        Stack<Vertex> stack = new Stack<Vertex>();
        int time = 0;
        int topNum = vertices.length;
        for (Vertex startVertex : starters) {
            stack.push(startVertex);
            while (!stack.empty()) {
                Vertex current = stack.pop();
                if (current.finished > 0)
                    continue;
                if (current.processed) {
                    current.finished = ++time;
                    current.topNum = topNum--;
                    continue;
                }
                current.discovered = ++time;
                if (!graph.containsKey(current)) {// out-degree = 0
                    current.finished = ++time;
                    current.topNum = topNum--;
                    continue;
                }
                current.processed = true;
                stack.push(current);
                for (Vertex neighbor : graph.get(current))
                    if (neighbor.discovered == 0) {
                        neighbor.pred = current;
                        stack.push(neighbor);
                    }
            }
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        HashMap<Vertex, Set<Vertex>> graph = new HashMap<Vertex, Set<Vertex>>();
        ArrayList<Vertex> verticesList = new ArrayList<>();
        Scanner scr = new Scanner(new File(out + "/prerequisites.txt"));
        String[] splitLine;

        scr.nextLine(); // skip first line
        while(scr.hasNextLine()) {
            splitLine = scr.nextLine().split("[\t,\"]"); // split by indicators
            Vertex from = new Vertex(splitLine[0]); // first index is guaranteed to not be in list
            Vertex to;
            verticesList.add(from); // add to list of discovered vertices
            for(int i = 1; i < splitLine.length; i++) {
                if(splitLine[i].equals("")) // skip unneeded empty string from split
                    continue;
                to = new Vertex(splitLine[i]); // create new vertex from pre req to compare and find in list
                if(!verticesList.contains(to)) // if not in list, add
                    verticesList.add(to);
                addEdge(verticesList.get(verticesList.indexOf(from)), verticesList.get(verticesList.indexOf(to)), graph);
                // add edge from 'from' vertex to 'to' vertex
            }
        }

        Vertex[] verticesArr = verticesList.toArray(new Vertex[verticesList.size()]); // convert list to arr to sort

        topSortUsingDFS(graph, verticesArr);
        Arrays.sort(verticesArr);
        for (Vertex v : verticesArr) // prints in reverse order
            System.out.println(v);
    }
}
