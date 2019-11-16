import java.util.ArrayList;
import java.util.List;

/**
 * Create with Graph-Algorithms
 * User: XFour
 * Date: 2019/11/11
 * Time: 23:38
 * Description:
 */
public class GraphDFS {

    /**
     * 图
     */
    private Graph G;
    /**
     * 标记是否被访问过的数组
     */
    private boolean[] visited;
    /**
     * 记录遍历结果的集合
     */
    private List<Integer> pre = new ArrayList<>();
    private List<Integer> post = new ArrayList<>();

    /**
     * 构造函数
     * @param G 需要被遍历的图
     */
    public GraphDFS(Graph G) {
        this.G = G;
        //初始化标记数组，数组的大小为传入的图的顶点数
        visited = new boolean[G.V()];
        //遍历所有没有被访问过的节点，避免连通分量大于1出现有节点没有被遍历到的情况
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                dfs(v);
            }
        }
    }

    /**
     * 深度优先遍历
     * @param v 节点 v
     */
    private void dfs(int v) {
        //标记节点 v 为已遍历的状态
        visited[v] = true;
        //将节点 v 的值添加进结果集合
        pre.add(v);
        //递归遍历所有与节点 v 相连但是没有被遍历过的节点
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w);
            }
        }
        post.add(v);
    }

    /**
     * 获得先序遍历结果
     * @return 遍历结果
     */
    public Iterable<Integer> pre() {
        return pre;
    }

    /**
     * 获得后序遍历结果
     * @return 遍历结果
     */
    public Iterable<Integer> post() {
        return post;
    }

    public static void main(String[] args) {
        Graph g = new Graph("03-Graph-DFS/05-Graph-DFS-Improvement/g.txt");
        GraphDFS graphDFS = new GraphDFS(g);
        System.out.println(graphDFS.pre());
        System.out.println(graphDFS.post());
    }

}
