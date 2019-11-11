import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Create with Graph-Algorithms
 * User: XFour
 * Date: 2019/11/10
 * Time: 15:18
 * Description: 邻接矩阵
 */
public class AdjMatrix {

    //顶点个数
    private int V;
    //边数
    private int E;
    //邻接矩阵
    private int[][] adj;

    /**
     * 构造函数，传入文件名称
     * @param fileName 文件名称
     */
    public AdjMatrix(String fileName) {
        File file = new File(fileName);
        try {
            Scanner scanner = new Scanner(file);
            V = scanner.nextInt();
            if (V < 0) {
                throw new IllegalArgumentException("V must be non-negative");
            }
            adj = new int[V][V];
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
                if (adj[a][b] == 1) {
                    throw new IllegalArgumentException("Parallel Edges is Detected");
                }
                adj[a][b] = 1;
                adj[b][a] = 1;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 验证顶点的合法性
     * @param v 顶点
     */
    private void validateVertex(int v) {
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
    public ArrayList<Integer> adj(int v) {
        validateVertex(v);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            if (adj[V][i] == 1) {
                res.add(i);
            }
        }
        return res;
    }

    /**
     * 获取顶点 v 的度
     * @param v 顶点 v
     * @return 顶点 v 的度
     */
    public int degree(int v) {
        return adj(v).size();
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
        return adj[v][w] == 1;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("V = %d, E = %d\n", V, E));
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                sb.append(String.format("%d ", adj[i][j]));
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        AdjMatrix adjMatrix = new AdjMatrix("02-Graph-Basics/03-Adjacency-Matrix/g.txt");
        System.out.println(adjMatrix);
    }

}
