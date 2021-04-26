# Railroad Java Program

This is a java program that calculates routes and distances for a railroad system represented by a directed weighted graph. 
This program works for graphs of 5 vertices, however that can be altered by changing N or V. The program parses through inputs
and creates three representations: a weighted adjacency matrix, an unweighted adjacency matrix, and an adjacency list. The letters
A, B, C, D, E are mapped to numbers using a hash table. For the first five outputs, the program calculates distances of specific 
routes. The weighted adjacency matrix representation is used for this and it is a relatively simple algorithm. For outputs 6 and 7
(number of trips), the unweighted matrix is used because distances are not required. This algorithm utilizes the fact that the matrix
raised to a power results in the number of paths from a source vertex to a destination vertex. Outputs 8 and 9 (length of shortest route)
are calculated using a topological sort algorithm. Unfortunately the algorithm doesn't work when the source and destination are the same,
so for output 9 (B to B) I broke it down into two sub-problems: calculate the shortest path from B to connecting vertices (A and E in this case),
and then from A to B and E to B, and the minimum from those distances is the answer. In addition to the program, there is a unit test in the test
folder as required in the instructions.

To run the program, the repository can be cloned, or simply the Railroad.java file can be downloaded. Open a terminal or command line and navigate
to the folder where the file is located. Enter the following input into the command line: java Railroad.java AB5 BC4 CD8 DC8 DE6 AD5 CE2 EB3 AE7.
The outputs should be 9, 5, 13, 22, NO SUCH ROUTE, 2, 3, 9, 9. Note that a java development kit must be installed to run the file (I used OpenJDK 11).


