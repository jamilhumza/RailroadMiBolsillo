import java.util.*;
import java.util.HashMap;

public class Railroad {
    static int N = 5;
    static final int INF=Integer.MAX_VALUE;
    class AdjListNode
    {
        private int v;
        private int weight;
        AdjListNode(int _v, int _w) { v = _v;  weight = _w; }
        int getV() { return v; }
        int getWeight()  { return weight; }
    }

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
            adj[u].add(node);// Add v to u's list
        }

        // A recursive function used by shortestPath.
        // See below link for details
        void topologicalSortUtil(int v, Boolean visited[], Stack stack)
        {
            // Mark the current node as visited.
            visited[v] = true;
            Integer i;

            // Recur for all the vertices adjacent to this vertex
            Iterator<AdjListNode> it = adj[v].iterator();
            while (it.hasNext())
            {
                AdjListNode node =it.next();
                if (!visited[node.getV()])
                    topologicalSortUtil(node.getV(), visited, stack);
            }
            // Push current vertex to stack which stores result
            stack.push(new Integer(v));
        }

        // The function to find shortest paths from given vertex. It
        // uses recursive topologicalSortUtil() to get topological
        // sorting of given graph.
        int shortestPath(int s, int d)
        {
            Stack stack = new Stack();
            int dist[] = new int[V];

            // Mark all the vertices as not visited
            Boolean visited[] = new Boolean[V];
            for (int i = 0; i < V; i++)
                visited[i] = false;

            // Call the recursive helper function to store Topological
            // Sort starting from all vertices one by one
            for (int i = 0; i < V; i++)
                if (visited[i] == false)
                    topologicalSortUtil(i, visited, stack);

            // Initialize distances to all vertices as infinite and
            // distance to source as 0
            for (int i = 0; i < V; i++)
                dist[i] = INF;
            dist[s] = 0;

            // Process vertices in topological order
            while (stack.empty() == false)
            {
                // Get the next vertex from topological order
                int u = (int)stack.pop();

                // Update distances of all adjacent vertices
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
            // Print the calculated shortest distances
            /*for (int i = 0; i < V; i++)
            {
                if (dist[i] == INF)
                    System.out.print( "INF ");
                else
                    System.out.print( dist[i] + " ");
            }*/
        }
    }

    // Method to create a new graph instance through an object
    // of ShortestPath class.
    Graph newGraph(int number)
    {
        return new Graph(number);
    }






    public static Map<Character, Integer> letters;
    static {
        letters = new HashMap<>();
        letters.put('A', 0);
        letters.put('B', 1);
        letters.put('C', 2);
        letters.put('D', 3);
        letters.put('E', 4);
    }

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

    static void multiply(int a[][], int b[][])
    {
        // Creating an auxiliary matrix to
        // store elements of the
        // multiplication matrix
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

        // storing the multiplication
        // result in a[][]
        for (int i=0; i<N; i++)
            for (int j=0; j<N; j++)

                // Updating our matrix
                a[i][j] = mul[i][j];
    }

    static void power(int F[][], int n)
    {
        int M[][] = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j<N ; j++) {
                M[i][j] = F[i][j];
            }
        }

        // Multiply it with initial values
        // i.e with F(0) = 0, F(1) = 1,
        // F(2) = 1
        if (n == 1)
            return;

        power(F, n / 2);

        multiply(F, F);

        if (n%2 != 0)
            multiply(F, M);

        // Multiply it with initial values
        // i.e with F(0) = 0, F(1) = 1,
        // F(2) = 1
        return;
    }

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

        for(int i=0; i < args.length; i++) {
            x = letters.get(args[i].charAt(0));
            y = letters.get(args[i].charAt(1));
            z = args[i].charAt(2) - '0';
            graph1[x][y] = z;
            graph2[x][y] = 1;
            g.addEdge(x, y, z);
        }
        //{ {0, 5, 0, 5, 7},
        //{0, 0, 4, 0, 0},
        //{0, 0, 0, 8, 2},
        //{0, 0, 8, 0, 6},
        //{0, 3, 0, 0, 0}
        //};
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

        tripsCC += graph2[2][2];
        power(graph2, 2);
        tripsCC += graph2[2][2];

        revert(graph1, graph2);

        power(graph2, 3);
        tripsCC += graph2[2][2];


        revert(graph1, graph2);
        power(graph2, 4);
        tripsAC = graph2[0][2];
        revert(graph1, graph2);
        System.out.println(tripsCC);
        System.out.println(tripsAC);

        System.out.println(g.shortestPath(0, 2));

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

        power(graph2, 6);
        int tripsCC2 = 0;

        tripsCC2 += graph2[2][2];
        System.out.println(tripsCC2);
    }
}
//test input: "java Prog.java AB5 BC4 CD8 DC8 DE6 AD5 CE2 EB3 AE7"