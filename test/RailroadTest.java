import org.junit.Test;

import static org.junit.Assert.*;

public class RailroadTest {
    static int[][] graph = {{0, 5, 0, 5, 7},
                            {0, 0, 4, 0, 0},
                            {0, 0, 0, 8, 2},
                            {0, 0, 8, 0, 6},
                            {0, 3, 0, 0, 0}};
    @Test
    public void testRoute() {
        Railroad tester = new Railroad();
        assertEquals("Route ABC must be 9", 9, tester.distanceRoute(graph, "ABC"));
    }


}