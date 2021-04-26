import java.util.*;
import java.util.HashMap;

public class Railroad {
    static int N = 5;
    static final int INF=Integer.MAX_VALUE;

    //Adjacency list representation of directed weighted graph
    class AdjListNode
    {
        private int v;
        private int weight;
        AdjListNode(int _v, int _w) { v = _v;  weight = _w; }
        int getV() { return v; }
        int getWeight()  { return weight; }
    }

    //Graph methods for the adjacency list representation
    class Graph
    {
        private int V;
        private LinkedList<AdjListNode>adj[];
        Graph(int v)
        {
            V=v;
            adj = new LinkedList[V];
            for (int i=0; i<v; ++i)
                adj[i] = new LinkedList<AdjListNode>();
        }
        void addEdge(int u, int v, int weight)
        {
            AdjListNode node = new AdjListNode(v,weight);
            adj[u].add(node);
        }

        //Utility function for the shortest path method
        void topologicalSortUtil(int v, Boolean visited[], Stack stack)
        {

            visited[v] = true;
            Integer i;

            Iterator<AdjListNode> it = adj[v].iterator();
            while (it.hasNext())
            {
                AdjListNode node =it.next();
                if (!visited[node.getV()])
                    topologicalSortUtil(node.getV(), visited, stack);
            }

            stack.push(new Integer(v));
        }

        //Method to calculate the shortest path between source vertex and destination vertex
        int shortestPath(int s, int d)
        {
            Stack stack = new Stack();
            int dist[] = new int[V];


            Boolean visited[] = new Boolean[V];
            for (int i = 0; i < V; i++)
                visited[i] = false;


            for (int i = 0; i < V; i++)
                if (visited[i] == false)
                    topologicalSortUtil(i, visited, stack);


            for (int i = 0; i < V; i++)
                dist[i] = INF;
            dist[s] = 0;


            while (stack.empty() == false)
            {

                int u = (int)stack.pop();


                Iterator<AdjListNode> it;
                if (dist[u] != INF)
                {
                    it = adj[u].iterator();
                    while (it.hasNext())
                    {
                        AdjListNode i= it.next();
                        if (dist[i.getV()] > dist[u] + i.getWeight())
                            dist[i.getV()] = dist[u] + i.getWeight();
                    }
                }
            }

            return dist[d];

        }
    }

    //Create a new graph with adjacency list representation
    Graph newGraph(int number)
    {
        return new Graph(number);
    }

    //Hash table that allows us to parse the input string (ex.AB5) and convert it to integers
    public static Map<Character, Integer> letters;
    static {
        letters = new HashMap<>();
        letters.put('A', 0);
        letters.put('B', 1);
        letters.put('C', 2);
        letters.put('D', 3);
        letters.put('E', 4);
    }

    //Calculates the distance for a certain route
    static int distanceRoute(int[][] graph, String route) {
        int distance = 0;
        int[] a = new int[route.length()];

        for (int i = 0; i < route.length(); i++) {
            a[i] = letters.get(route.charAt(i));
        }

        for (int i = 0; i < a.length - 1; i++) {
            if (graph[a[i]][a[i+1]] == 0) {
                distance = 0;
                break;
            }
            distance += graph[a[i]][a[i+1]];
        }

        return distance;
    }

    //Utility function for calculating exponent of a matrix
    static void multiply(int a[][], int b[][])
    {
        int mul[][] = new int[N][N];
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                mul[i][j] = 0;
                for (int k = 0; k < N; k++)
                    mul[i][j] += a[i][k]
                            * b[k][j];
            }
        }

        for (int i=0; i<N; i++)
            for (int j=0; j<N; j++)
                a[i][j] = mul[i][j];
    }

    //Calculating a matrix raised to a power n
    static void power(int F[][], int n)
    {
        int M[][] = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j<N ; j++) {
                M[i][j] = F[i][j];
            }
        }

        if (n == 1)
            return;

        power(F, n / 2);

        multiply(F, F);

        if (n%2 != 0)
            multiply(F, M);

        return;
    }

    //Method that reverts a matrix back to its original form after altering
    static void revert(int one[][], int two[][]) {
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
                if (one[i][j] != 0) {
                    two[i][j] = 1;
                }
                else {
                    two[i][j] = 0;
                }
        }
    }

    public static void main(String[] args) throws Exception {
        Railroad t = new Railroad();
        Graph g = t.newGraph(N);
        int[][] graph1 = new int[N][N];
        int[][] graph2 = new int[N][N];
        int x = 0;
        int y = 0;
        int z = 0;

        //Parse the inputs and convert to two adjacency matrices and one adjacency list
        for(int i=0; i < args.length; i++) {
            x = letters.get(args[i].charAt(0));
            y = letters.get(args[i].charAt(1));
            z = args[i].charAt(2) - '0';
            graph1[x][y] = z;
            graph2[x][y] = 1;
            g.addEdge(x, y, z);
        }

        //Calculate the required distances for outputs 1-5
        int[] distances = {distanceRoute(graph1, "ABC"), distanceRoute(graph1, "AD"), distanceRoute(graph1, "ADC"), distanceRoute(graph1, "AEBCD"), distanceRoute(graph1, "AED") };
        for (int i = 0; i < distances.length; i++) {
            if (distances[i] == 0) {
                System.out.println("NO SUCH ROUTE");
            }
            else {
                System.out.println(distances[i]);
            }
        }

        int tripsCC = 0;
        int tripsAC = 0;

        //Calculating the number of trips for output 6
        tripsCC += graph2[2][2];
        power(graph2, 2);
        tripsCC += graph2[2][2];
        revert(graph1, graph2);
        power(graph2, 3);
        tripsCC += graph2[2][2];
        System.out.println(tripsCC);

        //Calculating the number of trips for output 7
        revert(graph1, graph2);
        power(graph2, 4);
        tripsAC = graph2[0][2];
        revert(graph1, graph2);
        System.out.println(tripsAC);

        //Calculating the shortest route for output 8
        System.out.println(g.shortestPath(0, 2));

        //Calculating the shortest route for output 9
        int[] helper = new int[N];
        int[] sums = new int[N];
        for (int i = 0; i < helper.length; i++) {
            if (graph1[i][1] != 0) {
                helper[i] = graph1[i][1];
            }
        }
        int min = Integer.MAX_VALUE;
        int tempsum = 0;
        for (int i = 0; i < sums.length; i++) {
            tempsum = g.shortestPath(1, i) + helper[i];
            if (helper[i] != 0 && g.shortestPath(1, i) != INF && tempsum < min ) {
                min = tempsum;
            }
        }
        System.out.println(min);
    }
}
//test input: "java Railroad.java AB5 BC4 CD8 DC8 DE6 AD5 CE2 EB3 AE7"