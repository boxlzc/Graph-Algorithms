import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create with Graph-Algorithms
 * User: XFour
 * Date: 2019/11/12
 * Time: 14:50
 * Description:
 */
public class CC {

    /**
     * 图
     */
    private Graph G;
    /**
     * 标记是否被访问过以及连通分量的数组
     */
    private int[] visited;
    /**
     * 连通分量的值
     */
    private int count;

    /**
     * 构造函数
     * @param G 需要被遍历的图
     */
    public CC(Graph G) {
        this.G = G;
        //初始化标记数组，数组的大小为传入的图的顶点数
        visited = new int[G.V()];
        //将 visited 数组的所有值初始化为 -1
        Arrays.fill(visited, -1);
        //遍历所有没有被访问过的节点，避免连通分量大于1出现有节点没有被遍历到的情况
        for (int v = 0; v < G.V(); v++) {
            if (visited[v] == -1) {
                dfs(v, count);
                count++;
            }
        }
    }

    /**
     * 深度优先遍历
     * @param v 顶点 v
     * @param ccid 该次遍历的连通分量 id
     */
    private void dfs(int v, int ccid) {
        //标记节点 v 为已遍历的状态
        visited[v] = ccid;
        //递归遍历所有与节点 v 相连但是没有被遍历过的节点
        for (int w : G.adj(v)) {
            if (visited[w] == -1) {
                dfs(w, ccid);
            }
        }
    }

    /**
     * 获取连通分量
     * @return 连通分量
     */
    public int count() {
        return count;
    }

    /**
     * 判断给定的两个顶点是否在同一个连通分量中
     * @param v 顶点 v
     * @param w 顶点 w
     * @return 判断结果
     */
    public boolean isConnected(int v, int w) {
        //判断顶点 v 以及顶点 w 是否合法
        G.validateVertex(v);
        G.validateVertex(w);
        //如果两个顶点在 visited 数组中的值相同，则证明在同一个连通分量中
        return visited[v] == visited[w];
    }

    /**
     * 返回所有的连通分量
     * @return 连通分量数组
     */
    public List<Integer>[] components() {
        List<Integer>[] res = new ArrayList[count];
        for (int i = 0; i < count; i++) {
            res[i] = new ArrayList<>();
        }
        for (int v = 0; v < G.V(); v++) {
            res[visited[v]].add(v);
        }
        return res;
    }

    public static void main(String[] args) {
        Graph g = new Graph("03-Graph-DFS/05-Graph-DFS-Improvement/g.txt");
        CC cc = new CC(g);
        for (int i = 0; i < cc.visited.length; i++) {
            System.out.print(cc.visited[i] + " ");
        }
        System.out.println();
        System.out.println(cc.count());
    }

}
