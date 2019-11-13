import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * Create with Graph-Algorithms
 * User: XFour
 * Date: 2019/11/11
 * Time: 23:57
 * Description:
 */
public class Graph {

    //顶点个数
    private int V;
    //边数
    private int E;
    //邻接点集合
    private TreeSet<Integer>[] adj;

    /**
     * 构造函数，传入文件名称
     * @param fileName 文件名称
     */
    public Graph(String fileName) {
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }
            adj = new TreeSet[V];
            for (int i = 0; i < V; i++) {
                adj[i] = new TreeSet<Integer>();
            }
            E = scanner.nextInt();
            if (E < 0) {
                throw new IllegalArgumentException("E must be non-negative");
            }
            for (int i = 0; i < E; i++) {
                int a = scanner.nextInt();
                validateVertex(a);
                int b = scanner.nextInt();
                validateVertex(b);
                if (a == b) {
                    throw new IllegalArgumentException("Self loop is Detected");
                }
                if (adj[a].contains(b)) {
                    throw new IllegalArgumentException("Parallel Edges is Detected");
                }
                adj[a].add(b);
                adj[b].add(a);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证顶点的合法性
     * @param v 顶点
     */
    public void validateVertex(int v) {
        if (v < 0 || v >= V) {
            throw new IllegalArgumentException("vertex" + v + "is invalid");
        }
    }

    /**
     * 获取顶点个数
     * @return 顶点个数
     */
    public int V() {
        return V;
    }

    /**
     * 获取边的数量
     * @return 边的数量
     */
    public int E() {
        return E;
    }

    /**
     * 获取与顶点 v 邻接的所有顶点
     * @param v 顶点 v
     * @return 与顶点 v 邻接的所有顶点
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * 获取顶点 v 的度
     * @param v 顶点 v
     * @return 顶点 v 的度
     */
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * 判断顶点 v 以及 w 之间是否存在一条边
     * @param v 顶点 v
     * @param w 顶点 w
     * @return true 表示存在边，false 表示不存在
     */
    public boolean hasEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return adj[v].contains(w);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int v = 0; v < V; v++) {
            sb.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                sb.append(String.format("%d ", w));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
