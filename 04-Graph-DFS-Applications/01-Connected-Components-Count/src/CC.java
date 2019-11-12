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
     * 标记是否被访问过的数组
     */
    private boolean[] visited;
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
        visited = new boolean[G.V()];
        //遍历所有没有被访问过的节点，避免连通分量大于1出现有节点没有被遍历到的情况
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                dfs(v);
                count++;
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
        //递归遍历所有与节点 v 相连但是没有被遍历过的节点
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                dfs(w);
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

    public static void main(String[] args) {
        Graph g = new Graph("03-Graph-DFS/05-Graph-DFS-Improvement/g.txt");
        CC cc = new CC(g);
        System.out.println(cc.count());
    }

}
