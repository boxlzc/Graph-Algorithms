import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Create with Graph-Algorithms
 * User: XFour
 * Date: 2019/11/19
 * Time: 11:16
 * Description:
 */
public class GraphBFS {

    /**
     * 图 G
     */
    private Graph G;

    /**
     * 标记访问的数组
     */
    private boolean[] visited;

    /**
     * BFS 遍历结果集合
     */
    private List<Integer> order = new ArrayList<>();

    /**
     * 构造函数，传入一个图
     * @param g
     */
    public GraphBFS(Graph g) {
        this.G = g;
        this.visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                bfs(v);
            }
        }
    }

    /**
     * 广度优先遍历
     * @param s 顶点 s
     */
    public void bfs(int s) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(s);
        visited[s] = true;
        while (queue.size() != 0) {
            Integer v = queue.poll();
            order.add(v);
            for (Integer w : G.adj(v)) {
                if (!visited[w]) {
                    queue.add(w);
                    visited[w] = true;
                }
            }
        }
    }

    /**
     * 返回遍历结果
     * @return 遍历结果
     */
    public Iterable<Integer> order() {
        return order;
    }

    public static void main(String[] args){

        Graph g = new Graph("05-Graph-BFS/02-BFS/g.txt");
        GraphBFS graphBFS = new GraphBFS(g);
        System.out.println("BFS Order : " + graphBFS.order());
    }



}
